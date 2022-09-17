package dominio;

import interfaz.TipoJugador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jugador {
    private String cedula;
    private String nombre;
    private int edad;
    private String escuela;
    private TipoJugador tipo;

    public Jugador() {
    }

    public Jugador(String cedula, String nombre, int edad, String escuela, TipoJugador tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.escuela = escuela;
        this.tipo = tipo;
    }

    public String getCedula() {
        return cedula;
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

    //Desarrollar
    public int getConvertirCedula(){
        int cedulaInt = 0;

        return cedulaInt;
    }

    //Desarrollar
    public static boolean validarCedula(String cedula){

            if(cedula.length()==11){

                Pattern pat11 = Pattern.compile("\\d[.]\\d\\d\\d[.]\\d\\d\\d[-]\\d");
                Matcher mat11 = pat11.matcher(cedula);

                if (mat11.matches()) {
                    return true;
                }
            }else if(cedula.length()==9){
                Pattern pat9 = Pattern.compile("\\d\\d\\d[.]\\d\\d\\d[-]\\d");
                Matcher mat9 = pat9.matcher(cedula);
                if (mat9.matches()) {
                    return true;
                }
            }
        return false;
    }
}
