package farma;

import java.io.IOException;
import java.time.LocalDateTime;
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

public class PoliaSceneController {

    private PoleDao poleDao = DaoFactory.INSTANCE.getPoleDao();
    private PoleFxModel aktualnePole = new PoleFxModel();
    private ObservableList<Pole> p;
    private Pole kliknutePole;

    public PoliaSceneController() {
        aktualnePole = new PoleFxModel();
    }

    @FXML
    private Button podlaParcelyButton;

    @FXML
    private Button zobrazVsetkyButton;

    @FXML
    private Button rozsireneVyhladavanieButton;

    @FXML
    private TableView<Pole> poliaTableView;

    @FXML
    private Button pridatPoleButton;

    @FXML
    private Button zmazatPoleButton;

    @FXML
    void initialize() {

        zobrazVsetkyButton.setOnAction(eh -> {
            poliaTableView.setItems(FXCollections.observableArrayList(poleDao.getAll()));
            kliknutePole = null;
        });

        // id
        TableColumn<Pole, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        poliaTableView.getColumns().add(idCol);

        // parcela
        TableColumn<Pole, String> typParcelyCol = new TableColumn<>("Typ Parcely");
        typParcelyCol.setCellValueFactory(new PropertyValueFactory<>("typParcely"));
        poliaTableView.getColumns().add(typParcelyCol );
        
        TableColumn<Pole, String> cisloParcelyCol = new TableColumn<>("Cislo Parcely");
        cisloParcelyCol.setCellValueFactory(new PropertyValueFactory<>("cisloParcely"));
        poliaTableView.getColumns().add(cisloParcelyCol);

        // vymera
        TableColumn<Pole, Integer> vymeraCol = new TableColumn<>("Výmera");
        vymeraCol.setCellValueFactory(new PropertyValueFactory<>("vymera"));
        poliaTableView.getColumns().add(vymeraCol);

        // typ
        TableColumn<Pole, String> typCol = new TableColumn<>("Typ Pozemku");
        typCol.setCellValueFactory(new PropertyValueFactory<>("typPozemku"));
        poliaTableView.getColumns().add(typCol);

        // kúpna cena
        TableColumn<Pole, Double> vlastnictvoCol = new TableColumn<>("Vlastnictvo");
        vlastnictvoCol.setCellValueFactory(new PropertyValueFactory<>("vlastnictvo"));
        poliaTableView.getColumns().add(vlastnictvoCol);

        poliaTableView.setItems(FXCollections.observableArrayList(poleDao.getAll()));

        pridatPoleButton.setOnAction(eh ->{
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
            poliaTableView.setItems(FXCollections.observableArrayList(poleDao.getAll()));
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
                    /*
                    PolePopisController controller = new PolePopisController(kliknutePole);
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
                        // toto sa vykona az po zatvoreni okna
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                     */
                }
            });
            return row;
        });

    }

}
