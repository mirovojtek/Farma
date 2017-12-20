package farma;

import java.io.IOException;
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

public class PoliaSceneController {

    private final PoleDao poleDao = DaoFactory.INSTANCE.getPoleDao();
    private PoleFxModel aktualnePole = new PoleFxModel();
    private ObservableList<Pole> p;
    private Pole kliknutePole;
    private List<Pole> polia;

    public PoliaSceneController() {
        aktualnePole = new PoleFxModel();
    }

    @FXML
    private Button zobrazVsetkyButton;

    @FXML
    private Button rozsireneVyhladavanieButton;

    @FXML
    private TableView<Pole> poliaTableView;

    @FXML
    private TableColumn<Pole, Integer> idTableColumn;

    @FXML
    private TableColumn<Pole, String> typParcelyTableColumn;

    @FXML
    private TableColumn<Pole, String> cisloParcelyTableColumn;

    @FXML
    private TableColumn<Pole, Integer> vymeraTableColumn;

    @FXML
    private TableColumn<Pole, String> typPozemkuTableColumn;

    @FXML
    private TableColumn<Pole, String> vlastnictvoTableColumn;

    @FXML
    private Button pridatPoleButton;

    @FXML
    private Button zmazatPoleButton;

    @FXML
    void initialize() {

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typParcelyTableColumn.setCellValueFactory(new PropertyValueFactory<>("typParcely"));
        cisloParcelyTableColumn.setCellValueFactory(new PropertyValueFactory<>("cisloParcely"));
        vymeraTableColumn.setCellValueFactory(new PropertyValueFactory<>("vymera"));
        typPozemkuTableColumn.setCellValueFactory(new PropertyValueFactory<>("typPozemku"));
        vlastnictvoTableColumn.setCellValueFactory(new PropertyValueFactory<>("vlastnictvo"));

        polia = poleDao.getAll();
        poliaTableView.setItems(FXCollections.observableArrayList(poleDao.getAll()));
        if (polia.isEmpty()) {
            poliaTableView.setPlaceholder(new Label("Žiadne polia sa v databáze nenáchadzajú."));

        }

        pridatPoleButton.setOnAction(eh -> {
            PoliaEditSceneController controller
                    = new PoliaEditSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PoliaEditScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Pridať pole");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getBolaPridanaPolozka()) {
                poliaTableView.setItems(FXCollections.observableArrayList(poleDao.getAll()));
            }
        });

        zmazatPoleButton.setOnAction(eh -> {
            if (kliknutePole == null) {
                PrazdneMazanieSceneController controller = new PrazdneMazanieSceneController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("PrazdneMazanie.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Zmazať pole");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.showAndWait();
                    // toto sa vykona az po zatvoreni okna
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }

                return;
            }
            PoleDeleteSceneController controller
                    = new PoleDeleteSceneController(kliknutePole.getId());
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PotvrdenieZmazania.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Zmazať pole");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            poliaTableView.setItems(FXCollections.observableArrayList(poleDao.getAll()));
            kliknutePole = null;
        });

        rozsireneVyhladavanieButton.setOnAction(eh -> {
            PoliaRozsireneVyhladavanieController controller
                    = new PoliaRozsireneVyhladavanieController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PoleRozsireneVyhladavanie.fxml"));
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
                List<Pole> poleRozsirene = poleDao.rozsireneVyhladavanie(controller.getTypParcely(),
                        controller.getCisloParcely(), controller.getTypPozemku(), controller.getVlastnictvo());
                poliaTableView.setItems(FXCollections.observableArrayList(poleRozsirene));
                if (poleRozsirene.size() == 0) {
                    poliaTableView.setPlaceholder(new Label("Polia so zadanými parametrami sa v databáze nenachádzajú."));
                    // lebo: https://stackoverflow.com/questions/24765549/remove-the-default-no-content-in-table-text-for-empty-javafx-table
                }
            }
        });

        zobrazVsetkyButton.setOnAction(eh -> {
            polia = poleDao.getAll();
            poliaTableView.setItems(FXCollections.observableArrayList(poleDao.getAll()));
            if (polia.isEmpty()) {
                poliaTableView.setPlaceholder(new Label("Žiadne polia sa v databáze nenáchadzajú."));
            }
            kliknutePole = null;
        });

        // zdroj: https://stackoverflow.com/questions/30191264/javafx-tableview-how-to-get-the-row-i-clicked
        TableView<Pole> table = poliaTableView;
        table.setRowFactory(tv -> {
            TableRow<Pole> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    kliknutePole = row.getItem();
                }
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    kliknutePole = row.getItem();
                }
            });
            return row;
        });
    }
}
