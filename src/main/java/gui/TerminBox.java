package gui;

import javafx.scene.control.DatePicker;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;

public class TerminBox {

    private JFrame frame;
    private JTextField textField, textField_0;
    private JTextField textField_1;
    private JTextField textField_2;

    int month = java.util.Calendar.getInstance().get(Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(Calendar.YEAR);

    JLabel label = new JLabel("", JLabel.CENTER);

    String day = "";
    JDialog d;
    JButton[] button = new JButton[49];



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

    public TerminBox(JFrame parent) {
        d = new JDialog();
        d.setModal(true);

        String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

        JPanel p1 = new JPanel(new GridLayout(7,7));
        p1.setPreferredSize(new Dimension(430, 120));


        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.white);

            if (x > 6)
            button[x].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    day = button[selection].getActionCommand();
                    d.dispose();
                }
            });

            if (x < 7) {
                button[x].setText(header[x]);

                button[x].setForeground(Color.red);

            }
            p1.add(button[x]);
        }

    JPanel p2 = new JPanel(new GridLayout(1, 3));
    JButton previous = new JButton("<<");

        previous.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            month++;
            displayDate();
        }
    });
        JButton next = new JButton(">>");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month--;
                displayDate();
            }
        });

        p2.add(previous);
        p2.add(next);

        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();

        d.setLocationRelativeTo(parent);
        displayDate();

        d.setVisible(true);

}


    public void displayDate() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
            java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("MMMM yyyy");

            java.util.Calendar calendar = java.util.Calendar.getInstance();

            calendar.set(year,month,1);

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
            button[x].setText("" + day);

            label.setText(simpleDateFormat.format(calendar.getTime()));
            d.setTitle("Date Picker");


    }

    public String setPickedDate() {
        if (day.equals(""))
            return day;

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "dd-MM-yyyy");

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
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

        final JTextField text = new JTextField();

        textField = new JTextField();
        textField.setBounds(128, 28, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblFrom = new JLabel("From");
        lblFrom.setBounds(65, 31, 46, 14);
        frame.getContentPane().add(lblFrom);

        JButton btnDate1 = new JButton("...");
        btnDate1.setBounds(220, 30, 20, 14);
        frame.getContentPane().add(btnDate1);

        JLabel lblUntil = new JLabel("Until");
        lblUntil.setBounds(280, 31, 46, 14);
        frame.getContentPane().add(lblUntil);

        textField_0 = new JTextField();
        textField_0.setBounds(345, 28, 86, 20);
        frame.getContentPane().add(textField_0);

        JButton btnDate2 = new JButton("...");
        btnDate2.setBounds(440, 30, 20, 14);
        frame.getContentPane().add(btnDate2);


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
                if (textField.getText().isEmpty() || (textField_0.getText().isEmpty()) || (textField_1.getText().isEmpty()) || (textField_2.getText().isEmpty()) || (textArea_1.getText().isEmpty()) || (comboBox.getSelectedItem().equals("Select")))
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

        btnDate1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                textField.setText(new TerminBox(frame).setPickedDate());
            }
        });

        btnDate2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                textField_0.setText(new TerminBox(frame).setPickedDate());
            }
        });


    }


}





