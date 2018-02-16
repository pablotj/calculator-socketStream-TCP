package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import static javax.swing.SwingConstants.RIGHT;

/**
 *
 * @author Pablo
 */
public final class Interface extends JFrame {
    private static ClientTCP client;
    private final String TITLE = "CALCULATOR";
    String [] calculo;
    Boolean what = false; // true-> Operando 1 && false -> Operando 2
    
    private JLabel result;
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
                    btnAddition, btnSubstract, btnDivision, btnMultiplication,
                    btnAC, btnPoint, btnEqual;
    
    public Interface(){
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
	}
        
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200,350);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setTitle(TITLE);
        
        calculo = new String[] {"","",""};
        what = false;
    }
            
    public void init(){
        
        // ---- ROW 0 -->> [result] ----//
        result = new JLabel("0.0");
        result.setBounds(0, 0, 200, 50);
        result.setForeground(Color.white);
        result.setHorizontalAlignment(RIGHT);
        result.setFont(new Font("Arial", Font.BOLD, 30));
        add(result);
        
        
        // ---- ROW 1 -->> [AC,/] ----//
        btnAC = new JButton("AC");
        btnAC.setBounds(0, 50, 150, 50);
        btnAC.addActionListener(new ButtonListener());
        add(btnAC);
        
        btnDivision = new JButton("/");
        btnDivision.setBounds(150, 50,50, 50);
        btnDivision.addActionListener(new ButtonListener());
        btnDivision.setBackground(java.awt.Color.orange);
        add(btnDivision);
        
        
        // ---- ROW 2 -->> [7,8,9,x] ----//
        btn7 = new JButton("7");
        btn7.setBounds(0, 100,  50, 50);
        btn7.addActionListener(new ButtonListener());
        add(btn7);
        
        btn8 = new JButton("8");
        btn8.setBounds(50, 100,  50, 50);
        btn8.addActionListener(new ButtonListener());
        add(btn8);
        
        btn9 = new JButton("9");
        btn9.setBounds(100, 100, 50, 50);
        btn9.addActionListener(new ButtonListener());
        add(btn9);
        
        btnMultiplication = new JButton("x");
        btnMultiplication.setBounds(150, 100,  50, 50);
        btnMultiplication.addActionListener(new ButtonListener());
        btnMultiplication.setBackground(java.awt.Color.orange);
        add(btnMultiplication);

        
        // ---- ROW 3 -->> [4,5,6,-] ----//
        btn4 = new JButton("4");
        btn4.setBounds(0, 150, 50, 50);
        btn4.addActionListener(new ButtonListener());
        add(btn4);
        
        btn5 = new JButton("5");
        btn5.setBounds(50, 150,  50, 50);
        btn5.addActionListener(new ButtonListener());
        add(btn5);
        
        btn6 = new JButton("6");
        btn6.setBounds(100, 150, 50, 50);
        btn6.addActionListener(new ButtonListener());
        add(btn6);
        
        btnSubstract = new JButton("-");
        btnSubstract.setBounds(150, 150, 50, 50);
        btnSubstract.addActionListener(new ButtonListener());
        btnSubstract.setBackground(java.awt.Color.orange);
        add(btnSubstract);
        
        
        // ---- ROW 4 -->> [1,2,3,+] ----//  
        btn1 = new JButton("1");
        btn1.setBounds(0, 200, 50, 50);
        btn1.addActionListener(new ButtonListener());
        add(btn1);
        
        btn2 = new JButton("2");
        btn2.setBounds(50,200, 50, 50);
        btn2.addActionListener(new ButtonListener());
        add(btn2);
        
        btn3 = new JButton("3");
        btn3.setBounds(100, 200,  50, 50);
        btn3.addActionListener(new ButtonListener());
        add(btn3);
        
        btnAddition = new JButton("+");
        btnAddition.setBounds(150, 200, 50, 50);
        btnAddition.addActionListener(new ButtonListener());
        btnAddition.setBackground(Color.orange);
        add(btnAddition);

        
        // ---- ROW 5 -->> [0, ',' ,=] ----//
        btn0 = new JButton("0");
        btn0.setBounds(0, 250, 100, 50);
        btn0.addActionListener(new ButtonListener());
        add(btn0);
        
        btnPoint = new JButton(",");
        btnPoint.setBounds(100, 250, 50, 50);
        btnPoint.addActionListener(new ButtonListener());
        add(btnPoint);
        
        btnEqual = new JButton("=");
        btnEqual.setBounds(150, 250, 50, 50);
        btnEqual.addActionListener(new ButtonListener());
        btnEqual.setBackground(java.awt.Color.orange);
        add(btnEqual);  
        
    }
    
    class ButtonListener implements ActionListener {

    ButtonListener() {
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String btn = e.getActionCommand();
        if(!"".equals(calculo[1])){
            what=true;
        }
        switch (btn) {
            case "AC":
                result.setText("0.0");
                calculo = new String[] {"","",""};
                what = false;

                break;
                
            case "/":
                calculo[1] = btn;
                what = true;
                result.setText("/");
                
                break;
                
            case "x":
                calculo[1] = btn;
                what = true;
                result.setText("x");
                
                break;
                
            case "-":
                calculo[1] = btn;
                what = true;
                result.setText("-");
                
                break;
            
            case "+":
                calculo[1] = btn;
                what = true;
                result.setText("+");
                
                break;
            case ",":
                
                if(what && !calculo[2].contains(".")){
                    calculo[2] += ".";
                    result.setText(calculo[2]);
                }else if (!what && !calculo[0].contains(".")){
                    calculo[0] += ".";
                    result.setText(calculo[0]);
                }
                break;
            case "=":
                
                if("".equals(calculo[0]) && "".equals(calculo[1]) && "".equals(calculo[2])){
                    calculo = new String []{"0.0","+","0.0"};
                }
                else if(!"".equals(calculo[0]) && "".equals(calculo[1]) && "".equals(calculo[2])){
                    calculo = new String []{calculo[0],"+",calculo[0]};
                }
                else if(!"".equals(calculo[0]) && !"".equals(calculo[0]) && "".equals(calculo[2])){
                    calculo = new String []{calculo[0],calculo[1],calculo[0]};
                }
                else if("".equals(calculo[0]) && "".equals(calculo[1]) && !"".equals(calculo[2])){
                    calculo = new String []{"0.0","+",calculo[2]};
                }
                else if("".equals(calculo[0]) && !"".equals(calculo[1]) && !"".equals(calculo[2])){
                    calculo = new String []{"0.0",calculo[1],calculo[2]};
                }
                
                
                
                String total = calcular(operation());
                
                System.out.println(total); 
                result.setText(total);
                calculo[0] = total;
                calculo[2] = "";
                
               
                
                
                break;

            case "0":
                if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
                
                break;
            case "1":
                if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
                
                break;
            case "2":
               if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
                
                break;
            case "3":
               if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
                
                break;
            case "4":
                if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
                
                break;
            case "5":
                if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
                
                break;
            case "6":
                if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
               
                break;
            case "7":
                if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
              
                break;
            case "8":
                if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
               
                break;
            case "9":
                if(what){
                    calculo[2] += btn;
                    result.setText(calculo[2]);
                }else{
                    calculo[0] += btn;
                    result.setText(calculo[0]);
                }
              
                break;
        }
        System.out.println(Arrays.toString(calculo));
    }

    }

    public String calcular(String op) {
        client = new ClientTCP();
        System.out.println(op.toString());
        return client.comunication(op);
      
    }
    
    public String operation(){
        return calculo[0]+" "+calculo[1]+" "+calculo[2];
    }

}
