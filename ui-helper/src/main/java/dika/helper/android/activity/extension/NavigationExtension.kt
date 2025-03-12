@file:Suppress("unused")

package dika.helper.android.activity.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
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