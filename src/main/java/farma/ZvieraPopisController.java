package farma;

import farma.DaoFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ZvieraPopisController {

    private ZvieraFxModel popisZviera;
    private Zviera kliknuteZviera;
    private final ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    private ObservableList<Zviera> zvierataList = null;

    public ZvieraPopisController(Zviera zviera) {
        kliknuteZviera = zviera;
        popisZviera = new ZvieraFxModel();
    }

    @FXML
    private TextArea popisTextArea;

    @FXML
    private Button vlozitPopisButton;

    @FXML
    private TextFlow popisTextFlow;

    @FXML
    private TableView<Zviera> vybraneZvieraTableView;

    @FXML
    private TableColumn<Zviera, String> registracneCisloZvieraCol;

    @FXML
    private TableColumn<Zviera, String> druhZvieraCol;

    @FXML
    private TableColumn<Zviera, String> plemenoZvieraCol;

    @FXML
    private TableColumn<Zviera, String> pohlavieZvieraCol;

    @FXML
    private TableColumn<Zviera, Object> datumNarodeniaZvieraCol;

    @FXML
    private TableColumn<Zviera, Object> datumNadobudnutiaZvieraCol;

    @FXML
    private TableColumn<Zviera, Double> cenaZvieraCol;

    @FXML
    void initialize() {
        Zviera zviera = zvieraDao.findByRegistracneCislo(kliknuteZviera.getRegistracneCislo());
        List<Zviera> vybraneZviera = new ArrayList<>();
        vybraneZviera.add(kliknuteZviera);
        zvierataList = FXCollections.observableArrayList(vybraneZviera);

        registracneCisloZvieraCol.setCellValueFactory(new PropertyValueFactory<>("registracneCislo"));
        druhZvieraCol.setCellValueFactory(new PropertyValueFactory<>("druh"));
        plemenoZvieraCol.setCellValueFactory(new PropertyValueFactory<>("plemeno"));
        pohlavieZvieraCol.setCellValueFactory(new PropertyValueFactory<>("pohlavie"));
        datumNarodeniaZvieraCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNarodenia"));
        datumNadobudnutiaZvieraCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNadobudnutia"));
        cenaZvieraCol.setCellValueFactory(new PropertyValueFactory<>("kupnaCena"));
        vybraneZvieraTableView.setItems(zvierataList);

        popisZviera.setZviera(zviera);
        Text t1 = new Text(popisZviera.getPopis());
        popisTextFlow.getChildren().add(t1);
        popisTextArea.textProperty().bindBidirectional(popisZviera.popisProperty());

        vlozitPopisButton.setOnAction(eh -> {
            zvieraDao.pridajPopis(popisZviera.getZviera());
            Text t2 = new Text(popisZviera.getPopis());
            popisTextFlow.getChildren().clear();
            popisTextFlow.getChildren().add(t2);
        });
    }
}
