package digital.klik.helper.common.model

data class Time(
    var hour: Int,
    var minute: Int,
    var second: Int = 0,
    var milliSecond: Int = 0
)