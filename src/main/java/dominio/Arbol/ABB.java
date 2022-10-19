package dominio.Arbol;


import dominio.Lista.ListaGen;
import interfaz.Consulta;

public class ABB<T extends Comparable<T>> {

    private NodoGenArbol<T> raiz;

    public ABB() {
        this.raiz = null;
    }

    public void insertar(T dato) {
        if (this.raiz == null) {
            this.raiz = new NodoGenArbol<>(dato);
        } else {
            insertarRec(this.raiz, dato);
        }
    }

    private void insertarRec(NodoGenArbol<T> nodo, T dato) {
        if (dato.compareTo(nodo.getDato()) < 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoGenArbol<>(dato));
            } else {
                insertarRec(nodo.getIzq(), dato);
            }
        } else if (dato.compareTo(nodo.getDato()) > 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoGenArbol<>(dato));
            } else {
                insertarRec(nodo.getDer(), dato);
            }
        }

    }

    public ListaGen<T> listarAscendente() {
        ListaGen<T> nuevaLista = new ListaGen<>();
        listarAscendente(raiz, nuevaLista);
        return nuevaLista;
    }

    private void listarAscendente(NodoGenArbol<T> nodo, ListaGen<T> nuevaLista) {
        if (nodo != null) {
            listarAscendente(nodo.getIzq(), nuevaLista);
            nuevaLista.agregarAlFinal((T) nodo.getDato());
            listarAscendente(nodo.getDer(), nuevaLista);
        }
    }

    public ListaGen listarDescendente() {
        ListaGen<T> nuevaLista = new ListaGen<>();
        listarDescendente(this.raiz, nuevaLista);
        return nuevaLista;
    }

    private void listarDescendente(NodoGenArbol<T> nodo, ListaGen<T> nuevaLista) {
        if (nodo != null) {
            listarDescendente(nodo.getDer(), nuevaLista);
            nuevaLista.agregarAlFinal((T) nodo.getDato());
            listarDescendente(nodo.getIzq(), nuevaLista);
        }
    }

    public boolean pertenece(T dato) {
        return pertenece(dato, raiz);
    }

    private boolean pertenece(T dato, NodoGenArbol<T> nodo) {
        if (nodo == null) {
            return false;
        } else if (dato.compareTo((T) nodo.getDato()) == 0) {
            return true;
        } else {
            if (dato.compareTo((T) nodo.getDato()) < 0) {
                return pertenece(dato, nodo.getIzq());
            } else {
                return pertenece(dato, nodo.getDer());
            }
        }
    }

    public Tupla buscar(T dato) {
        Tupla parTupla = new Tupla<>(null, 0);
        buscar(dato, raiz, parTupla);
        return parTupla;
    }

    private void buscar(T dato, NodoGenArbol<T> nodo, Tupla nuevaTupla) {
        if (nodo != null && nuevaTupla.getDato() == null) {
            nuevaTupla.setCantidad(nuevaTupla.getCantidad()+1);
            if (dato.compareTo(nodo.getDato()) == 0) {
                nuevaTupla.setDato(nodo.getDato());
            } else {
                if (dato.compareTo((T) nodo.getDato()) < 0) {
                    buscar(dato, nodo.getIzq(), nuevaTupla);
                } else {
                    buscar(dato, nodo.getDer(), nuevaTupla);
                }
            }
        }
    }

    public ABB<T> filtrar(Consulta consulta){
       ABB<T> encontrados = new ABB<>();
       filtrar(this.raiz,consulta);
       return encontrados;
    }

    private void filtrar(NodoGenArbol<T> nodo, Consulta consulta) {
        if(nodo !=null){

        }
    }
}
