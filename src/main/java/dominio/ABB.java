package dominio;


public class ABB<T extends Comparable<T>> {

    private NodoGenArbol raiz;

    public ABB() {
        this.raiz = null;
    }

    // Pos: Inserta el dato pasado como parametro en el arbol manteniendolo ordenado.
    public void insertar(T dato) {
        if (this.raiz == null) {
            this.raiz = new NodoGenArbol(dato);
        } else {
            insertarRec(this.raiz, dato);
        }
    }

    private void insertarRec(NodoGenArbol<Jugador> nodo, T dato) {
        if (dato.compareTo((T) nodo.getDato())<0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoGenArbol(dato));
            } else {
                insertarRec(nodo.getIzq(), dato);
            }
        } else if (dato.compareTo((T) nodo.getDato())>0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoGenArbol(dato));
            } else {
                insertarRec(nodo.getDer(), dato);
            }
        }

    }

    //In Order
    public ListaGen<T> listarAscendente(){
        ListaGen<T> nuevaLista = new ListaGen<>();
        listarAscendente(raiz,nuevaLista);
        return nuevaLista;
    }

    private void listarAscendente(NodoGenArbol nodo,ListaGen<T> nuevaLista) {
        if(nodo != null){
            listarAscendente(nodo.getIzq(),nuevaLista);
            nuevaLista.agregarAlFinal((T)nodo.getDato());
            listarAscendente(nodo.getDer(),nuevaLista);
        }
    }

    public ListaGen listarDescendente(){
        ListaGen<T> nuevaLista = new ListaGen<>();
        listarDescendente(this.raiz,nuevaLista);
        return nuevaLista;
    }

    private void listarDescendente(NodoGenArbol nodo,ListaGen<T> nuevaLista) {
        if(nodo != null){
            listarDescendente(nodo.getDer(),nuevaLista);
            nuevaLista.agregarAlFinal((T)nodo.getDato());
            listarDescendente(nodo.getIzq(),nuevaLista);
        }
    }

    public boolean pertenece(T dato){
        return pertenece(dato,raiz);
    }

    private boolean pertenece(T dato, NodoGenArbol nodo) {
        if(nodo == null){
            return false;
        }else if(dato.compareTo((T) nodo.getDato())==0){
            return true;
        }else{
            if(dato.compareTo((T) nodo.getDato())<0){
                return pertenece(dato,nodo.getIzq());
            }else{
                return pertenece(dato,nodo.getDer());
            }
        }
    }

    public Tupla buscar(T dato){

        Tupla parTupla = new Tupla<>(null,0);
        buscar(dato,raiz,parTupla);
        return parTupla;
    }

    private void buscar(T dato, NodoGenArbol nodo,Tupla nuevaTupla) {

        if (nodo != null && nuevaTupla.dato == null) {
            nuevaTupla.cantidad++;
            if (dato.compareTo((T) nodo.getDato()) == 0) {
                nuevaTupla.dato = nodo.getDato();
            } else {
                if (dato.compareTo((T) nodo.getDato()) < 0) {
                    buscar(dato, nodo.getIzq(),nuevaTupla);
                } else {
                    buscar(dato, nodo.getDer(),nuevaTupla);
                }
            }
        }
    }


   /* public int borrarMinimo(){
        return borrarMinimo(raiz,0,null);
    }

    private int borrarMinimo(NodoGenArbol nodo,int minimo,NodoGenArbol ant)
    {
       if(nodo != null) {
           if (nodo.getIzq() != null) {
               ant = nodo;
               int min = nodo.getDato();
               minimo = min;
               int minIzq = borrarMinimo(nodo.getIzq(), min, ant);
               return minIzq;
           } else {
               if (ant.getDer() != null) {
                   ant.setIzq(nodo.getDer());
                   return nodo.getDato();
               } else {
                   ant.setIzq(null);
                   return nodo.getDato();
               }
           }
       }else{
           return -1;
       }
    }
     */

    /* Ejercicio 6 */
    /*Desarrolle un algoritmo que, recibiendo un valor entero k, retorne la cantidad de elementos que son mayores a k.*/
   /* public int cantidadMayores(int k){
        return cantidadMayores(k,raiz);
    }

    private int cantidadMayores(int k,NodoGenArbol nodo) {

        if(nodo!=null){
           if(k< nodo.getDato()){
                int derCant = 1+cantidadMayores(k,nodo.getDer());
                int izqCant = cantidadMayores(k,nodo.getIzq());
                return derCant+izqCant;
           }else{
               return cantidadMayores(k,nodo.getDer());
           }
        }else{
            return 0;
        }
    }
    */




}
