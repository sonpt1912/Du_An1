/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPopupMenu popupMenu = new JPopupMenu();
        window.add(popupMenu);
        window.setVisible(true);
    }
}
