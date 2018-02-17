package com.company


import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Array
import java.net.InetSocketAddress
import java.net.Socket
import java.util.Arrays
import java.net.ServerSocket
import java.util.ArrayList

class ServerTCP {
    private val result: Float = 0.toFloat()



        private val ADDRES = "localhost"
        private val PORT = 55555
        private var addr: InetSocketAddress? = null
        private var `in`: InputStream? = null
        private var out: OutputStream? = null



        fun servir() {

            while (true) {

                try {

                    ServerSocket().use { serverSocket ->

                        addr = InetSocketAddress(ADDRES, PORT)
                        serverSocket.bind(addr)

                        serverSocket.accept().use { newSocket ->

                            `in` = newSocket.getInputStream()
                            out = newSocket.getOutputStream()

                            val inBytes = ByteArray(25)
                            `in`!!.read(inBytes)
                            var mensaje = String(inBytes).split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                            val numero1 = java.lang.Float.parseFloat(mensaje[0])
                            val operador = mensaje[1]
                            val numero2 = java.lang.Float.parseFloat(mensaje[2])

                            when (operador) {
                                "+" -> out!!.write((sumar(numero1, numero2).toString() + " ").toString().toByteArray())
                                "-" -> out!!.write((restar(numero1, numero2).toString() + " ").toString().toByteArray())
                                "/" -> out!!.write((dividir(numero1, numero2).toString() + " ").toString().toByteArray())
                                "x" -> out!!.write((multiplicar(numero1, numero2).toString() + " ").toString().toByteArray())
                            }
                        }
                    }
                } catch (e: IOException) {
                    println("Error al procesar la solicitud")
                }

            }
        }

        fun sumar(n1: Float, n2: Float): Float {
            return n1 + n2
        }

        fun restar(n1: Float, n2: Float): Float {
            return n1 - n2
        }

        fun dividir(n1: Float, n2: Float): Float {
            return n1 / n2
        }

        fun multiplicar(n1: Float, n2: Float): Float {
            return n1 * n2
        }
    }



