package dika.helper.android.api

import dika.helper.core.Result
import dika.helper.android.api.constant.ErrorMessage
import dika.helper.android.api.constant.HttpStatus
import dika.helper.android.api.exception.ApiException
import dika.helper.android.api.exception.ConnectException
import dika.helper.core.exception.AppException
import dika.helper.core.exception.UnknownException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ApiExecutorHelper {

    private fun handleHttpError(throwable: Throwable): AppException {
        return when(throwable) {
            is HttpException -> {
                val response = throwable.response()
                val errorBody = response?.errorBody()?.string() ?: ""
                val httpStatus = HttpStatus.from(throwable.code())
                ApiException(
                    httpStatus,
                    errorBody
                )
            }

            is SocketTimeoutException,
            is IOException -> ConnectException(
                ErrorMessage.CONNECTION_ERROR.message,
                throwable
            )
            else -> UnknownException(throwable.message, throwable)
        }
    }


    fun <T : Any> singleExecute(single: Single<T>, handler: (result: Result<T>) -> Unit): DisposableSingleObserver<T> {
        return single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<T>() {
                override fun onSuccess(t: T) {
                    val result = Result.Success(t)
                    handler(result)
                }

                override fun onError(e: Throwable) {
                    val error = handleHttpError(e)
                    val result = Result.Failure<T>(error)
                    handler(result)
                }
            })
    }

    fun <T: Any> singleBlockingExecute(single: Single<T>): Result<T> {
        return try {
            val data = single.subscribeOn(Schedulers.io())
                .blockingGet()
            Result.Success(data)
        } catch (e: Exception) {
            val error = handleHttpError(e)
            Result.Failure(error)
        }
    }
}