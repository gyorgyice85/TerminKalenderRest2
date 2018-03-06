package database;

import gui.MemoCalendar;
import gui.TerminBox;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TerminDAO {

    TerminBox terminBox = new TerminBox();

    public List<Termin> findAll() {
        List<Termin> list = new ArrayList<Termin>();
        Connection c = null;
        String sql = "SELECT * FROM Termin";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<Termin> findByBeschreibung(String beschreibung) {
        List<Termin> list = new ArrayList<Termin>();
        Connection c = null;
        String sql = "SELECT * FROM Termin WHERE Beschreibung LIKE '%'|| ? || '%'";
        try {
            c = ConnectionHelper.getConnection();
            // prepareStatement creates a PreparedStatement object for sending
            // parameterized SQL statements to the database.
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, beschreibung);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public Termin findById(int id) {
        String sql = "SELECT * FROM Termin WHERE id = ?";
        Termin termin = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                termin = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return termin;
    }


   //
    public Termin create(Termin termin) {


        // Initialisieren Value
        String strBeschreibung = terminBox.getBeschreibung();
        String strOrt = terminBox.getWhere();
        java.util.Date strVon = terminBox.getFrom();
        java.util.Date strBis = terminBox.getUntil();


        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO Termin (Beschreibung, Ort, Von, Bis) VALUES (?, ?, ?, ?)",
                    new String[] { "ID" });
            ps.setString(1, strBeschreibung);
            ps.setString(2, strOrt);
            ps.setTimestamp(3, new java.sql.Timestamp(strVon.getTime()));
            ps.setTimestamp(4, new java.sql.Timestamp(strBis.getTime()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            termin.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return termin;
    }

    public Termin save(Termin termin) {
        return termin.getId() > 0 ? update(termin) : create(termin);
    }


    public Termin update(Termin termin) {

        String strBeschreibung = terminBox.getBeschreibung();
        String strOrt = terminBox.getWhere();
        java.util.Date strVon = terminBox.getFrom();
        java.util.Date strBis = terminBox.getUntil();

        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement
                    ("UPDATE Termin SET Beschreibung =?, Ort =?, Von =?, Bis =? WHERE id =?");
            ps.setString(1, strBeschreibung);
            ps.setString(2, strOrt);
            ps.setTimestamp(3, new java.sql.Timestamp(strVon.getTime()));
            ps.setTimestamp(4, new java.sql.Timestamp(strBis.getTime()));
            ps.setInt(5, termin.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return termin;
    }

    /**
    @return whether deleting the row was successful
    */
    public boolean remove(int id) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Termin WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    Termin processRow(ResultSet rs) throws SQLException {
        Termin termin = new Termin();
        termin.setId(rs.getInt("ID"));
        termin.setBeschreibung(rs.getString("Beschreibung"));
        termin.setOrt(rs.getString("Ort"));
        termin.setVon(rs.getTimestamp("Von"));
        termin.setBis(rs.getTimestamp("Bis"));


        return termin;
    }


}
