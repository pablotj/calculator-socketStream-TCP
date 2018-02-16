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
public class Main {
    private static Threads thread;
    
    public static void main(String[] args) {
        thread = new Threads("SERVER");
        thread.start();
        thread = new Threads("CLIENT");
        thread.start();
     }
}
