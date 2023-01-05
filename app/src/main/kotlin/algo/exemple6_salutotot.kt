package algo

import java.util.concurrent.CompletableFuture.runAsync
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore

/*
* 3 versions :
*   * avec semaphores et thread
*   * avec ExecutorService
*   * avec completableFuture + ExecutorService
*/

fun main() {
    val salut = "Salut"
    val toto = " Toto"
    val s1 = Semaphore(1)
    val s2 = Semaphore(0)
    val repeatCount = 3
    Thread {
        repeat(repeatCount) {
            s1.acquire()
            if (it != 0) print(" ")
            print(salut + "(${Thread.currentThread().id})")
            s2.release()
        }
    }.start()
    Thread {
        repeat(repeatCount) {
            s2.acquire()
            print(toto + "(${Thread.currentThread().id})")
            s1.release()
        }
    }.start()
}

fun main2() {
    val salut = "Salut"
    val toto = " Toto"
    val ex = Executors.newSingleThreadExecutor()
    repeat(3) {
        if (it != 0) print(" ")
        print(salut + "(${Thread.currentThread().id})")
        ex.submit { print(toto + "(${Thread.currentThread().id})") }.get()
    }
}

fun main3() {
    val salut = "Salut"
    val toto = " Toto"
    val ex = Executors.newSingleThreadExecutor()
    repeat(3) {
        if (it != 0) print(" ")
        print(salut + "(${Thread.currentThread().id})")
        runAsync({ print(toto + "(${Thread.currentThread().id})") }, ex).join()
    }
}
