package farma;

import java.util.List;

public interface StrojDao {
    
    void add(Stroj stroj);

    Stroj findById(int id);

    List<Stroj> getAll();

    boolean deleteById(int id);
    
    void pridajPopis(Stroj stroj);
}
