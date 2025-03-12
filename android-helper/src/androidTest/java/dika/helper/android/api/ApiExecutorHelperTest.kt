package dika.helper.android.api

import androidx.test.platform.app.InstrumentationRegistry
import dika.helper.android.common.extension.logDebug
import dika.helper.android.common.extension.logError
import dika.helper.android.exception.AppException
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Test
import retrofit2.http.GET
import retrofit2.http.Query

class ApiExecutorHelperTest {
    interface MovieDbApi {
        @GET("genre/movie/list")
        fun getGenres(@Query("api_key") apiKey: String,
                      @Query("language") language: String): Single<String>
    }

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val apiClient = ApiClient("https://api.themoviedb.org/3/")
    private val movieDbApi = apiClient.createEndPoint(appContext.applicationContext, MovieDbApi::class.java)

    @Test
    fun singleExecute() {
        val api = movieDbApi.getGenres(
            apiKey = "80d88767a1f71fd675666cfa9bd065bc",
            language = "en-US"
        )

        try {
            val data = api.blockingGet()
            logDebug("Data : $data")
            assertTrue(data.isNotEmpty())
        } catch (e: Exception) {
            logError("${e.message}", e)
            assertTrue(e is AppException)
        }
    }

    @Test
    fun singleBlockingExecute() {
        val api = movieDbApi.getGenres(
            apiKey = "80d88767a1f71fd675666cfa9bd065bc",
            language = "en-US"
        )

        when(val result = ApiExecutorHelper.singleBlockingExecute(api)) {
            is Result.Success -> {
                val data = result.data
                logDebug(data)
                assertTrue(data.isNotEmpty())
            }
            is Result.Failure -> {
                val e = result.error
                logError("${e.message}", e)
                assertTrue(e is AppException)
            }
        }
    }
}