package dika.helper.core.config

import dika.helper.core.config.constant.Environment


object EnvironmentConfig {
    private var environment = Environment.DEBUG

    fun changeEnvironmentMode(environment: Environment) {
        EnvironmentConfig.environment = environment
    }

    fun isDebuggingMode(): Boolean {
        return environment == Environment.DEBUG
    }

    fun isProductionMde(): Boolean {
        return environment == Environment.PRODUCTION
    }
}