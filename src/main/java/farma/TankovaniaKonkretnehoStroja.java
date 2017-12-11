
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

public class TankovaniaKonkretnehoStroja {
 
     private TankovanieDao tankovanieDao = DaoFactory.INSTANCE.getTankovanieDao();
     private Tankovanie kliknuteTankovanie;
     private Stroj kliknutyStroj;
     private StrojDao strojDao = DaoFactory.INSTANCE.getStrojDao();
     private ObservableList<Stroj> strojeList = null;
     
     public TankovaniaKonkretnehoStroja(Stroj stroj){
        kliknutyStroj = stroj;
    }
    
    @FXML
    private TableView<Stroj> konkretnyStrojTableView;

    @FXML
    private TableView<Tankovanie> tankovaniaTableView;

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
        
        TableColumn<Stroj, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        konkretnyStrojTableView.getColumns().add(idCol);

        TableColumn<Stroj, String> vyrobcaCol = new TableColumn<>("Výrobca");
        vyrobcaCol.setCellValueFactory(new PropertyValueFactory<>("vyrobca"));
       konkretnyStrojTableView.getColumns().add(vyrobcaCol);

        TableColumn<Stroj, String> typCol = new TableColumn<>("Typ");
        typCol.setCellValueFactory(new PropertyValueFactory<>("typ"));
       konkretnyStrojTableView.getColumns().add(typCol);

        TableColumn<Stroj, String> kategoriaCol = new TableColumn<>("Kategória");
        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
         konkretnyStrojTableView.getColumns().add(kategoriaCol);

        TableColumn<Stroj, Object> datumCol = new TableColumn<>("Dátum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
         konkretnyStrojTableView.getColumns().add(datumCol);

        TableColumn<Stroj, Double> cenaCol = new TableColumn<>("Cena");
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
         konkretnyStrojTableView.getColumns().add(cenaCol);
        
         konkretnyStrojTableView.setItems(strojeList);
        
        TableColumn<Tankovanie, Integer> idStrojCol = new TableColumn<>("Id Stroja");
        idStrojCol.setCellValueFactory(new PropertyValueFactory<>("strojId"));
        tankovaniaTableView.getColumns().add(idStrojCol);
        
        TableColumn<Tankovanie, Object> datumTankovanieCol = new TableColumn<>("Dátum");
        datumTankovanieCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        tankovaniaTableView.getColumns().add(datumTankovanieCol);
        
        TableColumn<Tankovanie, Double> cenaTankovanieCol = new TableColumn<>("Množstvo paliva");
        cenaTankovanieCol.setCellValueFactory(new PropertyValueFactory<>("mnozstvo"));
        tankovaniaTableView.getColumns().add(cenaTankovanieCol);
        
        tankovaniaTableView.setItems(FXCollections.observableArrayList(tankovanieDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
        
        pridatButton.setOnAction(eh ->{
         TankovanieEditSceneController controller = new TankovanieEditSceneController(kliknutyStroj);
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("PridatTankovanie.fxml"));
                        loader.setController(controller);
                        Parent parentPane = loader.load();
                        Scene scene = new Scene(parentPane);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Pridať tankovanie");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        // toto sa vykona az po zatvoreni okna
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                    tankovaniaTableView.setItems(FXCollections.observableArrayList(tankovanieDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
        });
        
        TableView<Tankovanie> table = tankovaniaTableView;
        table.setRowFactory(tv -> {
            TableRow<Tankovanie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    kliknuteTankovanie= row.getItem();
                }
                     });
            return row;
        });
        
        zmazatButton.setOnAction(eh ->{
            if (kliknuteTankovanie == null) {
                PrazdneMazanieSceneController controller = new PrazdneMazanieSceneController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("PrazdneMazanie.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Zmazať tankovanie");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    // toto sa vykona az po zatvoreni okna
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }

                return;
            }
            TankovanieDeleteSceneController controller
                    = new TankovanieDeleteSceneController(kliknuteTankovanie);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PotvrdenieZmazania.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Zmazať tankovanie");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            tankovaniaTableView.setItems(FXCollections.observableArrayList(tankovanieDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
            kliknuteTankovanie = null;
        });
        
        zmazatVsetkoButton.setOnAction(eh ->{
            TankovanieDeleteAllController controller
                    = new TankovanieDeleteAllController(kliknutyStroj);
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
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            tankovaniaTableView.setItems(FXCollections.observableArrayList(tankovanieDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
        });
    }
}
