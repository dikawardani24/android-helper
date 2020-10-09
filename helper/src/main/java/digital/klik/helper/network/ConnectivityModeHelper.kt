package digital.klik.helper.network

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import digital.klik.helper.network.constant.ConnectivityMode

@Suppress("unused")
object ConnectivityModeHelper {

    private fun checkConnectivity(context: Context): ConnectivityMode {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        cm.run {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = getNetworkCapabilities(activeNetwork)
                networkCapabilities?.run {
                    ConnectivityMode.from(this)
                } ?: ConnectivityMode.NONE

            } else {
                @Suppress("DEPRECATION")
                activeNetworkInfo?.run {
                    ConnectivityMode.from(this)
                } ?: ConnectivityMode.NONE
            }
        }
    }
}