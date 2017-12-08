package farma;

import java.util.List;

public interface StrojDao {
    
    void add(Stroj stroj);

    Stroj findById(int id);

    List<Stroj> getAll();

    boolean deleteById(int id);
    
    List<String> getVyrobcovia();
    
    List<String> getTypy();
    
    List<String> getTypyPodlaVyrobcu(String vyrobca);
    
    List<String> getKategorie();
    
    List<String> getRokyNadobudnutia();
    
    List<Stroj> rozsireneVyhladavanie(String vyrobca, String typ, String kategoria, String rokNadobudnutia);        
    
}
