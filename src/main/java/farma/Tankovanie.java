package farma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tankovanie {

    private int id;
    private int strojId;
    private double mnozstvo;
    private LocalDate datum;
    
    private String fDatum;

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

    public LocalDate getDatum() {
        return datum;
    }
    
    public String getFDatum() {
        DateTimeFormatter formatovac = DateTimeFormatter.
                ofPattern("dd.MM.yyyy");
        return datum.format(formatovac);
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return id + " " + strojId + " " + mnozstvo + " " + datum;
    }

}
