package farma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FinancieDeleteSceneController {

    private int id;
    FinancieDao financieDao = DaoFactory.INSTANCE.getFinancieDao();
    FinancieFxModel financieFxModel = new FinancieFxModel();
    private boolean zmazanaPolozka;

    public FinancieDeleteSceneController(int id) {
        zmazanaPolozka = false;
        this.id = id;
    }

    boolean getZmazanaPolozka() {
        return zmazanaPolozka;
    }

    @FXML
    private Button anoButton;

    @FXML
    private Button nieButton;

    @FXML
    void initialize() {
        anoButton.setOnAction(eh -> {
            financieDao.deletePodlaId(id);
            zmazanaPolozka = true;
            anoButton.getScene().getWindow().hide();
        });

        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });
    }

}
