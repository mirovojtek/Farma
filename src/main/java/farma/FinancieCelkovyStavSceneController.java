package farma;

import com.sun.prism.paint.Color;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class FinancieCelkovyStavSceneController {

    private FinancieDao financieDao = DaoFactory.INSTANCE.getFinancieDao();

    @FXML
    private TextFlow vydajeTextFlow;

    @FXML
    private TextFlow prijmyTextFlow;

    @FXML
    private TextFlow spoluTextFlow;

    @FXML
    private Button okButton;

    @FXML
    void initialize() {

        List<Financie> polozky = financieDao.getAll();
        if (polozky.isEmpty()) {
            vydajeTextFlow.getChildren().add(new Text("0"));
            prijmyTextFlow.getChildren().add(new Text("0"));
            spoluTextFlow.getChildren().add(new Text("0"));
        } else {
            double prijmy = 0;
            double vydaje = 0;
            double spolu = 0;

            for (Financie polozka : polozky) {
                if (polozka.getTyp().equals("výdaj")) {
                    vydaje += polozka.getSuma();
                } else {
                    prijmy += polozka.getSuma();
                }
            }
            spolu = prijmy - vydaje;
            if (spolu < 0) {
                spoluTextFlow.setStyle("-fx-background-color: red");
            }
            if (spolu > 0) {
                spoluTextFlow.setStyle("-fx-background-color: lightgreen");
            }
            vydajeTextFlow.getChildren().add(new Text(Double.toString(vydaje)));
            prijmyTextFlow.getChildren().add(new Text(Double.toString(prijmy)));
            spoluTextFlow.getChildren().add(new Text(Double.toString(spolu)));
        }

        okButton.setOnAction(eh -> {
            okButton.getScene().getWindow().hide();
        });

    }
}
