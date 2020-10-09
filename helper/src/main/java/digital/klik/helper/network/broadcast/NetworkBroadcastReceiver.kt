@file:Suppress("DEPRECATION")

package digital.klik.helper.network.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData
import digital.klik.helper.network.constant.ConnectivityMode
import digital.klik.helper.network.model.ConnectionInfo

class NetworkBroadcastReceiver(private val liveData: MutableLiveData<ConnectionInfo>) :
    BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val activeNetwork = intent?.extras?.get(ConnectivityManager.EXTRA_EXTRA_INFO)
        val isConnected: Boolean
        val mode: ConnectivityMode

        if (activeNetwork is NetworkInfo) {
            isConnected = activeNetwork.isConnectedOrConnecting
            mode = if (isConnected) { ConnectivityMode.from(activeNetwork) } else { ConnectivityMode.NONE }
        } else {
            isConnected = false
            mode = ConnectivityMode.NONE
        }

        val connectionInfo = ConnectionInfo(isConnected, mode)
        liveData.postValue(connectionInfo)
    }
}