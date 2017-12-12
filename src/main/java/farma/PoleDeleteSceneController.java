package farma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PoleDeleteSceneController {

    private int id;
    PoleDao poleDao = DaoFactory.INSTANCE.getPoleDao();
    PoleFxModel zvieraFxModel = new PoleFxModel();

    public PoleDeleteSceneController(int id) {
        this.id = id;
    }

    @FXML
    private Button anoButton;

    @FXML
    private Button nieButton;

    @FXML
    void initialize() {
        anoButton.setOnAction(eh -> {
            poleDao.deletePodlaId(id);
            anoButton.getScene().getWindow().hide();
        });

        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });
    }

}
