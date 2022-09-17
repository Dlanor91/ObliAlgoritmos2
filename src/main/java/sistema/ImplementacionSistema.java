package sistema;

import dominio.ABB;
import dominio.Jugador;
import dominio.NodoGenArbol;
import interfaz.Consulta;
import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;

public class ImplementacionSistema implements Sistema {
    private ABB<Jugador> arbolJugadores;
    //no terminador
    @Override
    public Retorno inicializarSistema(int maxCentros) {
        if(maxCentros>5){
            //Todo lo que se inicialice seria aqui
            arbolJugadores = new ABB<>();
            return Retorno.ok();
        }

        return Retorno.error1("maxCentros es menor o igual a 5.");
    }

    @Override
    public Retorno explorarCentroUrbano(boolean[] correctas, int[] puntajes, int minimo) {
        return Retorno.noImplementada();
    }

    //no terminado
    @Override
    public Retorno registrarJugador(String ci, String nombre,int edad, String escuela, TipoJugador tipo) {
        if(!ci.isEmpty() && !nombre.isEmpty() && edad != 0 && !escuela.isEmpty() && tipo != null){
            if(Jugador.validarCedula(ci)){
                Jugador nuevoJugador = new Jugador(ci,nombre,edad,escuela,tipo);
                //verificar jugadores repetidos
                arbolJugadores.insertar(nuevoJugador);
                return Retorno.ok();
            }else{
                return Retorno.error2("La cedula no tiene un formato valido.");
            }
        }else{
            return Retorno.error1("Debe completar todos los parametros.");
        }

    }

    @Override
    public Retorno filtrarJugadores(Consulta consulta) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarJugador(String ci) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCedulaAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCedulaDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorTipo(TipoJugador unTipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCentroUrbano(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }
}
