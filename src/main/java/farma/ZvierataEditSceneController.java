package farma;

import farma.DaoFactory;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
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
    private DatePicker datumNarodeniaDatePicker;
       
    @FXML
    private DatePicker datumNadobudnutiaDatePicker;
     
    @FXML
    private TextField plemenoTextField;

    @FXML
    private TextField kupnaCenaTextField;

    @FXML
    private TextField pohlavieTextField;

    @FXML
    private Button vlozitButton;

    @FXML
    void initialize() {

        List<Zviera> zvierata = zvieraDao.getAll();
        zvierataList = FXCollections.observableArrayList(zvierata);

        registracneCisloTextField.textProperty().bindBidirectional(aktualneZviera.registracneCisloProperty());
        druhTextField.textProperty().bindBidirectional(aktualneZviera.druhProperty());
        plemenoTextField.textProperty().bindBidirectional(aktualneZviera.plemenoProperty());
        pohlavieTextField.textProperty().bindBidirectional(aktualneZviera.pohlavieProperty());

        StringConverter<Number> converter = new NumberStringConverter();
        kupnaCenaTextField.textProperty().bindBidirectional(aktualneZviera.kupnaCenaProperty(), converter);

        vlozitButton.setOnAction(eh -> {
            if (aktualneZviera.getRegistracneCislo() == null
                    || (aktualneZviera.getRegistracneCislo() != null && aktualneZviera.getRegistracneCislo().isEmpty())) {
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
                try {
                    aktualneZviera.setDatumNarodenia(datumNarodeniaDatePicker.getValue());
                    aktualneZviera.setDatumNadobudnutia(datumNadobudnutiaDatePicker.getValue());
                    zvieraDao.add(aktualneZviera.getZviera());
                    // vyčistenie všetkých textFieldov po pridaní zvieraťa
                    registracneCisloTextField.clear();
                    druhTextField.clear();
                    plemenoTextField.clear();
                    pohlavieTextField.clear();
                    kupnaCenaTextField.clear();
                } catch (Exception e) {
                    System.err.println("Problem s vložením.");

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
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }

                }

            }
        });

    }
}
