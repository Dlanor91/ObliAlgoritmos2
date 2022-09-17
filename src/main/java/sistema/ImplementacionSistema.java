package sistema;

import interfaz.Consulta;
import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;

public class ImplementacionSistema implements Sistema {

    @Override
    public Retorno inicializarSistema(int maxCentros) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno explorarCentroUrbano(boolean[] correctas, int[] puntajes, int minimo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarJugador(String ci, String nombre,int edad, String escuela, TipoJugador tipo) {
        return Retorno.noImplementada();
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
