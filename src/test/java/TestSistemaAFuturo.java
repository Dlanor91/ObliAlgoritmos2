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

        Assertions.assertEquals(Retorno.ok(), sistema.inicializarSistema(15));
        //pruebas nuestras
        Assertions.assertEquals(Retorno.error1("Error1").getResultado(), sistema.registrarJugador("","",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("6.123.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("6.123.456.1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.ok().getResultado(), sistema.registrarJugador("123.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("123.456.1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("a34.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("11.234.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("0.234.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());
        Assertions.assertEquals(Retorno.error2("Error2").getResultado(), sistema.registrarJugador("034.456-1","Pedro",12,"Escuela1", TipoJugador.MEDIO).getResultado());

    }
}
