package dika.helper.api.extension

import androidx.lifecycle.ViewModel
import dika.helper.api.ApiExecutorHelper
import dika.helper.core.Result
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver

fun <T: Any> ViewModel.singleExecute(single: Single<T>,
                                     handler: (result: Result<T>) -> Unit): DisposableSingleObserver<T> {
    return ApiExecutorHelper.singleExecute(
        single = single,
        handler = handler
    )
}

fun <T: Any> Single<T>.execute(handler: (result: Result<T>) -> Unit): DisposableSingleObserver<T> {
    return ApiExecutorHelper.singleExecute(
        single = this,
        handler = handler
    )
}