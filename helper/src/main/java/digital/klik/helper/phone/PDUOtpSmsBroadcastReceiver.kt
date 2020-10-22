package digital.klik.helper.phone

import android.content.Intent
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


abstract class PDUOtpSmsBroadcastReceiver: PDUSmsBroadcastReceiver() {

    @Parcelize
    data class OTPCode(val sender: String, val otpCode: String) : Parcelable

    abstract fun onExtractOtpCodeFrom(sms: Sms): OTPCode

    final override fun onExtractDataFromReceivedSms(sms: Sms, receiverIntent: Intent) {
        val otpCode = onExtractOtpCodeFrom(sms)
        receiverIntent.putExtra(KEY_OTP, otpCode)
    }

    companion object {
        const val KEY_OTP = "OTP_CODE"
    }
}
