package farma;

import java.util.List;

public interface ZvieraDao {

    Zviera add(Zviera zviera);

    Zviera findByRegistracneCislo(String rc);

    List<Zviera> getAll();

    boolean deleteByRegistracneCislo(String rc);

    void pridajPopis(Zviera zviera);
}
