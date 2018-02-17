package com.company

/**
 *
 * @author pablo
 */
object Main {
    private var thread: Threads? = null

    @JvmStatic
    fun main(args: Array<String>) {
        thread = Threads("SERVER")
        thread!!.start()
        thread = Threads("CLIENT")
        thread!!.start()
    }
}
