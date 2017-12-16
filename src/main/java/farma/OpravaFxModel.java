
package farma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OpravaFxModel {
 
    private Stroj stroj;
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty porucha = new SimpleStringProperty();
    private StringProperty popis = new SimpleStringProperty();
    private DoubleProperty cena = new SimpleDoubleProperty();
    private ObjectProperty<LocalDate> datum = new SimpleObjectProperty();

    public OpravaFxModel(Stroj stroj){
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

    public StringProperty poruchaProperty() {
        return porucha;
    }

    public void setPorucha(String porucha) {
        this.porucha.set(porucha);
    }
    
    public String getPorucha(){
        return porucha.get();
    }

    public StringProperty popisProperty() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis.set(popis);
    }
    
    public String getPopis(){
        return popis.get();
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
    
    public Oprava getOprava(){
        Oprava oprava = new Oprava();
        oprava.setId(getId());
        oprava.setCena(getCena());
        oprava.setDatum(getDatum());
        oprava.setPopis(getPopis());
        oprava.setPorucha(getPorucha());
        oprava.setIdStroj(stroj.getId());
        return oprava;
    }
     public void setOprava(Oprava oprava){
          setId(oprava.getId());
          setCena(oprava.getCena());
          setDatum(oprava.getDatum());
          setPopis(oprava.getPopis());
          setPorucha(oprava.getPorucha());    
     }
     
     public void newOprava(){
         setId(0);
         setCena(0.0);
         setDatum(null);
         setPopis(null);
         setPorucha(null);
     }
    
}
