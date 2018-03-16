package gui;

import data.Termin;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

/**
 * The <code>DayDetailPanel</code> ensures the panel in which the details (appointments) of the active day will be placed.
 * It is placed within <code>MainPanel</code>.
 * @version 1.0
 * @see MainPanel
 */
public class DayDetailPanel extends JPanel {
    private Integer day, month, year;
    private Integer dayDetailPanelWidth, dayDetailPanelHeight;
    private MainPanel mainPanel;
    private JScrollPane scrollPane;

    private List<Termin> appointments;
    ClientSession cs;

    /**
     * Constructor. Creates an calendar object and inits the calendar-panel.
     */
    public DayDetailPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.cs = mainPanel.cs;

        drawDayDetailPanel();
    }

    /**
     * Draws the day-detail panel.
     */
    private void drawDayDetailPanel() {

        setLayout(null);
        setBackground(Color.WHITE);
        setDayDetailPanelDimensions();
        setDayDetailPanelBounds();

        month = mainPanel.mainFrame.calendar.month.getActiveMonth();
        day = mainPanel.mainFrame.calendar.day.getActiveDay();
        year = mainPanel.mainFrame.calendar.year.getActiveYear();
        Date date = mainPanel.mainFrame.calendar.getDate(month, day, year);
        appointments = cs.nutzerHandle.getTermineAmTag(cs.nutzer, new java.sql.Date(date.getTime()));

        drawDayLabel();
        drawAppointments();
    }

    /**
     * Updates the day-detail panel dimensions and sets it's new bounds.
     */
    public void resizeDayDetailPanel() {
        setDayDetailPanelDimensions();
        setDayDetailPanelBounds();
    }

    /**
     * Sets the day-detail panel's dimensions.
     */
    private void setDayDetailPanelDimensions() {
        dayDetailPanelWidth = (int) (mainPanel.mainFrame.getContentPane().getWidth() * 0.25);
        dayDetailPanelHeight = mainPanel.mainFrame.getContentPane().getHeight();
    }

    /**
     * Sets the day-detail panel's bounds with the known dimensions.
     */
    private void setDayDetailPanelBounds() {
        setBounds((int) mainPanel.mainFrame.getContentPane().getWidth() - dayDetailPanelWidth, 0, dayDetailPanelWidth, dayDetailPanelHeight);
        //scrollPane.setBounds(0,mainPanel.getTopPanelHeight(),dayDetailPanelWidth, dayDetailPanelHeight - mainPanel.getTopPanelHeight());
    }

    /**
     * Draws the weekday-name and month day heading.
     */
    private void drawDayLabel() {
        String weekDayName = mainPanel.mainFrame.calendar.week.getWeekDayName(mainPanel.mainFrame.calendar.getDate(month, day, year));
        JLabel dayLabel = new JLabel(weekDayName+" "+day);
        dayLabel.setBounds(15,10,dayDetailPanelWidth,50);
        dayLabel.setForeground(Color.decode("#333333"));
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        add(dayLabel);
    }

    /**
     * Redraws the day detail-panel
     */
    public void redrawDayDetailPanel() {
        removeAll();
        drawDayDetailPanel();
        validate();
        repaint();
    }

    /**
     * Draws each appointment with it's contents in the panel.
     */
    private void drawAppointments() {

        JPanel appointmentsPanel = new JPanel();
        appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS));
        appointmentsPanel.setBackground(Color.WHITE);
        appointmentsPanel.setOpaque(true);

        scrollPane = new JScrollPane(appointmentsPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#E2E2E2")));
        scrollPane.setOpaque(true);
        // set bounds
        scrollPane.setBounds(0,mainPanel.getTopPanelHeight(),dayDetailPanelWidth, dayDetailPanelHeight - mainPanel.getTopPanelHeight());
        scrollPane.getVerticalScrollBar().setUnitIncrement(25);

        Integer appointmentsSize = appointments.size();

        if(appointmentsSize > 0) {
            for (Integer i = 0; i < appointments.size(); i++) {
                Termin appointment = appointments.get(i);

                Boolean hasOrt = true;
                Boolean hasBeschreibung = true;
                if (appointment.getOrt() == null) {
                    hasOrt = false;
                }
                if (appointment.getBeschreibung() == null) {
                    hasBeschreibung = false;
                }

                // components
                Border spacingBorder = new EmptyBorder(0, 6, 0, 6);
                String startTime = String.valueOf(appointment.getVon());
                startTime = startTime.substring(0, startTime.length()-5);
                String endTime = String.valueOf(appointment.getBis());
                endTime = endTime.substring(0, endTime.length()-5);
                JLabel time = new JLabel(startTime+" - "+endTime);
                time.setBorder(spacingBorder);

                JButton inviteButton = new JButton("Invite");
                inviteButton.addActionListener(new InviteHandler(appointment));


                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(new DeleteAppointmentHandler(appointment));

                // create panel and add components
                JPanel appointmentPanel = new JPanel();
                appointmentPanel.setLayout(new BoxLayout(appointmentPanel, BoxLayout.Y_AXIS));
                appointmentPanel.add(time);
                if (hasBeschreibung) {
                    JLabel description = new JLabel("Name: "+appointment.getBeschreibung());
                    description.setBorder(spacingBorder);
                    appointmentPanel.add(description);
                }
                if (hasOrt) {
                    JLabel location = new JLabel("Place: "+appointment.getOrt());
                    location.setBorder(spacingBorder);
                    appointmentPanel.add(location);
                }

                appointmentPanel.add(inviteButton);
                appointmentPanel.add(deleteButton);

                appointmentPanel.setOpaque(false);
                appointmentPanel.setBorder(new EmptyBorder(10, 12, 10, 12));
                appointmentsPanel.add(appointmentPanel);
            }
        }
        else {
            JLabel noResultsLabel = new JLabel("No events found");
            noResultsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noResultsLabel.setBorder(new EmptyBorder(10, 12, 10, 12));
            appointmentsPanel.add(noResultsLabel);
        }

        add(scrollPane);
    }

    /**
     * Inner class. Triggers an actionlistener when a delete button is clicked.
     */
    class DeleteAppointmentHandler implements ActionListener {

        private Termin termin;

        /**
         * Constructor, stores the appointment.
         * @param termin
         */
        public DeleteAppointmentHandler(Termin termin) {
            this.termin = termin;
        }

        /**
         * Deletes an appointment based on an appointment id
         * @param e
         */
        public void actionPerformed(ActionEvent e) {

            int dialogResult = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete the following event? \n\n" + termin.toPrettyString(),
                    "Delete event",
                    JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                // delete appointment
                cs.terminHandle.remove(termin.getId());
                // redraw panels
                mainPanel.calendarPanel.monthPanel.redrawMonthPanel();
                mainPanel.dayDetailPanel.redrawDayDetailPanel();
            }
        }
    }

    /**
     * Inner class. Triggers an actionlistener when a invite button is clicked.
     */
    class InviteHandler implements ActionListener {

        private Termin termin;

        /**
         * Constructor, stores the appointment.
         * @param termin
         */
        public InviteHandler(Termin termin) {
            this.termin = termin;
        }

        /**
         * Shows a Window to allow for inviting people to an appointment
         * @param e
         */
        public void actionPerformed(ActionEvent e) {

            Integer offsetX = getLocationOnScreen().x;
            Integer offsetY = getLocationOnScreen().y;

            new TeilnehmerFrame(termin, cs, offsetX, offsetY);
        }
    }
}
