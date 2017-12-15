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

public class FinancieSceneController {

    private final FinancieDao financieDao = DaoFactory.INSTANCE.getFinancieDao();
    private FinancieFxModel aktualnaPolozka = new FinancieFxModel();
    private ObservableList<Financie> f;
    private Financie kliknutaPolozka;

    @FXML
    private Button zobrazVsetkyButton;

    @FXML
    private Button rozsireneVyhladavanieButton;

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
    void initialize() {

        idFinancieCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        datumFinancieCol.setCellValueFactory(new PropertyValueFactory<>("fDatum"));
        typFinancieCol.setCellValueFactory(new PropertyValueFactory<>("typ"));
        sumaFinancieCol.setCellValueFactory(new PropertyValueFactory<>("suma"));
        popisFinancieCol.setCellValueFactory(new PropertyValueFactory<>("popis"));
        financieTableView.setItems(FXCollections.observableArrayList(financieDao.getAll()));

        zobrazVsetkyButton.setOnAction(eh -> {
            financieTableView.setItems(FXCollections.observableArrayList(financieDao.getAll()));
            kliknutaPolozka = null;
        });

        /*
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
         */
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
            financieTableView.setItems(FXCollections.observableArrayList(financieDao.getAll()));

        });
        /*
        zmazatPolozkuButton.setOnAction(eh -> {
           
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
            }
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
            strojeTableView.setItems(FXCollections.observableArrayList(strojDao.getAll()));
            kliknutyStroj = null;
            }
                );
         */
    }
}
