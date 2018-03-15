package database;

import data.Nutzer;
import data.Termin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeilnehmerDAO {

    /**
     * Methode um die Nutzer, die fuer einen Termin eingeladet sind, zurueckzugeben
     * @param termin das Termin
     * @return List<Nutzer> die Liste der eingeladenen Nutzer
     */
    public List<Nutzer> getTeilnehmer(Termin termin) {
        return  getTeilnehmer(termin.getId());
    }

    /**
     * Methode um die Nutzer, die fuer einen Termin eingeladet sind, zurueckzugeben
     * @param terminId ID des Termins
     * @return List<Nutzer> die Liste der eingeladenen Nutzer
     */
    public List<Nutzer> getTeilnehmer(int terminId) {
        List<Nutzer> list = new ArrayList<Nutzer>();
        Connection c = null;
        NutzerDAO nutzer = new NutzerDAO();
        String sql = "SELECT NUTZER.* FROM NUTZER JOIN TEILNEHMER WHERE " +
                "Teilnehmer.NutzerID = ID  AND TerminID = ?";

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, terminId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(nutzer.processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    /**
     * Methode um Termine des Nutzers zurückzugeben
     * @param nutzer der Nutzer
     * @return List<Termin> Liste der Termine
     */
    public List<Termin> getTermine(Nutzer nutzer){
        return getTermine(nutzer.getId());
    }

    /**
     * Methode um Termine des Nutzers zurückzugeben
     * @param nutzer der Nutzer
     * @return List<Termin> Liste der Termine
     */
    public List<Termin> getTermine(Nutzer nutzer, String tag){
        return getTermine(nutzer.getId(), tag);
    }

    /**
     * Methode um Termine des Nutzers zurückzugeben
     * @param nutzerID der Nutzer
     * @return List<Termin> Liste der Termine
     */
    public List<Termin> getTermine(int nutzerID) {
        return getTermine(nutzerID, null);
    }

    public static Date stripTime(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }

    public static Date stripTime(Timestamp date) {
        return stripTime(new Date(date.getTime()));
    }

    /**
     * Methode um Termine des Nutzers zurückzugeben
     * @param nutzerID der Nutzer
     * @param tag der Tag worauf wir die Suche beschraenken sollen (optional)
     * @return List<Termin> Liste der Termine
     */
    public List<Termin> getTermine(int nutzerID, String tag) {

        List<Termin> list = new ArrayList<Termin>();
        Connection c = null;
        TerminDAO terminDAO = new TerminDAO();
        String sql = "SELECT TERMIN.* FROM TERMIN JOIN TEILNEHMER WHERE " +
                "Teilnehmer.TerminID = ID AND NutzerID = ?";

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, nutzerID);


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Termin termin = terminDAO.processRow(rs);
                if (tag != null) {
                    Date tagDate = Date.valueOf(tag);
                    Date tagVon = stripTime(termin.getVon());
                    Date tagBis = stripTime(termin.getBis());
                    if (tagVon.compareTo(tagDate) <= 0 && tagDate.compareTo(tagBis) <= 0) {
                        list.add(termin);
                    }
                } else {
                    list.add(termin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    /**
     * Die Metode fuegt einen neuen Teilhemner zur Teilnehmerliste hinzu
     * @param nutzer der neue Teilnehmer
     * @param termin
     */
    public void create(Nutzer nutzer, Termin termin){
        create(nutzer.getId(), termin.getId());
    }


    /**
     * Die Metode fuegt einen neuen Teilhemner zur Teilnehmerliste hinzu
     * @param nutzerID der ID des neuen Teilnehmer
     * @param terminId
     */
    public void create(int nutzerID, int terminId){

        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO Teilnehmer(NutzerID, TerminID) VALUES (?, ?)");
            ps.setInt(1, nutzerID);
            ps.setInt(2, terminId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    /**
     * Die Methode loescht den Teilnehmer des Termins
     * @param nutzer der loeschende Teilnehmer
     * @param termin
     * @return
     */
    public boolean remove(Nutzer nutzer, Termin termin) {
        return remove(nutzer.getId(), termin.getId());
    }

    /**
     * Die Methode loescht den Teilnehmer des Termins
     * @param nutzerID der loeschende Teilnehmer
     * @param terminID
     * @return
     */
    public boolean remove(int nutzerID, int terminID){
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement
                    ("DELETE FROM Teilnehmer WHERE NutzerID =? AND TerminID = ?");
            ps.setInt(1, nutzerID);
            ps.setInt(2, terminID);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }
}
