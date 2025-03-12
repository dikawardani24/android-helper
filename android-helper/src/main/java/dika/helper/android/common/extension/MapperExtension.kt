@file:Suppress("unused")

package dika.helper.android.common.extension

import dika.helper.android.common.MapperHelper

fun <I, O> List<I>.transformToArrayList(map: (input: I) -> O): ArrayList<O> {
    return MapperHelper.transformToArrayList(this, map)
}

fun <I, O> List<I>.transformToList(map: (input: I) -> O): List<O> {
    return MapperHelper.transformToList(this, map)
}

fun <I, O> Array<I>.transformToList(map: (input: I) -> O): List<O> {
    val inputs = toList()
    return MapperHelper.transformToList(inputs, map)
}

fun <I, O> List<I>?.transformToArrayListNullable(map: (input: I) -> O): ArrayList<O>? {
    return MapperHelper.transformToArrayListNullable(this, map)
}

fun <I, O> List<I>?.transformToListNullable(map: (input: I) -> O): List<O>? {
    return MapperHelper.transformToListNullable(this, map)
}

fun <I, O> Array<I>?.transformToListNullable(map: (input: I) -> O): List<O>? {
    val inputs = this?.toList()
    return MapperHelper.transformToListNullable(inputs, map)
}