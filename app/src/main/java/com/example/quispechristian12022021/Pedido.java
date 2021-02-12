package com.example.quispechristian12022021;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id_pedido;
    private double total;
    private double total_ice;
    private double total_iva;
    private List<Detalle> detalles;
    public Pedido (){
        detalles = new ArrayList<Detalle>();
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal_ice() {
        return total_ice;
    }

    public void setTotal_ice(double total_ice) {
        this.total_ice = total_ice;
    }

    public double getTotal_iva() {
        return total_iva;
    }

    public void setTotal_iva(double total_iva) {
        this.total_iva = total_iva;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }
}
