package dika.helper.core.extension

import dika.helper.core.InstanceHelper

inline fun <reified T> Any?.cast(block: T.() -> Unit) = InstanceHelper.cast<T>(this, block)

inline fun <reified T> Any?.cast(): T = InstanceHelper.cast<T>(this)