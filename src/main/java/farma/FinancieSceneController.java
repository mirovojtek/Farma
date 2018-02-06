package farma;

import farma.biznis.FinancieManager;
import farma.biznis.FinancieManagerImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
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

public class FinancieSceneController {

    private final FinancieDao financieDao = DaoFactory.INSTANCE.getFinancieDao();
    private FinancieFxModel aktualnaPolozka = new FinancieFxModel();
    private List<Financie> vsetkyPolozky;
    private Financie kliknutaPolozka;
    private FinancieManager fManager = new FinancieManagerImpl();
    private Stav stav;
    private List<Stav> stavList = new ArrayList<>();

    @FXML
    private Button zobrazitObdobieButton;

    @FXML
    private Button zobrazDenButton;

    @FXML
    private Button zobrazTypButton;

    @FXML
    private Button zobrazVsetkyButton;

    @FXML
    private TableView<Financie> financieTableView;

    @FXML
    private TableColumn<Financie, Integer> idFinancieCol;

    @FXML
    private TableColumn<Financie, Object> datumFinancieCol;

    @FXML
    private TableColumn<Financie, String> typFinancieCol;

    @FXML
    private TableColumn<Financie, Double> sumaFinancieCol;

    @FXML
    private TableColumn<Financie, String> popisFinancieCol;

    @FXML
    private Button pridatPolozkuButton;

    @FXML
    private Button zmazatpolozkuButton;

    @FXML
    private TableView<Stav> stavTableView;

    @FXML
    private TableColumn<Stav, Double> prijmyCol;

    @FXML
    private TableColumn<Stav, Double> vydajeCol;

    @FXML
    private TableColumn<Stav, Double> spoluCol;

    @FXML
    void initialize() {

        idFinancieCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        datumFinancieCol.setCellValueFactory(new PropertyValueFactory<>("fDatum"));
        typFinancieCol.setCellValueFactory(new PropertyValueFactory<>("typ"));
        sumaFinancieCol.setCellValueFactory(new PropertyValueFactory<>("suma"));
        popisFinancieCol.setCellValueFactory(new PropertyValueFactory<>("popis"));
        financieTableView.setItems(FXCollections.observableArrayList(financieDao.getAll()));

        vsetkyPolozky = financieDao.getAll();
        financieTableView.setItems(FXCollections.observableArrayList(vsetkyPolozky));

        stav = fManager.getStav(vsetkyPolozky);
        prijmyCol.setCellValueFactory(new PropertyValueFactory<>("prijmy"));
        vydajeCol.setCellValueFactory(new PropertyValueFactory<>("vydaje"));
        spoluCol.setCellValueFactory(new PropertyValueFactory<>("spolu"));
        stavList.clear();
        stavList.add(stav);
        stavTableView.setItems(FXCollections.observableArrayList(stav));

        if (vsetkyPolozky.isEmpty()) {
            financieTableView.setPlaceholder(new Label("Žiadne finančné položky sa v databáze nenáchadzajú."));
        }

        zobrazDenButton.setOnAction(eh -> {
            FinancieVyberDnaSceneController controller = new FinancieVyberDnaSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("FinancieVyberDnaScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Vybrať deň");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getDate() != null) {
                vsetkyPolozky = financieDao.getAllByDate(controller.getDate());
                financieTableView.setItems(FXCollections.observableArrayList(vsetkyPolozky));
                stav = fManager.getStav(vsetkyPolozky);
                stavList.clear();
                stavList.add(stav);
                stavTableView.setItems(FXCollections.observableArrayList(stav));
                if (vsetkyPolozky.size() == 0) {
                    financieTableView.setPlaceholder(new Label("Finančné položky daného typu sa nenašli."));
                    // zdroj: https://stackoverflow.com/questions/24765549/remove-the-default-no-content-in-table-text-for-empty-javafx-table
                }
            }
            kliknutaPolozka = null;
        });

        zobrazTypButton.setOnAction(eh -> {
            FinancieVyberTypuSceneController controller = new FinancieVyberTypuSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("FinancieVyberTypuScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Vybrať typ");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getTyp() != null) {
                vsetkyPolozky = financieDao.getAllByTyp(controller.getTyp());
                financieTableView.setItems(FXCollections.observableArrayList(vsetkyPolozky));

                stav = fManager.getStav(vsetkyPolozky);
                stavList.clear();
                stavList.add(stav);
                stavTableView.setItems(FXCollections.observableArrayList(stav));

                if (vsetkyPolozky.size() == 0) {
                    financieTableView.setPlaceholder(new Label("Finančné položky daného typu sa nenašli."));
                    // zdroj: https://stackoverflow.com/questions/24765549/remove-the-default-no-content-in-table-text-for-empty-javafx-table
                }
            }
            kliknutaPolozka = null;
        });

        zobrazitObdobieButton.setOnAction(eh -> {
            FinancieZaObdobieSceneController controller = new FinancieZaObdobieSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("FinancieZaObdobieScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Vybrať typ");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }

            if (controller.getBolVyber()) {
                vsetkyPolozky = financieDao.getAllZaObdobie(controller.getDatumOd(), controller.getDatumDo());
                financieTableView.setItems(FXCollections.observableArrayList(vsetkyPolozky));
                stav = fManager.getStav(vsetkyPolozky);
                stavList.clear();
                stavList.add(stav);
                stavTableView.setItems(FXCollections.observableArrayList(stav));
                if (vsetkyPolozky.isEmpty()) {
                    financieTableView.setPlaceholder(new Label("Žiadne finančné položky sa v databáze nenáchadzajú."));
                }
                kliknutaPolozka = null;
            }
        });

        zobrazVsetkyButton.setOnAction(eh -> {
            vsetkyPolozky = financieDao.getAll();
            financieTableView.setItems(FXCollections.observableArrayList(vsetkyPolozky));

            stav = fManager.getStav(vsetkyPolozky);
            prijmyCol.setCellValueFactory(new PropertyValueFactory<>("prijmy"));
            vydajeCol.setCellValueFactory(new PropertyValueFactory<>("vydaje"));
            spoluCol.setCellValueFactory(new PropertyValueFactory<>("spolu"));
            stavList.clear();
            stavList.add(stav);

            stavTableView.setItems(FXCollections.observableArrayList(stav));

            if (vsetkyPolozky.isEmpty()) {
                financieTableView.setPlaceholder(new Label("Žiadne finančné položky sa v databáze nenáchadzajú."));
            }
            kliknutaPolozka = null;
        });

        pridatPolozkuButton.setOnAction(eh -> {
            FinancieEditSceneController controller = new FinancieEditSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("FinancieEditScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Pridať položku");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getBolaPridanaPolozka()) {
                financieTableView.setItems(FXCollections.observableArrayList(financieDao.getAll()));
                stav = fManager.getStav(financieDao.getAll());
                stavList.clear();
                stavList.add(stav);
                stavTableView.setItems(FXCollections.observableArrayList(stavList));
            }
        });

        TableView<Financie> table = financieTableView;
        table.setRowFactory(tv -> {
            TableRow<Financie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    kliknutaPolozka = row.getItem();
                }
            });
            return row;
        });

        zmazatpolozkuButton.setOnAction(eh -> {
            if (kliknutaPolozka == null) {
                PrazdneMazanieSceneController controller = new PrazdneMazanieSceneController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("PrazdneMazanie.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Zmazať položku");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                return;
            }
            FinancieDeleteSceneController controller
                    = new FinancieDeleteSceneController(kliknutaPolozka.getId());
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PotvrdenieZmazania.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Zmazať položku");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (controller.getZmazanaPolozka()) {
                vsetkyPolozky = financieDao.getAll();
                financieTableView.setItems(FXCollections.observableArrayList(vsetkyPolozky));
                stav = fManager.getStav(vsetkyPolozky);
                stavList.clear();
                stavList.add(stav);
                stavTableView.setItems(FXCollections.observableArrayList(stav));
            }
            kliknutaPolozka = null;
        });
    }
}
