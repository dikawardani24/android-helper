package digital.klik.helper

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.collections.ArrayList

class AppSignatureHelper(context: Context): ContextWrapper(context) {
    val appSignatures: List<String>
        @Suppress("PackageManagerGetSignatures", "DEPRECATION")
        get() {
            val appCodes = ArrayList<String>()
            try {
                val packageName = packageName
                val packageManager = packageManager
                val signatures = packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES
                ).signatures

                signatures.forEach {
                    val hash =
                        hash(
                            packageName,
                            it.toCharsString()
                        )
                    appCodes.add(hash)
                }
            } catch (e: NoSuchAlgorithmException) {
                Log.e(TAG, "Unable to hashing: ${e.message}")
            } catch (e: PackageManager.NameNotFoundException) {
                Log.e(TAG, "Unable to find package to obtain hash.$e")
            }

            return appCodes
        }

    companion object {
        private val TAG = AppSignatureHelper::class.simpleName
        private const val HASH_TYPE = "SHA-256"
        private const val NUM_HASHED_BYTES = 9
        private const val NUM_BASE64_CHAR = 11

        private fun hash(packageName: String, signature: String): String {
            val  appInfo = "$packageName $signature"
            val messageDigest = MessageDigest.getInstance(HASH_TYPE)
            messageDigest.update(appInfo.toByteArray(StandardCharsets.UTF_8))
            // truncated into NUM_HASHED_BYTES
            val hashSignature =  Arrays.copyOfRange(messageDigest.digest(), 0,
                NUM_HASHED_BYTES
            )
            // encode into Base64
            val base64Hash = Base64.encodeToString(hashSignature, Base64.NO_PADDING or Base64.NO_WRAP).substring(0,
                NUM_BASE64_CHAR
            )

            Log.d(TAG, "hash: pkg: $packageName -- hash: $base64Hash")
            return base64Hash
        }
    }
}