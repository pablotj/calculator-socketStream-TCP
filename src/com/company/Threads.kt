package com.company


/**
 *
 * @author pablo
 */
class Threads(private val ID: String) : Thread() {

    private var ui: Interface? = null
    private var server: ServerTCP? = null

    override fun run() {
        when (ID) {
            "SERVER" -> {
                server = ServerTCP()
                server!!.servir()
            }
            "CLIENT" -> {
                ui = Interface()
                ui!!.isVisible = true
            }
        }
    }

}
