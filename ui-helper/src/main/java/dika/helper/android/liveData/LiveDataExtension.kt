package dika.helper.android.liveData

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, block: (it: T) ->  Unit) {
    observe(owner, Observer {
        block(it)
    })
}

fun <T> AppCompatActivity.observe(liveData: LiveData<T>, block: (it: T) -> Unit) {
    liveData.observe(this, block)
}

fun <T> Fragment.observe(liveData: LiveData<T>, block: (it: T) -> Unit) {
    liveData.observe(viewLifecycleOwner, block)
}