package dominio.Lista;

import dominio.Nodo.NodoGen;

public class ListaGen<T> implements Lista<T>{
    private NodoGen<T> primero;
    private NodoGen<T> ultimo;
    private int cantNodos;

    public ListaGen() {
        this.primero = null;
        this.ultimo = null;
        this.cantNodos = 0;
    }

    public NodoGen<T> getPrimero() {
        return primero;
    }

    public void setPrimero(NodoGen<T> primero) {
        this.primero = primero;
    }

    public NodoGen<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoGen<T> ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public void setCantNodos(int cantNodos) {
        this.cantNodos = cantNodos;
    }

    @Override
    public void agregarAlFinal(T elementoNuevo) {
        NodoGen<T> nuevo = new NodoGen<>(elementoNuevo);

        if(estaVacia()){
            setPrimero(nuevo);
            setUltimo(nuevo);
        }else{
            ultimo.setSiguiente(nuevo);
            setUltimo(nuevo);
        }
        cantNodos++;
    }

    @Override
    public boolean estaVacia() {
        return getPrimero() ==null;
    }


    @Override
    public String toString(){
        String mostrar="";
        NodoGen<T> aux=getPrimero();
        for(int i=0;i<getCantNodos()-1;i++){
            mostrar += ""+aux.getDato()+" | ";
            aux=aux.getSiguiente();
        }
        mostrar += aux.getDato();
        return mostrar;
    }
}
