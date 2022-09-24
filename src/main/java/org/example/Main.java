package org.example;

import org.example.view.GUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::createAndShowGui);
    }
}