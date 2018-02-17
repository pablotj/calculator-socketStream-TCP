package com.company


import java.awt.Color
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.Arrays
import javax.swing.*
import javax.swing.SwingConstants.RIGHT

/**
 *
 * @author Pablo
 */
class Interface : JFrame() {
    private val TITLE = "CALCULATOR"

    internal var saved: Array<String>
    internal var operatorPosition: Boolean? = false // true-> Operator 1 && false -> Operator 2

    private var result: JLabel? = null
    private var btn0: JButton? = null
    private var btn1: JButton? = null
    private var btn2: JButton? = null
    private var btn3: JButton? = null
    private var btn4: JButton? = null
    private var btn5: JButton? = null
    private var btn6: JButton? = null
    private var btn7: JButton? = null
    private var btn8: JButton? = null
    private var btn9: JButton? = null
    private var btnAddition: JButton? = null
    private var btnSubtract: JButton? = null
    private var btnDivision: JButton? = null
    private var btnMultiplication: JButton? = null
    private var btnAC: JButton? = null
    private var btnPoint: JButton? = null
    private var btnEqual: JButton? = null

    companion object {
        private var client: ClientTCP? = null
    }

    fun calculate(op: String): String {
        println(op)
        client = ClientTCP()
        return client!!.communication(op)
    }

    fun operation(): String {
        return saved[0] + " " + saved[1] + " " + saved[2]
    }

    init {
        // Set theme
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel")
        } catch (e: ClassNotFoundException) {
        } catch (e: IllegalAccessException) {
        } catch (e: InstantiationException) {
        } catch (e: UnsupportedLookAndFeelException) {
        }

        init()

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(200, 350)
        setLocationRelativeTo(null)
        layout = null
        isResizable = false
        isVisible = true
        title = TITLE

        saved = arrayOf("", "", "")
        operatorPosition = false
    }

    private fun init() {

        // ---- ROW 0 -->> [result] ----//
        result = JLabel("0.0")
        result!!.setBounds(0, 0, 200, 50)
        result!!.foreground = Color.white
        result!!.horizontalAlignment = RIGHT
        result!!.font = Font("Arial", Font.BOLD, 30)
        add(result)


        // ---- ROW 1 -->> [AC,/] ----//
        btnAC = JButton("AC")
        btnAC!!.setBounds(0, 50, 150, 50)
        btnAC!!.addActionListener(ButtonListener())
        add(btnAC)

        btnDivision = JButton("/")
        btnDivision!!.setBounds(150, 50, 50, 50)
        btnDivision!!.addActionListener(ButtonListener())
        btnDivision!!.background = java.awt.Color.orange
        add(btnDivision)


        // ---- ROW 2 -->> [7,8,9,x] ----//
        btn7 = JButton("7")
        btn7!!.setBounds(0, 100, 50, 50)
        btn7!!.addActionListener(ButtonListener())
        add(btn7)

        btn8 = JButton("8")
        btn8!!.setBounds(50, 100, 50, 50)
        btn8!!.addActionListener(ButtonListener())
        add(btn8)

        btn9 = JButton("9")
        btn9!!.setBounds(100, 100, 50, 50)
        btn9!!.addActionListener(ButtonListener())
        add(btn9)

        btnMultiplication = JButton("x")
        btnMultiplication!!.setBounds(150, 100, 50, 50)
        btnMultiplication!!.addActionListener(ButtonListener())
        btnMultiplication!!.background = java.awt.Color.orange
        add(btnMultiplication)


        // ---- ROW 3 -->> [4,5,6,-] ----//
        btn4 = JButton("4")
        btn4!!.setBounds(0, 150, 50, 50)
        btn4!!.addActionListener(ButtonListener())
        add(btn4)

        btn5 = JButton("5")
        btn5!!.setBounds(50, 150, 50, 50)
        btn5!!.addActionListener(ButtonListener())
        add(btn5)

        btn6 = JButton("6")
        btn6!!.setBounds(100, 150, 50, 50)
        btn6!!.addActionListener(ButtonListener())
        add(btn6)

        btnSubtract = JButton("-")
        btnSubtract!!.setBounds(150, 150, 50, 50)
        btnSubtract!!.addActionListener(ButtonListener())
        btnSubtract!!.background = java.awt.Color.orange
        add(btnSubtract)


        // ---- ROW 4 -->> [1,2,3,+] ----//
        btn1 = JButton("1")
        btn1!!.setBounds(0, 200, 50, 50)
        btn1!!.addActionListener(ButtonListener())
        add(btn1)

        btn2 = JButton("2")
        btn2!!.setBounds(50, 200, 50, 50)
        btn2!!.addActionListener(ButtonListener())
        add(btn2)

        btn3 = JButton("3")
        btn3!!.setBounds(100, 200, 50, 50)
        btn3!!.addActionListener(ButtonListener())
        add(btn3)

        btnAddition = JButton("+")
        btnAddition!!.setBounds(150, 200, 50, 50)
        btnAddition!!.addActionListener(ButtonListener())
        btnAddition!!.background = Color.orange
        add(btnAddition)


        // ---- ROW 5 -->> [0, ',' ,=] ----//
        btn0 = JButton("0")
        btn0!!.setBounds(0, 250, 100, 50)
        btn0!!.addActionListener(ButtonListener())
        add(btn0)

        btnPoint = JButton(",")
        btnPoint!!.setBounds(100, 250, 50, 50)
        btnPoint!!.addActionListener(ButtonListener())
        add(btnPoint)

        btnEqual = JButton("=")
        btnEqual!!.setBounds(150, 250, 50, 50)
        btnEqual!!.addActionListener(ButtonListener())
        btnEqual!!.background = java.awt.Color.orange
        add(btnEqual)

    }

    internal inner class ButtonListener : ActionListener {

        override fun actionPerformed(e: ActionEvent) {

            val btn = e.actionCommand
            if ("" != saved[1]) {
                operatorPosition = true
            }

            if("0"==btn||"1"==btn||"2"==btn||"3"==btn||"4"==btn||"5"==btn||"6"==btn||"7"==btn||"8"==btn||"9"==btn){

                if (operatorPosition!!) {
                    saved[2] += btn
                    result!!.text = saved[2]

                } else {
                    saved[0] += btn
                    result!!.text = saved[0]
                }

            }else if("/"==btn ||"x"==btn ||"-"==btn ||"+"==btn ) {

                saved[1] = btn
                operatorPosition = true
                result!!.text = btn

            }else{

                when (btn) {

                    "AC" -> {
                        result!!.text = "0.0"
                        saved = arrayOf("", "", "")
                        operatorPosition = false
                    }

                    "," ->
                        if (operatorPosition!! && !saved[2].contains(".")) {
                            saved[2] += "."
                            result!!.text = saved[2]

                        } else if ((!operatorPosition!!)!! && !saved[0].contains(".")) {
                            saved[0] += "."
                            result!!.text = saved[0]
                        }

                    "=" -> {

                        if ("" == saved[0] && "" == saved[1] && "" == saved[2]) {
                            saved = arrayOf("0.0", "+", "0.0")

                        } else if ("" != saved[0] && "" == saved[1] && "" == saved[2]) {
                            saved = arrayOf(saved[0], "+", saved[0])

                        } else if ("" != saved[0] && "" != saved[0] && "" == saved[2]) {
                            saved = arrayOf(saved[0], saved[1], saved[0])

                        } else if ("" == saved[0] && "" == saved[1] && "" != saved[2]) {
                            saved = arrayOf("0.0", "+", saved[2])

                        } else if ("" == saved[0] && "" != saved[1] && "" != saved[2]) {
                            saved = arrayOf("0.0", saved[1], saved[2])
                        }

                        val total = calculate(operation())

                        println(total)
                        result!!.text = total
                        saved[0] = total
                        saved[2] = ""
                    }
                }
            }
            println(Arrays.toString(saved))
        }
    }
}
