package sistema;

import dominio.Arbol.ABB;
import dominio.Arbol.Tupla;

import dominio.CentroUrbano;
import dominio.Grafo.Camino;
import dominio.Grafo.Grafo;
import dominio.Jugador;
import dominio.Lista.ListaGen;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    private ABB<Jugador> arbolJugadores;
    private Grafo grafoCentrosUrbanos;
    private ListaGen<Jugador> listaJugadoresAvanzado;
    private ListaGen<Jugador> listaJugadoresInicial;
    private ListaGen<Jugador> listaJugadoresMedio;
    private ListaGen<Jugador> listaJugadoresMonitor;

    //Ejercicio 1 - A medias
    @Override
    public Retorno inicializarSistema(int maxCentros) {
        if (maxCentros > 5) {
            //Todo lo que se inicialice seria aqui
            arbolJugadores = new ABB<>();
            grafoCentrosUrbanos = new Grafo(maxCentros, true);
            listaJugadoresAvanzado = new ListaGen<>();
            listaJugadoresInicial = new ListaGen<>();
            listaJugadoresMedio = new ListaGen<>();
            listaJugadoresMonitor = new ListaGen<>();
            return Retorno.ok();
        }

        return Retorno.error1("maxCentros es menor o igual a 5.");
    }

    //Ejercicio 2 - Listo
    @Override
    public Retorno explorarCentroUrbano(boolean[] correctas, int[] puntajes, int minimo) {
        int totalPuntos = 0;
        int respuestasCorrectas = 0;
        String resultadoExploracion = "No pasa";
        if (correctas != null && puntajes != null) {
            if (correctas.length >= 3 && puntajes.length >= 3) {
                if (correctas.length == puntajes.length) {
                    if (minimo > 0) {
                        for (int i = 0; i < correctas.length; i++) {
                            if (correctas[i]) {
                                respuestasCorrectas++;
                                totalPuntos += puntajes[i];
                                if (respuestasCorrectas == 3) {
                                    totalPuntos += 3;
                                }
                                if (respuestasCorrectas == 4) {
                                    totalPuntos += 5;
                                }
                                if (respuestasCorrectas >= 5) {
                                    totalPuntos += 8;
                                }
                            } else {
                                respuestasCorrectas = 0;
                            }
                        }
                        if (totalPuntos >= minimo) resultadoExploracion = "Pasa";
                        Retorno retorno = new Retorno(Retorno.Resultado.OK, totalPuntos, resultadoExploracion);
                        return retorno;
                    } else {
                        return Retorno.error4("El mínimo es menor o igual a cero.");
                    }
                } else {
                    return Retorno.error3("Los arrays no tienen la misma cantidad de elementos.");
                }
            } else {
                return Retorno.error2("Los arrays tienen menos de 3 elementos.");
            }
        } else {
            return Retorno.error1("Alguno de los parámetros es null.");
        }
    }

    //Ejercicio 3 - Listo
    @Override
    public Retorno registrarJugador(String ci, String nombre, int edad, String escuela, TipoJugador tipo) {
        if (Jugador.validar(ci, nombre, edad, escuela, tipo)) {
            if (Jugador.validarCedula(ci)) {
                Jugador nuevoJugador = new Jugador(ci, nombre, edad, escuela, tipo);
                if (!arbolJugadores.pertenece(nuevoJugador)) {
                    arbolJugadores.insertar(nuevoJugador);
                    if (nuevoJugador.getTipo().getIndice() == 0) { //Avanzado
                        listaJugadoresAvanzado.agregarAlFinal(nuevoJugador);
                    } else if (nuevoJugador.getTipo().getIndice() == 1) { //Medio
                        listaJugadoresMedio.agregarAlFinal(nuevoJugador);
                    } else if (nuevoJugador.getTipo().getIndice() == 2) { //Inicial
                        listaJugadoresInicial.agregarAlFinal(nuevoJugador);
                    } else { //Monitor
                        listaJugadoresMonitor.agregarAlFinal(nuevoJugador);
                    }
                    return Retorno.ok("El jugador fue registrado exitosamente.");
                } else {

                    return Retorno.error3("Ya existe un jugador registrado con esa cédula.");
                }
            } else {
                return Retorno.error2("La cedula no tiene un formato valido.");
            }
        } else {
            return Retorno.error1("Debe completar todos los parametros.");
        }

    }

    //Ejercicio 4 - No implementado
    @Override
    public Retorno filtrarJugadores(Consulta consulta) {
        return Retorno.noImplementada();
    }

    //Ejercicio 5 - Listo
    @Override
    public Retorno buscarJugador(String ci) {
        if (!ci.isEmpty() && Jugador.validarCedula(ci)) {
            Tupla jugadorEncontrado = arbolJugadores.buscar(new Jugador(ci));
            if (jugadorEncontrado.getDato() != null) {
                Retorno retorno = new Retorno(Retorno.Resultado.OK, jugadorEncontrado.getCantidad(), jugadorEncontrado.getDato().toString());
                return retorno;
            } else {
                return Retorno.error2("No existe un jugador registrado con esa cédula.");
            }
        } else {
            return Retorno.error1("La cédula no tiene formato válido.");
        }
    }

    //Ejercicio 7 - Listo
    @Override
    public Retorno listarJugadoresPorCedulaAscendente() {
        Retorno retorno = new Retorno(Retorno.Resultado.OK, 0, "");
        ListaGen<Jugador> listaJugadoresAsc = arbolJugadores.listarAscendente();
        if (listaJugadoresAsc != null) {
            retorno = new Retorno(Retorno.Resultado.OK, 0, listaJugadoresAsc.toString());
        }

        return retorno;
    }

    //Ejercicio 6 - Listo
    @Override
    public Retorno listarJugadoresPorCedulaDescendente() {

        Retorno retorno = new Retorno(Retorno.Resultado.OK, 0, "");
        ListaGen<Jugador> listaJugadoresDes = arbolJugadores.listarDescendente();
        if (listaJugadoresDes != null) {
            retorno = new Retorno(Retorno.Resultado.OK, 0, listaJugadoresDes.toString());
        }

        return retorno;
    }

    //Ejercicio 8 - Listo
    @Override
    public Retorno listarJugadoresPorTipo(TipoJugador unTipo) {

        if (unTipo != null) {
            if (unTipo.getIndice() == 0) { //Avanzado
                return new Retorno(Retorno.Resultado.OK, 0, listaJugadoresAvanzado.toString());
            } else if (unTipo.getIndice() == 1) { //Medio
                return new Retorno(Retorno.Resultado.OK, 0, listaJugadoresMedio.toString());
            } else if (unTipo.getIndice() == 2) { //Inicial
                return new Retorno(Retorno.Resultado.OK, 0, listaJugadoresInicial.toString());
            } else { //Monitor
                return new Retorno(Retorno.Resultado.OK, 0, listaJugadoresMonitor.toString());
            }
        } else {
            return Retorno.error1("Tipo es null");
        }
    }

    //Ejercicio 9 - Listo
    @Override
    public Retorno registrarCentroUrbano(String codigo, String nombre) {
        if (!grafoCentrosUrbanos.esLleno()) {
            if (CentroUrbano.validar(codigo, nombre)) {
                CentroUrbano cuNew = new CentroUrbano(codigo, nombre);
                if (!grafoCentrosUrbanos.existeVertice(cuNew)) {
                    grafoCentrosUrbanos.agregarVertice(cuNew);
                    return Retorno.ok();
                } else {
                    return Retorno.error3("Ya existe un centro con ese código.");
                }
            } else {
                return Retorno.error2("Algún dato es vacío o nulo.");
            }
        } else {
            return Retorno.error1("En el sistema ya hay registrados maxCentros.");
        }
    }

    //Ejercicio 10 - Listo
    @Override
    public Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        if (Camino.validar(costo, tiempo, kilometros)) {
            if (!codigoCentroOrigen.isEmpty() && !codigoCentroDestino.isEmpty() && Camino.validar(estadoDelCamino)) {
                CentroUrbano nuevoCUOrigen = new CentroUrbano(codigoCentroOrigen);
                if (grafoCentrosUrbanos.existeVertice(nuevoCUOrigen)) {
                    CentroUrbano nuevoCUDestino = new CentroUrbano(codigoCentroDestino);
                    if (grafoCentrosUrbanos.existeVertice(nuevoCUDestino)) {
                        if (!grafoCentrosUrbanos.existeArista(nuevoCUOrigen, nuevoCUDestino)) {
                            grafoCentrosUrbanos.agregarArista(nuevoCUOrigen, nuevoCUDestino, costo, tiempo, kilometros, estadoDelCamino);
                            return Retorno.ok();
                        } else {
                            return Retorno.error5("Ya existe un camino entre el origen y el destino.");
                        }
                    } else {
                        return Retorno.error4("No existe el centro de destino.");
                    }
                } else {
                    return Retorno.error3("No existe el centro de origen.");
                }
            } else {
                return Retorno.error2("Alguno de los parámetros String o enum es vacío o null.");
            }
        } else {
            return Retorno.error1("Alguno de los parámetros double es menor o igual a 0.");
        }
    }

    //Ejercicio 11
    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        if (Camino.validar(costo, tiempo, kilometros)) {
            if (!codigoCentroOrigen.isEmpty() && !codigoCentroDestino.isEmpty() && Camino.validar(estadoDelCamino)) {
                CentroUrbano nuevoCUOrigen = new CentroUrbano(codigoCentroOrigen);
                if (grafoCentrosUrbanos.existeVertice(nuevoCUOrigen)) {
                    CentroUrbano nuevoCUDestino = new CentroUrbano(codigoCentroDestino);
                    if (grafoCentrosUrbanos.existeVertice(nuevoCUDestino)) {
                        if (grafoCentrosUrbanos.existeArista(nuevoCUOrigen, nuevoCUDestino)) {
                            grafoCentrosUrbanos.actualizarArista(nuevoCUOrigen, nuevoCUDestino, costo, tiempo, kilometros, estadoDelCamino);
                            return Retorno.ok();
                        } else {
                            return Retorno.error5("No existe un camino entre el origen y el destino.");
                        }
                    } else {
                        return Retorno.error4("No existe el centro de destino.");
                    }
                } else {
                    return Retorno.error3("No existe el centro de origen.");
                }
            } else {
                return Retorno.error2("Alguno de los parámetros String o enum es vacío o null.");
            }
        } else {
            return Retorno.error1("Alguno de los parámetros double es menor o igual a 0.");
        }
    }

    //Ejercicio 12 - Listo
    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        if (cantidad >= 0) {
            CentroUrbano buscarCU = grafoCentrosUrbanos.obtenerVertice(codigoCentroOrigen);
            if (buscarCU != null) {
                ABB<CentroUrbano> nuevoArbol = grafoCentrosUrbanos.bfsSinRepetir(buscarCU, cantidad);
                Retorno retorno = new Retorno(Retorno.Resultado.OK, 0, nuevoArbol.listarAscendente().toString());
                return retorno;
            } else {
                return Retorno.error2("El centro no está registrado en el sistema.");
            }
        } else {
            return Retorno.error1("La cantidad es menor que cero.");
        }
    }

    //Ejercicio 13 - No implementado
    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }

    //Ejercicio 14 - No implementado
    @Override
    public Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }
}
