package rest;

import client.NutzerHandle;
import client.TerminHandle;
import database.Nutzer;
import database.Termin;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;
import webservices.EinladungEvent;
import webservices.KalenderServer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

public class EventsDemo {

    private HttpServer server;
    private WebTarget target;

    private TerminHandle terminHandle;
    private NutzerHandle nutzerHandle;

    @Before
    public void setUp() {
        // start the server
        server = KalenderServer.startServer();
        // create the client
        terminHandle = new TerminHandle();
        nutzerHandle = new NutzerHandle();
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void einladung() throws InterruptedException {

        Nutzer wer = nutzerHandle.create(new Nutzer("wer", "wer"));
        Nutzer wen = nutzerHandle.create(new Nutzer("wen", "wen"));
        Termin termin = new Termin();
        termin.setBeschreibung("einladung test");
        termin = terminHandle.create(termin);


        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(KalenderServer.BASE_URI)
                .path("events")
                .path(String.valueOf(wer.getId()));
        SseEventSource sseEventSource = SseEventSource.target(target).build();
        sseEventSource.register((event) -> System.out.println(event.getName() + "; "
                 + event.readData(EinladungEvent.class)));
        sseEventSource.open();

        terminHandle.einladen(termin, wer, wen);
        terminHandle.ablehnen(termin, wen);
        terminHandle.annehmen(termin, wen);

// do other stuff, block here and continue when done
        Thread.sleep(1000);

        sseEventSource.close();
    }
}
