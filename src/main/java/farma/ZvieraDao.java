package farma;

import farma.Zviera;
import java.util.List;

public interface ZvieraDao {

    Zviera add(Zviera zviera);

    Zviera findByRegistracneCislo(int rc);   

    List<Zviera> getAll();

    boolean deleteByRegistracneCislo(int rc);
    
}
