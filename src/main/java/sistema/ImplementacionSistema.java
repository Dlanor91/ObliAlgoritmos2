/*
246484 - Emilio Barcelona
276733 - Ronald Lima
278293 - Maira Hill
*/
package sistema;

import dominio.Arbol.ABB;
import dominio.Arbol.NodoGenArbol;
import dominio.Tupla.Tupla;

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

    //Ejercicio 1 - Listo
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
        String resultadoExploracion = "no pasa";
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
                        if (totalPuntos >= minimo) resultadoExploracion = "pasa";
                        return Retorno.ok(totalPuntos, resultadoExploracion);
                    } else {
                        return Retorno.error4("El m??nimo es menor o igual a cero.");
                    }
                } else {
                    return Retorno.error3("Los arrays no tienen la misma cantidad de elementos.");
                }
            } else {
                return Retorno.error2("Los arrays tienen menos de 3 elementos.");
            }
        } else {
            return Retorno.error1("Alguno de los par??metros es null.");
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

                    return Retorno.error3("Ya existe un jugador registrado con esa c??dula.");
                }
            } else {
                return Retorno.error2("La cedula no tiene un formato valido.");
            }
        } else {
            return Retorno.error1("Debe completar todos los parametros.");
        }

    }

    //Ejercicio 4 - Listo
    @Override
    public Retorno filtrarJugadores(Consulta consulta) {
        if (consulta != null) {
            ListaGen<String> listaGen = new ListaGen<>();
            filtrar(arbolJugadores.getRaiz(), listaGen, consulta);
            return Retorno.ok( 0, listaGen.toString());
        } else {
            return Retorno.error1("La consulta esta vac??a.");
        }
    }

    private void filtrar(NodoGenArbol<Jugador> nodo, ListaGen<String> listaJugadores, Consulta consulta) {
        if (nodo != null) {
            filtrar(nodo.getIzq(), listaJugadores, consulta);
            if (recorrerConsulta(consulta.getRaiz(), nodo)) listaJugadores.agregarAlFinal(nodo.getDato().getCedula());
            filtrar(nodo.getDer(), listaJugadores, consulta);
        }
    }

    private boolean recorrerConsulta(Consulta.NodoConsulta nodo, NodoGenArbol<Jugador> nodoJugador) {
        if (nodo != null) {
            return switch (nodo.getTipoNodoConsulta()) {
                case EscuelaIgual -> (nodoJugador.getDato().getEscuela().equals(nodo.getValorString()));
                case NombreIgual -> (nodoJugador.getDato().getNombre().equals(nodo.getValorString()));
                case EdadMayor -> (nodoJugador.getDato().getEdad() > (nodo.getValorInt()));
                case Or ->
                        (recorrerConsulta(nodo.getIzq(), nodoJugador) || recorrerConsulta(nodo.getDer(), nodoJugador));
                case And ->
                        (recorrerConsulta(nodo.getIzq(), nodoJugador) && recorrerConsulta(nodo.getDer(), nodoJugador));
            };
        }
        return false;
    }

    //Ejercicio 5 - Listo
    @Override
    public Retorno buscarJugador(String ci) {
        if (!ci.isEmpty() && Jugador.validarCedula(ci)) {
            Tupla jugadorEncontrado = arbolJugadores.buscar(new Jugador(ci));
            if (jugadorEncontrado.getDato() != null) {
                return Retorno.ok(jugadorEncontrado.getCantidad(), jugadorEncontrado.getDato().toString());
            } else {
                return Retorno.error2("No existe un jugador registrado con esa c??dula.");
            }
        } else {
            return Retorno.error1("La c??dula no tiene formato v??lido.");
        }
    }

    //Ejercicio 7 - Listo
    @Override
    public Retorno listarJugadoresPorCedulaAscendente() {
        ListaGen<Jugador> listaJugadoresAsc = arbolJugadores.listarAscendente();
        if (listaJugadoresAsc.getCantNodos() != 0) {
           return Retorno.ok(listaJugadoresAsc.toString());
        }

        return Retorno.ok("");
    }

    //Ejercicio 6 - Listo
    @Override
    public Retorno listarJugadoresPorCedulaDescendente() {
        ListaGen<Jugador> listaJugadoresDes = arbolJugadores.listarDescendente();
        if (listaJugadoresDes.getCantNodos() != 0) {
            return Retorno.ok( listaJugadoresDes.toString());
        }

        return Retorno.ok( "");
    }

    //Ejercicio 8 - Listo
    @Override
    public Retorno listarJugadoresPorTipo(TipoJugador unTipo) {
        if (unTipo != null) {
            if (unTipo.getIndice() == 0) { //Avanzado
                return Retorno.ok(listaJugadoresAvanzado.toString());
            } else if (unTipo.getIndice() == 1) { //Medio
                return Retorno.ok(listaJugadoresMedio.toString());
            } else if (unTipo.getIndice() == 2) { //Inicial
                return Retorno.ok(listaJugadoresInicial.toString());
            } else { //Monitor
                return Retorno.ok(listaJugadoresMonitor.toString());
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
                    return Retorno.error3("Ya existe un centro con ese c??digo.");
                }
            } else {
                return Retorno.error2("Alg??n dato es vac??o o nulo.");
            }
        } else {
            return Retorno.error1("En el sistema ya hay registrados maxCentros.");
        }
    }

    //Ejercicio 10 - Listo
    @Override
    public Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        if (Camino.validar(costo, tiempo, kilometros)) {
            if (codigoCentroOrigen != null && codigoCentroDestino != null && !codigoCentroOrigen.isEmpty() && !codigoCentroDestino.isEmpty() && Camino.validar(estadoDelCamino)) {
                CentroUrbano nuevoCUOrigen = grafoCentrosUrbanos.obtenerVertice(codigoCentroOrigen);
                if (nuevoCUOrigen != null) {
                    CentroUrbano nuevoCUDestino = grafoCentrosUrbanos.obtenerVertice(codigoCentroDestino);
                    if (nuevoCUDestino != null) {
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
                return Retorno.error2("Alguno de los par??metros String o enum es vac??o o null.");
            }
        } else {
            return Retorno.error1("Alguno de los par??metros double es menor o igual a 0.");
        }
    }

    //Ejercicio 11 - Listo
    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        if (Camino.validar(costo, tiempo, kilometros)) {
            if (codigoCentroOrigen != null && codigoCentroDestino != null && !codigoCentroOrigen.isEmpty() && !codigoCentroDestino.isEmpty() && Camino.validar(estadoDelCamino)) {
                CentroUrbano nuevoCUOrigen = grafoCentrosUrbanos.obtenerVertice(codigoCentroOrigen);
                if (nuevoCUOrigen != null) {
                    CentroUrbano nuevoCUDestino = grafoCentrosUrbanos.obtenerVertice(codigoCentroDestino);
                    if (nuevoCUDestino != null) {
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
                return Retorno.error2("Alguno de los par??metros String o enum es vac??o o null.");
            }
        } else {
            return Retorno.error1("Alguno de los par??metros double es menor o igual a 0.");
        }
    }

    //Ejercicio 12 - Listo
    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        if (cantidad >= 0) {
            CentroUrbano buscarCU = grafoCentrosUrbanos.obtenerVertice(codigoCentroOrigen);
            if (buscarCU != null) {
                ABB<CentroUrbano> nuevoArbol = grafoCentrosUrbanos.bfsSinRepetir(buscarCU, cantidad);
                return Retorno.ok(nuevoArbol.listarAscendente().toString());
            } else {
                return Retorno.error2("El centro no est?? registrado en el sistema.");
            }
        } else {
            return Retorno.error1("La cantidad es menor que cero.");
        }
    }

    //Ejercicio 13 - Listo
    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino) {
        if (codigoCentroOrigen != null && !codigoCentroOrigen.isEmpty() && codigoCentroDestino != null && !codigoCentroDestino.isEmpty()) {
            CentroUrbano codOrigen = grafoCentrosUrbanos.obtenerVertice(codigoCentroOrigen);
            CentroUrbano codDestino = grafoCentrosUrbanos.obtenerVertice(codigoCentroDestino);
            if (codOrigen != null) {
                if (codDestino != null) {
                    Tupla costoKMS = grafoCentrosUrbanos.dijkstraImplementado(codOrigen, codDestino, "kms");
                    if (costoKMS.getCantidad() != Integer.MAX_VALUE && costoKMS.getCantidad() > 0) {
                        return Retorno.ok(costoKMS.getCantidad(), costoKMS.getDato().toString());
                    } else {
                        return Retorno.error2("No hay camino entre el origen y el destino.");
                    }
                } else {
                    return Retorno.error4("No existe el Centro Urbano de Destino.");
                }
            } else {
                return Retorno.error3("No existe el Centro Urbano de Origen.");
            }
        } else {
            return Retorno.error1("Alguno de los c??digos es vac??o o null.");
        }
    }

    //Ejercicio 14 - Listo
    @Override
    public Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {
        if (codigoCentroOrigen != null && !codigoCentroOrigen.isEmpty() && codigoCentroDestino != null && !codigoCentroDestino.isEmpty()) {
            CentroUrbano codOrigen = grafoCentrosUrbanos.obtenerVertice(codigoCentroOrigen);
            CentroUrbano codDestino = grafoCentrosUrbanos.obtenerVertice(codigoCentroDestino);
            if (codOrigen != null) {
                if (codDestino != null) {
                    Tupla costoMonedas = grafoCentrosUrbanos.dijkstraImplementado(codOrigen, codDestino, "costos");
                    if (costoMonedas.getCantidad() != Integer.MAX_VALUE && costoMonedas.getCantidad() > 0) {
                        return Retorno.ok(costoMonedas.getCantidad(), costoMonedas.getDato().toString());
                    } else {
                        return Retorno.error2("No hay camino entre el origen y el destino.");
                    }
                } else {
                    return Retorno.error4("No existe el Centro Urbano de Destino.");
                }
            } else {
                return Retorno.error3("No existe el Centro Urbano de Origen.");
            }
        } else {
            return Retorno.error1("Alguno de los c??digos es vac??o o null.");
        }
    }
}
