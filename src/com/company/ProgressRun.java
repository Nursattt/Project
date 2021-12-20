package com.company;

import authorization.AuthMenu;

import javax.swing.*;
import java.awt.*;

public class ProgressRun extends JFrame {
    static JProgressBar b;
    static JFrame f;
    public ProgressRun() {
        f = new JFrame("TECHNODOM");

        // create a panel
        JPanel p = new JPanel();

        // create a progressbar
        b = new JProgressBar();
        // set initial value
        b.setValue(0);

        b.setStringPainted(true);

        // add progressbar

        p.add(b);

        // add panel
        f.add(p);

        // set the size of the frame
        f.setSize(300, 150);
        f.setVisible(true);

        fill();
        f.setVisible(false);
        AuthMenu authFrame = new AuthMenu();
        authFrame.setVisible(true);
    }

    public static void fill()
    {
        int i = 0;
        try {
            while (i <= 100) {
                // fill the menu bar
                b.setValue(i);

                // delay the thread
                Thread.sleep(10);
                i += 1;
            }
        }
        catch (Exception e) {
        }
    }
}
