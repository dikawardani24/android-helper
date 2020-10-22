package digital.klik.helper.common

import digital.klik.helper.exception.AppException

object InstanceHelper {
    inline fun <reified T> cast(instance: Any?, block: T.() -> Unit) {
        if (instance is T) {
            block(instance)
        } else {
            throw AppException("$instance cannot be cast to ${T::class.java.simpleName}")
        }
    }

    inline fun <reified T> cast(instance: Any?): T {
        return if (instance is T) {
            instance
        } else {
            throw AppException("$instance cannot be cast to ${T::class.java.simpleName}")
        }
    }
}