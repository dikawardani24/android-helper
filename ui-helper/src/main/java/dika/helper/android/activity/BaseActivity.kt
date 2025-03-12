package dika.helper.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.BuildConfig
import dika.helper.android.security.extension.installProviderIfNeeded
import dika.helper.core.config.EnvironmentConfig
import dika.helper.core.config.constant.Environment

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun dikaOnCreate(savedInstanceState: Bundle?)

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dikaOnCreate(savedInstanceState)
        installProviderIfNeeded()
        val environmentConfig = if(BuildConfig.DEBUG) Environment.DEBUG else Environment.PRODUCTION
        EnvironmentConfig.changeEnvironmentMode(environmentConfig)
    }
}