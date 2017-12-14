package farma;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FinancieFxModel {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> datum = new SimpleObjectProperty<>();
    private DoubleProperty suma = new SimpleDoubleProperty();
    private StringProperty typ = new SimpleStringProperty();
    private StringProperty popis = new SimpleStringProperty();

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public Integer getId() {
        return id.get();
    }
    
    

    public ObjectProperty<LocalDate> datumProperty() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum.set(datum);
    }

    public LocalDate getDatum(){
        return datum.get();
    }
      
    public Double getSuma() {
        return suma.get();
    }

    public void setSuma(Double suma) {
        this.suma.set(suma);
    }
    
    public DoubleProperty sumaProperty() {
        return suma;
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

    public StringProperty popisProperty() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis.set(popis);
    }
    
     public String getPopis() {
        return popis.get();
    }
     
     public Financie getPolozka(){
        Financie polozka = new Financie();
        polozka.setDatum(getDatum());
        polozka.setSuma(getSuma());
        polozka.setTyp(getTyp());
        polozka.setPopis(getPopis());
        return polozka;
    }
     public void setFinancie(Financie polozka){
          setId(polozka.getId());
          setSuma(polozka.getSuma());
          setDatum(polozka.getDatum());
          setTyp(polozka.getTyp());
          setPopis(polozka.getPopis());      
     }
     
     public void newFinancie(){
         setId(0);
         setSuma(0.0);
         setDatum(null);
         setTyp(null);
         setPopis(null);
     }
    
    
     
}
