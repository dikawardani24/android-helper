package dika.helper.android.network.model

import dika.helper.android.network.constant.ConnectivityMode

data class ConnectionInfo(
    val isConnected: Boolean,
    val mode: ConnectivityMode
)
