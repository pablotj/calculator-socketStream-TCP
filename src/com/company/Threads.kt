package com.company

/**
 *
 * @author Pablo
 */
class Threads(private val ID: String) : Thread() {

    private var ui: Interface? = null
    private var server: ServerTCP? = null

    override fun run() {
        when (ID) {
            "SERVER" -> {
                server = ServerTCP()
                server!!.respond()
            }
            "CLIENT" -> {
                ui = Interface()
                ui!!.isVisible = true
            }
        }
    }

}
