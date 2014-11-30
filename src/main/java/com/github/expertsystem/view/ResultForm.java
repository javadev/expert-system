package com.github.expertsystem.view;

import com.github.expertsystem.BufferedInputFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Form shows description of calculation's process, that is reading from file
 */
public class ResultForm extends JFrame {

    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTextArea textArea;

    public ResultForm(String title, String filename) {
        super(title);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
        pack();
        setBounds(0, 0, 900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        printFromFile(filename);
    }

    private void printFromFile(String filename) {
        BufferedInputFile bufferedInputFile = new BufferedInputFile();
        try{
            textArea.setText(bufferedInputFile.read(filename));
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }

}
