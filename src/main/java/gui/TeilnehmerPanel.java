package gui;

import data.Nutzer;
import data.Termin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
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
public class TeilnehmerPanel extends JPanel {

    private JFrame appointmentFrame;
    private ClientSession cs;
    private JComboBox<Nutzer> inviteComboBox;

    private Termin termin;

    /**
     * Constructor. Sets the global variables and calls the draw method.
     * @param termin The appointment
     * @param cs the client session
     */
    public TeilnehmerPanel(Termin termin, ClientSession cs, JFrame appointmentFrame) {
        super(new SpringLayout());

        this.termin = termin;
        this.cs = cs;
        this.appointmentFrame = appointmentFrame;

        drawAppointmentPanel();
    }

    /**
     * Redraws the day detail-panel
     */
    public void redrawAppointmentPanel() {
        removeAll();
        drawAppointmentPanel();
        validate();
        repaint();
    }

    /**
     * Draws the appointment panel.
     */
    public void drawAppointmentPanel() {

        JButton einladenButton = new JButton("Einladen");
        einladenButton.setPreferredSize(new Dimension(200,40));
        einladenButton.addActionListener(new EinladenHandler(cs));

        List<Nutzer> teilnehmer = cs.terminHandle.getTeilnehmer(termin);
        List<Nutzer> eingeladene = cs.terminHandle.getEingeladene(termin);
        List<Nutzer> nutzers = cs.nutzerHandle.findAll();
        nutzers.remove(cs.nutzer);
        nutzers.removeAll(teilnehmer);
        nutzers.removeAll(eingeladene);

        inviteComboBox = new JComboBox<>();
        for (Integer i = 0; i < nutzers.size(); i++) {
            Nutzer nutzer = nutzers.get(i);
            inviteComboBox.addItem(nutzer);
        }

        this.add(new JLabel("Termin:"));
        this.add(new JLabel(termin.toPrettyString()));

        this.add(new JLabel("Teilnehmer:"));
        JLabel teilnehmerLabel = new JLabel();
        StringBuilder teilnehmerText = new StringBuilder();
        for (Nutzer nutzer : teilnehmer) {
            teilnehmerText.append(nutzer.toString() + "\t");
        }
        teilnehmerLabel.setText(teilnehmerText.toString());
        this.add(teilnehmerLabel);

        this.add(new JLabel("Eingeladene:"));
        JLabel einladLabel = new JLabel();
        StringBuilder einladText = new StringBuilder();
        for (Nutzer nutzer : eingeladene) {
            einladText.append(nutzer.toString() + "\t");
        }
        einladLabel.setText(einladText.toString());
        this.add(einladLabel);

        this.add(new JLabel("Einladen:"));
        this.add(inviteComboBox);

        this.add(new JLabel(""));
        this.add(einladenButton);

// lay out the panel
        SpringUtilities.makeCompactGrid(this,
                5, 2, //rows, cols
                20, 20, //initX, initY
                20, 20 //xPad, yPad
        );
    }


    /**
     * Shows an message dialog when an event is succesfully added.
     * @param name the name of the event.
     */
    private void showSuccesMessage(String name) {
        JOptionPane.showMessageDialog(null, "Your invitation is succesfully sent.", "Invitation sent", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Inner class. Triggers an actionlistener when the <code>addAppointmentButton</code> is clicked.
     */
    class EinladenHandler implements ActionListener {

        private ClientSession cs;

        public EinladenHandler(ClientSession cs) {
            this.cs = cs;
        }

        /**
         * Opens new frame where a new appointment can be added.
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            Nutzer wen = (Nutzer)inviteComboBox.getSelectedItem();
            cs.terminHandle.einladen(termin, cs.nutzer, wen);
            showSuccesMessage("");
            redrawAppointmentPanel();
        }
    }
}
