package dominio.Grafo;

import dominio.Arbol.ABB;
import dominio.Arbol.Tupla;
import dominio.CentroUrbano;
import dominio.Cola.Cola;
import dominio.Cola.ColaImp;
import dominio.Lista.Lista;
import dominio.Lista.ListaGen;
import interfaz.EstadoCamino;

public class Grafo {

    private int tope;
    private int cantidad;
    private CentroUrbano[] vertices;
    private Camino[][] matAdyacentes; //matriz de adyacentes

    public Grafo(int unTope, boolean esDirigido) {
        this.tope = unTope;
        this.cantidad = 0;
        this.vertices = new CentroUrbano[unTope];
        this.matAdyacentes = new Camino[unTope][unTope];

        if (esDirigido) {
            for (int i = 0; i < this.tope; i++) {
                for (int j = 0; j < this.tope; j++) {
                    this.matAdyacentes[i][j] = new Camino(); //no se hace mas en otro lugar
                }
            }
        } else {
            for (int i = 0; i < this.tope; i++) {
                for (int j = i; j < this.tope; j++) {
                    this.matAdyacentes[i][j] = new Camino();
                    this.matAdyacentes[j][i] = this.matAdyacentes[i][j];

                }
            }
        }
    }

    public boolean esLleno() {
        return this.cantidad == this.tope;
    }

    public boolean esVacio() {
        return this.cantidad == 0;
    }

    // PRE: !esLleno()
    private int obtenerPosLibre() {
        for (int i = 0; i < this.tope; i++) {
            if (this.vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(CentroUrbano vert) {
        for (int i = 0; i < this.tope; i++) {
            if (this.vertices[i] != null) {
                if (this.vertices[i].equals(vert)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // PRE: !esLleno && !existeVertice
    public void agregarVertice(CentroUrbano vert) {
        int pos = obtenerPosLibre();
        this.vertices[pos] = vert;
        this.cantidad++;
    }

    public boolean existeVertice(CentroUrbano vert) {
        return obtenerPos(vert) != -1;
    }

    public CentroUrbano obtenerVertice(String codigo) {
        CentroUrbano nuevo = null;
        for (int i = 0; i < this.tope; i++) {
            if (this.vertices[i] != null) {
                if (this.vertices[i].getCodigo().equals(codigo)) {
                    return nuevo = this.vertices[i];
                }
            }
        }
        return nuevo;
    }

    // existeVertice(origen) && existeVertice(destino) && !existeArista
    public void agregarArista(CentroUrbano origen, CentroUrbano destino, double costo, double tiempo, double kms, EstadoCamino estadoDelCamino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdyacentes[posOrigen][posDestino].setExiste(true);
        matAdyacentes[posOrigen][posDestino].setCosto(costo);
        matAdyacentes[posOrigen][posDestino].setTiempo(tiempo);
        matAdyacentes[posOrigen][posDestino].setKms(kms);
        matAdyacentes[posOrigen][posDestino].setEstadoDelCamino(estadoDelCamino);
    }

    // existeVertice(origen) && existeVertice(destino)
    public boolean existeArista(CentroUrbano origen, CentroUrbano destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        return matAdyacentes[posOrigen][posDestino].isExiste();
    }

    public boolean actualizarArista(CentroUrbano origen, CentroUrbano destino, double costo, double tiempo, double kms, EstadoCamino estadoDelCamino)
    {
        if(existeArista(origen, destino)){
            int posOrigen = obtenerPos(origen);
            int posDestino = obtenerPos(destino);
            matAdyacentes[posOrigen][posDestino].setCosto(costo);
            matAdyacentes[posOrigen][posDestino].setTiempo(tiempo);
            matAdyacentes[posOrigen][posDestino].setKms(kms);
            matAdyacentes[posOrigen][posDestino].setEstadoDelCamino(estadoDelCamino);
            return true;
        }
        return false;
    }

    public Lista<CentroUrbano> verticesAdyacentes(CentroUrbano vert) {
        Lista<CentroUrbano> retorno = new ListaGen<>();
        int pos = obtenerPos(vert);
        for (int i = 0; i < tope; i++) {
            if (this.matAdyacentes[pos][i].isExiste()) {
                retorno.agregarAlFinal(vertices[i]);
            }
        }
        return retorno;
    }

    // Pre: existeVertice(vert)
    public Lista<CentroUrbano> verticesIncidentes(CentroUrbano vert) {
        Lista<CentroUrbano> retorno = new ListaGen<>();
        int pos = obtenerPos(vert);
        for (int i = 0; i < tope; i++) {
            if (this.matAdyacentes[i][pos].isExiste()) {
                retorno.agregarAlFinal(vertices[i]);
            }
        }
        return retorno;
    }

    //Pre: existeVertice(vert)
    public void dfs(CentroUrbano vert) {
        boolean[] visitados = new boolean[tope]; // Inicia todo en false
        Lista<CentroUrbano> retorno = new ListaGen<>();
        int pos = obtenerPos(vert);
        dfsRec(pos, visitados);

    }

    private void dfsRec(int pos, boolean[] visitados) {
        System.out.println(vertices[pos]);
        visitados[pos] = true;
        for (int i = 0; i < tope; i++) {
            if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                dfsRec(i, visitados);
            }
        }

    }

    public void dfsSaltos(CentroUrbano vert,int cantidad) {
        boolean[] visitados = new boolean[tope]; // Inicia todo en false
        int pos = obtenerPos(vert);
        dfsSaltos(pos, visitados,cantidad);

    }
    private void dfsSaltos(int pos, boolean[] visitados,int cantidad) {
        System.out.print(vertices[pos]+"|");
        visitados[pos] = true;
        for (int i = 0; i <= cantidad; i++) {
            if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                dfsSaltos(i, visitados,cantidad);
            }
        }

    }


    //visitando cuando desencolo puedo encolar elementos repetidos
    //Pre: existeVertice(vert)
    public void bfs(CentroUrbano vert) {
        boolean[] visitados = new boolean[tope];
        int inicio = obtenerPos(vert);
        Cola<Integer> cola = new ColaImp<>();
        cola.encolar(inicio);
        while (!cola.esVacia()) {
            //desencolar
            //si no esta visitado
            int pos = cola.desencolar();
            if (!visitados[pos]) {
                visitados[pos] = true;
                System.out.println(vertices[pos]);
                for (int i = 0; i < tope; i++) {
                    if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                        cola.encolar(i);
                    }
                }
            }//si no esta visitado

        }
    }

    public boolean bfsCamino(CentroUrbano vert,CentroUrbano buscado) {
        boolean[] visitados = new boolean[tope];
        int inicio = obtenerPos(vert);
        Cola<Integer> cola = new ColaImp<>();
        cola.encolar(inicio);
        visitados[inicio] = true;
        while (!cola.esVacia()) {
            int pos = cola.desencolar();
            if(vertices[pos].compareTo(buscado) == 0) return true;
            for (int i = 0; i < tope; i++) {
                if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                    cola.encolar(i);
                    visitados[i] = true;
                }
            }
        }
        return false;
    }

    //Marcando como visitado al encolar no encolo elementos repetidos
    public ABB<CentroUrbano> bfsSinRepetir(CentroUrbano vert,int nivel) {
        boolean[] visitados = new boolean[tope];
        int inicio = obtenerPos(vert);
        Cola<Integer> cola = new ColaImp<>();
        ColaImp<Tupla> colaNivel = new ColaImp<>();
        cola.encolar(inicio);
        visitados[inicio] = true;
        ABB<CentroUrbano> nuevoArbolCentroUrbano = new ABB<>();
        int cant =0;
        Tupla control = new Tupla<>(inicio,cant);
        colaNivel.encolar(control);
        while (!colaNivel.esVacia()&& colaNivel.getInicio().getDato().getCantidad()<=nivel) {
            int pos = cola.desencolar();
            int posCola = colaNivel.desencolar().getCantidad();
            //Tupla t = colaNivel.desencolar();
            nuevoArbolCentroUrbano.insertar(vertices[pos]);
            //System.out.println(vertices[pos]);
            for (int i = 0; i < tope; i++) {
                if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                    cola.encolar(i);
                    visitados[i] = true;
                    control = new Tupla<>(i,cant);
                    control.setCantidad(posCola + 1);
                    //control.setCantidad(t.getCantidad() + 1);
                    colaNivel.encolar(control);
                }
            }
        }
        return nuevoArbolCentroUrbano;
    }

    public double dijkstraKMS(CentroUrbano vOrigen, CentroUrbano vDestino){
        int posOrigen = obtenerPos(vOrigen);
        int posDestino = obtenerPos(vDestino);

        boolean[] visitados = new boolean[this.tope];
        double[] costos = new double[this.tope];
        CentroUrbano[] anterior = new CentroUrbano[this.tope];
        for(int i = 0; i<tope; i++){
            costos[i] = Integer.MAX_VALUE;
            anterior[i] = new CentroUrbano("","");
        }
        costos[posOrigen] = 0; // Marcar el origen con distancia cero

        for(int i = 0; i<tope; i++){ //Loop (cantidad de vertices)
            //Obtener el vertice no visitado de menor costo(si hay varios cualquiera)
            int pos= obtenerSiguienteVerticeNoVisitadoDeMenorDistancia(costos,visitados);
            if(pos != -1){
                //2 Visitarlo
                visitados[pos] = true;
                //3 Evaluar si tengo que actualizar el costo de los adyacentes NO VISITADOS
                for(int j =0; j<tope;j++){
                    if(matAdyacentes[pos][j].isExiste() && !visitados[j] && matAdyacentes[pos][j].getEstadoDelCamino() != EstadoCamino.MALO){
                        double distanciaNueva = costos[pos] + matAdyacentes[pos][j].getKms();
                        if(distanciaNueva < costos[j]) {
                            costos[j] = distanciaNueva;
                            anterior[j] = vertices[pos];
                        }
                    }
                }
            }
        }

        return costos[posDestino];
    }

    public double dijkstraCosto(CentroUrbano vOrigen, CentroUrbano vDestino){
        int posOrigen = obtenerPos(vOrigen);
        int posDestino = obtenerPos(vDestino);

        boolean[] visitados = new boolean[this.tope];
        double[] costos = new double[this.tope];
        CentroUrbano[] anterior = new CentroUrbano[this.tope];
        for(int i = 0; i<tope; i++){
            costos[i] = Integer.MAX_VALUE;
            anterior[i] = new CentroUrbano("","");
        }
        costos[posOrigen] = 0; // Marcar el origen con distancia cero

        for(int i = 0; i<tope; i++){ //Loop (cantidad de vertices)
            //Obtener el vertice no visitado de menor costo(si hay varios cualquiera)
            int pos= obtenerSiguienteVerticeNoVisitadoDeMenorDistancia(costos,visitados);
            if(pos != -1){
                //2 Visitarlo
                visitados[pos] = true;
                //3 Evaluar si tengo que actualizar el costo de los adyacentes NO VISITADOS
                for(int j =0; j<tope;j++){
                    if(matAdyacentes[pos][j].isExiste() && !visitados[j] && matAdyacentes[pos][j].getEstadoDelCamino() != EstadoCamino.MALO){
                        double distanciaNueva = costos[pos] + matAdyacentes[pos][j].getCosto();
                        if(distanciaNueva < costos[j]) {
                            costos[j] = distanciaNueva;
                            anterior[j] = vertices[pos];
                        }
                    }
                }
            }
        }

        return costos[posDestino];
    }

    private int obtenerSiguienteVerticeNoVisitadoDeMenorDistancia(double [] costos, boolean[] visitados){
        int posMin = -1;
        double min = Integer.MAX_VALUE;
        for(int i = 0; i<tope; i++){
            if(!visitados[i] && costos[i]< min){
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }
}
