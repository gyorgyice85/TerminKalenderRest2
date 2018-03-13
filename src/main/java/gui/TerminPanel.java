package gui;

import client.NutzerHandle;
import client.TerminHandle;
import data.Nutzer;
import data.Termin;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The <code>AppointmentPanel</code> ensures the panel of the <code>AppointmentFrame</code>.
 * Its shows the option to add an appointment and is placed within <code>AppointmentFrame</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see TerminFrame
 */
public class TerminPanel extends JPanel {
    private Date date;
    private JFrame appointmentFrame;
    private CalendarPanel calendarPanel;
    private JTextField nameTextField, placeTextField, startTimeTextField, endTimeTextField, inviteTextField;
    private JComboBox<Nutzer> inviteComboBox;
    private NutzerHandle nutzerHandle;


    /**
     * Constructor. Sets the global variables and calls the draw method.
     * @param month the month of the clicked daypanel
     * @param day the day of the clicked daypanel
     * @param year the year of the clicked daypanel
     * @param calendarPanel the calendarpanel the clicked daypabel is part of, to have access to its (parents) methods
     */
    public TerminPanel(Integer month, Integer day, Integer year, CalendarPanel calendarPanel, JFrame appointmentFrame) {
        this.calendarPanel = calendarPanel;
        this.appointmentFrame = appointmentFrame;
        this.date = calendarPanel.mainPanel.mainFrame.calendar.getDate(month, day, year);

        drawAppointmentPanel();
    }

    /**
     * Draws the appointment panel.
     */
    public void drawAppointmentPanel() {
        setLayout(new SpringLayout());
        String[] labels = {"Name", "Place", "Start Time", "End Time", "Invite", ""};
        int numPairs = labels.length;

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(200,40));
        saveButton.addActionListener(new saveAppointmentHandler());

        ArrayList<JTextField> textFieldList = listTextFields();

       /* List<Nutzer> nutzers = nutzerHandle.findAll();
        inviteComboBox = new JComboBox<>();
        for (Integer i = 0; i < nutzers.size(); i++) {
            nutzerHandle.findAll();
            Nutzer nutzer = nutzers.get(i);
            inviteComboBox.addItem(nutzer);
        }
*/
        // fill the panel
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            add(l);
            if (i+1 < numPairs) {
                add(textFieldList.get(i));
            //} else if (i == (numPairs-1)) {
                //add(inviteComboBox);
            } else {
                add(saveButton);
            }
        }

        // lay out the panel
        SpringUtilities.makeCompactGrid(this,
                numPairs, 2, //rows, cols
                20, 20, //initX, initY
                20, 20 //xPad, yPad
        );
    }

    /**
     * List the textfields for use with the for-loop in <code>drawAppointments</code>.
     * @return ArrayList of textfields
     */
    private ArrayList<JTextField> listTextFields() {
        ArrayList<JTextField> textFieldList  = new ArrayList<>();
        textFieldList.add(nameTextField = new JTextField());
        textFieldList.add(placeTextField = new JTextField());
        textFieldList.add(startTimeTextField = new JTextField());
        textFieldList.add(endTimeTextField = new JTextField());
        textFieldList.add(inviteTextField = new JTextField());

        return textFieldList;
    }

    /**
     * Shows an message dialog when the name of an event isn't filled in.
     */
    private void showNameError() {
        JOptionPane.showMessageDialog(null, "The name of the event must be filled in.", "Invalid name", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show an message dialog when the filled in times aren't valid.
     */
    private void showTimeError() {
        JOptionPane.showMessageDialog(null,
                "The start time or end time are invalid.\n" +
                        "Allowed format: JJJJ-MM-TT HH:MM:SS.\n" +
                        "End time must be greater than start time.", "Invalid times",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows an message dialog when an event is succesfully added.
     * @param name the name of the event.
     */
    private void showSuccesMessage(String name) {
        JOptionPane.showMessageDialog(null, "Your event \""+name+"\" is succesfully added.", "Event added", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Inner class. Triggers an actionlistener when the <code>addAppointmentButton</code> is clicked.
     */
    class saveAppointmentHandler implements ActionListener {
        /**
         * Opens new frame where a new appointment can be added.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            Boolean validName = true;
            Boolean validTimes = true;

            // get values
            String name = nameTextField.getText();
            String place = placeTextField.getText();
            Timestamp startTime = Timestamp.valueOf(startTimeTextField.getText()); // remove whitespace
            Timestamp endTime = Timestamp.valueOf(endTimeTextField.getText());

            // validate place
            if (place.isEmpty()) { place = null; }

            // validate name
            if (name == null || name.isEmpty()) {
                validName = false;
            }

            if (validTimes) {
                // is end time greater then start time
                if (startTime.after(endTime)) {
                    validTimes = false;
                }
            }


            if (validName && validTimes) {
                // add appointment
                Termin termin = new Termin(name, place, startTime, endTime);
                TerminHandle terminHandle = new TerminHandle();
                terminHandle.create(termin);
                // close frame
                appointmentFrame.setVisible(false);
                appointmentFrame.dispose();
                // repaint panels and show succes message
                calendarPanel.monthPanel.redrawMonthPanel();
                showSuccesMessage(name);
            }
            else {
                // show errors
                if(!validName) { showNameError(); }
                if(!validTimes) { showTimeError(); }
            }
        }
    }
}
