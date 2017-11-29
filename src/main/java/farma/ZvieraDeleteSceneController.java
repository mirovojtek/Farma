package farma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ZvieraDeleteSceneController {

    private String rc;
    ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    ZvieraFxModel zvieraFxModel = new ZvieraFxModel();

    public ZvieraDeleteSceneController(String rc) {
        this.rc = rc;
    }

    @FXML
    private Button anoButton;

    @FXML
    private Button nieButton;

    @FXML
    void initialize() {

        anoButton.setOnAction(eh -> {
            zvieraDao.deleteByRegistracneCislo(rc);
            anoButton.getScene().getWindow().hide();
        });

        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });

    }

}
