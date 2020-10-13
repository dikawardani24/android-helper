package digital.klik.helper.api.model

import java.util.concurrent.TimeUnit

data class TimeOut(
    val time: Long,
    val timeUnit: TimeUnit = TimeUnit.SECONDS
)