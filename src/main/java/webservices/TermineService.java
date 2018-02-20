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


    public TermineService() {
        terminDAO = new TerminDAO();
        teilnehmerDAO = new TeilnehmerDAO();

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Termin> findAll() {
        return terminDAO.findAll();
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
    @Path("{terminId}/teilnehmer")    // TODO
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createTeilnehmer(@PathParam("terminId") int terminId) {
        return teilnehmerDAO.create(nutzerId, terminId);
    }
}
