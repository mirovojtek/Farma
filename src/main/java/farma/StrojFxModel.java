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

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty vyrobca = new SimpleStringProperty();
    private StringProperty typ = new SimpleStringProperty();
    private StringProperty kategoria = new SimpleStringProperty();
    private DoubleProperty cena = new SimpleDoubleProperty();
    private ObjectProperty<LocalDate> datum = new SimpleObjectProperty();
    private ObjectProperty<List<Oprava>> opravy = new SimpleObjectProperty();
    private ObjectProperty<List<Tankovanie>> tankovania = new SimpleObjectProperty();
    private StringProperty popis = new SimpleStringProperty();

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

    public ObjectProperty<LocalDate> datumProperty() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum.set(datum);
    }

    public LocalDate getDatum() {
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

    public StringProperty popisProperty() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis.set(popis);
    }

    public String getPopis() {
        return popis.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public Stroj getStroj() {
        Stroj stroj = new Stroj();
        stroj.setId(getId());
        stroj.setVyrobca(getVyrobca());
        stroj.setTyp(getTyp());
        stroj.setKategoria(getKategoria());
        stroj.setCena(getCena());
        stroj.setDatum(getDatum());
        stroj.setOpravy(getOpravy());
        stroj.setTankovania(getTankovania());
        stroj.setPopis(getPopis());
        return stroj;
    }

    public void setStroj(Stroj stroj) {
        setId(stroj.getId());
        setVyrobca(stroj.getVyrobca());
        setTyp(stroj.getTyp());
        setKategoria(stroj.getKategoria());
        setCena(stroj.getCena());
        setDatum(stroj.getDatum());
        setOpravy(stroj.getOpravy());
        setTankovania(stroj.getTankovania());
        setPopis(stroj.getPopis());
    }

    public void newStroj() {
        setId(0);
        setVyrobca(null);
        setTyp(null);
        setKategoria(null);
        setCena(0.0);
        setDatum(null);
        setOpravy(null);
        setTankovania(null);
        setPopis(null);
    }
}
