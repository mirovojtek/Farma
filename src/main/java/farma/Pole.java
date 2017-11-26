package farma;

public class Pole {

    private int id;
    private String parcela;
    private int vymera;
    private String typ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public int getVymera() {
        return vymera;
    }

    public void setVymera(int vymera) {
        this.vymera = vymera;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    @Override
    public String toString() {
        return id + " " + parcela + " " + vymera + " " + typ;
    }

}
