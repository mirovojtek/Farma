package farma;

import java.io.IOException;
import java.time.Clock;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZvierataSceneController {

    private ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    private ZvieraFxModel aktualneZviera = new ZvieraFxModel();
    private ObservableList<Zviera> z;
    private Zviera kliknuteZviera;

    public ZvierataSceneController() {
        aktualneZviera = new ZvieraFxModel();
    }

    @FXML
    private Button pridatZvieraButton;

    @FXML
    private TableView<Zviera> zvierataTableView;

    @FXML
    private Button zmazatZvieraButton;

    @FXML
    void initialize() {
        List<Zviera> zvierata = zvieraDao.getAll();

        // registračné číslo
        TableColumn<Zviera, String> registracneCisloCol = new TableColumn<>("Registračné číslo");
        registracneCisloCol.setCellValueFactory(new PropertyValueFactory<>("registracneCislo"));
        zvierataTableView.getColumns().add(registracneCisloCol);

        // druh
        TableColumn<Zviera, String> druhCol = new TableColumn<>("Druh");
        druhCol.setCellValueFactory(new PropertyValueFactory<>("druh"));
        zvierataTableView.getColumns().add(druhCol);

        // plemeno
        TableColumn<Zviera, String> plemenoCol = new TableColumn<>("Plemeno");
        plemenoCol.setCellValueFactory(new PropertyValueFactory<>("plemeno"));
        zvierataTableView.getColumns().add(plemenoCol);

        // pohlavie
        TableColumn<Zviera, String> pohlavieCol = new TableColumn<>("Pohlavie");
        pohlavieCol.setCellValueFactory(new PropertyValueFactory<>("pohlavie"));
        zvierataTableView.getColumns().add(pohlavieCol);

        // dátum narodenia
        TableColumn<Zviera, Object> datumNarodeniaCol = new TableColumn<>("Dátum narodenia");
        datumNarodeniaCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNarodenia"));
        zvierataTableView.getColumns().add(datumNarodeniaCol);

        // dátum nadobudnutia
        TableColumn<Zviera, Object> datumNadobudnutiaCol = new TableColumn<>("Dátum nadobudnutia");
        datumNadobudnutiaCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNadobudnutia"));
        zvierataTableView.getColumns().add(datumNadobudnutiaCol);

        // kúpna cena
        TableColumn<Zviera, Double> kupnaCenaCol = new TableColumn<>("Kúpna cena");
        kupnaCenaCol.setCellValueFactory(new PropertyValueFactory<>("kupnaCena"));
        zvierataTableView.getColumns().add(kupnaCenaCol);

        zvierataTableView.setItems(FXCollections.observableArrayList(zvieraDao.getAll()));

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
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            zvierataTableView.setItems(FXCollections.observableArrayList(zvieraDao.getAll()));
        });

        TableView<Zviera> table = zvierataTableView;
        table.setRowFactory(tv -> {
            TableRow<Zviera> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                    kliknuteZviera = row.getItem();
                }
            });
            return row;
        });

        zmazatZvieraButton.setOnAction(eh -> {
            if (kliknuteZviera == null) {
                ZvieraPrazdneMazanieSceneController controller = new ZvieraPrazdneMazanieSceneController();
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
                    stage.showAndWait();
                    // toto sa vykona az po zatvoreni okna
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }

                return;
            }
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
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            zvierataTableView.setItems(FXCollections.observableArrayList(zvieraDao.getAll()));
        });

    }
}
