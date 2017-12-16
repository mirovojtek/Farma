package farma;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FinancieVyberDnaSceneController {

    LocalDate date;

    void setDate(LocalDate datum) {
        this.date = datum;
    }

    LocalDate getDate() {
        return date;
    }

    @FXML
    private DatePicker denDatePicker;

    @FXML
    private Button vybratDenButton;

    @FXML
    void initialize() {

        vybratDenButton.setOnAction(eh -> {
            if (denDatePicker.getValue() != null && ((denDatePicker.getValue().compareTo(LocalDate.now()) <= 0))) {
                setDate(denDatePicker.getValue());
                vybratDenButton.getScene().getWindow().hide();
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
