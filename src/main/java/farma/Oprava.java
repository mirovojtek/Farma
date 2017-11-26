package farma;

import java.time.LocalDate;

public class Oprava {

    private int id;
    private int idStroj;
    private LocalDate datum;
    private double cena;
    private String porucha;
    private String popis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStroj() {
        return idStroj;
    }

    public void setIdStroj(int idStroj) {
        this.idStroj = idStroj;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getPorucha() {
        return porucha;
    }

    public void setPorucha(String porucha) {
        this.porucha = porucha;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @Override
    public String toString() {
        if (popis == null) {
            return id + " " + idStroj + " " + datum + " " + cena + " " + porucha;
        }
        return id + " " + idStroj + " " + datum + " " + cena + " " + porucha + " " + popis;
    }

}
