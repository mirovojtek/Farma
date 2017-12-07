
package farma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class StrojFxModel {
    
    private StringProperty vyrobca = new SimpleStringProperty();
    private StringProperty typ = new SimpleStringProperty();
    private StringProperty kategoria = new SimpleStringProperty();
    private DoubleProperty cena = new SimpleDoubleProperty();
    private ObjectProperty<LocalDateTime> datum = new SimpleObjectProperty();
    private ObjectProperty<List<Oprava>> opravy = new SimpleObjectProperty();
    private ObjectProperty<List<Tankovanie>> tankovania = new SimpleObjectProperty();

    public StringProperty vyrobcaProperty() {
        return vyrobca;
    }

    public void setVyrobca(String vyrobca) {
        this.vyrobca.set(vyrobca);
    }
    
    public String getVyrobca() {
        return vyrobca.get();
    }

    public StringProperty typProperty() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ.set(typ);
    }
    
     public String getTyp() {
        return typ.get();
    }

    public StringProperty kategoriaProperty() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria.set(kategoria);
    }
    
    public String getKategoria() {
        return kategoria.get();
    }

    public DoubleProperty cenaProperty() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena.set(cena);
    }
    
    public Double getCena() {
        return cena.get();
    }

    public ObjectProperty<LocalDateTime> datumProperty() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum.set(datum);
    }
    
    public LocalDateTime getDatum() {
        return datum.get();
    }

    public ObjectProperty<List<Oprava>> opravyProperty() {
        return opravy;
    }

    public void setOpravy(List<Oprava> opravy) {
        this.opravy.set(opravy);
    }
    
     public List<Oprava> getOpravy() {
        return opravy.get();
    }


    public ObjectProperty<List<Tankovanie>> tankovaniaProperty() {
        return tankovania;
    }

    public void setTankovania(List<Tankovanie> tankovania) {
        this.tankovania.set(tankovania);
    }
    
    public List<Tankovanie> getTankovania() {
        return tankovania.get();
    }
    
    public Stroj getStroj(){
        Stroj stroj = new Stroj();
        stroj.setVyrobca(getVyrobca());
        stroj.setTyp(getTyp());
        stroj.setKategoria(getKategoria());
        stroj.setCena(getCena());
        stroj.setDatum(getDatum());
        //stroj.setOpravy(getOpravy());
        //stroj.setTankovania(getTankovania());
        return stroj;
    }
    
    public void setStroj(Stroj stroj){
        setVyrobca(stroj.getVyrobca());
        setTyp(stroj.getTyp());
        setKategoria(stroj.getKategoria());
        setCena(stroj.getCena());
        setDatum(stroj.getDatum());
        setOpravy(stroj.getOpravy());
        setTankovania(stroj.getTankovania());
    }
    
    public void newStroj(){
        setVyrobca(null);
        setTyp(null);
        setKategoria(null);
        setCena(0.0);
        setDatum(null);
        setOpravy(null);
        setTankovania(null);
    }
}
