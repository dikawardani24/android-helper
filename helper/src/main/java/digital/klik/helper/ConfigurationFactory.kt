package digital.klik.helper

import digital.klik.helper.config.DataStoreConfig
import digital.klik.helper.exception.ConfigException

object ConfigurationFactory {
    private lateinit var dataStoreConfig: DataStoreConfig

    fun install(dataStoreConfig: DataStoreConfig) {
        ConfigurationFactory.dataStoreConfig = dataStoreConfig
    }

    fun loadDataStoreConfig(): DataStoreConfig {
        if (this::dataStoreConfig.isInitialized) {
            return dataStoreConfig
        } else {
            throw ConfigException("Configuration for ${DataStoreConfig::class.simpleName} is not initialized")
        }
    }
}
