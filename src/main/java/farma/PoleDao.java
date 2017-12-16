package farma;

import java.util.List;

public interface PoleDao {

    List<Pole> getAll();

    Pole add(Pole pole);

    boolean deletePodlaId(int id);
    
    Pole findById(int id);

    List<String> getTypyParciel();
    
    List<String> getCislaParcielPodlaTypu(String typParcely);
    
    List<String> getTypyPozemkov();
    
    List<String> getVlastnictvo();
    
    List<Pole> rozsireneVyhladavanie(String typParcely, String cisloParcely, String typPozemku, String Vlastnictvo);
}