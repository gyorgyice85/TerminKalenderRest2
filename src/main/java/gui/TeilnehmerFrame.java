package gui;

import data.Termin;

import javax.swing.*;

/**
 * The <code>AppointmentFrame</code> ensures the window of the popup used to add an appointment.
 * It is called by <code>DayPanel</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see DayPanel
 */
public class TeilnehmerFrame extends JFrame {
    public Integer frameWidth = 292, frameHeight = 352;
    private TeilnehmerPanel appointmentPanel; // for now private
    private ClientSession cs;
    private Termin termin;

    /**
     * Constructor. Calls the initialization of the frame.
     */
    public TeilnehmerFrame(Termin termin, ClientSession cs, Integer offsetX, Integer offsetY) {
        this.termin = termin;
        this.cs = cs;
        initFrame(offsetX, offsetY);
    }

    /**
     * Inits the frame.
     */
    private void initFrame(Integer offsetX, Integer offsetY){
        new JFrame();
        setTitle("Invitees and participants");
        setResizable(false);
        setSize(frameWidth,frameHeight);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(offsetX, offsetY);
        //setAlwaysOnTop(true);

        // add content to frame
        appointmentPanel = new TeilnehmerPanel(termin, cs, this);
        setContentPane(appointmentPanel);
        pack();
        setVisible(true);
    }
}
