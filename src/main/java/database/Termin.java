package database;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class Termin {

        private int id;
        private Timestamp von;
        private Timestamp bis;
        private String beschreibung;
        private String ort;


    public Termin() {}

    public Termin(int id, Timestamp von, Timestamp bis, String ort, String beschreibung) {

        this.id = id;
        this.von = von;
        this.bis = bis;
        this.ort = ort;
        this.beschreibung = beschreibung;
    }

    public Termin(Timestamp von, Timestamp bis, String ort, String beschreibung) {

        this.von = von;
        this.bis = bis;
        this.ort = ort;
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        return "Termin{" +
                "id=" + id +
                ", von=" + von +
                ", bis=" + bis +
                ", beschreibung='" + beschreibung + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }

    /**
     * Methode um die eingeladenen Nutzer des Termins zurückzugeben
     * @return List<Nutzer> Liste der Teilnehmer
     */
    public List<Nutzer> getEingeladene(){

        EinladungDAO einladung = new EinladungDAO();

        return einladung.getEingeladene(this);

    }

    /**
     * Methode um die Teilnehmer des Termins zurückzugeben
     * @return List<Nutzer> Liste der Teilnehmer
     */
    public List<Nutzer> getTeilnehmer(){

        TeilnehmerDAO teilnehmer = new TeilnehmerDAO();

        return teilnehmer.getTeilnehmer(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    public Timestamp getVon() {
        return von;
    }

    public void setVon(Timestamp von) {
        this.von = von;
    }

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    public Timestamp getBis() {
        return bis;
    }

    public void setBis(Timestamp bis) {
        this.bis = bis;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Termin termin = (Termin) o;
        return id == termin.id &&
                Objects.equals(von, termin.von) &&
                Objects.equals(bis, termin.bis) &&
                Objects.equals(beschreibung, termin.beschreibung) &&
                Objects.equals(ort, termin.ort);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, von, bis, beschreibung, ort);
    }
}
