package dika.helper.android.api.extension

import androidx.lifecycle.ViewModel
import dika.helper.android.Result
import dika.helper.android.api.ApiExecutorHelper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver

fun <T: Any> ViewModel.singleExecute(single: Single<T>,
                                     handler: (result: Result<T>) -> Unit): DisposableSingleObserver<T> {
    return ApiExecutorHelper.singleExecute(
        single = single,
        handler = handler
    )
}