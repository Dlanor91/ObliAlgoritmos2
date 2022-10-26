package dominio.Arbol;

public class NodoGenArbol<T> {
    private final T dato;
    private NodoGenArbol<T> izq;
    private NodoGenArbol<T> der;

    public NodoGenArbol(T unDato) {
        this.dato = unDato;
        this.izq = null;
        this.der = null;
    }

    public T getDato() {
        return dato;
    }


    public NodoGenArbol<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoGenArbol<T> izq) {
        this.izq = izq;
    }

    public NodoGenArbol<T> getDer() {
        return der;
    }

    public void setDer(NodoGenArbol<T> der) {
        this.der = der;
    }

}
