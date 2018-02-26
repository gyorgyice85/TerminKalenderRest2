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


    //Gibt den Nutzer nach dem Name an.
    //Wenn keinen Nutzer gefunden wurde, listet es alle Nutzer aus
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Nutzer> find(@QueryParam("vorname") String vorname, @QueryParam("nachname") String nachname) {
        if (vorname == null || nachname == null) {
            return nutzerDAO.findAll();
        } else {
            return nutzerDAO.findByName(vorname, nachname);
        }
    }

    //Erstellt den Nutzer
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Nutzer createNutzer(Nutzer nutzer) {
        return nutzerDAO.create(nutzer);
    }


    //Gibt den Nutzer nach dem ID an
    @GET
    @Path("{nutzerID}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Nutzer findById(@PathParam("nutzerID") int nutzerID) {
        return nutzerDAO.findById(nutzerID);
    }


    //Ändert den Nutzer
    @PUT
    @Path("{nutzerID}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Nutzer updateNutzer(Nutzer nutzer) {
       return nutzerDAO.update(nutzer);
    }


    //Löscht den Nutzer
    @DELETE
    @Path("{nutzerID}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean removeNutzer(@PathParam("nutzerID") int nutzerID) {
        return nutzerDAO.remove(nutzerID);
    }


    //Gibt alle Einladungen des Nutzers an
    @GET
    @Path("{nutzerID}/einladungen")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Termin> getEinladungen(@PathParam("nutzerID") int nutzerID) {
        return einladungDAO.getEinladungen(nutzerID);
    }


    //Gibt alle Termine des Nutzers an
    @GET
    @Path("{nutzerID}/termine")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Termin> getTermine(@PathParam("nutzerID") int nutzerID) {
        return teilnehmerDAO.getTermine(nutzerID);
    }
}
