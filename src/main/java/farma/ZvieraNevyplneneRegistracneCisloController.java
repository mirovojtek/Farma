package farma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ZvieraNevyplneneRegistracneCisloController {

    @FXML
    private Button OKButton;

    @FXML
    void initialize() {
        OKButton.setOnAction(eh -> {
            OKButton.getScene().getWindow().hide();
        });
    }
}
