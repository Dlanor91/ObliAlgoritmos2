package dominio.Cola;

import dominio.Nodo.NodoGen;

public class ColaImp<T> implements Cola<T> {

    private NodoGen<T> inicio;
    private NodoGen<T> fin;
    private int cantElementos;

    public ColaImp() {

    }

    public NodoGen<T> getInicio() {
        return inicio;
    }

    public void setInicio(NodoGen<T> inicio) {
        this.inicio = inicio;
    }

    public NodoGen<T> getFin() {
        return fin;
    }

    public void setFin(NodoGen<T> fin) {
        this.fin = fin;
    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }

    @Override
    public boolean esVacia() {
        return this.cantElementos == 0;
    }

    @Override
    public void encolar(T dato){
        if(this.inicio == null){
            inicio = new NodoGen<T>(dato);
            fin = inicio;
        }else{
            fin.setSiguiente(new NodoGen<T>(dato));
            fin = fin.getSiguiente();
        }
        this.cantElementos++;
    }

    @Override
    public T desencolar() {
        T dato = this.inicio.getDato();
        inicio = inicio.getSiguiente();
        this.cantElementos--;
        if(this.inicio == null){
            fin = null;
        }

        return dato;
    }


}
