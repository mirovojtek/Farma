package farma;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FinancieZaObdobieSceneController {

    private LocalDate datumOd;
    private LocalDate datumDo;
    private boolean daSa;

    @FXML
    private DatePicker datumOdDatePicker;

    @FXML
    private DatePicker datumDoDatePicker;

    @FXML
    private Button okButton;

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }

    @FXML
    void initialize() {

        okButton.setOnAction(eh -> {
            daSa = true;

            // ošetrenie dátumu OD
            if (datumOdDatePicker.getValue() != null) {
                daSa = daSa && (datumOdDatePicker.getValue().compareTo(LocalDate.now()) <= 0);
                if (daSa) {
                    setDatumOd(datumOdDatePicker.getValue());
                }
            } else {
                setDatumOd(LocalDate.MIN);
                System.out.println(getDatumOd());
            }

            // ošetrenie dátumu DO
            if (datumDoDatePicker.getValue() != null) {
                daSa = daSa && (datumDoDatePicker.getValue().compareTo(LocalDate.now()) <= 0);
                if (daSa) {
                    setDatumDo(datumDoDatePicker.getValue());
                }
            } else {
                setDatumDo(LocalDate.now());
                System.out.println(getDatumDo());
            }

            // ošetrenie dátumov - dátum OD je najviac dátum DO
            if (datumOdDatePicker.getValue() != null && datumOdDatePicker.getValue() != null) {
                daSa = daSa && (datumOdDatePicker.getValue().compareTo(datumOdDatePicker.getValue()) <= 0);
            }

            if (!daSa) {
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
            } else {
                okButton.getScene().getWindow().hide();
            }
        });
    }
}
