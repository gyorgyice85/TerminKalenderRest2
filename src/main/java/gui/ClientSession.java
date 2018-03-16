/**
 * Eridho Buffery Rollian
 *
 */
package gui;

import client.NutzerHandle;
import client.TerminHandle;
import data.Nutzer;

/**
 * Ein Objekt fuer den GUI Klienten, wo der nutzerID der eingeloggte Nutzer gespeichert ist,
 * und die Termin und Nutzer Handles verfuegbar sind.
 */
public class ClientSession {

    /** Der nutzerID der eingeloggte Nutzer */
    public int nutzerID;
    public Nutzer nutzer;
    public TerminHandle terminHandle;
    public NutzerHandle nutzerHandle;

    public ClientSession() {
        terminHandle = new TerminHandle();
        nutzerHandle = new NutzerHandle();
    }
}
