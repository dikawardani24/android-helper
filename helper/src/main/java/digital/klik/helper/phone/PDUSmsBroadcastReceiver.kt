package digital.klik.helper.phone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony

abstract class PDUSmsBroadcastReceiver : BroadcastReceiver() {

    data class Sms(val sender: String, val message: String)

    protected abstract fun onExtractDataFromReceivedSms(sms: Sms, receiverIntent: Intent)

    private fun broadCastDataSms(context: Context, sms: Sms) {
        val receiverSmsIntent = Intent(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        onExtractDataFromReceivedSms(sms, receiverSmsIntent)
        context.sendBroadcast(receiverSmsIntent)
    }

    final override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        if (action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            smsMessages.forEach {
                val message = it.displayMessageBody
                val sender = it.displayOriginatingAddress
                val sms = Sms(sender, message)

                context?.run {
                    broadCastDataSms(this, sms)
                }
            }
        }
    }
}