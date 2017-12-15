package farma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StrojPopisController {

    private final StrojFxModel popisStroj;
    private final Stroj kliknutyStroj;
    private final StrojDao strojDao = DaoFactory.INSTANCE.getStrojDao();
    private ObservableList<Stroj> strojeList = null;

    public StrojPopisController(Stroj stroj) {
        kliknutyStroj = stroj;
        popisStroj = new StrojFxModel();
    }

    @FXML
    private TableView<Stroj> kliknutyStrojTableView;

    @FXML
    private Button opravyButton;

    @FXML
    private Button tankovaniaButton;

    @FXML
    private TextArea popisTextArea;

    @FXML
    private TextFlow popisTextFlow;

    @FXML
    private Button vlozitPopisButton;

    @FXML
    private TableColumn<Stroj, Integer> idCol;

    @FXML
    private TableColumn<Stroj, String> vyrobcaCol;

    @FXML
    private TableColumn<Stroj, String> typCol;

    @FXML
    private TableColumn<Stroj, String> kategoriaCol;

    @FXML
    private TableColumn<Stroj, Object> datumCol;

    @FXML
    private TableColumn<Stroj, Double> cenaCol;

    @FXML
    void initialize() {
        Stroj stroj = strojDao.findById(kliknutyStroj.getId());
        List<Stroj> vybranyStroj = new ArrayList<>();
        vybranyStroj.add(stroj);
        strojeList = FXCollections.observableArrayList(vybranyStroj);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        vyrobcaCol.setCellValueFactory(new PropertyValueFactory<>("vyrobca"));
        typCol.setCellValueFactory(new PropertyValueFactory<>("typ"));
        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        datumCol.setCellValueFactory(new PropertyValueFactory<>("fDatum"));
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        kliknutyStrojTableView.setItems(strojeList);

        popisStroj.setStroj(stroj);
        Text t1 = new Text(popisStroj.getPopis());
        popisTextFlow.getChildren().add(t1);
        popisTextArea.textProperty().bindBidirectional(popisStroj.popisProperty());

        vlozitPopisButton.setOnAction(eh -> {
            strojDao.pridajPopis(popisStroj.getStroj());
            Text t2 = new Text(popisStroj.getPopis());
            popisTextFlow.getChildren().clear();
            popisTextFlow.getChildren().add(t2);
        });
        opravyButton.setOnAction(eh -> {
            OpravyKonkretnehoStroja controller = new OpravyKonkretnehoStroja(kliknutyStroj);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("OpravyKonkretnehoStroja.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Opravy");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        tankovaniaButton.setOnAction(eh -> {
            TankovaniaKonkretnehoStroja controller = new TankovaniaKonkretnehoStroja(kliknutyStroj);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("TankovaniaKonkretnehoStroja.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Tankovania");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
    }

}
