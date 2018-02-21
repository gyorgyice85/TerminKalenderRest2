package webservices;

import database.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(TermineService.webContextPath)
public class TermineService {

    static final String webContextPath = "/termine";

    TerminDAO terminDAO;
    TeilnehmerDAO teilnehmerDAO;
    EinladungDAO einladungDAO;


    public TermineService() {
        terminDAO = new TerminDAO();
        teilnehmerDAO = new TeilnehmerDAO();
        einladungDAO = new EinladungDAO();

    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Termin create(Termin termin) {
        return terminDAO.create(termin);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Termin> findByBeschreibung(@QueryParam("beschreibung") String beschreibung) {
        if (beschreibung == null) {
            return terminDAO.findAll();
        } else {
            return terminDAO.findByBeschreibung(beschreibung);
        }
    }


    @GET
    @Path("{terminId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Termin findById(@PathParam("terminId") int terminId) {
        return terminDAO.findById(terminId);
    }

    @PUT
    @Path("{terminId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Termin update(Termin termin) {
        return terminDAO.update(termin);
    }


    @DELETE
    @Path("{terminId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean remove(@PathParam("terminId") int terminId) {
        return terminDAO.remove(terminId);
    }


    @GET
    @Path("{terminId}/teilnehmer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Nutzer> getTeilnehmer(@PathParam("terminId") int terminId) {
        return teilnehmerDAO.getTeilnehmer(terminId);
    }

    @POST
    @Path("{terminId}/teilnehmer")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createTeilnehmer(Nutzer nutzer, @PathParam("terminId") int terminId) {
        teilnehmerDAO.create(nutzer.getId(), terminId);
    }

    @DELETE
    @Path("{terminId}/teilnehmer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean removeTeilnehmer(Nutzer nutzer, @PathParam("terminId") int terminId) {
        return teilnehmerDAO.remove(nutzer.getId(), terminId);
    }

    @GET
    @Path("{terminId}/eingeladene")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Nutzer> getEingeladene(@PathParam("terminId") int terminId) {
        return einladungDAO.getEingeladene(terminId);
    }


    @POST
    @Path("{terminId}/eingeladene")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createEingeladene(Nutzer nutzer, @PathParam("terminId") int terminId) {
        einladungDAO.annehmen(nutzer.getId(), terminId);
    }

    @DELETE
    @Path("{terminId}/eingeladene")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void removeEingeladene(Nutzer nutzer, @PathParam("terminId") int terminId) {
         einladungDAO.ablehnen(nutzer.getId(), terminId);
    }

    @POST
    @Path("{terminId}/einladung")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createEinladung(Nutzer wer, Nutzer wen, @PathParam("terminId") int terminId) {
        einladungDAO.create(wer.getId(), wen.getId(), terminId);
    }

}
