package farma;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import javafx.collections.FXCollections;
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

public class StrojeEditSceneController {

    private final StrojFxModel aktualnyStroj;
    private final StrojDao strojDao = DaoFactory.INSTANCE.getStrojDao();
    private boolean bolaPridanaPolozka = false;

    public StrojeEditSceneController() {
        aktualnyStroj = new StrojFxModel();
    }

    boolean getBolaPridanaPolozka() {
        return bolaPridanaPolozka;
    }
    
    @FXML
    private TextField vyrobcaTextField;

    @FXML
    private TextField typTextField;

    @FXML
    private TextField kategoriaTextField;

    @FXML
    private DatePicker datumDatePicker;

    @FXML
    private TextField cenaTextField;

    @FXML
    private Button vlozitButton;

    @FXML
    void initialize() {
        vyrobcaTextField.textProperty().bindBidirectional(aktualnyStroj.vyrobcaProperty());
        typTextField.textProperty().bindBidirectional(aktualnyStroj.typProperty());
        kategoriaTextField.textProperty().bindBidirectional(aktualnyStroj.kategoriaProperty());

        StringConverter<Number> converter = new NumberStringConverter();
        cenaTextField.textProperty().bindBidirectional(aktualnyStroj.cenaProperty(), converter);

        vlozitButton.setOnAction(eh -> {
            //
            boolean daSaPridat = true;

            // ošetrenie výrobcu
            daSaPridat = daSaPridat && aktualnyStroj.getVyrobca() != null && aktualnyStroj.getVyrobca().length() > 0 && aktualnyStroj.getVyrobca().length() <= 45;

            // ošeternie typu
            daSaPridat = daSaPridat && aktualnyStroj.getTyp() != null && aktualnyStroj.getTyp().length() > 0 && aktualnyStroj.getTyp().length() <= 45;

            // ošetrenie kategórie
            daSaPridat = daSaPridat && aktualnyStroj.getKategoria() != null && aktualnyStroj.getKategoria().length() > 0 && aktualnyStroj.getKategoria().length() <= 20;

            // ošetrenie zadania dátumu - je zadaný a je najneskôr dnešný
            daSaPridat = daSaPridat && aktualnyStroj.getDatum() != null && aktualnyStroj.getDatum().compareTo(LocalDate.now()) <= 0;

            // ošetrenie ceny
            daSaPridat = daSaPridat && aktualnyStroj.getCena() != null && aktualnyStroj.getCena() >= 0 && aktualnyStroj.getCena() < 1000000;

            if (daSaPridat) {
                strojDao.add(aktualnyStroj.getStroj());
                bolaPridanaPolozka = true;
                vyrobcaTextField.clear();
                typTextField.clear();
                kategoriaTextField.clear();
                datumDatePicker.getEditor().clear();
                cenaTextField.clear();
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
