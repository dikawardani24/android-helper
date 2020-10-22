package digital.klik.helper.phone

class DefaultPDUOtpBroadcastReceiver : PDUOtpSmsBroadcastReceiver() {

    override fun onExtractOtpCodeFrom(sms: Sms): OTPCode {
        val message = sms.message
        val otpCode = if (message.contains("OTP"))
                message.substring(23, 29)
            else
                message.substring(20, 26)

        return OTPCode(sms.sender, otpCode)
    }
}