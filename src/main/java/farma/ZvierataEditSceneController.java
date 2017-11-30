package farma;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import javafx.util.converter.NumberStringConverter;
import javax.swing.text.DateFormatter;

public class ZvierataEditSceneController {

    private Zviera zviera;
    private ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    private ZvieraFxModel aktualneZviera;
    private ObservableList<Zviera> zvierataList = null;

    public ZvierataEditSceneController() {
        aktualneZviera = new ZvieraFxModel();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField registracneCisloTextField;

    @FXML
    private TextField druhTextField;

    @FXML
    private TextField datumNarodeniaTextField;

    @FXML
    private TextField datumNadobudnutiaTextField;

    @FXML
    private TextField plemenoTextField;

    @FXML
    private TextField kupnaCenaTextField;

    @FXML
    private TextField pohlavieTextField;

    @FXML
    private ListView<Zviera> zvierataListView;

    @FXML
    private Button vlozitButton;

    @FXML
    void initialize() {

        List<Zviera> zvierata = zvieraDao.getAll();
        zvierataList = FXCollections.observableArrayList(zvierata);
        zvierataListView.setItems(zvierataList);

        registracneCisloTextField.textProperty().bindBidirectional(aktualneZviera.registracneCisloProperty());
        druhTextField.textProperty().bindBidirectional(aktualneZviera.druhProperty());
        plemenoTextField.textProperty().bindBidirectional(aktualneZviera.plemenoProperty());
        pohlavieTextField.textProperty().bindBidirectional(aktualneZviera.pohlavieProperty());

        StringConverter<Number> converter = new NumberStringConverter();
        kupnaCenaTextField.textProperty().bindBidirectional(aktualneZviera.kupnaCenaProperty(), converter);

        /*datumNarodeniaTextField.textProperty().bindBidirectional(
                aktualneZviera.datumNarodeniaProperty(), new StringConverter<LocalDateTime>() {
            private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    
            @Override
            public String toString(LocalDateTime t) {
                return formatter.format(t);
            }

            @Override
            public LocalDateTime fromString(String string) {
                try {
                    datumNarodeniaTextField.setStyle("-fx-background-color: white;");
                    return LocalDateTime.parse(string, formatter);
                } catch (DateTimeParseException e) {
                    datumNarodeniaTextField.setStyle("-fx-background-color: lightcoral;");
                    return null;
                }
            }
        });
        datumNadobudnutiaTextField.textProperty().bindBidirectional
        (aktualneZviera.datumNadobudnutiaProperty(), new StringConverter<LocalDateTime>() {
            private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            @Override
            public String toString(LocalDateTime t) {
                return formatter.format(t);
            }

            @Override
            public LocalDateTime fromString(String string) {
                try {
                    datumNadobudnutiaTextField.setStyle("-fx-background-color: white;");
                    return LocalDateTime.parse(string, formatter);
                } catch (DateTimeParseException e) {
                    datumNadobudnutiaTextField.setStyle("-fx-background-color: lightcoral;");
                    return null;
                }
            }
        });*/
        aktualneZviera.setDatumNadobudnutia(LocalDateTime.now());
        aktualneZviera.setDatumNarodenia(LocalDateTime.now());

        vlozitButton.setOnAction(eh -> {
            if (aktualneZviera.getRegistracneCislo() == null || 
              (aktualneZviera.getRegistracneCislo() != null && aktualneZviera.getRegistracneCislo().isEmpty())) {
                ZvieraNevyplneneRegistracneCisloController controller
                        = new ZvieraNevyplneneRegistracneCisloController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("NevyplneneRegCislo.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Registračné číslo");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    // toto sa vykona az po zatvoreni okna
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            } else {
                zvieraDao.add(aktualneZviera.getZviera());
                zvierataList.setAll(zvieraDao.getAll());
                // vyčistenie všetkých textFieldov po pridaní zvieraťa
                registracneCisloTextField.clear();
                druhTextField.clear();
                plemenoTextField.clear();
                pohlavieTextField.clear();
                datumNarodeniaTextField.clear();
                datumNadobudnutiaTextField.clear();
                kupnaCenaTextField.clear();

            }
        });

    }
}
