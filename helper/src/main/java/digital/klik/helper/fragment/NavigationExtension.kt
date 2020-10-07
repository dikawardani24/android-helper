package digital.klik.helper.fragment

import android.content.Intent
import androidx.fragment.app.Fragment
import digital.klik.helper.activity.extension.startActivity
import digital.klik.helper.activity.extension.startOtherActivityForResult
import kotlin.reflect.KClass

fun Fragment.startOtherActivityForResult(kClass: KClass<*>, requestCode: Int, block: Intent.() -> Unit = {}) {
    requireActivity().startOtherActivityForResult(kClass, requestCode, block)
}

fun Fragment.startOtherActivity(kClass: KClass<*>, block: Intent.() -> Unit = {}) {
    requireActivity().startActivity(kClass, block)
}