package dika.helper.android.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat

@Suppress("MemberVisibilityCanBePrivate", "unused")
abstract class DefaultBackableActivity : BaseActivity() {
    private var upIndicator: Drawable? = null

    protected abstract fun kDigiOnCreateUI(savedInstanceState: Bundle?)

    protected fun setUpIndicator(resId: Int) {
        upIndicator = ContextCompat.getDrawable(this, resId)
    }

    protected fun setUpIndicator(drawable: Drawable) {
        upIndicator = drawable
    }

    final override fun kDigiOnCreate(savedInstanceState: Bundle?) {
        kDigiOnCreateUI(savedInstanceState)

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            upIndicator?.run {
                setHomeAsUpIndicator(this)
            }
        }
    }

    protected fun kDigiOnOptionItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> kDigiOnOptionItemSelected(item)
        }
    }
}