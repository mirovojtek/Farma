package farma;

import java.time.LocalDate;
import java.util.List;

public interface FinancieDao {

    List<Financie> getAll();

    Financie add(Financie financie);

    boolean deletePodlaId(int id);

    Financie findById(int id);

    List<Financie> getAllByDate(LocalDate localDate);

}
