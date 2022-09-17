package dominio;

public class NodoGenLista<T> {
    private T dato;
    NodoGenLista<T> siguiente;

    public NodoGenLista(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoGenLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGenLista<T> siguiente) {
        this.siguiente = siguiente;
    }
}
