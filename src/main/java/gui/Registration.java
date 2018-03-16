/**
 * Eridho Buffery Rollian
 *
 */
package gui;

import data.Nutzer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration {

        static JFrame frame;
        static JTextField textField_1;
        static JTextField textField_2;
        private static JLabel lblRegistrationSystems;

    private ClientSession cs;

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Registration window = new Registration(new ClientSession());
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
        public Registration(ClientSession cs) {
            this.cs = cs;
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

            JLabel lblNachname = new JLabel("Last name:");
            lblNachname.setBounds(150, 120, 100, 30);
            frame.getContentPane().add(lblNachname);

            textField_1 = new JTextField();
            textField_1.setBounds(250, 120, 300, 30);
            frame.getContentPane().add(textField_1);
            textField_1.setColumns(10);

            JLabel lblVorname = new JLabel("First name:");
            lblVorname.setBounds(150, 220, 100, 30);
            frame.getContentPane().add(lblVorname);

            textField_2 = new JTextField();
            textField_2.setBounds(250, 220, 300, 30);
            frame.getContentPane().add(textField_2);
            textField_2.setColumns(10);

            JButton btnClear = new JButton("Clear");

            btnClear.setBounds(400, 350, 89, 23);
            frame.getContentPane().add(btnClear);

            JButton btnSubmit = new JButton("Submit");
            btnSubmit.setBounds(250, 350, 89, 23);
            frame.getContentPane().add(btnSubmit);


            btnSubmit.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent arg0) {
                    if(textField_1.getText().isEmpty()||(textField_2.getText().isEmpty()))
                        JOptionPane.showMessageDialog(null, "Data Missing");
                    else {
                        Boolean validName = true;

                        String nachName = textField_1.getText();
                        String vorName = textField_2.getText();

                        // validate name
                        if (nachName == null || nachName.isEmpty()) {
                            validName = false;
                        }

                        // validate name
                        if (vorName == null || vorName.isEmpty()) {
                            validName = false;
                        }

                        if (validName) {
                            Nutzer nutzer = new Nutzer(vorName, nachName);
                            nutzer = cs.nutzerHandle.create(nutzer);
                            cs.nutzer = nutzer;
                            cs.nutzerID = nutzer.getId();

                            JOptionPane.showMessageDialog(null, "Data Submitted");
                            MainFrame window = new MainFrame(cs);
                            window.setVisible(true);
                            frame.dispose();
                        }
                    }
                }
            });

            btnClear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField_2.setText(null);
                    textField_1.setText(null);
                }
            });

            JSeparator separator_1 = new JSeparator();
            separator_1.setBounds(38, 73, 700, 10);
            frame.getContentPane().add(separator_1);

            lblRegistrationSystems = new JLabel("Registration");
            lblRegistrationSystems.setFont(new Font("Tahoma", Font.BOLD, 20));
            lblRegistrationSystems.setBounds(320, 20, 700, 31);
            frame.getContentPane().add(lblRegistrationSystems);

        }
}
