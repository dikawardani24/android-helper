package digital.klik.helper.api

import android.content.Context
import digital.klik.helper.api.exception.TimeoutException
import digital.klik.helper.api.extension.installProviderIfNeeded
import digital.klik.helper.config.EnvironmentConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

@Suppress("MemberVisibilityCanBePrivate", "unused")
class ApiClient(private val baseUrl: String) {
    private val okHttpClientBuilder = OkHttpClient.Builder()
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(baseUrl)

    private fun validateTimeout(timeout: Long) {
        if (timeout <= 0) {
            throw TimeoutException("Timeout must be greater than 0")
        }
    }

    fun setAllTimeOutInSecond(timeoutInSeconds: Long): ApiClient {
        validateTimeout(timeoutInSeconds)

        val timeUnit = TimeUnit.SECONDS
        okHttpClientBuilder.readTimeout(timeoutInSeconds, timeUnit)
            .writeTimeout(timeoutInSeconds, timeUnit)
            .connectTimeout(timeoutInSeconds, timeUnit)

        return this
    }

    fun setConnectTimeout(timeout: Long, timeUnit: TimeUnit): ApiClient {
        validateTimeout(timeout)
        okHttpClientBuilder.connectTimeout(timeout, timeUnit)
        return this
    }

    fun setConnectTimeout(timeoutInSeconds: Long): ApiClient {
        return setConnectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
    }

    fun setReadTimeout(timeout: Long, timeUnit: TimeUnit): ApiClient {
        okHttpClientBuilder.readTimeout(timeout, timeUnit)
        return this
    }

    fun setReadTimeout(timeoutInSeconds: Long): ApiClient {
        return setReadTimeout(timeoutInSeconds, TimeUnit.SECONDS)
    }

    fun setWriteTimeout(timeout: Long, timeUnit: TimeUnit): ApiClient {
        okHttpClientBuilder.writeTimeout(timeout, timeUnit)
        return this
    }

    fun setWriteTimeout(timeoutInSeconds: Long): ApiClient {
        return setWriteTimeout(timeoutInSeconds, TimeUnit.SECONDS)
    }

    fun addInterceptor(block: (chain: Interceptor.Chain) -> Response): ApiClient {
        okHttpClientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                return block(chain)
            }
        })

        return this
    }

    fun setSslSocketFactory(socketFactory: SSLSocketFactory, trustManager: X509TrustManager): ApiClient {
        okHttpClientBuilder.sslSocketFactory(socketFactory, trustManager)
        return this
    }

    fun<T> createEndPoint(context: Context, apiInterfaceClass: Class<T>): T {
        if (EnvironmentConfig.isDebuggingMode()) {
            val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val interceptorAlreadyExist = okHttpClientBuilder.interceptors().contains(interceptor)
            if (!interceptorAlreadyExist) {
                okHttpClientBuilder.addInterceptor {
                    interceptor.intercept(it)
                }
            }
        }

        context.installProviderIfNeeded()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(apiInterfaceClass)
    }
}