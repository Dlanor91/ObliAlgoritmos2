package dominio.Nodo;

public class NodoGen<T> {
    private T dato;
    NodoGen<T> siguiente;

    public NodoGen(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }


    public NodoGen<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGen<T> siguiente) {
        this.siguiente = siguiente;
    }
}
