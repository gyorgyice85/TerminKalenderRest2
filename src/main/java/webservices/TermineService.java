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

//    @GET      // TODO: zusammenfuegen mit findAll() Methode oben
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public List<Termin> findByBeschreibung(@QueryParam("beschreibung") String beschreibung) {
//        return terminDAO.findByBeschreibung(beschreibung);
//    }


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Termin findById(@PathParam("id") int id) {
        return terminDAO.findById(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Termin update(Termin termin) {
        return terminDAO.update(termin);
    }


    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean remove(@PathParam("id") int id) {
        return terminDAO.remove(id);
    }


//    @GET
//    @Path("{id}")   // TODO
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public List<Nutzer> getTeilnehmer(@PathParam("id") int id) {
//        return teilnehmerDAO.getTeilnehmer(id);
//    }

//    @POST
//    @Path("{id}")    // TODO
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public void createTeilnehmer(@PathParam("id") int id) {
//        return teilnehmerDAO.create(nutzer, id);
//    }
}
