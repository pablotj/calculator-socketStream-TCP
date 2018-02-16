package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerTCP {
    
    private static final String ADDRES = "localhost"; 
    private static final int PORT = 55555; 
    private static InetSocketAddress addr;
    private static InputStream in;
    private static OutputStream out;
    private float result;
    
    
    public static void servir() {

        while(true){
        String [] mensaje;
        try {

            try (ServerSocket serverSocket = new ServerSocket()) {
                
                addr = new InetSocketAddress(ADDRES, PORT);
                serverSocket.bind(addr);

                try (Socket newSocket = serverSocket.accept()) {

                    in = newSocket.getInputStream();
                    out = newSocket.getOutputStream();
                    
                    byte[] inBytes = new byte[25];
                    in.read(inBytes);
                    mensaje = new String(inBytes).split(" ");
                    
                    float numero1 = Float.parseFloat(mensaje[0]);
                    String operador = mensaje[1];
                    float numero2 = Float.parseFloat(mensaje[2]);
                    
                    switch(operador){
                        case "+": 
                            out.write(String.valueOf(sumar(numero1,numero2)+" ").getBytes());
                        break;
                        case "-": 
                            out.write(String.valueOf(restar(numero1,numero2)+" ").getBytes());
                        break;
                        case "/": 
                            out.write(String.valueOf(dividir(numero1,numero2)+" ").getBytes());
                        break;
                        case "x": 
                            out.write(String.valueOf(multiplicar(numero1,numero2)+" ").getBytes());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al procesar la solicitud");
        }}
    }
    
    public static Float sumar(float n1, float n2){
        return (n1+n2);
    } 
    
   public static Float restar(float n1, float n2){
        return (n1-n2);
    } 
   public static Float dividir(float n1, float n2){
        return (n1/n2);
    } 
   public static Float multiplicar(float n1, float n2){
        return (n1*n2);
    } 
        
    

}
