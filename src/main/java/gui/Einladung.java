package gui;

import client.NutzerHandle;
import data.Nutzer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Einladung {
    static JFrame frame;
    static JTextField textField_1;
    static JTextField textField_2;
    static JTextField textField_3;
    private static JLabel lblRegistrationSystems;

    static Color accent = new Color(0, 188, 212);
    static Color bg = new Color(96, 125, 139);

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Einladung window = new Einladung();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Font loadFont(String name, float size){
        InputStream is = Einladung.class.getResourceAsStream("/resources/"+name);
        Font title = null;
        try {
            title = Font.createFont(Font.TRUETYPE_FONT, is);
            title = title.deriveFont(size);
        } catch (FontFormatException ex) {ex.printStackTrace();
        }
        catch (IOException ex){ex.printStackTrace();}
        return title;
    }

    /**
     * Create the application.
     */
    public Einladung() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public static void initialize() {
        frame = new JFrame();
        frame.setBounds(800, 500, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        JLabel lblWer = new JLabel("Wer");
        lblWer.setBounds(150, 120, 100, 30);
        frame.getContentPane().add(lblWer);

        textField_1 = new JTextField();
        textField_1.setBounds(250, 120, 300, 30);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblWen = new JLabel("Wen");
        lblWen.setBounds(150, 180, 100, 30);
        frame.getContentPane().add(lblWen);

        textField_2 = new JTextField();
        textField_2.setBounds(250, 180, 300, 30);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblTermin = new JLabel("Termin");
        lblTermin.setBounds(150, 240, 100, 30);
        frame.getContentPane().add(lblTermin);

        textField_3 = new JTextField();
        textField_3.setBounds(250, 240, 300, 30);
        frame.getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(600, 350, 89, 23);
        frame.getContentPane().add(btnClear);

        JButton btnSubmit = new JButton("Submit");
        //btnSubmit.setBackground(Color.BLACK);
        //btnSubmit.setForeground(Color.GRAY);
        btnSubmit.setBounds(450, 350, 89, 23);
        frame.getContentPane().add(btnSubmit);

        JButton btnAnnehmen = new JButton("Annehmen");
        btnAnnehmen.setBounds(280, 350, 100, 23);
        frame.getContentPane().add(btnAnnehmen);

        JButton btnAblehnen = new JButton("Ablehnen");
        btnAblehnen.setBounds(100, 350, 100, 23);
        frame.getContentPane().add(btnAblehnen);


        btnAnnehmen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                textField_3.setText(null);
                textField_2.setText(null);
                textField_1.setText(null);
            }
        });

        btnAblehnen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                textField_3.setText(null);
                textField_2.setText(null);
                textField_1.setText(null);
            }
        });


        btnSubmit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                if(textField_1.getText().isEmpty()||(textField_2.getText().isEmpty()))
                    JOptionPane.showMessageDialog(null, "Data Missing");
                else {
                    Boolean validWer = true;
                    Boolean validWen = true;
                    Boolean validTermin = true;

                    String wen = textField_1.getText();
                    String wer = textField_2.getText();
                    String termin = textField_3.getText();

                    // validate name
                    if (wer == null || wer.isEmpty()) {
                        validWer = false;
                    }

                    // validate name
                    if (wen == null || wen.isEmpty()) {
                        validWen = false;
                    }

                    // validate name
                    if (termin == null || termin.isEmpty()) {
                        validTermin = false;
                    }

                    if (validWer) {
                       /* Nutzer nutzer = new Nutzer(wer, wen);
                        NutzerHandle nutzerHandler = new NutzerHandle();
                        nutzerHandler.create(nutzer);*/

                        JOptionPane.showMessageDialog(null, "Data Submitted");
                        MainFrame window = new MainFrame();
                        window.setVisible(true);
                    }
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_3.setText(null);
                textField_2.setText(null);
                textField_1.setText(null);
            }
        });

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(38, 73, 700, 10);
        frame.getContentPane().add(separator_1);

        lblRegistrationSystems = new JLabel("Einladung");
        lblRegistrationSystems.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblRegistrationSystems.setBounds(320, 20, 700, 31);
        frame.getContentPane().add(lblRegistrationSystems);

    }
}
