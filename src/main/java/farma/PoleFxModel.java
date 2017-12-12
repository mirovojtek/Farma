package farma;

import java.time.LocalDateTime;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PoleFxModel {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty parcela = new SimpleStringProperty();
    private IntegerProperty vymera = new SimpleIntegerProperty();
    private StringProperty typ = new SimpleStringProperty();
    private ObjectProperty<LocalDateTime> datumNadobudnutia = new SimpleObjectProperty<>();
    private DoubleProperty cena = new SimpleDoubleProperty();

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public Integer getId() {
        return id.get();
    }

    public StringProperty parcelaProperty() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela.set(parcela);
    }

    public String getParcela() {
        return parcela.get();
    }

    public IntegerProperty vymeraProperty() {
        return vymera;
    }

    public void setVymera(Integer vymera) {
        this.vymera.set(vymera);
    }

    public Integer getVymera() {
        return vymera.get();
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

    public ObjectProperty<LocalDateTime> datumNadobudnutiaProperty() {
        return datumNadobudnutia;
    }

    public void setDatumNadobudnutia(LocalDateTime datumNadobudnutia) {
        this.datumNadobudnutia.set(datumNadobudnutia);
    }

    public LocalDateTime getDatumNadobudnutia() {
        return datumNadobudnutia.get();
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

    public Pole getPole() {
        Pole pole = new Pole();
        pole.setId(getId());
        pole.setParcela(getParcela());
        pole.setVymera(getVymera());
        pole.setTyp(getTyp());
        pole.setDatumNadobudnutia(getDatumNadobudnutia());
        pole.setCena(getCena());
        return pole;
    }

    public void setPole(Pole pole) {
        setId(pole.getId());
        setParcela(pole.getParcela());
        setVymera(pole.getVymera());
        setTyp(pole.getTyp());
        setDatumNadobudnutia(pole.getDatumNadobudnutia());
        setCena(pole.getCena());
    }

    public void newPole() {
        setId(0);
        setParcela(null);
        setVymera(0);
        setTyp(null);
        setDatumNadobudnutia(null);
        setCena(0.0);
    }
}
