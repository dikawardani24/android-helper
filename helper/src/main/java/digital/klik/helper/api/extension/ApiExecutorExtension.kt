package digital.klik.helper.api.extension

import androidx.lifecycle.ViewModel
import digital.klik.helper.Result
import digital.klik.helper.api.ApiExecutorHelper
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

fun <T: Any> ViewModel.singleExecute(single: Single<T>,
                                handler: (result: Result<T>) -> Unit): DisposableSingleObserver<T> {
    return ApiExecutorHelper.singleExecute(
        single = single,
        handler = handler
    )
}