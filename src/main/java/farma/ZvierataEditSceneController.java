package farma;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class ZvierataEditSceneController {

    private Zviera zviera;
    private final ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    private final ZvieraFxModel aktualneZviera;
    private ObservableList<Zviera> zvierataList = null;
    private String pohlavie = "%";
    private boolean bolaPridanaPolozka = false;
    private final String[] pohlavia = {"m", "f"};

    public ZvierataEditSceneController() {
        aktualneZviera = new ZvieraFxModel();
    }

    boolean getBolaPridanaPolozka() {
        return bolaPridanaPolozka;
    }

    @FXML
    private TextField registracneCisloTextField;

    @FXML
    private TextField druhTextField;

    @FXML
    private TextField plemenoTextField;

    @FXML
    private ComboBox<String> pohlavieComboBox;

    @FXML
    private DatePicker datumNarodeniaDatePicker;

    @FXML
    private DatePicker datumNadobudnutiaDatePicker;

    @FXML
    private TextField cenaTextField;

    @FXML
    private Button vlozitButton;

    public String getPohlavie() {
        return pohlavie;
    }

    @FXML
    void initialize() {
        pohlavieComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(pohlavia)));
        List<Zviera> zvierata = zvieraDao.getAll();
        zvierataList = FXCollections.observableArrayList(zvierata);

        registracneCisloTextField.textProperty().bindBidirectional(aktualneZviera.registracneCisloProperty());
        druhTextField.textProperty().bindBidirectional(aktualneZviera.druhProperty());
        plemenoTextField.textProperty().bindBidirectional(aktualneZviera.plemenoProperty());

        pohlavieComboBox.valueProperty().bindBidirectional(aktualneZviera.pohlavieProperty());

        datumNarodeniaDatePicker.valueProperty().bindBidirectional(aktualneZviera.datumNarodeniaProperty());
        datumNadobudnutiaDatePicker.valueProperty().bindBidirectional(aktualneZviera.datumNadobudnutiaProperty());

        StringConverter<Number> converter = new NumberStringConverter();
        cenaTextField.textProperty().bindBidirectional(aktualneZviera.kupnaCenaProperty(), converter);

        vlozitButton.setOnAction(eh -> {
            boolean daSaPridat = true;

            // ošetrenie registračného čísla
            daSaPridat = daSaPridat && aktualneZviera.getRegistracneCislo() != null && aktualneZviera.getRegistracneCislo().length() > 0 && aktualneZviera.getRegistracneCislo().length() <= 30;

            // ošetrenie druhu
            daSaPridat = daSaPridat && aktualneZviera.getDruh() != null && aktualneZviera.getDruh().length() > 0 && aktualneZviera.getDruh().length() <= 25;

            // ošetrenie plemena
            daSaPridat = daSaPridat && aktualneZviera.getPlemeno() != null && aktualneZviera.getPlemeno().length() > 0 && aktualneZviera.getPlemeno().length() <= 45;

            // ošetrenie pohlavia
            daSaPridat = daSaPridat && aktualneZviera.getPohlavie() != null;

            // ošetrenie datumu narodenia
            daSaPridat = daSaPridat && aktualneZviera.getDatumNarodenia() != null && aktualneZviera.getDatumNarodenia().compareTo(LocalDate.now()) <= 0;

            // ošetrenie datumu nadobudnutia
            daSaPridat = daSaPridat && aktualneZviera.getDatumNadobudnutia() != null && aktualneZviera.getDatumNadobudnutia().compareTo(LocalDate.now()) <= 0;

            // ošetrenie správnosti oboch dátumov
            daSaPridat = daSaPridat && aktualneZviera.getDatumNarodenia().compareTo(aktualneZviera.getDatumNadobudnutia()) <= 0;

            // ošetrenie ceny
            daSaPridat = daSaPridat && aktualneZviera.getKupnaCena() != null && aktualneZviera.getKupnaCena() >= 0 && aktualneZviera.getKupnaCena() < 10000;

            if (daSaPridat) {
                zvieraDao.add(aktualneZviera.getZviera());
                bolaPridanaPolozka = true;
                registracneCisloTextField.clear();
                druhTextField.clear();
                plemenoTextField.clear();
                pohlavieComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(pohlavia)));
                datumNarodeniaDatePicker.getEditor().clear();
                datumNadobudnutiaDatePicker.getEditor().clear();
                cenaTextField.clear();
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
                } catch (Exception e) {
                }
            }
        });
    }
}
