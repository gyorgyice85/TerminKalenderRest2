/**
 *  Györgyi Palatinus
 */
package webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Path("events")
public class EventsService {

    public static final int NONE = Integer.MIN_VALUE;

    // Temporary store for messages when arrived
    static BlockingQueue<EinladungEvent> einladungMessageQueue = new LinkedBlockingQueue<EinladungEvent>();

    @GET
    @Path("{nutzerID}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getServerSentEvents(@Context SseEventSink eventSink, @Context Sse sse,
                                    @PathParam("nutzerID") int nutzerID) {
        new Thread(() -> {

            try {

                while(true) {
                    // Waits until a message arrives
                    EinladungEvent message = einladungMessageQueue.take();

                    // whether this message should be sent to the user with ID==nutzerID
                    boolean sendMessage = false;

                    switch (message.getType()) {
                        case EINLADUNG:
                            sendMessage = nutzerID == message.getWen();
                            break;
                        case ABGELEHNT:
                        case ANGENOMMEN:
                            sendMessage = nutzerID == message.getWer();
                            break;
                    }

                    if (sendMessage) {

                        final OutboundSseEvent event = sse.newEventBuilder()
                                .name("einladung-message-to-client")
                                .data(EinladungEvent.class, message)
                                .mediaType(MediaType.APPLICATION_XML_TYPE)
                                .build();
                        eventSink.send(event);
                    } else {
                        einladungMessageQueue.put(message);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

