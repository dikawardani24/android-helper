package digital.klik.helper.common

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NumberHelperTest {

    @Test
    fun formatWithNotation() {
        val result1 = NumberHelper.formatWithNotation(100.6)
        LoggerHelper.debug(this, result1)
        assertTrue(result1 == "100.60")

        val result2 = NumberHelper.formatWithNotation(999999.6)
        LoggerHelper.debug(this, result2)
        assertTrue(result2 == "999,999.60")


        val result3 = NumberHelper.formatWithNotation(1000000.6)
        LoggerHelper.debug(this, result3)
        assertTrue(result3 == "1.00M")
    }
}