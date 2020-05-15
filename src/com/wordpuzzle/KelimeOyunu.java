package com.wordpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KelimeOyunu extends JFrame{

    private JTextField txt_alan_x = new JTextField(5);
    private JTextField txt_alan_y = new JTextField(5);
    private JTextField txt_bolge_sayisi = new JTextField(8);
    private JTextField txt_kazanma_puani = new JTextField(8);
    private JTextField txt_toplam_oyun = new JTextField(8);
    private JTextField txt_sayi_2x = new JTextField(8);
    private JTextField txt_sayi_3x = new JTextField(8);
    private JTextField txt_port_kur = new JTextField(8);

    private JTextField txt_ip = new JTextField(20);
    private JTextField txt_nickname = new JTextField(15);
    private JTextField txt_port_katil = new JTextField(8);

    private  JLabel lbl_kurulum = new JLabel("Oyun Kurun:");
    private  JLabel lbl_alan_x = new JLabel("Oyun Alani X:" );
    private  JLabel lbl_alan_y = new JLabel("Oyun Alani Y:" );
    private  JLabel lbl_bolge_sayisi = new JLabel("Kullanilmaz Bolge Sayis:" );
    private  JLabel lbl_kazanma_puani = new JLabel("Kazanma puani:" );
    private  JLabel lbl_toplam_oyun = new JLabel("Toplam Oyun:" );
    private  JLabel lbl_sayi_2x = new JLabel("2x Sayisi:" );
    private  JLabel lbl_sayi_3x = new JLabel("3x Sayisi:" );
    private  JLabel lbl_port_kur = new JLabel("Port" );

    private  JLabel lbl_oyuna_katil = new JLabel("Oyuna Katil" );
    private  JLabel lbl_ip = new JLabel("IP:" );
    private  JLabel lbl_nickname = new JLabel("Kullanici Adi:" );
    private  JLabel lbl_port_katil = new JLabel("Port" );

    private  JButton oyun_kur;
    private  JButton oyuna_katil ;

    public KelimeOyunu()
    {
        super("Kelime Oyunu");
        setLayout(new FlowLayout());
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        oyun_kur = new JButton("Oyun Kur");
        oyuna_katil = new JButton("Oyuna Katil");

        add(BorderLayout.WEST, lbl_kurulum);
        add(lbl_alan_x);
        add(txt_alan_x);
        add(lbl_alan_y);
        add(txt_alan_y);
        add(lbl_bolge_sayisi);
        add(txt_bolge_sayisi);
        add(lbl_kazanma_puani);
        add(txt_kazanma_puani);
        add(lbl_toplam_oyun);
        add(txt_toplam_oyun);
        add(lbl_sayi_2x);
        add(txt_sayi_2x);
        add(lbl_sayi_3x);
        add(txt_sayi_3x);
        add(lbl_port_kur);
        add(txt_port_kur);

        add(lbl_oyuna_katil);
        add(lbl_ip);
        add(txt_ip);
        add(lbl_nickname);
        add(txt_nickname);
        add(lbl_port_katil);
        add(txt_port_katil);

        add(oyun_kur);
        add(oyuna_katil);

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

        setVisible(true);

    }

    public void createGameHandler() {
        // TODO:
        System.out.println("CREATING THE GAME");
    }

    public void joinGameHandler() {
        // TODO:
        System.out.println("JOINING THE GAME");
    }

//    public void actionPerformed(ActionEvent e){
//
//        String btn = e.getActionCommand();
//
//        if(btn.equals("Oyun Kur")){
//
//            int alan_x = Integer.parseInt(txt_alan_x.getText());
//            int alan_y = Integer.parseInt(txt_alan_y.getText());
//            int bolge_sayisi = Integer.parseInt(txt_bolge_sayisi.getText());
//            int kazanma_puani = Integer.parseInt(txt_kazanma_puani.getText());
//            int toplam_oyun = Integer.parseInt(txt_toplam_oyun.getText());
//            int sayi_2x = Integer.parseInt(txt_sayi_2x.getText());
//            int sayi_3x = Integer.parseInt(txt_sayi_3x.getText());
//            int port_kur = Integer.parseInt(txt_port_kur.getText());
//
//
//
//        }
//
//        if(btn.equals("Oyuna Katil")){
//
//            String ip =  txt_ip.getText();
//            String nickname = txt_nickname.getText();
//            int port_katil = Integer.parseInt(txt_port_katil.getText());
//
//
//        }
//    }

    public static void main(String[] args) {

        KelimeOyunu ko = new KelimeOyunu();

    }
}