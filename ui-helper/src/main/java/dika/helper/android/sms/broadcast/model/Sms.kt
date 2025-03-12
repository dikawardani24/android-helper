package dika.helper.android.sms.broadcast.model

import android.os.Parcelable
import android.telephony.SmsMessage
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sms (val sender: String, val message: String) : Parcelable {

    companion object {

        fun from(smsMessage: SmsMessage): Sms {
            return Sms(
                sender = smsMessage.displayOriginatingAddress,
                message = smsMessage.displayMessageBody
            )
        }

    }
}