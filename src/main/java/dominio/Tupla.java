package dominio;

public class Tupla<T> {
    public T dato;
    public int cantidad;

    public Tupla() {
    }

    public Tupla(T dato, int cantidad) {
        this.dato = dato;
        this.cantidad = cantidad;
    }
}
