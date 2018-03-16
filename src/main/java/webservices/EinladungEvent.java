/**
 *  Györgyi Palatinus
 */
package webservices;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EinladungEvent {

    public enum EinladungEventType {EINLADUNG, ANGENOMMEN, ABGELEHNT}

    private EinladungEventType type;
    private int wer;
    private int wen;
    private int terminID;

    public EinladungEvent(EinladungEventType type, int wer, int wen, int terminID) {
        this.type = type;
        this.wer = wer;
        this.wen = wen;
        this.terminID = terminID;
    }

    public EinladungEvent() {
    }

    public EinladungEventType getType() {
        return type;
    }

    public int getWer() {
        return wer;
    }

    public int getWen() {
        return wen;
    }

    public int getTerminID() {
        return terminID;
    }

    public void setType(EinladungEventType type) {
        this.type = type;
    }

    public void setWer(int wer) {
        this.wer = wer;
    }

    public void setWen(int wen) {
        this.wen = wen;
    }

    public void setTerminID(int terminID) {
        this.terminID = terminID;
    }

    @Override
    public String toString() {
        return "EinladungEvent{" +
                "type=" + type +
                ", wer=" + wer +
                ", wen=" + wen +
                ", terminID=" + terminID +
                '}';
    }
}
