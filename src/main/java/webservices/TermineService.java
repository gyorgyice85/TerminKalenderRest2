package webservices;

import database.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(TermineService.webContextPath)
public class TermineService {

    static final String webContextPath = "/termine";

    private TerminDAO terminDAO;
    private TeilnehmerDAO teilnehmerDAO;
    private EinladungDAO einladungDAO;

    public TermineService() {
        terminDAO = new TerminDAO();
        teilnehmerDAO = new TeilnehmerDAO();
        einladungDAO = new EinladungDAO();
    }

    /**
     * Erstellt einen Termin
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Termin create(Termin termin) {
        return terminDAO.create(termin);
    }

    /**
     * Gibt den Termin nach einer Beschreibung an.
     * Wenn keine Beschreibung angegeben wurde, listet es alle Termine aus
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Termin> findByBeschreibung(@QueryParam("beschreibung") String beschreibung) {
        if (beschreibung == null) {
            return terminDAO.findAll();
        } else {
            return terminDAO.findByBeschreibung(beschreibung);
        }
    }

    /**
     * Gibt den Termin nach dem ID an
     */
    @GET
    @Path("{terminId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Termin findById(@PathParam("terminId") int terminId) {
        return terminDAO.findById(terminId);
    }

    /**
     * Es ändert den Termin
     */
    @PUT
    @Path("{terminId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Termin update(Termin termin) {
        return terminDAO.update(termin);
    }

    /**
     * Löscht den Termin
     */
    @DELETE
    @Path("{terminId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String remove(@PathParam("terminId") int terminId) {
        return String.valueOf(terminDAO.remove(terminId));
    }

    /**
     * Listet die Teilnehmer des Termins
     */
    @GET
    @Path("{terminId}/teilnehmer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Nutzer> getTeilnehmer(@PathParam("terminId") int terminId) {
        return teilnehmerDAO.getTeilnehmer(terminId);
    }

    /**
     * Erstellt einen Teilnehmer zum Termin
     */
    @POST
    @Path("{terminId}/teilnehmer")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createTeilnehmer(Nutzer nutzer, @PathParam("terminId") int terminId) {
        teilnehmerDAO.create(nutzer.getId(), terminId);
    }

    /**
     * Löscht den Teilnehmer vom Termin
     */
    @DELETE
    @Path("{terminId}/teilnehmer")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeTeilnehmer(Nutzer nutzer, @PathParam("terminId") int terminId) {
        return String.valueOf(teilnehmerDAO.remove(nutzer.getId(), terminId));
    }

    /**
     * Listet die Eingeladene des Termins
     */
    @GET
    @Path("{terminId}/eingeladene")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Nutzer> getEingeladene(@PathParam("terminId") int terminId) {
        return einladungDAO.getEingeladene(terminId);
    }

    /**
     * Eine Einladung annehmen: man wird vom Eingeladene zum Teilnehmer
     */
    @PUT
    @Path("{terminId}/eingeladene")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createEingeladene(Nutzer nutzer, @PathParam("terminId") int terminId) {
        einladungDAO.annehmen(nutzer.getId(), terminId);
    }

    /**
     * Eine Einladung ablehnen: man wird aus der Liste der Eingeladene gelöscht
     */
    @DELETE
    @Path("{terminId}/eingeladene")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void removeEingeladene(Nutzer nutzer, @PathParam("terminId") int terminId) {
         einladungDAO.ablehnen(nutzer.getId(), terminId);
    }

    /**
     * Erstellt eine Einladung zum Termin
     */
    @POST
    @Path("{terminId}/eingeladene")
    public void createEinladung(
            @QueryParam("wer") int wer,
            @QueryParam("wen") int wen,
            @PathParam("terminId") int terminId) {

        einladungDAO.create(wer, wen, terminId);
    }
}
