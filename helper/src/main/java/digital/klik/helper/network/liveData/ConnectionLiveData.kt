package digital.klik.helper.network.liveData

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import digital.klik.helper.network.broadcast.NetworkBroadcastReceiver
import digital.klik.helper.network.model.ConnectionInfo

class ConnectionLiveData(private val context: Context): MutableLiveData<ConnectionInfo>() {
    private val networkReceiver = NetworkBroadcastReceiver(this)

    @Suppress("DEPRECATION")
    override fun onActive() {
        super.onActive()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReceiver)
    }
}