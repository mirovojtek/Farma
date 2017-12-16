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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class FinancieEditSceneController {

    private final FinancieFxModel aktualnaPolozka;
    private final FinancieDao financieDao = DaoFactory.INSTANCE.getFinancieDao();
    private final String[] poleTypov = {"výdaj", "príjem"};

    public FinancieEditSceneController() {
        aktualnaPolozka = new FinancieFxModel();
    }

    private boolean bolaPridanaPolozka = false;

    boolean getBolaPridanaPolozka() {
        return bolaPridanaPolozka;
    }

    @FXML
    private DatePicker datumDatePicker;

    @FXML
    private TextField sumaTextField;

    @FXML
    private ComboBox<String> typComboBox;

    @FXML
    private TextField popisTextField;

    @FXML
    private Button pridatPolozkuButton;

    @FXML
    void initialize() {
        typComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(poleTypov)));

        //datum
        datumDatePicker.valueProperty().bindBidirectional(aktualnaPolozka.datumProperty());

        //suma
        StringConverter<Number> converter = new NumberStringConverter();
        try {
            sumaTextField.textProperty().bindBidirectional(aktualnaPolozka.sumaProperty(), converter);
        } catch (Exception chyba) {
        }

        // typ
        typComboBox.valueProperty().bindBidirectional(aktualnaPolozka.typProperty());

        //popis
        popisTextField.textProperty().bindBidirectional(aktualnaPolozka.popisProperty());

        pridatPolozkuButton.setOnAction(eh -> {
            boolean daSaPridat = true;

            // ošetrenie zadania dátumu - je zadaný a je najneskôr dnešný
            daSaPridat = daSaPridat && aktualnaPolozka.getDatum() != null && aktualnaPolozka.getDatum().compareTo(LocalDate.now()) <= 0;

            // ošetrenie sumy
            if (daSaPridat) {
                daSaPridat = daSaPridat && aktualnaPolozka.getSuma() > 0;
            }

            // ošetrenie typu
            if (daSaPridat) {
                daSaPridat = daSaPridat && aktualnaPolozka.getTyp() != null;
            }

            // ošetrenie popisu
            if (daSaPridat) {
                daSaPridat = daSaPridat && aktualnaPolozka.getPopis() != null && aktualnaPolozka.getPopis().length() > 0 && aktualnaPolozka.getPopis().length() <= 200;
            }

            if (daSaPridat) {
                financieDao.add(aktualnaPolozka.getPolozka());
                bolaPridanaPolozka = true;
                datumDatePicker.getEditor().clear();
                sumaTextField.clear();
                popisTextField.clear();
                typComboBox.setItems(Arrays.asList(poleTypov));
                popisTextField.clear();

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
