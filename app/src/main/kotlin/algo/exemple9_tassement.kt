package algo

fun main() {
    val tableau = arrayOf('a', 'b', '.', 'c', '.', '.', 'k')
    val result = tasser(tableau)
    println(result.contentToString())
    assert(result.contentEquals(arrayOf('a', 'b', 'c', 'k', '.', '.', '.')))
}

fun tasser(tableau: Array<Char>): Array<Char> {
    var nonDotCount = 0
    tableau
        .filter { it != '.' }
        .forEach { char -> tableau[nonDotCount++] = char }
    tableau.fill('.', nonDotCount, tableau.size)
    return tableau
}
