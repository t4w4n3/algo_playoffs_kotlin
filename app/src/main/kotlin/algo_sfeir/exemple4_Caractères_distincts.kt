package algo_playoffs

import java.util.TreeSet

fun main() {
    val chaine = "abcdemoderneancien"
    println(longestDisctinctChars(chaine))
}

fun longestDisctinctChars(chaine: String): String {
    var result = ""
    val distinctCharStringBuilder: TreeSet<Char> = TreeSet()
    chaine.forEach { char ->
        if (distinctCharStringBuilder.add(char)) return@forEach
        if (distinctCharStringBuilder.size <= result.length) return@forEach
        result = distinctCharStringBuilder.toCharArray().concatToString()
        distinctCharStringBuilder.clear()
    }
    return result
}
