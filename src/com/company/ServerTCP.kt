package com.company

import java.io.InputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket

/**
 *
 * @author Pablo
 */
class ServerTCP {

    private val ADDRESS = "localhost"
    private val PORT = 55555

    companion object {
        private var entry: InputStream? = null
        private var exit: OutputStream? = null
        private var adder: InetSocketAddress? = null
    }

    fun respond() {
        println("Server started at $ADDRESS on the port $PORT")
            while (true) {
                    ServerSocket().use { serverSocket ->

                        Companion.adder = InetSocketAddress(ADDRESS, PORT)
                        serverSocket.bind(Companion.adder)

                        serverSocket.accept().use { newSocket ->

                            Companion.entry = newSocket.getInputStream()
                            Companion.exit = newSocket.getOutputStream()

                            val inBytes = ByteArray(25)
                            Companion.entry!!.read(inBytes)
                            var request = String(inBytes)
                                    .split(" ".toRegex())
                                    .dropLastWhile {
                                        it.isEmpty()
                                    }.toTypedArray()
                            val number1 = java.lang.Float.parseFloat(request[0])
                            val symbol = request[1]
                            val number2 = java.lang.Float.parseFloat(request[2])

                            when (symbol) {
                                "+" -> Companion.exit!!.write((addition(number1, number2).toString() + " ").toByteArray())
                                "-" -> Companion.exit!!.write((subtract(number1, number2).toString() + " ").toByteArray())
                                "/" -> Companion.exit!!.write((division(number1, number2).toString() + " ").toByteArray())
                                "x" -> Companion.exit!!.write((multiplication(number1, number2).toString() + " ").toByteArray())
                            }
                        }
                    }

            }
        }

        private fun addition(n1: Float, n2: Float): Float {
            return n1 + n2
        }

        private fun subtract(n1: Float, n2: Float): Float {
            return n1 - n2
        }

        private fun division(n1: Float, n2: Float): Float {
            return n1 / n2
        }

        private fun multiplication(n1: Float, n2: Float): Float {
            return n1 * n2
        }


}



