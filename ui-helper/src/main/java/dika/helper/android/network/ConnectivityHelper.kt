package dika.helper.android.network

import android.content.Context
import android.net.ConnectivityManager

@Suppress("unused")
object ConnectivityHelper {

    @Suppress("DEPRECATION")
    fun verifyAvailableNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}