package farma;
import java.io.IOException;
import javafx.collections.FXCollections;
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

public class StrojSceneController {

    private Stroj kliknutyStroj;
    
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
    void initialize() {
        
        
        pridatButton.setOnAction(eh ->{
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
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            
        });
        
        TableView<Stroj> table = strojeTableView;
        table.setRowFactory(tv -> {
            TableRow<Stroj> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    kliknutyStroj = row.getItem();
                }
                
            });
        return row;
        });
        
        opravyButton.setOnAction(eh ->{
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
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
    }
}