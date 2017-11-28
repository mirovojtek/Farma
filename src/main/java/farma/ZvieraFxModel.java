package farma;

import farma.Zviera;
import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ZvieraFxModel {

    private StringProperty registracneCislo = new SimpleStringProperty();
    private StringProperty druh = new SimpleStringProperty();
    private StringProperty plemeno = new SimpleStringProperty();
    private StringProperty pohlavie = new SimpleStringProperty();
    private ObjectProperty<LocalDate> datumNarodenia = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> datumNadobudnutia = new SimpleObjectProperty<>();
    private DoubleProperty kupnaCena = new SimpleDoubleProperty();

    public StringProperty registracneCisloProperty() {
        return registracneCislo;
    }

    public void setRegistracneCislo(String registracneCislo) {
        this.registracneCislo.set(registracneCislo);
    }

    public String getRegistracneCislo() {
        return registracneCislo.get();
    }

    public StringProperty druhProperty() {
        return druh;
    }

    public void setDruh(String druh) {
        this.druh.set(druh);
    }

    public String getDruh() {
        return druh.get();
    }

    public StringProperty plemenoProperty() {
        return plemeno;
    }

    public void setPlemeno(String plemeno) {
        this.plemeno.set(plemeno);
    }

    public String getPlemeno() {
        return plemeno.get();
    }

    public StringProperty pohlavieProperty() {
        return pohlavie;
    }

    public void setPohlavie(String pohlavie) {
        this.pohlavie.set(pohlavie);
    }

    public String getPohlavie() {
        return pohlavie.get();
    }

    public ObjectProperty<LocalDate> datumNarodeniaProperty() {
        return datumNarodenia;
    }

    public void setDatumNarodenia(LocalDate datumNarodenia) {
        this.datumNarodenia.set(datumNarodenia);
    }

    public LocalDate getDatumNarodenia() {
        return datumNarodenia.get();
    }

    public ObjectProperty<LocalDate> datumNadobudnutiaProperty() {
        return datumNadobudnutia;
    }

    public void setDatumNadobudnutia(LocalDate datumNadobudnutia) {
        this.datumNadobudnutia.set(datumNadobudnutia);
    }

    public LocalDate getDatumNadobudnutia() {
        return datumNadobudnutia.get();
    }

    public DoubleProperty kupnaCenaProperty() {
        return kupnaCena;
    }

    public void setKupnaCena(Double kupnaCena) {
        this.kupnaCena.set(kupnaCena);
    }

    public Double getKupnaCena() {
        return kupnaCena.get();
    }

    public Zviera getZviera() {
        Zviera zviera = new Zviera();
        zviera.setRegistracneCislo(getRegistracneCislo());
        zviera.setDruh(getDruh());
        zviera.setPlemeno(getPlemeno());
        zviera.setPohlavie(getPohlavie());
        zviera.setDatumNarodenia(getDatumNarodenia());
        zviera.setDatumNadobudnutia(getDatumNadobudnutia());
        zviera.setKupnaCena(getKupnaCena());
        return zviera;
    }

    public void setZviera(Zviera zviera) {
        setRegistracneCislo(zviera.getRegistracneCislo());
        setDruh(zviera.getDruh());
        setPlemeno(zviera.getPlemeno());
        setPohlavie(zviera.getPohlavie());
        setDatumNarodenia(zviera.getDatumNarodenia());
        setDatumNadobudnutia(zviera.getDatumNadobudnutia());
        setKupnaCena(zviera.getKupnaCena());
    }

    public void newZviera() {
        setRegistracneCislo(null);
        setDruh(null);
        setPlemeno(null);
        setPohlavie(null);
        setDatumNarodenia(null);
        setDatumNadobudnutia(null);
        setKupnaCena(0.0);
    }

}
