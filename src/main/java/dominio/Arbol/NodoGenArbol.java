package dominio.Arbol;

public class NodoGenArbol<T> {
    private T dato;
    private NodoGenArbol<T> izq;
    private NodoGenArbol<T> der;

    public NodoGenArbol(T unDato) {
        this.dato = unDato;
        this.izq = null;
        this.der = null;
    }

    public NodoGenArbol() {
    }

    public NodoGenArbol(T unDato, NodoGenArbol<T> izq, NodoGenArbol<T> der) {
        this.dato = unDato;
        this.izq = izq;
        this.der = der;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
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
