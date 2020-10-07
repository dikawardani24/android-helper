package digital.klik.helper.api

import android.net.Uri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@Suppress("unused", "MemberVisibilityCanBePrivate")
object MultipartHelper {

    fun toTextPlainRequestBody(value: String): RequestBody {
        return value.toRequestBody("text/plain; charset=utf-8".toMediaType())
    }

    fun toTextPlainPart(value: String): MultipartBody.Part {
        val requestBody = toTextPlainRequestBody(value)
        return MultipartBody.Part.create(requestBody)
    }

    fun toImagePart(keyFile: String, value: Uri?): MultipartBody.Part? {
        return if (value == null) null
        else {
            val path = value.path
            if (path != null) {
                val file = File(path)

                val requestBody = file.asRequestBody("image/*".toMediaType())
                MultipartBody.Part.createFormData(keyFile, file.name, requestBody)
            } else {
                null
            }
        }
    }

    fun toImagePart(keyFile: String, value: File?): MultipartBody.Part? {
        return if (value == null) null
        else {
            val requestBody = value.name.toRequestBody("image/*".toMediaType())
            MultipartBody.Part.createFormData(keyFile, value.name, requestBody)
        }
    }

    fun toImagePart(value: File?): MultipartBody.Part? {
        return if (value == null) null
        else {
            val requestBody = value.name.toRequestBody("image/*".toMediaType())
            MultipartBody.Part.createFormData("file", value.name, requestBody)
        }
    }
}