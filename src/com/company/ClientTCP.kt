package com.company

import java.io.InputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket

/**
*
* @author Pablo
*/
class ClientTCP {

    private val ADDRESS = "localhost"
    private val PORT = 55555

    companion object {
        
        private var clientSocket: Socket? = null
        private var adder: InetSocketAddress? = null
        private var entry: InputStream? = null
        private var exit: OutputStream? = null

    }

    private fun connect() {

        Companion.clientSocket = Socket()
        Companion.adder = InetSocketAddress(ADDRESS, PORT)
        Companion.clientSocket!!.connect(Companion.adder)

        println("Connected at $ADDRESS on the port $PORT")

    }

    fun communication(operation: String): String {
        connect()

        var result: Array<String>? = null

        //The operation is sent to the server
        Companion.exit = Companion.clientSocket!!.getOutputStream()
        Companion.exit!!.write(operation.toByteArray())

        //The response is received from the server
        Companion.entry = Companion.clientSocket!!.getInputStream()
        val resultBytes = ByteArray(25)
        Companion.entry!!.read(resultBytes)

        result = String(resultBytes).split(regex = " ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

        close()
        return result[0]

    }

    private fun close() {

        Companion.clientSocket!!.close()
        println("Disconnected at $ADDRESS on the port $PORT")

    }
}
