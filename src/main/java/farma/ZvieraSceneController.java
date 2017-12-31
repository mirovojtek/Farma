package farma;

import farma.DaoFactory;
import farma.PrazdneMazanieSceneController;
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

public class ZvieraSceneController {

    private final ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    private ZvieraFxModel aktualneZviera;
    private ObservableList<Zviera> z;
    private Zviera kliknuteZviera;

    public ZvieraSceneController() {
        aktualneZviera = new ZvieraFxModel();
    }

    @FXML
    private Button pridatZvieraButton;

    @FXML
    private TableView<Zviera> zvierataTableView;

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
    private Button zmazatZvieraButton;

    @FXML
    private Button podlaRegCislaButton;

    @FXML
    private Button zobrazVsetkyButton;

    @FXML
    private Button rozsireneVyhladavanieButton;

    @FXML
    void initialize() {
        zobrazVsetkyButton.setOnAction(eh -> {
            List<Zviera> vsetkyZvierata = new ArrayList<Zviera>();
            vsetkyZvierata.addAll(zvieraDao.getAll());
            zvierataTableView.setItems(FXCollections.observableArrayList(vsetkyZvierata));
            if (vsetkyZvierata.isEmpty()) {
                zvierataTableView.setPlaceholder(new Label("V databáze sa žiadne zvieratá nenachádzajú."));
            }
            kliknuteZviera = null;
        });

        podlaRegCislaButton.setOnAction(eh -> {
            ZvieraPodlaRegistracnehoCislaController controller = new ZvieraPodlaRegistracnehoCislaController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ZadanieRegistracnehoCisla.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Zadajte registračné číslo");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getAkcia()) {
                Zviera zvieraPodlaRegCisla = zvieraDao.findByRegistracneCislo(controller.getRC());
                ObservableList<Zviera> zvieraPodlaRegCislaList = FXCollections.observableArrayList();
                if (zvieraPodlaRegCisla == null) {
                    zvierataTableView.setItems(FXCollections.observableArrayList(zvieraPodlaRegCislaList));
                    zvierataTableView.setPlaceholder(new Label("Zviera so zadaným registračným číslom sa v databáze nenachádza."));
                    // lebo: https://stackoverflow.com/questions/24765549/remove-the-default-no-content-in-table-text-for-empty-javafx-table
                } else {
                    zvieraPodlaRegCislaList.add(zvieraPodlaRegCisla);
                    zvierataTableView.setItems(FXCollections.observableArrayList(zvieraPodlaRegCislaList));
                }
            }
        });

        registracneCisloZvieraCol.setCellValueFactory(new PropertyValueFactory<>("registracneCislo"));
        druhZvieraCol.setCellValueFactory(new PropertyValueFactory<>("druh"));
        plemenoZvieraCol.setCellValueFactory(new PropertyValueFactory<>("plemeno"));
        pohlavieZvieraCol.setCellValueFactory(new PropertyValueFactory<>("pohlavie"));
        datumNarodeniaZvieraCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNarodenia"));
        datumNadobudnutiaZvieraCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNadobudnutia"));
        cenaZvieraCol.setCellValueFactory(new PropertyValueFactory<>("kupnaCena"));

        List<Zviera> vsetkyZvierata = zvieraDao.getAll();
        zvierataTableView.setItems(FXCollections.observableArrayList(vsetkyZvierata));
        if (vsetkyZvierata.isEmpty()) {
            zvierataTableView.setPlaceholder(new Label("V databáze sa žiadne zvieratá nenachádzajú."));
        }

        pridatZvieraButton.setOnAction(eh -> {
            ZvierataEditSceneController controller
                    = new ZvierataEditSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ZvierataEditScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Pridať zviera");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getBolaPridanaPolozka()) {
                zvierataTableView.setItems(FXCollections.observableArrayList(zvieraDao.getAll()));
            }
        });

        // zdroj: https://stackoverflow.com/questions/30191264/javafx-tableview-how-to-get-the-row-i-clicked
        TableView<Zviera> table = zvierataTableView;
        table.setRowFactory(tv -> {
            TableRow<Zviera> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    kliknuteZviera = row.getItem();
                }
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    kliknuteZviera = row.getItem();
                    ZvieraPopisController controller = new ZvieraPopisController(kliknuteZviera);
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("ZvieraPopis.fxml"));
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

        zmazatZvieraButton.setOnAction(eh -> {
            if (kliknuteZviera == null) {
                PrazdneMazanieSceneController controller = new PrazdneMazanieSceneController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("PrazdneMazanie.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Zmazať zviera");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.showAndWait();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            } else {
                ZvieraDeleteSceneController controller
                        = new ZvieraDeleteSceneController(kliknuteZviera.getRegistracneCislo());
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("PotvrdenieZmazania.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Zmazať zviera");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                if (controller.getBoloZmazaneZviera()) {
                    zvierataTableView.setItems(FXCollections.observableArrayList(zvieraDao.getAll()));
                    kliknuteZviera = null;
                }
            }
        });

        rozsireneVyhladavanieButton.setOnAction(eh -> {
            ZvieraRozsireneVyhladavanieController controller = new ZvieraRozsireneVyhladavanieController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ZvieraRozsireneVyhladavanie.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Rozšírene vyhľadávanie");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getAkcia() == true) {
                zvierataTableView.setItems(FXCollections.observableArrayList(zvieraDao.rozsireneVyhladavanie(
                        controller.getDruh(), controller.getPlemeno(), controller.getRokNarodenia(),
                        controller.getRokNadobudnutia(), controller.getPohlavie())));
            }
        });
    }
}
