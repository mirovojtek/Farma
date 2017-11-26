
package storage;

import farma.ZvieraDao;
import farma.Zviera;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MemoryZvieraDao implements ZvieraDao {
    
    private List<Zviera> zvierata = new ArrayList<>();
    
    public MemoryZvieraDao(){
        Zviera zviera = new Zviera();
        zviera.setDruh("krava");
        zviera.setDatumNarodenia(LocalDate.of(2015, Month.MARCH, 5));
        zviera.setPohlavie("f");
        zviera.setRegistracneCislo("8551247");
        zvierata.add(zviera);
        
        zviera = new Zviera();
        zviera.setDruh("prasa");
        zviera.setDatumNarodenia(LocalDate.of(2016, Month.JULY, 17));
        zviera.setPohlavie("m");
        zviera.setRegistracneCislo("453257");
        zviera.setKupnaCena(50.0);
        zvierata.add(zviera);
        
    }
    
    @Override
       public List<Zviera> getAll(){
           return zvierata;
       }
       
    @Override
       public Zviera add(Zviera zviera){
           zvierata.add(zviera);
           return zviera;
       }
       
    @Override
       public Zviera findByRegCislo(String rc){
           for(Zviera z : zvierata){
               if(z.getRegistracneCislo().equals(rc)){
                   return z;
               }
           }
           return null;
       }
       
    @Override
       public Zviera remove(String rc){
           Zviera z = findByRegCislo(rc);
           zvierata.remove(z);
           return z;
       }
}
