package farma;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tankovanie {

    private int id;
    private int strojId;
    private double mnozstvo;
    private LocalDateTime datum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrojId() {
        return strojId;
    }

    public void setStrojId(int strojId) {
        this.strojId = strojId;
    }

    public double getMnozstvo() {
        return mnozstvo;
    }

    public void setMnozstvo(double mnozstvo) {
        this.mnozstvo = mnozstvo;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return id + " " + strojId + " " + mnozstvo + " " + datum;
    }

}
