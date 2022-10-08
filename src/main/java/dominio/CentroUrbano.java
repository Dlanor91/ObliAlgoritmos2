package dominio;

public class CentroUrbano {
    private String codigo;
    private String nombre;

    public CentroUrbano(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public static boolean validar(String codigo, String nombre){
        if(!codigo.isEmpty() && !nombre.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        return ((CentroUrbano) o).codigo.equals(this.codigo);
    }
}
