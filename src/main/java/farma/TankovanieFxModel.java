
package farma;

import java.time.LocalDateTime;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


public class TankovanieFxModel {
    
    private Stroj stroj;
    private IntegerProperty id = new SimpleIntegerProperty();
    private DoubleProperty mnozstvo = new SimpleDoubleProperty();
    private ObjectProperty<LocalDateTime> datum = new SimpleObjectProperty();
    
    public TankovanieFxModel(Stroj stroj){
        this.stroj = stroj;
    }
    
    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    public int getId(){
        return id.get();
    }
    
    public DoubleProperty mnozstvoProperty() {
        return mnozstvo;
    }

    public void setMnozstvo(Double mnozstvo) {
        this.mnozstvo.set(mnozstvo);
    }
    
    public Double getMnozstvo() {
        return mnozstvo.get();
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
    
    public Tankovanie getTankovanie(){
        Tankovanie tankovanie = new Tankovanie();
        tankovanie.setId(getId());
        tankovanie.setMnozstvo(getMnozstvo());
        tankovanie.setDatum(getDatum());
        tankovanie.setStrojId(stroj.getId());
        return tankovanie;
    }
 
    public void setTankovanie(Tankovanie tankovanie){
        setId(tankovanie.getId());
        setMnozstvo(tankovanie.getMnozstvo());
        setDatum(tankovanie.getDatum());
    }
    
    public void newTankovanie(){
        setId(0);
        setMnozstvo(0.0);
        setDatum(null);
    }
}
