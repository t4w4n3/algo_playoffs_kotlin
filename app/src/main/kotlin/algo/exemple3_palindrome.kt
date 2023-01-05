package algo

fun main() {
    val tableau = arrayOf("kkaay", "rootr", "ee", "e", "")
    val result = tableau.map(::findPalindrom)
    println(result)
    assert(result == listOf("akyka", "ortro", "ee", "e", ""))
}

fun findPalindrom(str: String): String {
    val stringBuilder = StringBuilder()
    val listsOfIdenticalChars: Collection<List<Char>> = str.groupBy { it }.values
    val (identicalCharsEvenLists, identicalCharsOddLists) = listsOfIdenticalChars
        .groupBy { it.size.mod(2) == 0 }
        .let { it.getOrDefault(true, emptyList()).toMutableList() to it.getOrDefault(false, emptyList()) }
    if (identicalCharsOddLists.size > 1) {
        throw RuntimeException("no palindrom for the word $str")
    }
    if (identicalCharsOddLists.size == 1) {
        val uniqueIdenticalCharsOddList: MutableList<Char> = identicalCharsOddLists.first().toMutableList()
        val poppedLastCharOfUniqueOddList = uniqueIdenticalCharsOddList.removeLast()
        stringBuilder.append(poppedLastCharOfUniqueOddList)
        identicalCharsEvenLists += uniqueIdenticalCharsOddList
    }
    identicalCharsEvenLists.forEach { identicalCharsEvenList ->
        identicalCharsEvenList.forEachIndexed { index, char ->
            if (index.mod(2) == 0) {
                stringBuilder.insert(0, char)
            } else {
                stringBuilder.append(char)
            }
        }
    }
    return stringBuilder.toString()
}
