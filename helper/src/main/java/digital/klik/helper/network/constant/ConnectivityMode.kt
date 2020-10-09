@file:Suppress("DEPRECATION")

package digital.klik.helper.network.constant

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo

enum class ConnectivityMode {
    NONE,
    WIFI,
    MOBILE,
    ETHERNET,
    BLUETOOTH;

    companion object {
        fun from(networkInfo: NetworkInfo): ConnectivityMode {
            return when(networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> WIFI
                ConnectivityManager.TYPE_MOBILE -> MOBILE
                ConnectivityManager.TYPE_ETHERNET -> ETHERNET
                ConnectivityManager.TYPE_BLUETOOTH -> BLUETOOTH
                else -> NONE
            }
        }

        fun from(networkCapabilities: NetworkCapabilities): ConnectivityMode {
            networkCapabilities.run {
                return when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> WIFI
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> MOBILE
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ETHERNET
                    hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> BLUETOOTH
                    else -> NONE
                }
            }
        }
    }
}