/**
 * Eridho Buffery Rollian
 *
 */
package database;

import data.Termin;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestTerminDAO {

    TerminDAO terminDAO;

    @Before
    public void setUp() {
        terminDAO = new TerminDAO();
    }

    @Test
    public void testListAll() {
        for (Termin t : terminDAO.findAll()) {
            System.out.println(t);
        }
    }

    @Test
    public void testFindByBeschreibung() {
        for (Termin t : terminDAO.findByBeschreibung("MUSTER")) {
            System.out.println(t);
        }
    }

    @Test
    public void realityCheck() {
        TeilnehmerDAO teilnehmerDAO = new TeilnehmerDAO();
        List<Termin> result = teilnehmerDAO.getTermine(4377, "2018-03-13");
    }
}
