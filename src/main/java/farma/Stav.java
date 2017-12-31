package farma;

public class Stav {

    private double prijmy;
    private double vydaje;
    private double spolu;

    public Stav(double prijmy, double vydaje, double spolu) {
        this.prijmy = prijmy;
        this.vydaje = vydaje;
        this.spolu = spolu;
    }

    public double getPrijmy() {
        return prijmy;
    }

    public void setPrijmy(double prijmy) {
        this.prijmy = prijmy;
    }

    public double getVydaje() {
        return vydaje;
    }

    public void setVydaje(double vydaje) {
        this.vydaje = vydaje;
    }

    public double getSpolu() {
        return spolu;
    }

    public void setSpolu(double spolu) {
        this.spolu = spolu;
    }

}
