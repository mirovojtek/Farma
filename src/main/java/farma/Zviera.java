package farma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Zviera {

    private int id;
    private String registracneCislo;
    private String druh;
    private String plemeno;
    private String pohlavie;
    private LocalDateTime datumNarodenia;
    private LocalDateTime datumNadobudnutia;
    private double kupnaCena;
    private String popis;

    private String fDatumNarodenia;
    private String fDatumNadobudnutia;

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

    public LocalDateTime getDatumNarodenia() {
        return datumNarodenia;
    }

    public String getFDatumNarodenia() {
        DateTimeFormatter formatovac = DateTimeFormatter.
                ofPattern("dd.MM.yyyy");
        return datumNarodenia.format(formatovac);
    }

    public void setDatumNarodenia(LocalDateTime datumNarodenia) {
        this.datumNarodenia = datumNarodenia;
    }

    public LocalDateTime getDatumNadobudnutia() {
        return datumNadobudnutia;
    }

    public String getFDatumNadobudnutia() {
        DateTimeFormatter formatovac = DateTimeFormatter.
                ofPattern("dd.MM.yyyy");
        return datumNadobudnutia.format(formatovac);
    }

    public void setDatumNadobudnutia(LocalDateTime datumNadobudnutia) {
        this.datumNadobudnutia = datumNadobudnutia;
    }

    public double getKupnaCena() {
        return kupnaCena;
    }

    public void setKupnaCena(double kupnaCena) {
        this.kupnaCena = kupnaCena;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @Override
    public String toString() {
        return registracneCislo + " "
                + druh + " " + plemeno + " " + pohlavie + " " + datumNarodenia + " " + datumNadobudnutia + " " + kupnaCena + "" + popis;
    }

}
