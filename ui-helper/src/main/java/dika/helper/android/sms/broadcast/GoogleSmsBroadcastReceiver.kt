package dika.helper.android.sms.broadcast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import dika.helper.android.sms.broadcast.constant.GoogleFailedSmsCaused
import dika.helper.android.sms.broadcast.model.Sms

abstract class GoogleSmsBroadcastReceiver : BaseBroadcastReceiver() {

    protected abstract fun onRetrievedSms(context: Context, sms: Sms)

    protected abstract fun onFailure(context: Context, causedFailed: GoogleFailedSmsCaused)

    final override fun onReceivedContextAndIntent(context: Context, intent: Intent) {
        val action = intent.action

        if (action == SmsRetriever.SMS_RETRIEVED_ACTION) {
            val extras = intent.extras
            if (extras is Bundle) {
                val status = extras.get(SmsRetriever.EXTRA_STATUS)

                if (status is Status) {
                    when(status.statusCode) {
                        CommonStatusCodes.SUCCESS -> {
                            val smsMessage = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE)
                            if (smsMessage is String) {
                                val sms = Sms(
                                    sender = "",
                                    message = smsMessage
                                )

                                onRetrievedSms(context, sms)
                            } else {
                                onFailure(context, GoogleFailedSmsCaused.NON_STRING_DATA)
                            }
                        }

                        CommonStatusCodes.TIMEOUT -> {
                            onFailure(context, GoogleFailedSmsCaused.TIMEOUT)
                        }
                    }
                } else {
                    onFailure(context, GoogleFailedSmsCaused.NO_STATUS_HAS_BEEN_RECEIVED)
                }
            } else {
                onFailure(context, GoogleFailedSmsCaused.NO_EXTRA_DATA_HAS_BEEN_RECEIVED)
            }
        }
    }

}