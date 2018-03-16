package gui;

import calendar.CalendarData;
import client.EventsHandle;

import javax.swing.*;
import java.awt.*;

/**
 * The <code>MainFrame</code> ensures the main window of the calendar application.
 * @version 1.0
 */
public class MainFrame extends JFrame {

    public Integer frameWidth, frameHeight;
    public MainPanel mainPanel;
    public CalendarData calendar;

    protected ClientSession cs;
    EventsHandle eventsHandle;

    /**
     * Constructor. Calls the initialization of the frame, calendar and manager.
     */
    public MainFrame(ClientSession cs) {
        this.cs = cs;
        calendar = new CalendarData();
        initFrame();
    }

    /**
     * Inits the frame.
     */
    private void initFrame(){
        new JFrame();
        setFrameDimension(false);
        setTitle("Calendar of " + cs.nutzer.toString());
        //setSize(frameWidth,frameHeight);
        setSize(1300,800);
        setMinimumSize(new Dimension(1280, 800));

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                eventsHandle.close();
                System.exit(0);
            }
        });

        // add content to frame
        mainPanel = new MainPanel(MainFrame.this);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);

        setResizable(false);
        pack();
        setVisible(true);

        try {
            eventsHandle = new EventsHandle(cs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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