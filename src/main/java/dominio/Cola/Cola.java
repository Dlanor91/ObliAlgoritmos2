package dominio.Cola;

public interface Cola<T> {
    public boolean esVacia();
    public void encolar(T dato);

    //Pre: !esVacia()
   public T desencolar();



}
