package dominio;

public class CentroUrbano implements Comparable<CentroUrbano> {
    private String codigo;
    private String nombre;

    public CentroUrbano(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public CentroUrbano(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static boolean validar(String codigo, String nombre) {
        if (!codigo.isEmpty() && !nombre.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return ((CentroUrbano) o).codigo.equals(this.codigo);
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre;
    }

    @Override
    public int compareTo(CentroUrbano o) {
        int S = codigo.compareTo(o.codigo);
        if(S == 0){
            return 0;
        }
        if(S > 0){
            return 1;
        }
        return -1;
    }

}
