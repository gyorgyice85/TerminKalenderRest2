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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class NutzerServiceDemo {

    private HttpServer server;
    private WebTarget target;
    Nutzer nutzer = new Nutzer();

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

        //Damit wird ein Webservice aufgerufen.
        target = c.target(Main.BASE_URI);
    }


    @After
    public void tearDown() throws Exception {
        server.stop();
    }


    @Test
    public void getAllNutzer() {
        //Ruft das Webservice von Path(/nutzer) auf
        String responseMsg = target.path("nutzer").request().accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void getSomeNutzer() {
        Map<String, Nutzer> nutzerList = new HashMap<>();

        nutzerList = target.path("nutzer")
                        .queryParam("vorname", nutzer.getVorname())
                        .queryParam("nachname", nutzer.getNachname())
                        .request().accept(MediaType.APPLICATION_JSON)
                        .get(nutzerList.getClass());

        for (Nutzer nutzer : nutzerList.values()) {
            System.out.println(nutzer);
        }
    }


    @Test
    public void createNutzer() {  //TODO
        //String responseMsg = target.path("nutzer").request().accept(MediaType.APPLICATION_JSON).post(String.class);
        //System.out.println(responseMsg);

    }


    @Test
    public void getNutzerByID() {
        Nutzer responseMsg = target.path("nutzer").path(String.valueOf(nutzer.getId()))
                .request().accept(MediaType.APPLICATION_JSON).get(Nutzer.class);
        System.out.println(responseMsg);
    }

    @Test
    public void updateNutzer() {
        Nutzer updatedNutzer = target.path("nutzer").path(String.valueOf(nutzer.getId()))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(nutzer, MediaType.APPLICATION_XML), Nutzer.class);

        System.out.println("Geaenderter Nutzer: " + updatedNutzer);
    }

    @Test
    public void deleteNutzer() {
        Nutzer responseMsg = target.path("nutzer").path(String.valueOf(nutzer.getId())) //TODO
                .request().accept(MediaType.APPLICATION_JSON).delete(Nutzer.class);
        System.out.println(responseMsg);

    }

    @Test
    public void getEinladungen() {
        String responseMsg = target.path("nutzer").path(String.valueOf(nutzer.getId())).path("einladungen").request().
                accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void getTermine() {
        String responseMsg = target.path("nutzer").path(String.valueOf(nutzer.getId())).path("termine").request().
                accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(responseMsg);
    }
}
