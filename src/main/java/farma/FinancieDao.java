package farma;

import java.util.List;

public interface FinancieDao {
    
    List<Financie> getAll();

    Financie add(Financie financie);

    boolean deletePodlaId(int id);
    
    Financie findById(int id);
    
}
