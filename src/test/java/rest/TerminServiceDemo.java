package rest;

import database.Termin;
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
import java.util.HashMap;
import java.util.Map;


public class TerminServiceDemo {

    private HttpServer server;
    private WebTarget target;
    Termin termin = new Termin();

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
    public void createTermin() {
        Termin termin = new Termin();
        Termin createdTermin = target.path("termine").request(MediaType.APPLICATION_XML)
                .post(Entity.entity(termin, MediaType.APPLICATION_XML), termin.getClass());
        System.out.println(createdTermin);
    }

    @Test
    public void getSomeTermin(){
        Map<String, Termin> terminList = new HashMap<>();

        terminList = target.path("termine")
                .queryParam("beschreibung", termin.getBeschreibung())
                .request().accept(MediaType.APPLICATION_JSON)
                .get(terminList.getClass());

        for (Termin termin : terminList.values()) {
            System.out.println(termin);
        }
    }

    @Test
    public void getAllTermin() {
        String responseMsg = target.path("termine").request().accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void getTerminByID() {
        Termin responseMsg = target.path("termine").path(String.valueOf(termin.getId()))
                .request().accept(MediaType.APPLICATION_JSON).get(Termin.class);
        System.out.println(responseMsg);
    }

    @Test
    public void updateTermin() {
        Termin updatedTermin = target.path("termine").path(String.valueOf(termin.getId()))
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(termin, MediaType.APPLICATION_XML), termin.getClass());
        System.out.println(updatedTermin);
    }

    @Test
    public void deleteTermin() {

        Termin responseMsg = target.path("termine").path(String.valueOf(termin.getId()))
                .request().accept(MediaType.APPLICATION_JSON).delete(Termin.class);
        System.out.println(responseMsg);
    }

    @Test
    public void getTeilnehmer(){
        Termin responseMsg = target.path("termine").path(String.valueOf(termin.getId())).path("teilnehmer")
                .request().accept(MediaType.APPLICATION_JSON).get(Termin.class);
        System.out.println(responseMsg);
    }

    @Test
    public void createTeilnehmer(){
        Termin responseMsg = target.path("termine").path(String.valueOf(termin.getId())).path("teilnehmer")
                .request().accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(termin, MediaType.APPLICATION_XML), termin.getClass());
        System.out.println(responseMsg);
    }

    @Test
    public void deleteTeilnehmer(){
        Termin responseMsg = target.path("termine").path(String.valueOf(termin.getId())).path("teilnehmer")
                .request().accept(MediaType.APPLICATION_JSON).delete(Termin.class);
        System.out.println(responseMsg);
    }

    @Test
    public void getEingeladene(){
        Termin responseMsg = target.path("termine").path(String.valueOf(termin.getId())).path("eingeladene")
                .request().accept(MediaType.APPLICATION_JSON).get(Termin.class);
        System.out.println(responseMsg);
    }

    @Test
    public void updateEingeladene(){
        Termin responseMsg = target.path("termine").path(String.valueOf(termin.getId())).path("eingeladene")
                .request().accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(termin, MediaType.APPLICATION_XML), termin.getClass());
        System.out.println(responseMsg);
    }

    @Test
    public void deleteEingeladene(){
        Termin responseMsg = target.path("termine").path(String.valueOf(termin.getId())).path("eingeladene")
                .request().accept(MediaType.APPLICATION_JSON).delete(Termin.class);
        System.out.println(responseMsg);
    }

    @Test
    public void createEinladung(){
        Termin responseMsg = target.path("termine").path(String.valueOf(termin.getId())).path("eingeladene")
                .request().accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(termin, MediaType.APPLICATION_XML), termin.getClass());
        System.out.println(responseMsg);
    }



}
