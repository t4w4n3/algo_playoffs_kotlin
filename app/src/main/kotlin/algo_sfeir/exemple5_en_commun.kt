package algo_sfeir

fun main() {
    val list1 = "aeeeb".toCharArray().toList()
    val list2 = "bbceeg".toCharArray().toList()
    val result = findCommonChars(list1, list2)
    println(result)
    assert(result == "eeb".toCharArray().toList())
}

// Solution simple en O(longestList.size) + shortestList.size * O(1), soit le temps d'indexation dans la HashMap + le contains
// Dommage, pas de HashList native en Java/Kotlin, ç'aurait été pratique
fun findCommonChars(list1: List<Char>, list2: List<Char>): List<Char> {
    val (shortestList, longestList) = if (list1.size < list2.size) list1 to list2 else list2 to list1
    val charToOccurenceCountInLongestList: MutableMap<Char, Int> = longestList.fold(mutableMapOf(), ::countBy)
    return shortestList.filter { char ->
        val count: Int = charToOccurenceCountInLongestList[char] ?: return@filter false
        if (count == 1) return@filter charToOccurenceCountInLongestList.remove(char, 1)
        charToOccurenceCountInLongestList[char] = count - 1
        return@filter true
    }
}

fun countBy(accumulator: MutableMap<Char, Int>, element: Char): MutableMap<Char, Int> = accumulator.apply {
    put(element, getOrDefault(element, 0) + 1)
}
