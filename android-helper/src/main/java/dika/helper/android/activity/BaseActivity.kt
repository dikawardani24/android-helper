package dika.helper.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dika.helper.android.api.extension.installProviderIfNeeded

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun kDigiOnCreate(savedInstanceState: Bundle?)

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kDigiOnCreate(savedInstanceState)
        installProviderIfNeeded()
    }
}