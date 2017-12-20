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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StrojSceneController {

    private final StrojDao strojDao = DaoFactory.INSTANCE.getStrojDao();
    private StrojFxModel aktualnyStroj = new StrojFxModel();
    private ObservableList<Stroj> s;
    private Stroj kliknutyStroj;
    List<Stroj> vsetkyStroje = new ArrayList<>();

    @FXML
    private TableView<Stroj> strojeTableView;

    @FXML
    private Button tankovanieButton;

    @FXML
    private Button opravyButton;

    @FXML
    private Button zmazatButton;

    @FXML
    private Button pridatButton;

    @FXML
    private Button rozsireneVyhladavanieButton;

    @FXML
    private Button zobrazVsetkyButton;

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

        zobrazVsetkyButton.setOnAction(eh -> {
            vsetkyStroje = strojDao.getAll();
            strojeTableView.setItems(FXCollections.observableArrayList(vsetkyStroje));
            if (vsetkyStroje.isEmpty()) {
                strojeTableView.setPlaceholder(new Label("V databáze sa žiadne stroje nenachádzajú."));
            }
            kliknutyStroj = null;
        });

        rozsireneVyhladavanieButton.setOnAction(eh -> {
            StrojRozsireneVyhladavanieController controller
                    = new StrojRozsireneVyhladavanieController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("StrojRozsireneVyhladavanie.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Rozšírené vyhľadávanie");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getAkcia()) {
                List<Stroj> strojRozsirene = strojDao.rozsireneVyhladavanie(controller.getVyrobca(), controller.getTyp(), controller.getKategoria(), controller.getRokNadobudnutia());
                strojeTableView.setItems(FXCollections.observableArrayList(strojRozsirene));
                if (strojRozsirene.size() == 0) {
                    strojeTableView.setPlaceholder(new Label("Stroje so zadanými parametrami sa v databáze nenachádzajú."));
                    // lebo: https://stackoverflow.com/questions/24765549/remove-the-default-no-content-in-table-text-for-empty-javafx-table
                }
            }
        });

        pridatButton.setOnAction(eh -> {
            StrojeEditSceneController controller
                    = new StrojeEditSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("StrojeEditScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Pridať stroj");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getBolaPridanaPolozka()) {
                strojeTableView.setItems(FXCollections.observableArrayList(strojDao.getAll()));
            }
        });

        zmazatButton.setOnAction(eh -> {
            if (kliknutyStroj == null) {
                PrazdneMazanieSceneController controller = new PrazdneMazanieSceneController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("PrazdneMazanie.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Zmazať stroj");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                return;
            } else {
                StrojDeleteSceneController controller
                        = new StrojDeleteSceneController(kliknutyStroj.getId());
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("PotvrdenieZmazania.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Zmazať stroj");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                if (controller.getBolZmazanyStroj()) {
                    strojeTableView.setItems(FXCollections.observableArrayList(strojDao.getAll()));
                    kliknutyStroj = null;
                }
            }
        });

        // zdroj: https://stackoverflow.com/questions/30191264/javafx-tableview-how-to-get-the-row-i-clicked
        TableView<Stroj> table = strojeTableView;
        table.setRowFactory(tv -> {
            TableRow<Stroj> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    kliknutyStroj = row.getItem();
                }
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    kliknutyStroj = row.getItem();
                    StrojPopisController controller = new StrojPopisController(kliknutyStroj);
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("StrojKonkretnyPopis.fxml"));
                        loader.setController(controller);
                        Parent parentPane = loader.load();
                        Scene scene = new Scene(parentPane);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Popis");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
            });
            return row;
        });

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        vyrobcaCol.setCellValueFactory(new PropertyValueFactory<>("vyrobca"));
        typCol.setCellValueFactory(new PropertyValueFactory<>("typ"));
        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        datumCol.setCellValueFactory(new PropertyValueFactory<>("fDatum"));
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));

        vsetkyStroje = strojDao.getAll();
        strojeTableView.setItems(FXCollections.observableArrayList(vsetkyStroje));
        if (vsetkyStroje.isEmpty()) {
            strojeTableView.setPlaceholder(new Label("V databáze sa žiadne stroje nenachádzajú."));
        }

        strojeTableView.setItems(FXCollections.observableArrayList(strojDao.getAll()));

        opravyButton.setOnAction(eh -> {
            StrojTabulkaOprav controller
                    = new StrojTabulkaOprav();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("TabulkaOprav.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Opravy");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        tankovanieButton.setOnAction(eh -> {
            StrojTabulkaTankovania controller
                    = new StrojTabulkaTankovania();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("TabulkaTankovania.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Tankovanie");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
    }
}
