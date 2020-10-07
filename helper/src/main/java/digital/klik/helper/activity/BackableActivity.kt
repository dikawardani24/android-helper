package digital.klik.helper.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BackableActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}