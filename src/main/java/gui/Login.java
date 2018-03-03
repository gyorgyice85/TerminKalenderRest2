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
    private JButton btnReset;
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
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(38, 74, 124, 30);
        frame.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(38, 135, 124, 30);
        frame.getContentPane().add(lblPassword);

        textField = new JTextField();
        textField.setBounds(257, 79, 0, 2);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        txtUsername = new JTextField();
        txtUsername.setBounds(162, 79, 221, 30);
        frame.getContentPane().add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(162, 135, 221, 30);
        frame.getContentPane().add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String password=txtPassword.getText();
                String username=txtUsername.getText();

                if(password.contains("password") && username.contains("name")) {
                    txtPassword.setText(null);
                    txtUsername.setText(null);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
                    txtPassword.setText(null);
                    txtUsername.setText(null);
                }
            }
        });
        btnLogin.setBounds(39, 199, 89, 30);
        frame.getContentPane().add(btnLogin);

        btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                txtUsername.setText(null);
                txtPassword.setText(null);
            }
        });
        btnReset.setBounds(168, 199, 89, 30);
        frame.getContentPane().add(btnReset);

        btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame =new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Login Systems" ,
                        JOptionPane.YES_NO_CANCEL_OPTION)== JOptionPane.YES_NO_CANCEL_OPTION){
                    System.exit(0);
                }
            }
        });
        btnExit.setBounds(295, 199, 89, 30);
        frame.getContentPane().add(btnExit);

        JSeparator separator = new JSeparator();
        separator.setBounds(38, 186, 347, 2);
        frame.getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(38, 53, 347, 10);
        frame.getContentPane().add(separator_1);

        lblLoginSystems = new JLabel("Login Systems");
        lblLoginSystems.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblLoginSystems.setBounds(139, 11, 124, 31);
        frame.getContentPane().add(lblLoginSystems);
    }
}
