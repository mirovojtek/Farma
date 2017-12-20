package farma;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class TankovanieEditSceneController {

    private TankovanieFxModel aktualneTankovanie;
    private Stroj tankovanyStroj;
    private final TankovanieDao tankovanieDao = DaoFactory.INSTANCE.getTankovanieDao();
    private boolean daSaPridat;

    public TankovanieEditSceneController(Stroj stroj) {
        aktualneTankovanie = new TankovanieFxModel(stroj);
        this.tankovanyStroj = stroj;
    }

    @FXML
    private TextField mnozstvoTextField;

    @FXML
    private DatePicker tankovanieDatePicker;

    @FXML
    private Button vlozitButton;

    @FXML
    void initialize() {

        StringConverter<Number> converter = new NumberStringConverter();
        mnozstvoTextField.textProperty().bindBidirectional(aktualneTankovanie.mnozstvoProperty(), converter);
        tankovanieDatePicker.valueProperty().bindBidirectional(aktualneTankovanie.datumProperty());

        vlozitButton.setOnAction(eh -> {

            daSaPridat = true;

            // ošetrenie množstva
            daSaPridat = daSaPridat && aktualneTankovanie.getMnozstvo() != null && aktualneTankovanie.getMnozstvo() > 0 && aktualneTankovanie.getMnozstvo() < 10000;

            // ošetrenie dátumu - nie je v buducnosti
            daSaPridat = daSaPridat && aktualneTankovanie.getDatum() != null && (aktualneTankovanie.getDatum().compareTo(LocalDate.now()) <= 0);

            // ošetrenie dátumu - je aspon taký, kedy bol nadobudnutý tankovaný stroj
            daSaPridat = daSaPridat && tankovanyStroj.getDatum().compareTo(aktualneTankovanie.getDatum()) <= 0;

            if (daSaPridat) {

                tankovanieDao.add(aktualneTankovanie.getTankovanie());
                tankovanieDatePicker.getEditor().clear();
                mnozstvoTextField.clear();
                vlozitButton.getScene().getWindow().hide();
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
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        );
    }
}
