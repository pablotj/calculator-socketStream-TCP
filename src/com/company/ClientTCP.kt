package com.company


import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.util.Scanner
import java.util.logging.Level
import java.util.logging.Logger
import javax.swing.JOptionPane

class ClientTCP {
    private var clienteSocket: Socket? = null
    private var addr: InetSocketAddress? = null
    private var `in`: InputStream? = null
    private var out: OutputStream? = null
    //private int datos;

    fun connect() {
        clienteSocket = Socket()
        addr = InetSocketAddress("localhost", 55555)

        try {
            clienteSocket!!.connect(addr)
        } catch (ex: IOException) {
            JOptionPane.showMessageDialog(null, ex, "INTERNAL ERROR", JOptionPane.ERROR_MESSAGE)
            System.exit(0)
        }

    }

    fun comunication(operation: String): String {
        connect()

        var result: Array<String>? = null
        try {
            //Enviamos la operacion al servidor
            out = clienteSocket!!.getOutputStream()
            out!!.write(operation.toByteArray())

            //Recibimos la respuesta del servidor
            `in` = clienteSocket!!.getInputStream()
            val resultBytes = ByteArray(25)
            `in`!!.read(resultBytes)

            result = String(resultBytes).split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

        } catch (ex: IOException) {
            Logger.getLogger(ClientTCP::class.java!!.getName()).log(Level.SEVERE, null, ex)
            close()

        }

        close()
        return result!![0]


    }

    fun close() {
        try {
            clienteSocket!!.close()
        } catch (ex: IOException) {
            Logger.getLogger(ClientTCP::class.java!!.getName()).log(Level.SEVERE, null, ex)
        }

    }


}
