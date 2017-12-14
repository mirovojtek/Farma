package farma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Financie {

    private int id;
    private LocalDate datum;
    private double suma;
    private String typ;
    private String popis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
