package dika.helper.android.sms.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import dika.helper.android.sms.broadcast.model.Sms
import dika.helper.core.extension.transformToList

/**
 * The base sms broadcast receiver which will extract data from the given object [Intent] which contain data sms
 * using key pdus.
 *
 * @see BroadcastReceiver
 */
abstract class PduSmsBroadcastReceiver(private val knownSender: String = "") : BaseBroadcastReceiver() {

    /**
     * The handler of received sms data
     * @param context current active [Context]
     * @param smsMessages list of sms data
     */
    protected abstract fun onReceivedSms(context: Context, smsMessages: List<Sms>)

    final override fun onReceivedContextAndIntent(context: Context, intent: Intent) {
        val action = intent.action

        if (action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            if (smsMessages != null && smsMessages.isNotEmpty()) {
                val smsList = smsMessages.transformToList { Sms.from(it) }

                if (knownSender.isNotEmpty()) {
                    onReceivedSms(context, smsList.filter { knownSender == it.sender })
                } else {
                    onReceivedSms(context, smsList)
                }
            }
        }
    }
}