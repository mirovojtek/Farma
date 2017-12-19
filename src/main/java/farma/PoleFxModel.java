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
    private StringProperty typParcely = new SimpleStringProperty();
    private StringProperty cisloParcely = new SimpleStringProperty();
    private IntegerProperty vymera = new SimpleIntegerProperty();
    private StringProperty typPozemku = new SimpleStringProperty();
    private StringProperty vlastnictvo = new SimpleStringProperty();

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public Integer getId() {
        return id.get();
    }

    public StringProperty typParcelyProperty() {
        return typParcely;
    }

    public void setTypParcely(String parcela) {
        this.typParcely.set(parcela);
    }

    public String getTypParcely() {
        return typParcely.get();
    }

    public StringProperty cisloParcelyProperty() {
        return cisloParcely;
    }

    public void setCisloParcely(String parcela) {
        this.cisloParcely.set(parcela);
    }

    public String getCisloParcely() {
        return cisloParcely.get();
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

    public StringProperty typPozemkuProperty() {
        return typPozemku;
    }

    public void setTypPozemku(String typ) {
        this.typPozemku.set(typ);
    }

    public String getTypPozemku() {
        return typPozemku.get();
    }

    public StringProperty vlastnictvoProperty() {
        return vlastnictvo;
    }

    public void setVlastnictvo(String vlastnictvo) {
        this.vlastnictvo.set(vlastnictvo);
    }

    public String getVlastnictvo() {
        return vlastnictvo.get();
    }

    public Pole getPole() {
        Pole pole = new Pole();
        pole.setId(getId());
        pole.setTypParcely(getTypParcely());
        pole.setCisloParcely(getCisloParcely());
        pole.setVymera(getVymera());
        pole.setTypPozemku(getTypPozemku());
        pole.setVlastnictvo(getVlastnictvo());
        return pole;
    }

    public void setPole(Pole pole) {
        setId(pole.getId());
        setTypParcely(pole.getTypParcely());
        setCisloParcely(pole.getCisloParcely());
        setVymera(pole.getVymera());
        setTypPozemku(pole.getTypPozemku());
        setVlastnictvo(pole.getVlastnictvo());
    }

    public void newPole() {
        setId(0);
        setTypParcely(null);
        setCisloParcely(null);
        setVymera(0);
        setTypPozemku(null);
        setVlastnictvo(null);
    }
}
