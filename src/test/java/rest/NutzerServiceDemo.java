/**
 *  Annisa Yustina Puspakemala
 */
package rest;

import client.NutzerHandle;
import client.TerminHandle;
import data.Nutzer;
import data.Termin;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import webservices.KalenderServer;

import javax.ws.rs.client.WebTarget;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class NutzerServiceDemo {

    private HttpServer server;

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
        server.shutdown();
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
        List< Nutzer> nutzerList = nutzerHandle.findByName("Max", "");

        for (Nutzer nutzer : nutzerList) {
            System.out.println(nutzer);
        }
    }

    @Test
    public void createNutzer() {
        Nutzer erstellterNutzer = nutzerHandle.create(new Nutzer("Peter", "Musterman"));
        System.out.println(erstellterNutzer);
    }

    @Test
    public void getNutzerByID() {
        Nutzer responseMsg = nutzerHandle.findById(1234);
        System.out.println(responseMsg);
    }

    @Test
    public void updateNutzer() {
        Nutzer nutzer = new Nutzer("Max", "Musterring");
        Nutzer createdNutzer = nutzerHandle.create(nutzer);
        System.out.println("Eingefuegter Nutzer: " + createdNutzer);
        createdNutzer.setNachname("Musterman");
        Nutzer updatedNutzer = nutzerHandle.update(createdNutzer);
        System.out.println("Geaenderter Nutzer: " + updatedNutzer);
    }

    @Test
    public void deleteNutzer() {
        Nutzer createdNutzer = nutzerHandle.create(new Nutzer());
        boolean responseMsg = nutzerHandle.remove(createdNutzer.getId());
        System.out.println(responseMsg);
    }

    @Test
    public void getEinladungen() {

        List<Termin> einladungenList = nutzerHandle.getEinladungen(nutzerHandle.findById(4361));

        for (Termin termin : einladungenList) {
            System.out.println(termin);
        }
    }

    @Test
    public void getTermine() {

        List<Termin> terminList = nutzerHandle.getTermine(nutzerHandle.findById(4371));

        for (Termin termin : terminList) {
            System.out.println(termin);
        }
    }

    private void createTerminMitTeilnehmer(Nutzer nutzer, String von, String bis) {
        Termin termin = new Termin();
        termin.setVon(new Timestamp(Date.valueOf(von).getTime()));
        termin.setBis(new Timestamp(Date.valueOf(bis).getTime()));
        termin = terminHandle.create(termin);
        terminHandle.teilnehmerHinzufuegen(termin, nutzer);
    }

    private void listTermineAmTag(Nutzer nutzer, String tag) {
        System.out.println("Date = " + tag);
        for (Termin termin : nutzerHandle.getTermineAmTag(nutzer, Date.valueOf(tag))) {
            System.out.println(termin);
        }
    }

    @Test
    public void getTermineAmTag() {

        Nutzer nutzer = nutzerHandle.create(new Nutzer());

        createTerminMitTeilnehmer(nutzer, "2018-03-01", "2018-03-01");
        createTerminMitTeilnehmer(nutzer, "2018-03-02", "2018-03-02");
        createTerminMitTeilnehmer(nutzer, "2018-03-01", "2018-03-02");

        listTermineAmTag(nutzer,"2018-03-01");
        listTermineAmTag(nutzer,"2018-03-02");
        listTermineAmTag(nutzer,"2018-03-03");
    }

    @Test
    public void realityCheck() {
        Nutzer nutzer = nutzerHandle.findById(4377);
        listTermineAmTag(nutzer, "2018-03-13");
    }
}
