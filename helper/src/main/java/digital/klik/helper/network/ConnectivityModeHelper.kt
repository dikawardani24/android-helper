package digital.klik.helper.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

@Suppress("unused")
object ConnectivityModeHelper {

    enum class ConnectivityMode {
        NONE,
        WIFI,
        MOBILE,
        ETHERNET,
        BLUETOOTH
    }

    private fun checkConnectivity(context: Context): ConnectivityMode {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        cm.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getNetworkCapabilities(activeNetwork)?.run {
                    return when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectivityMode.WIFI
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectivityMode.MOBILE
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectivityMode.ETHERNET
                        hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> ConnectivityMode.BLUETOOTH
                        else -> ConnectivityMode.NONE
                    }
                }
            } else {
                @Suppress("DEPRECATION")
                activeNetworkInfo?.run {
                    return when (type) {
                        ConnectivityManager.TYPE_WIFI -> ConnectivityMode.WIFI
                        ConnectivityManager.TYPE_MOBILE -> ConnectivityMode.MOBILE
                        ConnectivityManager.TYPE_ETHERNET -> ConnectivityMode.ETHERNET
                        ConnectivityManager.TYPE_BLUETOOTH -> ConnectivityMode.BLUETOOTH
                        else -> ConnectivityMode.NONE
                    }
                }
            }
        }
        return ConnectivityMode.NONE
    }
}