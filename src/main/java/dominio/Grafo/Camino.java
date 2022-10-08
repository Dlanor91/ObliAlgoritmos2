package dominio.Grafo;

import interfaz.EstadoCamino;

public class Camino {

    private boolean existe;
    private double costo;
    private double tiempo;
    private double kms;
    private EstadoCamino estadoDelCamino;

    public Camino() {
        this.existe = false;
    }

    public Camino(double costo, double tiempo, double kms, EstadoCamino estadoDelCamino) {
        this.existe = true;
        this.costo = costo;
        this.tiempo = tiempo;
        this.kms = kms;
        this.estadoDelCamino = estadoDelCamino;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public EstadoCamino getEstadoDelCamino() {
        return estadoDelCamino;
    }

    public void setEstadoDelCamino(EstadoCamino estadoDelCamino) {
        this.estadoDelCamino = estadoDelCamino;
    }

    public static boolean validar(double costo, double tiempo, double kms){
        if(costo > 0 && tiempo > 0 && kms > 0){
            return true;
        }
        return false;
    }

    public static boolean validar(EstadoCamino ec){
        if(ec !=null){
            return true;
        }
        return false;
    }
}
