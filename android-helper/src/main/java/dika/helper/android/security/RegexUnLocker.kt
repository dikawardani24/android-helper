package dika.helper.android.security

@Suppress("unused")
class RegexUnLocker {
    private val regex: Regex

    constructor(regex: Regex) {
        this.regex = regex
    }

    constructor(regexString: String) {
        this.regex = regexString.toRegex()
    }

    fun decrypt(encrypted: String): String {
        val replacedString = encrypted.replace(regex, "")
        val reverseEncrypted = replacedString.reversed()
        val outPut = StringBuilder()
        var i = 0
        while (i < reverseEncrypted.length) {
            val result = reverseEncrypted.substring(i, i+2)
            outPut.append(result.toInt(16).toChar())
            i += 2
        }

        return outPut.toString()
    }
}