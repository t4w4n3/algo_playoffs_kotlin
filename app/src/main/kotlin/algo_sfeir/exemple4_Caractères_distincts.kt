package algo_playoffs

import java.util.TreeSet

fun main() {
    val chaine = "abcdemoderneancien"
    println(longestDisctinctChars(chaine))
}

fun longestDisctinctChars(chaine: String): String {
    var res = ""
    val distinctCharStringBuilder: TreeSet<Char> = TreeSet()
    chaine.forEach { char ->
        if (distinctCharStringBuilder.add(char)) return@forEach
        val size = distinctCharStringBuilder.size
        if (size <= res.length) return@forEach

        res = distinctCharStringBuilder.toCharArray().concatToString()
            .substring(0 until size - 1)
        distinctCharStringBuilder.clear()
    }
    return res
}
