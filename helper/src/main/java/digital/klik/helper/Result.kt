package digital.klik.helper

sealed class Result<T> {
    class Success<T>(val data: T): Result<T>()
    class Failure<T>(val error: Exception): Result<T>()
}