package digital.klik.helper.api

import androidx.test.platform.app.InstrumentationRegistry
import digital.klik.helper.common.extension.logDebug
import digital.klik.helper.common.extension.logError
import digital.klik.helper.exception.AppException
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {
    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") apiKey: String,
                  @Query("language") language: String): Single<String>
}

class ApiClientTest {
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val apiClient = ApiClient("https://api.themoviedb.org/3/")
    private val movieDbApi = apiClient.createEndPoint(appContext.applicationContext, MovieDbApi::class.java)

    @Test
    fun testApiClient() {
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
}