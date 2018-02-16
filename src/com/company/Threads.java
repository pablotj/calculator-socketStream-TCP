/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

/**
 *
 * @author pablo
 */
public class Threads extends Thread{
    
    private Interface ui;
    private ServerTCP server;
    private final String ID;

    public Threads(String id){
        this.ID = id;
    }
    
    public void run(){
        switch(ID){
            case "SERVER":
                server = new ServerTCP();
                server.servir();
                break;
            case "CLIENT":
                ui = new Interface();
                ui.setVisible(true);
                break;
        }
    }
    
}
