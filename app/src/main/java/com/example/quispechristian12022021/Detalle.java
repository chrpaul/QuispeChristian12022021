package com.example.quispechristian12022021;

public class Detalle {
    public int id_detalle;
    public int id_pedido_p;
    public int id_producto_p;
    public int cantidad;
    public double subtotal_iva;
    public double subtotal_ice;
    public double subtotal_producto;
    public Producto producto;

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_pedido_p() {
        return id_pedido_p;
    }

    public void setId_pedido_p(int id_pedido_p) {
        this.id_pedido_p = id_pedido_p;
    }

    public int getId_producto_p() {
        return id_producto_p;
    }

    public void setId_producto_p(int id_producto_p) {
        this.id_producto_p = id_producto_p;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal_iva() {
        return subtotal_iva;
    }

    public void setSubtotal_iva(double subtotal_iva) {
        this.subtotal_iva = subtotal_iva;
    }

    public double getSubtotal_ice() {
        return subtotal_ice;
    }

    public void setSubtotal_ice(double subtotal_ice) {
        this.subtotal_ice = subtotal_ice;
    }

    public double getSubtotal_producto() {
        return subtotal_producto;
    }

    public void setSubtotal_producto(double subtotal_producto) {
        this.subtotal_producto = subtotal_producto;
    }
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Detalle(){

    }
}
