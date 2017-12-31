package farma.biznis;

import farma.Financie;
import farma.Stav;
import java.util.List;

public class FinancieManagerImpl implements FinancieManager {

    private Stav stav;

    @Override
    public Stav getStav(List<Financie> polozky) {
        double prijmy = 0;
        double vydaje = 0;
        double spolu = 0;
        if (!polozky.isEmpty()) {
            for (Financie polozka : polozky) {
                if (polozka.getTyp().equals("výdaj")) {
                    vydaje += polozka.getSuma();
                } else {
                    prijmy += polozka.getSuma();
                }
            }
            spolu = prijmy - vydaje;
            //if (spolu < 0) {spoluTextFlow.setStyle("-fx-background-color: red");}
            //if (spolu > 0) {spoluTextFlow.setStyle("-fx-background-color: lightgreen");}
            //vydajeTextFlow.getChildren().add(new Text(Double.toString(vydaje)));
            //prijmyTextFlow.getChildren().add(new Text(Double.toString(prijmy)));
            //spoluTextFlow.getChildren().add(new Text(Double.toString(spolu)));
        }
        return new Stav(prijmy, vydaje, spolu);
    }
}
