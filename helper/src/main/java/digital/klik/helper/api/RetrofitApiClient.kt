package digital.klik.helper.api

import digital.klik.helper.api.model.TimeOut
import okhttp3.Cache
import okhttp3.CookieJar

class RetrofitApiClient {
    private var readTimeOut: TimeOut? = null
    private var connectTimeOut: TimeOut? = null
    private var writeTimeOut: TimeOut? = null
    private var cache: Cache? = null
    private var cookieJar: CookieJar = CookieJar.NO_COOKIES

    fun setTimeOut(timeOutInSeconds: Long) {
        val timeOut = TimeOut(timeOutInSeconds)
        readTimeOut = timeOut
        connectTimeOut = timeOut
        writeTimeOut = timeOut
    }


}