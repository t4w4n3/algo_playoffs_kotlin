package algo

import java.util.Comparator.comparing
import java.util.PriorityQueue

fun main() {
    val matrix: Array<IntArray> = arrayOf(
        intArrayOf(3, 7, 11, 13),
        intArrayOf(3, 7, 11, 13),
        intArrayOf(1, 4, 11, 11, 12),
        intArrayOf(1, 4, 11, 11, 12),
        intArrayOf(14, 15, 116, 2045),
        intArrayOf(14, 15, 116, 2045)
    )

    printMatrixElementsInOrder(matrix)
}

private data class MutableInt(var value: Int = 0) {
    fun incrementAndGet() = ++value
}

// Maintient un index trié du premier élément non affiché de chaque ligne de la matrice.
// Affiche le premier élément de l'index (le plus petit), puis le supprime de l'index.
// Ensuite, on ajoute dans l'index l'élément suivant de la même ligne de la matrice que l'élément tout juste affiché.
// Le nouvel élément est automatiquement et efficacement placé au bon endroit dans l'index
// On recommence jusqu'à ce que l'index soit vide
fun printMatrixElementsInOrder(matrix: Array<IntArray>) {
    val lineIndexToCursor: MutableMap<Int, MutableInt> = mutableMapOf()
    val lineIndexToValueAtItsCurrentCursor: PriorityQueue<Pair<Int, Int>> = matrix
        .asSequence()
        .mapIndexed { index, intArray -> index to intArray[0] }
        .toCollection(PriorityQueue(matrix.size, comparing { it.second }))
    while (lineIndexToValueAtItsCurrentCursor.isNotEmpty()) {
        val minValueByItsLineIndex: Pair<Int, Int>? = lineIndexToValueAtItsCurrentCursor.poll()
        if (minValueByItsLineIndex != null) {
            print("${minValueByItsLineIndex.second} ")
            val cursorForLineIndex: MutableInt = lineIndexToCursor.getOrPut(minValueByItsLineIndex.first) { MutableInt() }
            val replacement: Int? = matrix[minValueByItsLineIndex.first].getOrNull(cursorForLineIndex.incrementAndGet())
            if (replacement != null) {
                lineIndexToValueAtItsCurrentCursor.offer(minValueByItsLineIndex.first to replacement)
            }
        }
    }
}
