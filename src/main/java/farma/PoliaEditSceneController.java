package farma;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class PoliaEditSceneController {

    private PoleDao poleDao = DaoFactory.INSTANCE.getPoleDao();
    private PoleFxModel aktualnePole;

    public PoliaEditSceneController() {
        aktualnePole = new PoleFxModel();
    }

    boolean getBolaPridanaPolozka() {
        return bolaPridanaPolozka;
    }
    private boolean bolaPridanaPolozka = false;
    private boolean daSaPridat = false;

    @FXML
    private TextField cisloParcelyTextField;

    @FXML
    private TextField vymeraTextField;

    @FXML
    private TextField typPozemkuTextField;

    @FXML
    private Button vlozitButton;

    @FXML
    private RadioButton eRadioButton;

    @FXML
    private RadioButton cRadioButton;

    @FXML
    private RadioButton najomcaRadioButton;

    @FXML
    private RadioButton vlastnikRadioButton;

    @FXML
    private RadioButton ineRadioButton;

    @FXML
    void initialize() {

        //https://docs.oracle.com/javafx/2/ui_controls/radio-button.htm
        ToggleGroup group1 = new ToggleGroup();
        eRadioButton.setToggleGroup(group1);
        eRadioButton.setUserData("E");
        eRadioButton.setSelected(true);
        cRadioButton.setToggleGroup(group1);
        cRadioButton.setUserData("C");

        cisloParcelyTextField.textProperty().bindBidirectional(aktualnePole.cisloParcelyProperty());
        StringConverter<Number> converter = new NumberStringConverter();
        vymeraTextField.textProperty().bindBidirectional(aktualnePole.vymeraProperty(), converter);
        typPozemkuTextField.textProperty().bindBidirectional(aktualnePole.typPozemkuProperty());

        ToggleGroup group2 = new ToggleGroup();
        najomcaRadioButton.setToggleGroup(group2);
        najomcaRadioButton.setUserData("najomca");
        najomcaRadioButton.setSelected(true);
        vlastnikRadioButton.setToggleGroup(group2);
        vlastnikRadioButton.setUserData("vlastnik");
        ineRadioButton.setToggleGroup(group2);
        ineRadioButton.setUserData("ine");

        vlozitButton.setOnAction(eh -> {
            daSaPridat = true;

            // ošetrenie čísla parcely
            daSaPridat = daSaPridat && aktualnePole.getCisloParcely() != null && aktualnePole.getCisloParcely().length() > 0 && aktualnePole.getCisloParcely().length() <= 50;

            // ošetrenie výmery // menšie ako 10 000 000
            daSaPridat = daSaPridat && aktualnePole.getVymera() != null && aktualnePole.getVymera() > 0 && aktualnePole.getVymera() < 10000000;

            // ošetrenie typu pozemku
            daSaPridat = daSaPridat && aktualnePole.getTypPozemku() != null && aktualnePole.getTypPozemku().length() > 0 && aktualnePole.getTypPozemku().length() <= 50;

            if (daSaPridat) {
                aktualnePole.setVlastnictvo(group2.getSelectedToggle().getUserData().toString());
                aktualnePole.setTypParcely(group1.getSelectedToggle().getUserData().toString());
                poleDao.add(aktualnePole.getPole());
                cisloParcelyTextField.clear();
                typPozemkuTextField.clear();
                vymeraTextField.clear();
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
        });
    }
}
