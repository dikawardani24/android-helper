package digital.klik.helper.sms

import android.content.Context
import android.content.IntentFilter
import com.google.android.gms.auth.api.phone.SmsRetriever
import digital.klik.helper.sms.broadcast.GoogleSmsBroadcastReceiver

class SmsRetriever(private val context: Context, private val broadcastReceiver: GoogleSmsBroadcastReceiver) {

    fun register() {
        val smsClient = SmsRetriever.getClient(context)
        val task = smsClient.startSmsRetriever()

        task.addOnSuccessListener {
            context.registerReceiver(broadcastReceiver, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION))
        }
        task.addOnFailureListener {
            context.unregisterReceiver(broadcastReceiver)
        }
    }
}