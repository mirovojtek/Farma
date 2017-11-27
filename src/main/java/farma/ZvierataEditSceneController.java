package farma;

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
import javafx.util.StringConverter;
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
        /*
        registracneCisloTextField.textProperty().bindBidirectional(aktualneZviera.registracneCisloProperty());

        druhTextField.textProperty().bindBidirectional(aktualneZviera.druhProperty());

        plemenoTextField.textProperty().bindBidirectional(aktualneZviera.plemenoProperty());
        pohlavieTextField.textProperty().bindBidirectional(aktualneZviera.pohlavieProperty());

        /*
        datumNarodeniaTextField.textProperty().bindBidirectional(aktualneZviera.datumNarodeniaProperty(), new StringConverter<LocalDate>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyy");

            @Override
            public String toString(LocalDate t) {
                return formatter.format(t);
            }

            @Override
            public LocalDate fromString(String string) {
                try {
                    datumNarodeniaTextField.setStyle("-fx-background-color:white;");
                    return LocalDate.parse(string, formatter);
                } catch (DateTimeParseException e) {
                    datumNarodeniaTextField.setStyle("-fx-background-color:lightcoral;");
                    return null;
                }
            }
        });
        /*
        datumNadobudnutiaTextField.textProperty().bindBidirectional(aktualneZviera.datumNadobudnutiaProperty(), new StringConverter<LocalDate>() {
            private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

            @Override
            public String toString(LocalDate t) {
                return formatter.format(t);
            }

            @Override
            public LocalDate fromString(String string) {
                try {
                    datumNarodeniaTextField.setStyle("-fx-background-color:white;");
                    return LocalDate.parse(string, formatter);
                } catch (DateTimeParseException e) {
                    datumNarodeniaTextField.setStyle("-fx-background-color:lightcoral;");
                    return null;
                }
            }
        });
         */
        vlozitButton.setOnAction(eh -> {
            zvieraDao.add(aktualneZviera.getZviera());
            zvierataList.setAll(zvieraDao.getAll());
        });

    }
}
