
package storage;
 
import farma.Zviera;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ZvieraFxModel {
    
    private final StringProperty registracneCislo = new SimpleStringProperty();
    private final StringProperty druh = new SimpleStringProperty();
    private final StringProperty pohlavie = new SimpleStringProperty();
    private LocalDate datumNarodenia;
    private double kupnaCena;
    private String plemeno;
    private LocalDate datumNadobudnutia;
    
    

    public String getRegistracneCislo() {
        return registracneCislo.get();
    }

    public void setRegistracneCislo(String registracneCislo) {
        this.registracneCislo.set(registracneCislo);
    }
    
    public StringProperty registracneCisloProperty() {
        return registracneCislo;
    }

    public StringProperty druhProperty() {
        return druh;
    }
    
     public String getDruh() {
        return druh.get();
    }

    public void setDruh(String druh) {
        this.druh.set(druh);
    }

    public StringProperty pohlavieProperty() {
        return pohlavie;
    }
    
    public String getPohlavie() {
        return pohlavie.get();
    }

    public void setPohlavie(String pohlavie) {
        this.pohlavie.set(pohlavie);
    }
    
    public Zviera getZviera(){
        Zviera zviera = new Zviera();
        zviera.setRegistracneCislo(getRegistracneCislo());
        zviera.setDruh(getDruh());
        zviera.setPohlavie(getPohlavie());
        
        zviera.setKupnaCena(kupnaCena);
        zviera.setDatumNarodenia(datumNarodenia);
        zviera.setDatumNadobudnutia(datumNadobudnutia);
        zviera.setPlemeno(plemeno);
        return zviera;
    }
    
    public void setZviera(Zviera zviera){
        kupnaCena = zviera.getKupnaCena();
        datumNarodenia = zviera.getDatumNarodenia();
        datumNadobudnutia = zviera.getDatumNadobudnutia();
        plemeno = zviera.getPlemeno();
        setPohlavie(zviera.getPohlavie());
        setDruh(zviera.getDruh());
        setRegistracneCislo(zviera.getRegistracneCislo());
    }
    
    public void newZviera(){
        datumNadobudnutia = null;
        plemeno = null;
        kupnaCena = 0.0;
        datumNarodenia = null;
        setPohlavie(null);
        setDruh(null);
        setRegistracneCislo(null);
        
    }
    
    
}
