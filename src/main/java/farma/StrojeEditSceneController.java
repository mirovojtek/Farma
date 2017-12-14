package farma;

import java.io.IOException;
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

    public StrojeEditSceneController() {
        aktualnyStroj = new StrojFxModel();
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
            if (aktualnyStroj.getCena() < 0) {
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
                    // toto sa vykona az po zatvoreni okna
                } catch (Exception e) {
                }
                vyrobcaTextField.clear();
                kategoriaTextField.clear();
                typTextField.clear();
                cenaTextField.clear();
                return;
            }
            try {
                aktualnyStroj.setDatum(datumDatePicker.getValue());
                strojDao.add(aktualnyStroj.getStroj());
                vyrobcaTextField.clear();
                kategoriaTextField.clear();
                typTextField.clear();
                cenaTextField.clear();
            } catch (Exception e) {
                System.err.println(e);

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
                } catch (Exception ee) {
                }
            }
        });
    }
}
