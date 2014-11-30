package com.github.expertsystem;

import com.github.expertsystem.controller.AppController;
import com.github.expertsystem.view.MainForm;

public class MainClass {

    public static void main(String[] args) {

        new MainForm("Expert System", new AppController());

    }

}
