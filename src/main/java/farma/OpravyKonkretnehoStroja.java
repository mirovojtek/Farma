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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpravyKonkretnehoStroja {

    private Stroj kliknutyStroj;
    private Oprava kliknutaOprava;
    private final StrojDao strojDao = DaoFactory.INSTANCE.getStrojDao();
    private final OpravaDao opravaDao = DaoFactory.INSTANCE.getOpravaDao();
    private ObservableList<Stroj> strojeList = null;

    public OpravyKonkretnehoStroja(Stroj stroj) {
        kliknutyStroj = stroj;
    }

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
    private TableView<Stroj> konkretnyStrojTableView;

    @FXML
    private TableView<Oprava> opravyTableView;

    @FXML
    private TableColumn<Oprava, Integer> idOpravaCol;

    @FXML
    private TableColumn<Oprava, Object> datumOpravaCol;

    @FXML
    private TableColumn<Oprava, Double> cenaOpravaCol;

    @FXML
    private TableColumn<Oprava, String> poruchaOpravaCol;

    @FXML
    private Button pridatButton;

    @FXML
    private Button zmazatButton;

    @FXML
    private Button zmazatVsetkoButton;

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
        konkretnyStrojTableView.setItems(strojeList);

        idOpravaCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        datumOpravaCol.setCellValueFactory(new PropertyValueFactory<>("fDatum"));
        cenaOpravaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        poruchaOpravaCol.setCellValueFactory(new PropertyValueFactory<>("porucha"));

        opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
       
        pridatButton.setOnAction(eh -> {
            OpravaEditSceneController controller = new OpravaEditSceneController(kliknutyStroj);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PridatOpravu.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Pridať opravu");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
        });

        TableView<Oprava> tabulka = opravyTableView;
        tabulka.setRowFactory(tv -> {
            TableRow<Oprava> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    kliknutaOprava = row.getItem();
                }
            });
            return row;
        });

        zmazatButton.setOnAction(eh -> {
            if (kliknutaOprava == null) {
                PrazdneMazanieSceneController controller = new PrazdneMazanieSceneController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("PrazdneMazanie.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Zmazať opravu");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }

                return;
            }
            OpravaDeleteSceneController controller
                    = new OpravaDeleteSceneController(kliknutaOprava);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PotvrdenieZmazania.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Zmazať opravu");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
            kliknutaOprava = null;
        });

        zmazatVsetkoButton.setOnAction(eh -> {
            OpravaDeleteAllController controller
                    = new OpravaDeleteAllController(kliknutyStroj);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PotvrdenieZmazaniaVsetkeho.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Vymaž všetko");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
        });
    }
}
