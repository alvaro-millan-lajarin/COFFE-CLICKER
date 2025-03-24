package Presenstation.Controller;

import Presenstation.View.Scene;

import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
    private Scene view;

    public final void run() {

        view.apply(mainController.getMainFrame());
    }


}