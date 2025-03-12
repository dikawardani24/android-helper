package dika.helper.android.config

import dika.helper.android.config.constant.Environment


object EnvironmentConfig {
    private var environment = Environment.DEBUG

    fun changeEnvironmentMode(environment: Environment) {
        this.environment = environment
    }

    fun isDebuggingMode(): Boolean {
        return environment == Environment.DEBUG
    }

    fun isProductionMde(): Boolean {
        return environment == Environment.PRODUCTION
    }
}