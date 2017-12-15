package farma;

import java.util.List;

public interface OpravaDao {

    List<Oprava> getAllPodlaIdStroja(int idStroja);

    List<Oprava> getAll();

    void add(Oprava oprava);

    void deletePodlaId(int id);

    void deleteAllPodlaIdStroja(int idStroja);

    Oprava findById(int id);

    void pridajPopis(Oprava oprava);
}
