package digital.klik.helper.security

object PaddingHelper {

    fun paddingWithString(message: String, padlength: Int, padstring: String?): String? {
        val slen = message.length % padlength
        var i = padlength - slen

        return if ((i > 0) && (i < padlength)) {
            val buf = StringBuffer(message.length + i)
            buf.insert(0, message)
            i = padlength - slen
            while (i > 0) {
                buf.append(padstring)
                i--
            }
            buf.toString()
        } else {
            message
        }
    }

}