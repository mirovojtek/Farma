package farma;

import farma.DaoFactory;
import farma.DaoFactory;
import farma.NespravneVyplnanieController;
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
            if (popisTextArea.getText().length() <= 500) {
                zvieraDao.pridajPopis(popisZviera.getZviera());
                Text t2 = new Text(popisZviera.getPopis());
                popisTextFlow.getChildren().clear();
                popisTextFlow.getChildren().add(t2);
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
                } catch (Exception e) {
                }
            }
        });
    }
}
