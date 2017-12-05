package farma;

import java.util.List;

public interface TankovanieDao {
    
    List<Tankovanie> getAllPodlaIdStroja(int idStroja);
    
    List<Tankovanie> getAll();
    
    void add(Tankovanie tankovanie);
    
    void deletePodlaId(int id);
    
    void deleteAllPodlaId(int id);
}
