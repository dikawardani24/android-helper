@file:Suppress("unused")

package digital.klik.helper.activity

import android.app.Activity
import android.content.Intent
import digital.klik.helper.KlikDigitalApplication
import digital.klik.helper.exception.AppException
import kotlin.reflect.KClass

fun Activity.getApplicationContainer(): KlikDigitalApplication {
    val app = application
    if (app is KlikDigitalApplication) {
        return app
    } else {
        throw AppException("The application container of ${app::class.simpleName} is not type of ${KlikDigitalApplication::class.simpleName}")
    }
}

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

fun Activity.startActivity(kClass: KClass<*>, block: Intent.() -> Unit = {}) {
    val intent = Intent(this, kClass.java)
    block(intent)
    startActivity(intent)
}