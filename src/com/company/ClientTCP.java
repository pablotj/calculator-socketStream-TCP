/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ClientTCP {
    private Socket clienteSocket;
    private InetSocketAddress addr;
    private InputStream in;
    private OutputStream out;
    //private int datos;
    
    public void connect(){
        clienteSocket = new Socket();
        addr = new InetSocketAddress("localhost", 55555);
        
        try{
            clienteSocket.connect(addr);
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex, "INTERNAL ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }
    
   public String comunication(String operation){
        connect();
       
        String result[] = null;
        try {
            //Enviamos la operacion al servidor
            out = clienteSocket.getOutputStream();
            out.write(operation.getBytes());
            
            //Recibimos la respuesta del servidor
            in = clienteSocket.getInputStream();
            byte[] resultBytes=new byte[25];
            in.read(resultBytes);
            
            result = new String(resultBytes).split(" ");
            
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
            close();
           
        }
        close();
        return result[0];


   }
   
   public void close(){
        try {
            clienteSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
