
package farma;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;


public class StrojeEditSceneController {
    private StrojFxModel aktualnyStroj;
    private StrojDao strojDao = DaoFactory.INSTANCE.getStrojDao();;
    
    public StrojeEditSceneController(){
        aktualnyStroj = new StrojFxModel();
    }
    
    @FXML
    private TextField vyrobcaTextField;

    @FXML
    private TextField typTextField;

    @FXML
    private TextField kategoriaTextField;

    @FXML
    private TextField datumTextField;

    @FXML
    private TextField cenaTextField;

    @FXML
    private Button vlozitButton;

    @FXML
    void initialize() {
         
         vyrobcaTextField.textProperty().bindBidirectional(aktualnyStroj.vyrobcaProperty());
         typTextField.textProperty().bindBidirectional(aktualnyStroj.typProperty());
         kategoriaTextField.textProperty().bindBidirectional(aktualnyStroj.kategoriaProperty());
         
         StringConverter<Number> converter = new NumberStringConverter();
         cenaTextField.textProperty().bindBidirectional(aktualnyStroj.cenaProperty(), converter);
         
        /* datumTextField.textProperty().bindBidirectional(aktualnyStroj.datumProperty(),
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
        });*/
        aktualnyStroj.setDatum(LocalDateTime.now());
         
         vlozitButton.setOnAction(eh ->{
            strojDao.add(aktualnyStroj.getStroj());
         });
    }
}
