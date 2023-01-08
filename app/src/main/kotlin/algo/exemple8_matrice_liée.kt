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

private data class MutableInt(var value: Int = 0) : Comparable<MutableInt> {
    fun incrementAndGet() = ++value
    fun get() = value
    override fun compareTo(other: MutableInt): Int = get().compareTo(other.get())
}

// Maintient une PriorityQueue (équivalent d'une TreeList, soit une liste ordonnée suivant un Comparator) de l'index du premier élément non affiché de chaque ligne de la matrice.
// Affiche le premier élément de la queue (le plus petit), puis l'en supprime.
// Ensuite, on ajoute dans la queue l'index de l'élément suivant de la même ligne de la matrice que l'élément tout juste affiché.
// Le nouvel index d'élément est automatiquement et efficacement placé au bon endroit dans la queue
// On recommence jusqu'à ce que la queue soit vide
fun printMatrixElementsInOrder(matrix: Array<IntArray>) {
    val lineIndexToCursor: PriorityQueue<Pair<Int, MutableInt>> = matrix
        .indices
        .asSequence()
        .map { lineIndex -> lineIndex to MutableInt() }
        .toCollection(PriorityQueue(matrix.size, comparing { matrix[it.first][it.second.value] }))
    while (lineIndexToCursor.isNotEmpty()) {
        val minValueByItsLineIndex: Pair<Int, MutableInt>? = lineIndexToCursor.poll()
        if (minValueByItsLineIndex != null) {
            print("${matrix[minValueByItsLineIndex.first][minValueByItsLineIndex.second.get()]} ")
            val replacementIndex: Int = minValueByItsLineIndex.second.incrementAndGet()
            if (replacementIndex < matrix[minValueByItsLineIndex.first].size) {
                lineIndexToCursor.offer(minValueByItsLineIndex.first to minValueByItsLineIndex.second)
            }
        }
    }
}
