package farma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Zviera {

    private int id;
    private String registracneCislo;
    private String druh;
    private String plemeno;
    private String pohlavie;
    private LocalDate datumNarodenia;
    private LocalDate datumNadobudnutia;
    private double kupnaCena;
    
    private String fDatumNarodenia;
    private String fDatumNadobudnutia;

    public String getfDatumNarodenia() {
        return fDatumNarodenia;
    }

    public void setfDatumNarodenia(String fDatumNarodenia) {
        this.fDatumNarodenia = fDatumNarodenia;
    }

    public String getfDatumNadobudnutia() {
        return fDatumNadobudnutia;
    }

    public void setfDatumNadobudnutia(String fDatumNadobudnutia) {
        this.fDatumNadobudnutia = fDatumNadobudnutia;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistracneCislo() {
        return registracneCislo;
    }

    public void setRegistracneCislo(String registracneCislo) {
        this.registracneCislo = registracneCislo;
    }

    public String getDruh() {
        return druh;
    }

    public void setDruh(String druh) {
        this.druh = druh;
    }

    public String getPlemeno() {
        return plemeno;
    }

    public void setPlemeno(String plemeno) {
        this.plemeno = plemeno;
    }

    public String getPohlavie() {
        return pohlavie;
    }

    public void setPohlavie(String pohlavie) {
        this.pohlavie = pohlavie;
    }

    public LocalDate getDatumNarodenia() {
        return datumNarodenia;
    }

    public String getFDatumNarodenia() {
        if (datumNarodenia == null) {
            return "null";
        }
        DateTimeFormatter formatovac = DateTimeFormatter.
                ofPattern("dd.MM.yyyy");
        return datumNarodenia.format(formatovac);
    }

    public void setDatumNarodenia(LocalDate datumNarodenia) {
        this.datumNarodenia = datumNarodenia;
    }

    public LocalDate getDatumNadobudnutia() {
        return datumNadobudnutia;
    }

    public String getFDatumNadobudnutia() {
        if (datumNadobudnutia == null) {
            return "null";
        }
        DateTimeFormatter formatovac = DateTimeFormatter.
                ofPattern("dd.MM.yyyy");
        return datumNadobudnutia.format(formatovac);
    }

    public void setDatumNadobudnutia(LocalDate datumNadobudnutia) {
        this.datumNadobudnutia = datumNadobudnutia;
    }

    public double getKupnaCena() {
        return kupnaCena;
    }

    public void setKupnaCena(double kupnaCena) {
        this.kupnaCena = kupnaCena;
    }

    @Override
    public String toString() {
        return registracneCislo + " "
                + druh + " " + plemeno + " " + pohlavie + " " + datumNarodenia + " " + datumNadobudnutia + " " + kupnaCena;
    }

}
