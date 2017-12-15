package farma;

import java.io.IOException;
import java.util.ArrayList;
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

    public ZvierataEditSceneController() {
        aktualneZviera = new ZvieraFxModel();
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

        List<Zviera> zvierata = zvieraDao.getAll();
        zvierataList = FXCollections.observableArrayList(zvierata);

        registracneCisloTextField.textProperty().bindBidirectional(aktualneZviera.registracneCisloProperty());
        druhTextField.textProperty().bindBidirectional(aktualneZviera.druhProperty());
        plemenoTextField.textProperty().bindBidirectional(aktualneZviera.plemenoProperty());

        pohlavieComboBox.setItems(FXCollections.observableArrayList(zvieraDao.getPohlavia()));
        pohlavieComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                pohlavie = newValue;
            }
        });
        StringConverter<Number> converter = new NumberStringConverter();
        cenaTextField.textProperty().bindBidirectional(aktualneZviera.kupnaCenaProperty(), converter);

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
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            } else {
                try {
                    aktualneZviera.setDatumNarodenia(datumNarodeniaDatePicker.getValue());
                    aktualneZviera.setDatumNadobudnutia(datumNadobudnutiaDatePicker.getValue());
                    aktualneZviera.setPohlavie(pohlavieComboBox.getValue());
                    zvieraDao.add(aktualneZviera.getZviera());
                    // vyčistenie všetkých textFieldov po pridaní zvieraťa
                    registracneCisloTextField.clear();
                    druhTextField.clear();
                    plemenoTextField.clear();
                    pohlavieComboBox.setItems(FXCollections.observableList(new ArrayList<String>()));
                    datumNadobudnutiaDatePicker.getEditor().clear();
                    datumNarodeniaDatePicker.getEditor().clear();
                    cenaTextField.clear();
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
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
            }
        });
    }
}
