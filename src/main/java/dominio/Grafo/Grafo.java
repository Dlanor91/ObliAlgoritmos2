package dominio.Grafo;

import dominio.Arbol.ABB;
import dominio.Arbol.Tupla;
import dominio.CentroUrbano;
import dominio.Cola.Cola;
import dominio.Cola.ColaImp;
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

    public void agregarArista(CentroUrbano origen, CentroUrbano destino, double costo, double tiempo, double kms, EstadoCamino estadoDelCamino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdyacentes[posOrigen][posDestino].setExiste(true);
        matAdyacentes[posOrigen][posDestino].setCosto(costo);
        matAdyacentes[posOrigen][posDestino].setTiempo(tiempo);
        matAdyacentes[posOrigen][posDestino].setKms(kms);
        matAdyacentes[posOrigen][posDestino].setEstadoDelCamino(estadoDelCamino);
    }

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
            nuevoArbolCentroUrbano.insertar(vertices[pos]);
            for (int i = 0; i < tope; i++) {
                if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                    cola.encolar(i);
                    visitados[i] = true;
                    control = new Tupla<>(i,cant);
                    control.setCantidad(posCola + 1);
                    colaNivel.encolar(control);
                }
            }
        }
        return nuevoArbolCentroUrbano;
    }

    public double dijkstraImplementado(CentroUrbano vOrigen, CentroUrbano vDestino,String seleccion){
        int posOrigen = obtenerPos(vOrigen);
        int posDestino = obtenerPos(vDestino);

        boolean[] visitados = new boolean[this.tope];
        double[] costos = new double[this.tope];
        double distanciaNueva = 0;
        CentroUrbano[] anterior = new CentroUrbano[this.tope];
        for(int i = 0; i<tope; i++){
            costos[i] = Integer.MAX_VALUE;
            anterior[i] = new CentroUrbano("","");
        }
        costos[posOrigen] = 0;
        for(int i = 0; i<tope; i++){
            int pos= obtenerSiguienteVerticeNoVisitadoDeMenorDistancia(costos,visitados);
            if(pos != -1){
                visitados[pos] = true;
                for(int j =0; j<tope;j++){
                    if(matAdyacentes[pos][j].isExiste() && !visitados[j] && matAdyacentes[pos][j].getEstadoDelCamino() != EstadoCamino.MALO){
                        if(seleccion.equals("kms")){
                            distanciaNueva = costos[pos] + matAdyacentes[pos][j].getKms();
                        }else if(seleccion.equals("costos")){
                            distanciaNueva= costos[pos] + matAdyacentes[pos][j].getCosto();
                        }
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
