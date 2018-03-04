package rest;

import client.NutzerHandle;
import database.Nutzer;
import database.Termin;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import webservices.KalenderServer;

import javax.ws.rs.client.WebTarget;
import java.util.List;

public class NutzerServiceDemo {

    private HttpServer server;
    private WebTarget target;

    private NutzerHandle nutzerHandle;

    @Before
    public void setUp() {
        // start the server
        server = KalenderServer.startServer();
        // create the client
        nutzerHandle = new NutzerHandle();
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void getAllNutzer() {
        List< Nutzer> nutzerList = nutzerHandle.findAll();

        for (Nutzer nutzer : nutzerList) {
            System.out.println(nutzer);
        }
    }

    @Test
    public void getSomeNutzer() {
        // NOTE: add nutzer Max Mustermann and Maximilian Musterman
        List< Nutzer> nutzerList = nutzerHandle.findByName("Max", "Muster");

        for (Nutzer nutzer : nutzerList) {
            System.out.println(nutzer);
        }
    }

    @Test
    public void createNutzer() {
        Nutzer erstellterNutzer = nutzerHandle.create(new Nutzer());
        System.out.println(erstellterNutzer);

    }

    @Test
    public void getNutzerByID() {
        Nutzer responseMsg = nutzerHandle.findById(1234);
        System.out.println(responseMsg);
    }

    @Test
    public void updateNutzer() {
        Nutzer nutzer = new Nutzer();
        // NOTE: insert, than update Nutzer
        Nutzer updatedNutzer = nutzerHandle.update(nutzer);

        System.out.println("Geaenderter Nutzer: " + updatedNutzer);
    }

    @Test
    public void deleteNutzer() {
        boolean responseMsg = nutzerHandle.remove(1234);
        System.out.println(responseMsg);
    }

    @Test
    public void getEinladungen() {

        Nutzer nutzer = new Nutzer();
        List<Termin> einladungenList = nutzerHandle.getEinladungen(nutzer);

        for (Termin termin : einladungenList) {
            System.out.println(termin);
        }
    }

    @Test
    public void getTermine() {

        Nutzer nutzer = new Nutzer();
        List<Termin> terminList = nutzerHandle.getTermine(nutzer);

        for (Termin termin : terminList) {
            System.out.println(termin);
        }
    }
}
