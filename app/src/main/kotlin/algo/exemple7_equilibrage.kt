package algo

import java.util.function.Function

class Exemple7 : Function<IntArray, Pair<IntArray, IntArray>> {
    override fun apply(tableau: IntArray): Pair<IntArray, IntArray> {
        val leftStack = mutableListOf<Int>()
        var leftSum = 0
        val rightStack = mutableListOf<Int>()
        var rightSum = 0
        tableau.sortedArrayDescending().forEach { int ->
            if (rightSum > leftSum) {
                leftStack += int
                leftSum += int
            } else {
                rightStack += int
                rightSum += int
            }
        }
        return leftStack.toIntArray().apply { sort() } to rightStack.toIntArray().apply { sort() }
    }
}
