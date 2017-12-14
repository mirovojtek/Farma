package farma;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pole {

    private int id;
    private String typParcely;
    private String cisloParcely;
    private double vymera;
    private String typPozemku;
    private String vlastnictvo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypParcely() {
        return typParcely;
    }

    public void setTypParcely(String typParcely) {
        this.typParcely = typParcely;
    }

    public String getCisloParcely() {
        return cisloParcely;
    }

    public void setCisloParcely(String parcela) {
        this.cisloParcely = parcela;
    }

    public double getVymera() {
        return vymera;
    }

    public void setVymera(double vymera) {
        this.vymera = vymera;
    }

    public String getTypPozemku() {
        return typPozemku;
    }

    public void setTypPozemku(String typ) {
        this.typPozemku = typ;
    }

    public String getVlastnictvo() {
        return vlastnictvo;
    }

    public void setVlastnictvo(String vlastnictvo) {
        this.vlastnictvo = vlastnictvo;
    }

    @Override
    public String toString() {
        return id + " " + typParcely + " " + cisloParcely + " " + vymera + " " + typPozemku;
    }

}
