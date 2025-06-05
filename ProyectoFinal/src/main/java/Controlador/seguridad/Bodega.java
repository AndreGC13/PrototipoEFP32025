/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.seguridad;

/**
 *
 * @author visitante
 */
public class Bodega{
    
    int id;
    String nombre;
    String tipobodega;
    String direccion;
    String estado;

    public Bodega(int id, String nombre, String tipobodega, String direccion,  String estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipobodega = tipobodega;
        this.direccion = direccion;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "id=" + id + ", nombre=" + nombre + ", tipobodega=" + tipobodega  + ", direccion=" + direccion  + ", estado=" + estado + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String gettipobodega() {
        return tipobodega;
    }

    public void settipobodega(String tipobodega) {
        this.tipobodega = tipobodega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

    public Bodega() {
    }
   
}
