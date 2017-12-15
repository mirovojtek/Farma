package farma;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public FinancieEditSceneController() {
        aktualnaPolozka = new FinancieFxModel();
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

    private final String[] poleTypov = {"výdaj", "príjem"};
    private final ObservableList<String> typy = FXCollections.observableArrayList(Arrays.asList(poleTypov));

    @FXML
    void initialize() {

        // datum
        StringConverter<Number> converter = new NumberStringConverter();
        sumaTextField.textProperty().bindBidirectional(aktualnaPolozka.sumaProperty(), converter);
        typComboBox.setItems(typy);

        popisTextField.textProperty().bindBidirectional(aktualnaPolozka.popisProperty());

        pridatPolozkuButton.setOnAction(eh -> {
            if (aktualnaPolozka.getSuma() < 0) {
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
                datumDatePicker.getEditor().clear();
                sumaTextField.clear();
                popisTextField.clear();
                typComboBox.setItems(FXCollections.observableList(new ArrayList<String>()));
                return;
            }
            try {
                aktualnaPolozka.setDatum(datumDatePicker.getValue());
                financieDao.add(aktualnaPolozka.getPolozka());
                datumDatePicker.getEditor().clear();
                sumaTextField.clear();
                popisTextField.clear();
                typComboBox.setItems(FXCollections.observableList(new ArrayList<String>()));
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
