package digital.klik.helper.api

import digital.klik.helper.Result
import digital.klik.helper.api.exception.ApiException
import digital.klik.helper.exception.AppException
import digital.klik.helper.api.exception.ConnectException
import digital.klik.helper.exception.UnknownException
import digital.klik.helper.api.constant.ErrorMessage
import digital.klik.helper.api.constant.HttpStatus
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class ApiExecutorHelper {

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


    fun <T> singleExecute(single: Single<T>, handler: (result: Result<T>) -> Unit): DisposableSingleObserver<T> {
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
}