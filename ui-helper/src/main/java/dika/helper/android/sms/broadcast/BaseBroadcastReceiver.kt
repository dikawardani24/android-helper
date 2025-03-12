package dika.helper.android.sms.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

abstract class BaseBroadcastReceiver: BroadcastReceiver() {

    /**
     * Handler for the non null received [Context] and [Intent]
     */
    protected abstract fun onReceivedContextAndIntent(context: Context, intent: Intent)

    /**
     * Check whether the received [Context] and [Intent] is not null
     */
    final override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) {
            return
        }

        onReceivedContextAndIntent(context, intent)
    }

}