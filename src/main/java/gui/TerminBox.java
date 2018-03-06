package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class TerminBox {

    private JFrame frame;
    private JTextField textField, textField_0;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TerminBox window = new TerminBox();
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
    public TerminBox() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 730, 489);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setBounds(128, 28, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblFrom = new JLabel("From");
        lblFrom.setBounds(65, 31, 46, 14);
        frame.getContentPane().add(lblFrom);

        JLabel lblUntil = new JLabel("Until");
        lblUntil.setBounds(230, 31, 46, 14);
        frame.getContentPane().add(lblUntil);

        textField_0 = new JTextField();
        textField_0.setBounds(295, 28, 86, 20);
        frame.getContentPane().add(textField_0);


        JLabel lblPhone = new JLabel("Where");
        lblPhone.setBounds(65, 68, 46, 14);
        frame.getContentPane().add(lblPhone);

        textField_1 = new JTextField();
        textField_1.setBounds(128, 65, 86, 20);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblEmailId = new JLabel("Invitees");
        lblEmailId.setBounds(65, 115, 46, 14);
        frame.getContentPane().add(lblEmailId);

        textField_2 = new JTextField();
        textField_2.setBounds(128, 112, 247, 17);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(65, 162, 46, 14);
        frame.getContentPane().add(lblAddress);

        final JTextArea textArea_1 = new JTextArea();
        textArea_1.setBounds(126, 157, 212, 40);
        frame.getContentPane().add(textArea_1);



        JButton btnClear = new JButton("Cancel");

        btnClear.setBounds(312, 320, 89, 23);
        frame.getContentPane().add(btnClear);

        JLabel lblOccupation = new JLabel("Show As");
        lblOccupation.setBounds(65, 220, 67, 14);
        frame.getContentPane().add(lblOccupation);

        final JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("Select");
        comboBox.addItem("Busy");
        comboBox.addItem("Free");
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        comboBox.setBounds(180, 220, 91, 20);
        frame.getContentPane().add(comboBox);


        JButton btnSubmit = new JButton("submit");

        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setForeground(Color.MAGENTA);
        btnSubmit.setBounds(65, 320, 89, 23);
        frame.getContentPane().add(btnSubmit);


        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(textField.getText().isEmpty() ||(textField_0.getText().isEmpty())||(textField_1.getText().isEmpty())||(textField_2.getText().isEmpty())||(textArea_1.getText().isEmpty())||(comboBox.getSelectedItem().equals("Select")))
                    JOptionPane.showMessageDialog(null, "Data Missing");
                else
                    JOptionPane.showMessageDialog(null, "Data Submitted");
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                }
        });

    }
}
