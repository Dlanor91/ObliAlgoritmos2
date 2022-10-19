package dominio;

import interfaz.TipoJugador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jugador implements Comparable<Jugador> {
    private int cedulaInt;
    private String cedula;
    private String nombre;
    private int edad;
    private String escuela;
    private TipoJugador tipo;

    public Jugador() {
    }

    public Jugador(String cedula) {
        this.cedula = cedula;
        this.cedulaInt = getConvertirCedulaEntero(cedula);
    }

    public Jugador(String cedula, String nombre, int edad, String escuela, TipoJugador tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.escuela = escuela;
        this.tipo = tipo;
        this.cedulaInt = getConvertirCedulaEntero(cedula);
    }

    public String getCedula() {
        return cedula;
    }

    public int getCedulaInt() {
        return cedulaInt;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getEscuela() {
        return escuela;
    }

    public TipoJugador getTipo() {
        return tipo;
    }

    public static int getConvertirCedulaEntero(String cedula) {

        String cedulaNueva = "";
        for (int i = 0; i < cedula.length(); i++) {
            if (cedula.charAt(i) != '.' && cedula.charAt(i) != '-') {
                cedulaNueva += cedula.charAt(i);
            }
        }
        int cedulaInt = Integer.parseInt(cedulaNueva);
        return cedulaInt;
    }

    public static boolean validar(String cedula, String nombre, int edad, String escuela, TipoJugador tipo) {
        if (cedula != null && nombre != null && escuela != null && !cedula.isEmpty() && !nombre.isEmpty() && edad > 0 && !escuela.isEmpty() && tipo != null) {
            return true;
        }
        return false;
    }

    public static boolean validarCedula(String cedula) {

        if (cedula.length() == 11) {

            Pattern pat11 = Pattern.compile("[1-9][.]\\d\\d\\d[.]\\d\\d\\d[-]\\d");
            Matcher mat11 = pat11.matcher(cedula);

            if (mat11.matches()) {
                return true;
            }
        } else if (cedula.length() == 9) {
            Pattern pat9 = Pattern.compile("[1-9]\\d\\d[.]\\d\\d\\d[-]\\d");
            Matcher mat9 = pat9.matcher(cedula);
            if (mat9.matches()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Jugador o) {
        if (cedulaInt == o.cedulaInt) {
            return 0;
        }
        if (cedulaInt > o.cedulaInt) {
            return 1;
        }

        return -1;
    }

    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + edad + ";" + escuela + ";" + tipo.getValor();
    }

}
