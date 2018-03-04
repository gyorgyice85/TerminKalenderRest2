package rest;

import client.TerminHandle;
import database.Nutzer;
import database.Termin;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import webservices.KalenderServer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;


public class TerminServiceDemo {

    private HttpServer server;
    private WebTarget target;

    private TerminHandle terminHandle;

    @Before
    public void setUp() {
        // start the server
        server = KalenderServer.startServer();
        // create the client
        terminHandle = new TerminHandle();
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void createTermin() {

        Termin termin = new Termin();
        Termin createdTermin = terminHandle.create(termin);
        System.out.println(createdTermin);
    }

    @Test
    public void getSomeTermin(){

        List<Termin> terminList = terminHandle.findByBeschreibung("projekt");

        for (Termin termin : terminList) {
            System.out.println(termin);
        }
    }

    @Test
    public void getAllTermin() {

        List<Termin> terminList = terminHandle.findAll();

        for (Termin termin : terminList) {
            System.out.println(termin);
        }

    }

    @Test
    public void getTerminByID() {
        Termin responseMsg = terminHandle.findById(1234);
        System.out.println(responseMsg);
    }

    @Test
    public void updateTermin() {
        Termin updatedTermin = terminHandle.update(new Termin());
        System.out.println(updatedTermin);
    }

    @Test
    public void deleteTermin() {

        boolean response = terminHandle.remove(1234);
        System.out.println(response);
    }

    @Test
    public void getTeilnehmer() {
        List<Nutzer> teilnehmer = terminHandle.getTeilnehmer(new Termin());

        for (Nutzer nutzer : teilnehmer) {
            System.out.println(nutzer);
        }
    }

    @Test
    public void teilnehmerHinzufuegen(){
        Nutzer nutzer = new Nutzer();
        Termin termin = new Termin();
        terminHandle.teilnehmerHinzufuegen(termin, nutzer);
    }

    @Test
    public void absagen(){
        Nutzer nutzer = new Nutzer();
        Termin termin = new Termin();
        terminHandle.teilnahmeAbsagen(termin, nutzer);
    }

    @Test
    public void getEingeladene(){
        List<Nutzer> listEingeladene = terminHandle.getEingeladene(new Termin());

        for(Nutzer eingeladene: listEingeladene) {
            System.out.println(eingeladene);
        }
    }

    @Test
    public void annehmen(){
        Nutzer nutzer = new Nutzer();
        Termin termin = new Termin();
        terminHandle.annehmen(termin, nutzer);
    }

    @Test
    public void ablehnen(){
        Nutzer nutzer = new Nutzer();
        Termin termin = new Termin();
        terminHandle.ablehnen(termin, nutzer);
    }

    @Test
    public void einladen(){
        Nutzer wer = new Nutzer();
        Nutzer wen = new Nutzer();
        Termin termin = new Termin();
        terminHandle.einladen(termin, wer, wen);
    }

}
