package algo

import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

data class Product(val id: Int) : Serializable {
    companion object {
        @JvmStatic
        private val serialVersionUID = 363296167341239575L
    }
}

data class Commande(val products: List<Product>) : Serializable {
    companion object {
        @JvmStatic
        private val serialVersionUID = -6807421645107232184
    }
}

fun main() {
    val commande = Commande(listOf(Product(1), Product(2)))
    val byteArrayOutputStream = ByteArrayOutputStream()
    val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
    objectOutputStream.writeObject(commande)
    objectOutputStream.flush()
    objectOutputStream.close()
    val message = byteArrayOutputStream.toByteArray()
    println(String(message))
    val byteInputStream = message.inputStream()
    val objectInputStream = ObjectInputStream(byteInputStream)
    val commandeReceived = objectInputStream.readObject() as Commande
    objectInputStream.close()
    println(commandeReceived)
}
