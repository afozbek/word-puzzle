package com.wordpuzzle;

import javax.swing.*;
import java.awt.*;

public class KelimeOyunu {
    public KelimeOyunu() {

    }

    public KelimeOyunu(int winPoint, int boardX, int boardY) {
        JFrame oyunAlani = new JFrame("Oyun Alani");
        oyunAlani.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        oyunAlani.pack();
        oyunAlani.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        oyunAlani.setSize(height/2, width/2);
        oyunAlani.setLocationRelativeTo(null);

        JPanel oyunAlaniPaneli = new JPanel();
        oyunAlaniPaneli.setLayout(new FlowLayout());

        oyunAlani.add(oyunAlaniPaneli);

        oyunAlaniPaneli.add(new JButton("Helloo"));

        oyunAlani.setVisible(true);
    }
}
