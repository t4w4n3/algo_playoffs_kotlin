package algo_sfeir

fun main() {
    val tableau = arrayOf("kkaay", "rootr")
    val result = tableau.map(::findPalindrom)
    println(result)
    assert(result == listOf("akyka", "ortro"))
}

fun findPalindrom(str: String): String {
    val stringBuilder = StringBuilder()
    val listsOfIdenticalChars: Collection<List<Char>> = str.groupBy { it }.values
    val (identicalCharsEvenLists, identicalCharsOddLists) = listsOfIdenticalChars
        .groupBy { it.size.mod(2) == 0 }
        .let { it[true]!!.toMutableList() to it[false]!! }
    if (identicalCharsOddLists.size > 1) {
        throw RuntimeException("no palindrom for the word $str")
    }
    val uniqueIdenticalCharsOddList: MutableList<Char> = identicalCharsOddLists.first().toMutableList()
    val poppedLastCharOfUniqueOddList = uniqueIdenticalCharsOddList.removeLast()
    stringBuilder.append(poppedLastCharOfUniqueOddList)
    identicalCharsEvenLists += uniqueIdenticalCharsOddList
    identicalCharsEvenLists.forEach { identicalCharsEvenList ->
        identicalCharsEvenList.indices.forEach { index ->
            val char = identicalCharsEvenList[index]
            if (index.mod(2) == 0) {
                stringBuilder.insert(0, char)
            } else {
                stringBuilder.append(char)
            }
        }
    }
    return stringBuilder.toString()
}
