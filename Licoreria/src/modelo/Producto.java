package modelo;

public class Producto {
    private String nombre;
    private String marca;
    private float precio;
    private int cantidad;
    
    public Producto(){}
    
    public Producto(String nombre, String marca, float precio, int cantidad){
        this.nombre= nombre;
        this.precio= precio;
        this.marca= marca;
        this.cantidad= cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public String getMarca() {
        return marca;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public String toString(){
        return "Nombre: "+this.nombre+"\nMarca: "+this.marca+"\nCantidad: "+this.cantidad+"\nPrecio: "+this.precio;
    }
    
}