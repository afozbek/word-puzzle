package com.wordpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class KelimeOyunuBoard extends JFrame{

    private JPanel mainGamePanel = new JPanel();
    // OYUN KURMA
    private JTextField txt_kazanma_puani = new JTextField(8);
    private JTextField txt_alan_x = new JTextField(5);
    private JTextField txt_alan_y = new JTextField(5);
    private JTextField txt_port_kur = new JTextField(8);

    // OYUNA KATILMA
    private JTextField txt_ip = new JTextField(20);
    private JTextField txt_nickname = new JTextField(15);
    private JTextField txt_port_katil = new JTextField(8);

    // LABELS
    private  JLabel lbl_kurulum = new JLabel("Oyun Kurun:");
    private  JLabel lbl_alan_x = new JLabel("Oyun Alani X:" );
    private  JLabel lbl_alan_y = new JLabel("Oyun Alani Y:" );
    private  JLabel lbl_kazanma_puani = new JLabel("Kazanma puani:" );
    private  JLabel lbl_port_kur = new JLabel("Port" );

    private  JLabel lbl_oyuna_katil = new JLabel("Oyuna Katil" );
    private  JLabel lbl_ip = new JLabel("IP:" );
    private  JLabel lbl_nickname = new JLabel("Kullanici Adi:" );
    private  JLabel lbl_port_katil = new JLabel("Port" );

    // ACTION BUTTONS
    private  JButton oyun_kur;
    private  JButton oyuna_katil ;

    private EchoNIOServer echoNIOServer;
    public KelimeOyunuBoard()
    {
        super("Kelime Oyunu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        oyun_kur = new JButton("Oyun Kur");
        oyuna_katil = new JButton("Oyuna Katil");

        mainGamePanel.setLayout(new FlowLayout());
        mainGamePanel.setSize(500,200);
        add(mainGamePanel);

        mainGamePanel.add(lbl_kurulum);
        mainGamePanel.add(lbl_alan_x);
        mainGamePanel.add(txt_alan_x);
        mainGamePanel.add(lbl_alan_y);
        mainGamePanel.add(txt_alan_y);
        mainGamePanel.add(lbl_kazanma_puani);
        mainGamePanel.add(txt_kazanma_puani);
        mainGamePanel.add(lbl_port_kur);
        mainGamePanel.add(txt_port_kur);

        mainGamePanel.add(lbl_oyuna_katil);
        mainGamePanel.add(lbl_ip);
        mainGamePanel.add(txt_ip);
        mainGamePanel.add(lbl_nickname);
        mainGamePanel.add(txt_nickname);
        mainGamePanel.add(lbl_port_katil);
        mainGamePanel.add(txt_port_katil);

        mainGamePanel.add(oyun_kur);
        mainGamePanel.add(oyuna_katil);

        oyun_kur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGameHandler();
            }
        });
        oyuna_katil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinGameHandler();
            }
        });

        pack();
        setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(height/2, width/2);
        setLocationRelativeTo(null);
    }

    public void notifyUser(String message) {
        JOptionPane.showMessageDialog(mainGamePanel, message);
    }

    public void createGameHandler() {
        System.out.println("CREATING THE GAME");
        try {
//            int boardX = Integer.parseInt(txt_alan_x.getText());
//            int boardY = Integer.parseInt(txt_alan_y.getText());
//            int winPoint = Integer.parseInt(txt_kazanma_puani.getText());
            int port = Integer.parseInt(txt_port_kur.getText());


        } catch (NumberFormatException ex) {
            notifyUser("Beklenmedik bir hata oluştu 🤴");
        }
    }

    public void joinGameHandler() {
        System.out.println("JOINING THE GAME");
    }

    public static void main(String[] args) {
        KelimeOyunuBoard ko = new KelimeOyunuBoard();
    }
}