package farma;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Financie {

    private int id;
    private LocalDateTime datum;
    private double suma;
    private String typ;
    private String popis;
    private String fDatum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public String getFDatum() {
        if (datum == null) {
            return "null";
        }
        DateTimeFormatter formatovac = DateTimeFormatter.
                ofPattern("dd.MM.yyyy");
        return datum.format(formatovac);
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

}
