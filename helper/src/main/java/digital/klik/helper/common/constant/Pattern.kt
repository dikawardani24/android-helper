package digital.klik.helper.common.constant

@Suppress("unused")
enum class Pattern(val value: String) {
    DATE_FULL("yyyy-MM-dd HH:mm:ss"),
    DATE("yyyy-MM-dd"),
    NUMBER("#,###.00")
}