package farma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class Stroj {

    private int id;
    private String vyrobca;
    private String typ;
    private String kategoria;
    private double cena;
    private LocalDateTime datum;
    private List<Oprava> opravy = new ArrayList<Oprava>();
    private List<Tankovanie> tankovania = new ArrayList<Tankovanie>();
    private String popis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVyrobca() {
        return vyrobca;
    }

    public void setVyrobca(String vyrobca) {
        this.vyrobca = vyrobca;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public List<Oprava> getOpravy() {
        return opravy;
    }

    public void setOpravy(List<Oprava> opravy) {
        this.opravy = opravy;
    }

    public List<Tankovanie> getTankovania() {
        return tankovania;
    }

    public void setTankovania(List<Tankovanie> tankovania) {
        this.tankovania = tankovania;
    }

    public String getPopis(){
        return popis;
    }
    public void setPopis(String popis){
        this.popis = popis;
    }
    
    @Override
    public String toString() {
        return id + " " + vyrobca + " " + typ + " " + kategoria + " " + cena + " " + datum;
    }

}
