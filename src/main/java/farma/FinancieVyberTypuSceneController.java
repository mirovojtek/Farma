package farma;

import java.time.LocalDate;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FinancieVyberTypuSceneController {

    private String typ;
    private final String[] poleTypov = {"výdaj", "príjem"};

    void setTyp(String t) {
        this.typ = t;
    }

    String getTyp() {
        return typ;
    }

    @FXML
    private ComboBox<String> typComboBox;

    @FXML
    private Button vybratTypButton;

    @FXML
    void initialize() {
        typComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(poleTypov)));
        vybratTypButton.setOnAction(eh -> {
            if (typComboBox.getValue() != null) {
                setTyp(typComboBox.getValue());
                vybratTypButton.getScene().getWindow().hide();
            } else {
                NespravneVyplnanieController controller = new NespravneVyplnanieController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("NespravneVyplnenie.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Nesprávne vyplnenie údajov");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } catch (Exception e) {
                }
            }
        });
    }
}
