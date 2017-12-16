/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JTable;

/**
 *
 * @author LAB_L11
 */
public class Lote_material {
    
    int lot_codigo;
    int lot_precio;
    Date lot_fecha_compra;
    int lot_cantidad;
    int fk_mat_codigo;
    int fk_pro_rif;
    int fk_mat_pro_codigo;
    int fk_fac_codigo;

    public Lote_material(int lot_codigo, int lot_precio, Date lot_fecha_compra,int lot_cantidad, int fk_mat_codigo, int fk_pro_rif, int fk_mat_pro_codigo) {
        this.lot_codigo = lot_codigo;
        this.lot_precio = lot_precio;
        this.lot_fecha_compra = lot_fecha_compra;
        this.lot_cantidad=lot_cantidad;
        this.fk_mat_codigo = fk_mat_codigo;
        this.fk_pro_rif = fk_pro_rif;
        this.fk_mat_pro_codigo = fk_mat_pro_codigo;
        this.fk_fac_codigo = 0;
    }

    public Lote_material(int lot_precio, Date lot_fecha_compra, int lot_cantidad, int fk_mat_codigo, int fk_pro_rif, int fk_mat_pro_codigo) {
        this.lot_precio = lot_precio;
        this.lot_fecha_compra = lot_fecha_compra;
        this.lot_cantidad = lot_cantidad;
        this.fk_mat_codigo = fk_mat_codigo;
        this.fk_pro_rif = fk_pro_rif;
        this.fk_mat_pro_codigo = fk_mat_pro_codigo;
        this.fk_fac_codigo = 0;
    }
    
    

    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Lote_material(lot_precio,lot_fecha_compra,lot_cantidad,fk_mat_codigo,fk_pro_rif,fk_mat_pro_codigo,fk_fac_codigo) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, lot_precio);
            pst.setDate(2,lot_fecha_compra);
            pst.setInt(3,lot_cantidad);
            pst.setInt(4,fk_mat_codigo);
            pst.setInt(5,fk_pro_rif);
            pst.setInt(6,fk_mat_pro_codigo);
            pst.setInt(7, fk_fac_codigo);
            pst.executeUpdate();
            stm = "SELECT lot_codigo FROM lote_material WHERE lot_precio=? AND lot_fecha_compra=? AND lot_cantidad=? AND fk_mat_codigo = ? AND fk_pro_rif =? AND fk_mat_pro_codigo=? AND fk_fac_codigo=?";
            pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, lot_precio);
            pst.setDate(2,lot_fecha_compra);
            pst.setInt(3,lot_cantidad);
            pst.setInt(4,fk_mat_codigo);
            pst.setInt(5,fk_pro_rif);
            pst.setInt(6,fk_mat_pro_codigo);
            pst.setInt(7, fk_fac_codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.lot_codigo = rs.getInt("lot_codigo");
            }
            pst.close();
            pru_lot pl1 = new pru_lot(1,lot_codigo,1);
            pl1.agregarADB(conector);
            pru_lot pl2 = new pru_lot(2,lot_codigo,1);
            pl2.agregarADB(conector);
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Lote_material SET lot_precio = ?, lot_fecha_compra = ?,lot_cantidad = ?,fk_mat_codigo = ?,fk_pro_rif=?,fk_mat_pro_codigo WHERE lot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(7, lot_codigo);
            pst.setInt(1, lot_precio);
            pst.setDate(2,lot_fecha_compra);
            pst.setInt(3,lot_cantidad);
            pst.setInt(4,fk_mat_codigo);
            pst.setInt(5,fk_pro_rif);
            pst.setInt(6,fk_mat_pro_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Lote_material where lot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, lot_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public static ResultSet obtenerResultSet(ConectorDB conector, String query){
        try {
            PreparedStatement pst = conector.conexion.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return null;
    }
    
    public static List<Lote_material> obtenerTodos(ConectorDB conector){
        List<Lote_material> lotes = new ArrayList<Lote_material>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT lot_codigo as codigo,lot_precio as precio,lot_fecha_compra as Fecha_Compra,lot_cantidad as Cantidad,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Proveedor,fk_mat_pro_codigo FROM mat_pro");
            while (rs.next()) {
                Lote_material l = new Lote_material(rs.getInt("lot_codigo"),rs.getInt("lot_precio"),rs.getDate("lot_fecha_compra"),rs.getInt("lot_cantidad"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_pro_rif"),rs.getInt("fk_mat_pro_codigo"));
                lotes.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return lotes;
    }
    
    public static Lote_material buscarPorCodigo(ConectorDB conector, int id){
        Lote_material lote = null;
        try {
            String stm = "Select lot_codigo, lot_precio, lot_fecha_compra, lot_cantidad, fk_mat_codigo, fk_pro_rif, fk_mat_pro_codigo  from Lote_material where lot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lote = new Lote_material(rs.getInt("lot_codigo"),rs.getInt("lot_precio"),rs.getDate("lot_fecha_compra"),rs.getInt("lot_cantidad"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_pro_rif"),rs.getInt("fk_mat_pro_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return lote;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT l.lot_codigo as Codigo,l.lot_precio as Precio,l.lot_fecha_compra as Fecha_Compra,l.lot_cantidad as Cantidad,m.mat_nombre as Material,p.pro_nombre as Proveedor "
                + " FROM lote_material l, material m, proveedor p"
                + " WHERE l.fk_pro_rif=p.pro_rif AND l.fk_mat_codigo=m.mat_codigo");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static void llenarTablaPendientes(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT l.lot_codigo as Codigo,l.lot_precio as Precio,l.lot_fecha_compra as Fecha_Compra,l.lot_cantidad as Cantidad,m.mat_nombre as Material,p.pro_nombre as Proveedor "
                + " FROM lote_material l, material m, proveedor p"
                + " WHERE l.fk_pro_rif=p.pro_rif AND l.fk_mat_codigo=m.mat_codigo"
                + " AND exists(Select pru_lot_codigo from pru_lot where fk_lot_codigo= l.lot_codigo AND fk_est_codigo<4)");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }
    
    public static void llenarTablaFinalizados(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT l.lot_codigo as Codigo,l.lot_precio as Precio,l.lot_fecha_compra as Fecha_Compra,l.lot_cantidad as Cantidad,m.mat_nombre as Material,p.pro_nombre as Proveedor "
                + " FROM lote_material l, material m, proveedor p"
                + " WHERE l.fk_pro_rif=p.pro_rif AND l.fk_mat_codigo=m.mat_codigo"
                + " AND not exists(Select pru_lot_codigo from pru_lot where fk_lot_codigo= l.lot_codigo AND fk_est_codigo<4)");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }
    

    public static void llenarTablaDeFactura(ConectorDB conector, JTable jTable, int id){
        ResultSet rs =obtenerResultSet(conector,"SELECT l.lot_codigo as Codigo,m.mat_nombre as Material,l.lot_precio as Precio,l.lot_cantidad as Cantidad "
                + " FROM lote_material l, material m, proveedor p"
                + " WHERE l.fk_pro_rif=p.pro_rif AND l.fk_mat_codigo=m.mat_codigo"
                + " AND fk_fac_codigo="+String.valueOf(id));
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }
    
    public void setLot_codigo(int lot_codigo) {
        this.lot_codigo = lot_codigo;
    }

    public void setLot_precio(int lot_precio) {
        this.lot_precio = lot_precio;
    }

    public void setLot_fecha_compra(Date lot_fecha_compra) {
        this.lot_fecha_compra = lot_fecha_compra;
    }

    public void setLot_cantidad(int lot_cantidad) {
        this.lot_cantidad = lot_cantidad;
    }

    public void setFk_mat_codigo(int fk_mat_codigo) {
        this.fk_mat_codigo = fk_mat_codigo;
    }

    public void setFk_pro_rif(int fk_pro_rif) {
        this.fk_pro_rif = fk_pro_rif;
    }

    public void setFk_mat_pro_codigo(int fk_mat_pro_codigo) {
        this.fk_mat_pro_codigo = fk_mat_pro_codigo;
    }

    public int getLot_codigo() {
        return lot_codigo;
    }

    public int getLot_precio() {
        return lot_precio;
    }

    public Date getLot_fecha_compra() {
        return lot_fecha_compra;
    }

    public int getLot_cantidad() {
        return lot_cantidad;
    }

    public int getFk_mat_codigo() {
        return fk_mat_codigo;
    }

    public int getFk_pro_rif() {
        return fk_pro_rif;
    }

    public int getFk_mat_pro_codigo() {
        return fk_mat_pro_codigo;
    }

    public int getFk_fac_codigo() {
        return fk_fac_codigo;
    }

    public void setFk_fac_codigo(int fk_fac_codigo) {
        this.fk_fac_codigo = fk_fac_codigo;
    }
    
    

}

