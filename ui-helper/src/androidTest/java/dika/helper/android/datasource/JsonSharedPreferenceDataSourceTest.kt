package dika.helper.android.sharedPreference.datasource

import androidx.test.platform.app.InstrumentationRegistry
import dika.helper.core.extension.logDebug
import dika.helper.android.sharedPreference.SharePreferenceProvider
import dika.helper.android.sharedPreference.exception.SharedPreferenceException
import dika.helper.android.sharedPreference.extension.getPrivateSharedPreferenceProvider
import org.junit.Assert.assertTrue
import org.junit.Test

class JsonSharedPreferenceDataSourceTest {
    data class Employee(var firstName: String,
                        var lastName: String,
                        var salary: Double)

    class EmployeeDataSource(
        provider: SharePreferenceProvider
    ): JsonSharedPreferenceDataSource<Employee>(provider, Employee::class.java)

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val provider = appContext.getPrivateSharedPreferenceProvider("dika")
    private val dataSource: SharedPreferenceDataSource<Employee> = EmployeeDataSource(provider)

    @Test
    fun store() {
        val sampleData = Employee(
            firstName = "Dika",
            lastName = "Wardani",
            salary = 90000000.0
        )

        try {
            dataSource.store(sampleData)
            val saved = dataSource.getStoredData()
            logDebug("sample : $sampleData, saved: $saved")
            assertTrue(sampleData == saved)
        } catch (e: Exception) {
            logDebug("${e.message}")
            assertTrue(e is SharedPreferenceException)
        }
    }

    @Test
    fun getStoredData() {
    }

    @Test
    fun getStoredDataOrNull() {
    }

    @Test
    fun clearStoredData() {
    }
}