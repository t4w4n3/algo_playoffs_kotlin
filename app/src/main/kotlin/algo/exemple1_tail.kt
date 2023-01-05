package algo

import java.io.File
import java.io.RandomAccessFile
import kotlin.random.Random
import kotlin.system.exitProcess

fun createFile() {
    val file = File("ids.lst")
    val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    (1..1_000_000).joinToString("\n") {
        (1..10).map { Random.nextInt(0, charPool.size).let { charPool[it] } }.joinToString("")
    }.let(file::writeText)
}

fun main() {
//    createFile()
    tail(4, "ids.lst")
}

fun tail(lineCount: Int, filePath: String) {
    val file = File(filePath)
    if (!file.exists()) {
        println("file doesn't exist")
        exitProcess(1)
    }
    val fileLength = file.length()
    val randomAccessFile = RandomAccessFile(file, "r")
    var lineFeedCound: Long = 0
    var positionFromEnd: Long = 1
    do {
        randomAccessFile.seek(fileLength - positionFromEnd)
        val character = randomAccessFile.readByte().toInt().toChar()
        positionFromEnd++
        if (character == '\n') lineFeedCound++
    } while (lineFeedCound < lineCount)
    var tailStartingPosition: Long = fileLength - positionFromEnd + 2
    do {
        randomAccessFile.seek(tailStartingPosition)
        val character = randomAccessFile.readByte().toInt().toChar()
        print(character)
    } while (++tailStartingPosition != fileLength)
}
