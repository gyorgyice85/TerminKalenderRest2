/**
 * Eridho Buffery Rollian
 *
 */
package gui;

import data.Termin;

import javax.swing.*;

/**
 * The <code>TeilehmerFrame</code> ensures the window of the popup used to invite another user.
 * It is called by <code>DayPanel</code>.
 * @version 1.0
 * @see DayPanel
 */
public class TeilnehmerFrame extends JFrame {
    public Integer frameWidth = 292, frameHeight = 352;
    private TeilnehmerPanel teilnehmerPanel; // for now private
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
        setLocationRelativeTo(null);

        // add content to frame
        teilnehmerPanel = new TeilnehmerPanel(termin, cs, this);
        setContentPane(teilnehmerPanel);
        pack();
        setVisible(true);
    }
}
