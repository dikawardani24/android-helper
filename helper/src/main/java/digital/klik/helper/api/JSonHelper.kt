package digital.klik.helper.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import digital.klik.helper.common.constant.Pattern

@Suppress("unused")
object JSonHelper {
    val gson: Gson = GsonBuilder()
        .setDateFormat(Pattern.DATE_FULL.value)
        .disableHtmlEscaping()
        .create()

    fun <T> fromJson(kClass: Class<T>, strJson: String): T {
        return gson.fromJson(strJson, kClass)
    }

    fun <T> fromJson(type: java.lang.reflect.Type, strJson: String): T {
        return gson.fromJson(strJson, type)
    }

    fun <T> toJson(kClass: Class<T>, obj: T): String {
        return gson.toJson(obj, kClass)
    }

    fun <T> toJson(obj: T): String {
        return gson.toJson(obj)
    }
}