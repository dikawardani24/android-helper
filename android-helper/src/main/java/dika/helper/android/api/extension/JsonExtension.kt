package dika.helper.android.api.extension

import dika.helper.android.api.JSonHelper

fun <T> String.fromJson(kClass: Class<T>): T {
    return JSonHelper.gson.fromJson(this, kClass)
}

fun <T> String.fromJson(type: java.lang.reflect.Type): T {
    return JSonHelper.gson.fromJson(this, type)
}

fun <T> T.toJson(kClass: Class<T>): String {
    return JSonHelper.gson.toJson(this, kClass)
}

fun <T> T.toJson(): String {
    return JSonHelper.gson.toJson(this)
}