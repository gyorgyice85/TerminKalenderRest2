/**
 *  Györgyi Palatinus
 */
package client;

import data.Nutzer;
import data.Termin;
import gui.ClientSession;
import gui.Invitation;
import webservices.EinladungEvent;
import webservices.KalenderServer;

import javax.swing.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;
import java.util.function.Consumer;

public class EventsHandle {

    private ClientSession cs;
    private SseEventSource sseEventSource;

    public EventsHandle(ClientSession cs) throws InterruptedException {

        this.cs = cs;

        // subscribe to updates
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(KalenderServer.BASE_URI)
                .path("events")
                .path(String.valueOf(cs.nutzerID));
        sseEventSource = SseEventSource.target(target).build();

        // register event handler
        sseEventSource.register(
            new Consumer<InboundSseEvent>() {
                @Override
                public void accept(InboundSseEvent event) {
                    EinladungEvent einladungEvent = event.readData(EinladungEvent.class);
                    Termin termin = cs.terminHandle.findById(einladungEvent.getTerminID());
                    Nutzer wer = cs.nutzerHandle.findById(einladungEvent.getWer());
                    Nutzer wen = cs.nutzerHandle.findById(einladungEvent.getWen());

                    switch (einladungEvent.getType()) {
                        case EINLADUNG:
                            System.out.println("Einladung received: " + termin);
                            Invitation window = new Invitation(einladungEvent, cs);
                            window.frame.setVisible(true);
                            break;
                        case ANGENOMMEN:
                            System.out.println("Einladung accepted: " + termin);
                            JOptionPane.showMessageDialog(null,
                                    "Your invitation has been accepted. \n \n" +
                                    "Termin: \n" + termin.toPrettyString() + "\n\n" +
                                    "Eingeladene: " + wen.toString(),
                                    "Invitation accepted", JOptionPane.PLAIN_MESSAGE);
                            break;
                        case ABGELEHNT:
                            System.out.println("Einladung rejected: " + termin);
                            JOptionPane.showMessageDialog(null,
                                    "Your invitation has been rejected. \n \n" +
                                            "Termin: \n" + termin.toPrettyString() + "\n\n" +
                                            "Eingeladene: " + wen.toString(),
                                    "Invitation rejected", JOptionPane.PLAIN_MESSAGE);
                            break;
                    }
                }
            });
        sseEventSource.open();

        // do other stuff, block here and continue when done
        Thread.sleep(2 * 1000);
    }

    public void close() {
        sseEventSource.close();
    }
}
