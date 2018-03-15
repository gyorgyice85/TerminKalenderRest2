package gui;

import data.Nutzer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Login {


    private JFrame frame;

    private JButton btnRegistration;
    private JLabel lblLoginSystems;

    private ClientSession cs;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Login() {

        // create the client session
        cs = new ClientSession();

        // initialize GUI
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {

        frame = new JFrame();
        frame.setBounds(800, 500, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel lblUsername = new JLabel("Please select your name to login: ");
        lblUsername.setBounds(270, 105, 400, 80);
        frame.getContentPane().add(lblUsername);

        List<Nutzer> nutzers = cs.nutzerHandle.findAll();
        final JComboBox<Nutzer> comboBox = new JComboBox<>();

        for (Nutzer nutzer : nutzers) {
                comboBox.addItem(nutzer);
        }

        comboBox.setBounds(200, 220, 400, 30);
        frame.getContentPane().add(comboBox);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if (comboBox.getSelectedItem().equals("Select")/*txtUsername.getText().isEmpty()*/) {
                    JOptionPane.showMessageDialog(null, "Invalid Login Details",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    Nutzer eingeloggteNutzer = (Nutzer)comboBox.getSelectedItem();
                    cs.nutzer = eingeloggteNutzer;
                    cs.nutzerID = eingeloggteNutzer.getId();

                    MainFrame window = new MainFrame(cs);

                    window.setVisible(true);
                    frame.dispose();
                }
            }
        });
        btnLogin.setBounds(250, 350, 89, 30);
        frame.getContentPane().add(btnLogin);

        btnRegistration = new JButton("Registration");
        btnRegistration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Registration window = new Registration(cs);
                window.frame.setVisible(true);
                frame.dispose();
            }
        });

        btnRegistration.setBounds(400, 350, 120, 30);
        frame.getContentPane().add(btnRegistration);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(38, 73, 700, 10);
        frame.getContentPane().add(separator_1);

        lblLoginSystems = new JLabel("Welcome to Your Calender");
        lblLoginSystems.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblLoginSystems.setBounds(230, 18, 700, 31);
        frame.getContentPane().add(lblLoginSystems);
    }
}
