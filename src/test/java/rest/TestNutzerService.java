package rest;

import database.Nutzer;
import de.htwsaar.www.Main;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import webservices.KalenderServer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestNutzerService {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = KalenderServer.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void getAllNutzer() {
        String responseMsg = target.path("nutzer").request().accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void getNutzerByID() {
        String responseMsg = target.path("nutzer").path("1234")
                .request().accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void getSomeNutzer() {
        String responseMsg = target.path("nutzer")
                        .queryParam("vorname", "Max")
                        .queryParam("nachname", "Musterman")
                        .request().accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(responseMsg);
    }
}
