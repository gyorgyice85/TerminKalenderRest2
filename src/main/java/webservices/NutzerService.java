package webservices;

import database.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(NutzerService.webContextPath)
public class NutzerService {

    static final String webContextPath = "/nutzer";
    NutzerDAO nutzerDAO;
    EinladungDAO einladungDAO;
    TeilnehmerDAO teilnehmerDAO;

    public NutzerService() {
        nutzerDAO = new NutzerDAO();
        einladungDAO = new EinladungDAO();
        teilnehmerDAO = new TeilnehmerDAO();
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public List<Nutzer> findAll() {
//        return nutzerDAO.findAll();
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Nutzer> find(@QueryParam("vorname") String vorname, @QueryParam("nachname") String nachname) {
        if (vorname == null || nachname == null) {
            return nutzerDAO.findAll();
        } else {
            return nutzerDAO.findByName(vorname, nachname);
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Nutzer create(Nutzer nutzer) {
        return nutzerDAO.create(nutzer);
    }

    @GET
    @Path("{nutzerID}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Nutzer findById(@PathParam("nutzerID") int nutzerID) {
        return nutzerDAO.findById(nutzerID);
    }

    @PUT
    @Path("{id}")   // TODO nem kell?
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Nutzer update(Nutzer nutzer) {
        return nutzerDAO.update(nutzer);
    }

    @DELETE
    @Path("{nutzerID}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean removeNutzer(@PathParam("nutzerID") int nutzerID) {
        return nutzerDAO.remove(nutzerID);
    }


    @GET
    @Path("{nutzerID}/einladungen")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Termin> getEinladungen(@PathParam("nutzerID") int nutzerID) {
        return einladungDAO.getEinladungen(nutzerID);
    }

    @GET
    @Path("{nutzerID}/termine")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Termin> getTermine(@PathParam("nutzerID") int nutzerID) {
        return teilnehmerDAO.getTermine(nutzerID);
    }
}
