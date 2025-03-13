@file:Suppress("unused")

package dika.helper.android.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass


fun Activity.startOtherActivityForResult(kClass: KClass<*>, requestCode: Int, block: Intent.() -> Unit = {}) {
    val intent = Intent(this, kClass.java)
    block(intent)
    startActivityForResult(intent, requestCode)
}

fun Activity.setActivityResult(resultCode: Int, block: Intent.() -> Unit = {}) {
    val intent = Intent()
    block(intent)
    setResult(resultCode, intent)
    finish()
}

fun Activity.setActivityResult(resultCode: Int, kClass: KClass<*>, block: Intent.() -> Unit = {}) {
    val intent = Intent(this, kClass.java)
    block(intent)
    setResult(resultCode, intent)
    finish()
}

fun Context.startActivity(kClass: KClass<*>, block: Intent.() -> Unit = {}) {
    val intent = Intent(this, kClass.java)
    block(intent)
    startActivity(intent)
}

fun Fragment.startOtherActivityForResult(kClass: KClass<*>, requestCode: Int, block: Intent.() -> Unit = {}) {
    requireActivity().startOtherActivityForResult(kClass, requestCode, block)
}

fun Fragment.startOtherActivity(kClass: KClass<*>, block: Intent.() -> Unit = {}) {
    requireActivity().startActivity(kClass, block)
}