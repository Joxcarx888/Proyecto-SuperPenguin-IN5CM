/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorejon.model;


public class DetalleCompra {
    private int detaelleCompraId;
    private int CantidadComprada;
    private int productoId;
    private String producto;
    private int compraId;
    private String compra;

    public DetalleCompra() {
    }

    public DetalleCompra(int detaelleCompraId, int CantidadComprada, String producto, String compra) {
        this.detaelleCompraId = detaelleCompraId;
        this.CantidadComprada = CantidadComprada;
        this.producto = producto;
        this.compra = compra;
    }

    public int getDetaelleCompraId() {
        return detaelleCompraId;
    }

    public void setDetaelleCompraId(int detaelleCompraId) {
        this.detaelleCompraId = detaelleCompraId;
    }

    public int getCantidadComprada() {
        return CantidadComprada;
    }

    public void setCantidadComprada(int CantidadComprada) {
        this.CantidadComprada = CantidadComprada;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" + "detaelleCompraId=" + detaelleCompraId + ", CantidadComprada=" + CantidadComprada + ", productoId=" + productoId + ", producto=" + producto + ", compraId=" + compraId + ", compra=" + compra + '}';
    }
    
    
}
