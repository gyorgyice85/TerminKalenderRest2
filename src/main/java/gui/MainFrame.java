package gui;

import calendar.CalendarData;
import client.TerminHandle;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * The <code>MainFrame</code> ensures the main window of the calendar application.
 * It is called by <code>edu.avans.library.main.Main</code>.
 * @version 1.0
 */
public class MainFrame extends JFrame {
    public Integer frameWidth, frameHeight;
    public MainPanel mainPanel;
    public CalendarData calendar;
    public TerminHandle terminHandle;


    private int nutzerID;

    public int getNutzerID() {
        return nutzerID;
    }

    public void setNutzerID(int nutzerID) {
        this.nutzerID = nutzerID;
    }

    /**
     * Constructor. Calls the initialization of the frame, calendar and manager.
     */
    public MainFrame() {
        calendar = new CalendarData();
        terminHandle = new TerminHandle();
        initFrame();
    }

    /**
     * Inits the frame.
     */
    private void initFrame(){
        new JFrame();
        setFrameDimension(false);
        setTitle("Java Calendar");
        //setSize(frameWidth,frameHeight);
        setSize(1300,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280, 800));

        // add content to frame
        mainPanel = new MainPanel(MainFrame.this);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);

        setResizable(false);
        pack();
        setVisible(true);
    }

    /**
     * Gets the height of the frame.
     * @return the height of the frame
     */
    public Integer getMainFrameHeight() {
        return frameHeight;
    }

    /**
     * Gets the width of the frame.
     * @return the width of the frame
     */
    public Integer getMainFrameWidth() {
        return frameWidth;
    }

    /**
     * Sets the frame dimension variables.
     * @param resized decides whether the frame dimensions are those of the users screen, or needed to be requested from the frame itself.
     */
    public void setFrameDimension(boolean resized) {
        if (resized) {
            // window is being resized
            Dimension windowSize = getBounds().getSize();
            frameWidth = (int) windowSize.getWidth();
            frameHeight = (int) windowSize.getHeight();
        }
        else {
            // first time startup
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frameWidth = (int) screenSize.getWidth();
            frameHeight = (int) screenSize.getHeight();
        }
    }

}