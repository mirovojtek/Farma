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

public class OpravaPopisController {

    private final OpravaFxModel popisOprava;
    private final Oprava kliknutaOprava;
    private final Stroj kliknutyStroj;
    private final OpravaDao opravaDao = DaoFactory.INSTANCE.getOpravaDao();
    private ObservableList<Oprava> opravyList = null;

    public OpravaPopisController(Oprava oprava, Stroj stroj) {
        kliknutaOprava = oprava;
        kliknutyStroj = stroj;
        popisOprava = new OpravaFxModel(stroj);
    }

    @FXML
    private TableView<Oprava> kliknutaOpravaTableView;

    @FXML
    private TableColumn<Oprava, Integer> idOpravaCol;

    @FXML
    private TableColumn<Oprava, Integer> idStrojaOpravaCol;

    @FXML
    private TableColumn<Oprava, Object> datumOpravaCol;

    @FXML
    private TableColumn<Oprava, Double> cenaOpravaCol;

    @FXML
    private TableColumn<Oprava, String> poruchaOpravaCol;

    @FXML
    private TextFlow popisTextFlow;

    @FXML
    private TextArea popisTextArea;

    @FXML
    private Button vlozitPopisButton;

    @FXML
    void initialize() {

        Oprava oprava = opravaDao.findById(kliknutaOprava.getId());
        List<Oprava> vybranaOprava = new ArrayList<>();
        vybranaOprava.add(oprava);
        opravyList = FXCollections.observableArrayList(vybranaOprava);

        
        idOpravaCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idStrojaOpravaCol.setCellValueFactory(new PropertyValueFactory<>("idStroj"));
        datumOpravaCol.setCellValueFactory(new PropertyValueFactory<>("fDatum"));
        cenaOpravaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        poruchaOpravaCol.setCellValueFactory(new PropertyValueFactory<>("porucha"));
        kliknutaOpravaTableView.setItems(opravyList);

        popisOprava.setOprava(oprava);
        Text t1 = new Text(popisOprava.getPopis());
        popisTextFlow.getChildren().add(t1);
        popisTextArea.textProperty().bindBidirectional(popisOprava.popisProperty());

        vlozitPopisButton.setOnAction(eh -> {
            opravaDao.pridajPopis(popisOprava.getOprava());
            Text t2 = new Text(popisOprava.getPopis());
            popisTextFlow.getChildren().clear();
            popisTextFlow.getChildren().add(t2);
        });

    }

}
