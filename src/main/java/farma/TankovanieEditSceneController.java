
package farma;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class TankovanieEditSceneController {
  
    private TankovanieFxModel aktualneTankovanie;
    private TankovanieDao tankovanieDao = DaoFactory.INSTANCE.getTankovanieDao();
    
    public TankovanieEditSceneController(Stroj stroj){
        aktualneTankovanie = new TankovanieFxModel(stroj);
        aktualneTankovanie.setDatum(LocalDateTime.now());
    }
    
    @FXML
    private TextField mnozstvoTextField;

    @FXML
    private TextField datumTextField;

    @FXML
    private Button vlozitButton;

    @FXML
    void initialize() {
     
        StringConverter<Number> converter = new NumberStringConverter();
        mnozstvoTextField.textProperty().bindBidirectional(aktualneTankovanie.mnozstvoProperty(), converter);
        datumTextField.textProperty().bindBidirectional(aktualneTankovanie.datumProperty(),
                new StringConverter<LocalDateTime>() {

            private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy H:m");

            @Override
            public String toString(LocalDateTime t) {
                return formatter.format(t);
            }

            @Override
            public LocalDateTime fromString(String string) {
                try {
                    datumTextField.setStyle("-fx-background-color: white;");
                    return LocalDateTime.parse(string, formatter);
                } catch (DateTimeParseException e) {
                    datumTextField.setStyle("-fx-background-color: lightcoral;");
                    return null;
                }
            }
            
            });
        
        vlozitButton.setOnAction(eh ->{
            try{
                tankovanieDao.add(aktualneTankovanie.getTankovanie());
                mnozstvoTextField.clear();
                datumTextField.clear();
            }
                catch (Exception e) {
                    System.err.println(e);

                    NespravneVyplnanieController controller = new NespravneVyplnanieController();
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("NespravneVyplnenie.fxml"));
                        loader.setController(controller);
                        Parent parentPane = loader.load();
                        Scene scene = new Scene(parentPane);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Nesprávne vyplnenie údajov");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        // toto sa vykona az po zatvoreni okna
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
        });
        
    }
    
}
