package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Invitation {
    static  JFrame frame;
    static  JLabel lblInvitation;
    static  JLabel lblFrom;
    static  JLabel lblName;
    static  JLabel lblPlace;
    static  JLabel lblStartTime;
    static  JLabel lblEndTime;
    static  JButton btnAccept;
    static  JButton btnExit;
    static  JButton btnDecline;
    static  JLabel lblGetFrom;
    static  JLabel lblGetName;
    static  JLabel lblGetPlace;
    static  JLabel lblGetStartTime;
    static  JLabel lblGetEndTime;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Invitation window = new Invitation();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Invitation() {
        initialize();

    }

    public static void initialize() {
        frame = new JFrame();
        frame.setBounds(800, 500, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblFrom = new JLabel("From: ");
        lblFrom.setBounds(150, 120, 100, 30);
        frame.getContentPane().add(lblFrom);

        lblGetFrom = new JLabel("getFrom()");
        lblGetFrom.setOpaque(true);
        lblGetFrom.setBackground(new Color(176,196,222));
        lblGetFrom.setBounds(300, 120, 300, 30);
        frame.getContentPane().add(lblGetFrom);

        lblName = new JLabel("Name: ");
        lblName.setBounds(150, 160, 100, 30);
        frame.getContentPane().add(lblName);

        lblGetName = new JLabel("getName()");
        lblGetName.setOpaque(true);
        lblGetName.setBackground(new Color(176,196,222));
        lblGetName.setBounds(300, 160, 300, 30);
        frame.getContentPane().add(lblGetName);

        lblPlace = new JLabel("Place: ");
        lblPlace.setBounds(150, 200, 100, 30);
        frame.getContentPane().add(lblPlace);

        lblGetPlace = new JLabel("getPlace()");
        lblGetPlace.setOpaque(true);
        lblGetPlace.setBackground(new Color(176,196,222));
        lblGetPlace.setBounds(300, 200, 300, 30);
        frame.getContentPane().add(lblGetPlace);

        lblStartTime = new JLabel("Start Time: ");
        lblStartTime.setBounds(150, 240, 100, 30);
        frame.getContentPane().add(lblStartTime);

        lblGetStartTime = new JLabel("getStartTime()");
        lblGetStartTime.setOpaque(true);
        lblGetStartTime.setBackground(new Color(176,196,222));
        lblGetStartTime.setBounds(300, 240, 300, 30);
        frame.getContentPane().add(lblGetStartTime);

        lblEndTime = new JLabel("End Time: ");
        lblEndTime.setBounds(150, 280, 100, 30);
        frame.getContentPane().add(lblEndTime);

        lblGetEndTime = new JLabel("getEndTime()");
        lblGetEndTime.setOpaque(true);
        lblGetEndTime.setBackground(new Color(176,196,222));
        lblGetEndTime.setBounds(300, 280, 300, 30);
        frame.getContentPane().add(lblGetEndTime);

        btnExit = new JButton("Exit");
        btnExit.setBounds(550, 380, 89, 23);
        frame.getContentPane().add(btnExit);

        btnAccept = new JButton("Accept");
        btnAccept.setBounds(150, 380, 100, 23);
        frame.getContentPane().add(btnAccept);

        btnDecline = new JButton("Decline");
        btnDecline.setBounds(350, 380, 100, 23);
        frame.getContentPane().add(btnDecline);

        btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnDecline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(38, 73, 700, 10);
        frame.getContentPane().add(separator_1);

        lblInvitation = new JLabel("Invitation");
        lblInvitation.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblInvitation.setBounds(320, 20, 700, 31);
        frame.getContentPane().add(lblInvitation);
    }


}
