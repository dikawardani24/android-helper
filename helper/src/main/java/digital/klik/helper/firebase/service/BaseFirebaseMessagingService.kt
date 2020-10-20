package digital.klik.helper.firebase.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import digital.klik.helper.common.extension.logDebug

abstract class BaseFirebaseMessagingService : FirebaseMessagingService() {

    protected abstract fun onHandleData(data: Map<String, String>)
    protected abstract fun onHandleNotification(notification: RemoteMessage.Notification)

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        logDebug("New Token : $newToken")
    }

    final override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val notification = remoteMessage.notification
        if (notification != null) {
            onHandleNotification(notification)
        }

        val data = remoteMessage.data
        if (data.isNotEmpty()) {
            onHandleData(data)
        }
    }

}