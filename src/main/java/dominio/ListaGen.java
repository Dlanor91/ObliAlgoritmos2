package dominio;

public class ListaGen<T> {
    NodoGenLista<T> primero;
    NodoGenLista<T> ultimo;
    int cantNodos;

    public ListaGen() {
        this.primero = null;
        this.ultimo = null;
        this.cantNodos = 0;
    }

    public NodoGenLista<T> getPrimero() {
        return primero;
    }

    public void setPrimero(NodoGenLista<T> primero) {
        this.primero = primero;
    }

    public NodoGenLista<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoGenLista<T> ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public void setCantNodos(int cantNodos) {
        this.cantNodos = cantNodos;
    }


    public void agregarAlPrincipio(T valorNuevo) {
        NodoGenLista<T> nuevo = new NodoGenLista<>(valorNuevo);

        if(estaVacia()){
            setPrimero(nuevo);
            setUltimo(nuevo);
        }else{
            nuevo.setSiguiente(primero);
            primero= nuevo;
        }
        cantNodos++;
    }

    public void agregarAlFinal(T elementoNuevo) {
        NodoGenLista<T> nuevo = new NodoGenLista<>(elementoNuevo);

        if(estaVacia()){
            setPrimero(nuevo);
            setUltimo(nuevo);
        }else{
            ultimo.setSiguiente(nuevo);
            setUltimo(nuevo);
        }
        cantNodos++;
    }


    public T eliminarPrincipio() {

        NodoGenLista<T> aux =getPrimero();
        if(primero == ultimo){
            primero = null;
            ultimo= null;
            cantNodos --;
            return aux.getDato();
        }else {
            primero = primero.siguiente;
            cantNodos --;
            return aux.getDato();
        }

    }


    public int getLargo() {
        return getCantNodos();
    }


    public boolean estaVacia() {
        return getPrimero() ==null;
    }


    public T obtener(int indice) {
        NodoGenLista<T> aux = getPrimero();
        boolean encontrado = false;
        int conteo =0;
        while(aux !=null && !encontrado){
            if(conteo ==indice ){
                encontrado = true;
            }else{
                conteo++;
                aux = aux.getSiguiente();
            }
        }
        return aux.getDato();
    }

    @Override
    public String toString(){
        String mostrar="";
        NodoGenLista<T> aux=getPrimero();
        for(int i=0;i<getCantNodos()-1;i++){
            mostrar += ""+aux.getDato()+", ";
            aux=aux.getSiguiente();
        }
        mostrar += aux.getDato();
        return mostrar;
    }
}
