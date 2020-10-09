package digital.klik.helper.network.model

import digital.klik.helper.network.constant.ConnectivityMode

data class ConnectionInfo(
    val isConnected: Boolean,
    val mode: ConnectivityMode
)
