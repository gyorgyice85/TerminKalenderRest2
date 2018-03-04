package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Font;

public class Login {


    private JFrame frame;
    private JTextField textField;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnRegistration;
    private JButton btnExit;
    private JLabel lblLoginSystems;

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
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(600, 300, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(38, 74, 124, 30);
        frame.getContentPane().add(lblUsername);


        txtUsername = new JTextField();
        txtUsername.setBounds(162, 79, 221, 30);
        frame.getContentPane().add(txtUsername);
        txtUsername.setColumns(10);


        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String username = txtUsername.getText();

                if(username.contains("name")) {

                    txtUsername.setText(null);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Login Details",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                    txtPassword.setText(null);
                    txtUsername.setText(null);
                }
            }
        });
        btnLogin.setBounds(100, 199, 89, 30);
        frame.getContentPane().add(btnLogin);

        btnRegistration = new JButton("Registration");
        btnRegistration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                txtUsername.setText(null);
            }
        });

        btnRegistration.setBounds(200, 199, 120, 30);
        frame.getContentPane().add(btnRegistration);


        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(38, 53, 347, 10);
        frame.getContentPane().add(separator_1);

        lblLoginSystems = new JLabel("Termincalendar");
        lblLoginSystems.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblLoginSystems.setBounds(139, 11, 250, 31);
        frame.getContentPane().add(lblLoginSystems);
    }
}
