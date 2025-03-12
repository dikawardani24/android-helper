package dika.helper.android.api.exception

import dika.helper.android.api.constant.HttpStatus
import dika.helper.android.exception.AppException

@Suppress("unused")
class ApiException : AppException {
    private val errorBody: String?
    private val httpStatus: HttpStatus
    
    constructor(httpStatus: HttpStatus, errorBody: String?) : super() {
        this.httpStatus = httpStatus
        this.errorBody = errorBody
    }

    constructor(message: String?, httpStatus: HttpStatus, errorBody: String?) : super(message) {
        this.errorBody = errorBody
        this.httpStatus = httpStatus
    }
    constructor(message: String?, cause: Throwable?, httpStatus: HttpStatus, errorBody: String?) : super(message, cause) {
        this.httpStatus = httpStatus
        this.errorBody = errorBody
    }
    constructor(cause: Throwable?, httpStatus: HttpStatus, errorBody: String?) : super(cause) {
        this.httpStatus = httpStatus
        this.errorBody = errorBody
    }
    constructor(
        message: String?,
        cause: Throwable?,
        enableSuppression: Boolean,
        writableStackTrace: Boolean,
        httpStatus: HttpStatus,
        errorBody: String?
    ) : super(message, cause, enableSuppression, writableStackTrace) {
        this.httpStatus = httpStatus
        this.errorBody = errorBody
    }
}