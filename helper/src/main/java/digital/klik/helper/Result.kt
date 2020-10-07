package digital.klik.helper

import digital.klik.helper.exception.AppException

sealed class Result<T> {
    class Success<T>(val data: T): Result<T>()
    class Failure<T>(val error: AppException): Result<T>()
}