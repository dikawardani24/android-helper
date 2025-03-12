package dika.helper.core

@Suppress("unused", "MemberVisibilityCanBePrivate")
object MapperHelper {
    fun <I, O> transformToArrayList(inputs: List<I>, map: (input: I) -> O): ArrayList<O> {
        val outputs = ArrayList<O>()

        inputs.forEach {
            val output = map(it)
            outputs.add(output)
        }

        return outputs
    }

    fun <I, O> transformToList(inputs: List<I>, map: (input: I) -> O): List<O> {
        val outputs = transformToArrayList(inputs, map)
        return outputs.toList()
    }


    fun <I, O> transformToArrayListNullable(inputs: List<I>?, map: (input: I) -> O): ArrayList<O>? {
        return if (inputs == null) {
            return null
        } else {
            transformToArrayList(inputs, map)
        }
    }

    fun <I, O> transformToListNullable(inputs: List<I>?, map: (input: I) -> O): List<O>? {
        return transformToArrayListNullable(inputs, map)?.toList()
    }
}