package farma.biznis;

import farma.Financie;
import farma.Stav;
import java.util.List;

public interface FinancieManager {
    
    Stav getStav (List<Financie> polozky);
    
}
