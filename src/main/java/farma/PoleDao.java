package farma;

import java.util.List;

public interface PoleDao {

    List<Pole> getAll();

    Pole add(Pole pole);

    boolean deletePodlaId(int id);
    
    Pole findById(int id);

}
