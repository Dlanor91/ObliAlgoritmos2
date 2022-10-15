import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

/**
 * Reemplazar en un futuro por los test provistos por la catedra
 */
public class TestSistemaAFuturo {

    private Sistema sistema = new ImplementacionSistema();

    @Test
    public void testInit() {

        Assertions.assertEquals(Retorno.ok(), sistema.inicializarSistema(7));
        boolean [] lista = {true,true,true,false,true,true,true,true,true,true};
        boolean [] lista2 = {true,true};
        boolean [] lista3 = {true,true,true,false,true,true};
        int[] punt ={1,2,3,6,3,4,4,2,3,3};
        int min= 15;

        //pruebas nuestras
        Assertions.assertEquals(Retorno.error1("Error1").getResultado(), sistema.inicializarSistema(5).getResultado());
        Assertions.assertEquals(Retorno.error1("Error1").getResultado(), sistema.registrarJugador("","",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("6.123.456-1","PedroRaiz",12,"Escuela1", TipoJugador.MONITOR).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("6.123.456.1","Pedro",12,"Escuela1", TipoJugador.MONITOR).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("123.456-1","IzqPedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("123.456.1","Pedro",12,"Escuela1", TipoJugador.AVANZADO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("a34.456-1","Pedro",12,"Escuela1", TipoJugador.INICIAL).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("11.234.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("0.234.456-1","Pedro",12,"Escuela1", TipoJugador.INICIAL).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("034.456-1","Pedro",12,"Escuela1", TipoJugador.MONITOR).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("8.634.456-1","DerPedro",12,"Escuela1", TipoJugador.AVANZADO).getResultado());
        System.out.println(sistema.registrarJugador("8.634.456-6","DerPedro",12,"Escuela1", TipoJugador.MEDIO).getValorString());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("4.634.456-1","DerPedro",12,"Escuela1", TipoJugador.INICIAL).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("9.634.456-1","DerPedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error3("Error3").getResultado(), sistema.registrarJugador("8.634.456-1","DerPedroRepetido",12,"Escuela1", TipoJugador.MEDIO).getResultado());

        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.buscarJugador("8.634.456-1").getResultado());
        System.out.println(sistema.buscarJugador("8.634.456-1").getValorString());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.buscarJugador("9.634.456-1").getResultado());
        Assertions.assertEquals(Retorno.error2("No esta").getResultado(), sistema.buscarJugador("8.634.456-2").getResultado());
        System.out.println("-----");
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.listarJugadoresPorCedulaAscendente().getResultado());
        System.out.println(sistema.listarJugadoresPorCedulaAscendente().getValorString());
        System.out.println("-----");
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.listarJugadoresPorCedulaDescendente().getResultado());
        System.out.println(sistema.listarJugadoresPorCedulaDescendente().getValorString());
        System.out.println("-----");
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa","C0").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa1","C1").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa2","C2").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa3","C3").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa4","C4").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa5","C5").getResultado());
        Assertions.assertEquals(Retorno.error2("Vacio").getResultado(), sistema.registrarCentroUrbano("","C1").getResultado());
        Assertions.assertEquals(Retorno.error3("No esta").getResultado(), sistema.registrarCentroUrbano("aa","C1").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa6","C6").getResultado());
        Assertions.assertEquals(Retorno.error1("No esta").getResultado(), sistema.registrarCentroUrbano("aa","C0").getResultado());


        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCamino("aa","aa1",12,23,2, EstadoCamino.BUENO).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCamino("aa2","aa4",12,23,2, EstadoCamino.EXCELENTE).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCamino("aa1","aa2",12,23,2, EstadoCamino.EXCELENTE).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCamino("aa5","aa1",12,23,2, EstadoCamino.EXCELENTE).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCamino("aa5","aa3",12,23,2, EstadoCamino.EXCELENTE).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCamino("aa3","aa6",12,23,2, EstadoCamino.EXCELENTE).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCamino("aa2","aa6",12,23,2, EstadoCamino.EXCELENTE).getResultado());
        Assertions.assertEquals(Retorno.error5("No esta").getResultado(), sistema.registrarCamino("aa2","aa4",12,23,2, EstadoCamino.EXCELENTE).getResultado());
        Assertions.assertEquals(Retorno.error1("No esta").getResultado(), sistema.registrarCamino("aa2","aa4",0,23,2, EstadoCamino.BUENO).getResultado());
        Assertions.assertEquals(Retorno.error2("No esta").getResultado(), sistema.registrarCamino("aa2","aa4",12,23,2, null).getResultado());
        Assertions.assertEquals(Retorno.error3("No esta").getResultado(), sistema.registrarCamino("aa22","aa4",12,23,2, EstadoCamino.BUENO).getResultado());
        Assertions.assertEquals(Retorno.error4("No esta").getResultado(), sistema.registrarCamino("aa2","aa44",12,23,2, EstadoCamino.BUENO).getResultado());

        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.actualizarCamino("aa","aa1",12,23,7, EstadoCamino.MALO).getResultado());
        Assertions.assertEquals(Retorno.error5("No esta").getResultado(), sistema.actualizarCamino("aa1","aa4",12,23,2, EstadoCamino.EXCELENTE).getResultado());

        System.out.println("Ejercicio 12");
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.listadoCentrosCantDeSaltos("aa5",0).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.listadoCentrosCantDeSaltos("aa5",1).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.listadoCentrosCantDeSaltos("aa5",2).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.listadoCentrosCantDeSaltos("aa5",3).getResultado());
        System.out.println(sistema.listadoCentrosCantDeSaltos("aa5",1).getValorString());
        System.out.println(sistema.listadoCentrosCantDeSaltos("aa5",3).getValorString());
        Assertions.assertEquals(Retorno.error1("No esta").getResultado(), sistema.listadoCentrosCantDeSaltos("aa1",-2).getResultado());
        Assertions.assertEquals(Retorno.error2("No esta").getResultado(),sistema.listadoCentrosCantDeSaltos("aa12",0).getResultado());

        System.out.println("Ejercicio 8");
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.listarJugadoresPorTipo(TipoJugador.MEDIO).getResultado());
        System.out.println(sistema.listarJugadoresPorTipo(TipoJugador.MEDIO).getValorString());
        Assertions.assertEquals(Retorno.error1("No esta").getResultado(), sistema.listarJugadoresPorTipo(null).getResultado());
        System.out.println(sistema.listarJugadoresPorTipo(null).getValorString());
        System.out.println("-----");

        System.out.println("Ejercicio 2");
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.explorarCentroUrbano(lista,punt,min).getResultado());
        Assertions.assertEquals(Retorno.error1("No da").getResultado(), sistema.explorarCentroUrbano(null,punt,min).getResultado());
        Assertions.assertEquals(Retorno.error2("No da").getResultado(), sistema.explorarCentroUrbano(lista2,punt,min).getResultado());
        Assertions.assertEquals(Retorno.error3("No da").getResultado(), sistema.explorarCentroUrbano(lista3,punt,min).getResultado());
        Assertions.assertEquals(Retorno.error4("No da").getResultado(), sistema.explorarCentroUrbano(lista,punt,0).getResultado());
        System.out.println(sistema.explorarCentroUrbano(lista,punt,min).getValorString() +" total:"+ sistema.explorarCentroUrbano(lista,punt,min).getValorInteger() );

    }
}
