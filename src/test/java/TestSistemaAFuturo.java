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

        Assertions.assertEquals(Retorno.ok(), sistema.inicializarSistema(6));

        //pruebas nuestras
        Assertions.assertEquals(Retorno.error1("Error1").getResultado(), sistema.inicializarSistema(5).getResultado());
        Assertions.assertEquals(Retorno.error1("Error1").getResultado(), sistema.registrarJugador("","",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("6.123.456-1","PedroRaiz",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("6.123.456.1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("123.456-1","IzqPedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("123.456.1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("a34.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("11.234.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("0.234.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("034.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("8.634.456-1","DerPedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("4.634.456-1","DerPedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("9.634.456-1","DerPedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error3("Error3").getResultado(), sistema.registrarJugador("8.634.456-1","DerPedroRepetido",12,"Escuela1", TipoJugador.MEDIO).getResultado());

        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.buscarJugador("8.634.456-1").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.buscarJugador("9.634.456-1").getResultado());
        Assertions.assertEquals(Retorno.error2("No esta").getResultado(), sistema.buscarJugador("8.634.456-2").getResultado());

        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.listarJugadoresPorCedulaAscendente().getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.listarJugadoresPorCedulaDescendente().getResultado());

        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa","C1").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa1","C1").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa2","C1").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa3","C1").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa4","C1").getResultado());
        Assertions.assertEquals(Retorno.error3("No esta").getResultado(), sistema.registrarCentroUrbano("aa","C1").getResultado());
        Assertions.assertEquals(Retorno.error2("Vacio").getResultado(), sistema.registrarCentroUrbano("","C1").getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarCentroUrbano("aa5","C1").getResultado());
        Assertions.assertEquals(Retorno.error1("No esta").getResultado(), sistema.registrarCentroUrbano("aa7","C1").getResultado());

        System.out.println("Ver");

    }
}
