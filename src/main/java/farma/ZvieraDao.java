package farma;

import java.util.List;

public interface ZvieraDao {

    Zviera add(Zviera zviera);

    Zviera findByRegistracneCislo(String rc);

    List<Zviera> getAll();

    boolean deleteByRegistracneCislo(String rc);

    void pridajPopis(Zviera zviera);
    
    List<String> getDruhy();
    
    List<String> getPohlavia();
    
    List<String> getPlemena();
    
    List<String> getRokyNarodenia();
    
    List<String> getRokyNadobudnutia();
    
    List<Zviera> rozsireneVyhladavanie(String druh, String plemeno, String rokNarodenia, String rokNadobudnutia, String pohlavie);
}
