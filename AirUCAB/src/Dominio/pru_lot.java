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
public class pru_lot {
    
    int pru_lot_codigo;
    Date pru_lot_fecha_realizacion;
    int fk_pru_codigo;
    int fk_lot_codigo;

    public pru_lot(int pru_lot_codigo, Date pru_lot_fecha_realizacion, int fk_pru_codigo, int fk_lot_codigo) {
        this.pru_lot_codigo = pru_lot_codigo;
        this.pru_lot_fecha_realizacion = pru_lot_fecha_realizacion;
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_lot_codigo = fk_lot_codigo;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO pru_lot(pru_lot_codigo,pru_lot_fecha_realizacion,fk_pru_codigo,fk_lot_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pru_lot_codigo);
            pst.setDate(2, pru_lot_fecha_realizacion);
            pst.setInt(3,fk_pru_codigo);
            pst.setInt(4,fk_lot_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE pru_lot SET pru_lot_fecha_realizacion = ?,fk_pru_codigo = ?,fk_lot_codigo=? WHERE pru_lot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(4, pru_lot_codigo);
            pst.setDate(1, pru_lot_fecha_realizacion);
            pst.setInt(2,fk_pru_codigo);
            pst.setInt(3,fk_lot_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from pru_lot where pru_lot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pru_lot_codigo);
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

    public static List<pru_lot> obtenerTodos(ConectorDB conector){
        List<pru_lot> pls = new ArrayList<pru_lot>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT pru_lot_codigo as codigo,pru_lot_fecha_realizacion as Fecha_Realizacion,fk_pru_codigo as Codigo_Prueba,fk_lot_codigo as Codigo_lote FROM pru_lot");
            while (rs.next()) {
                pru_lot l = new pru_lot(rs.getInt("pru_lot_codigo"),rs.getDate("pru_lot_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_lot_codigo"));
                pls.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pls;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT pru_lot_codigo as Codigo,pru_lot_fecha_realizacion as Fecha_Realizacion,fk_pru_codigo as Codigo_Prueba,fk_lot_codigo as Codigo_Lote FROM pru_lot");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    /*
    public static Pru_lot buscarPorCodigo(ConectorDB conector, int codigo){
        Pru_lot l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pru_lot_fecha_realizacion as Fecha_Realizacion,fk_pru_codigo as Codigo_Prueba,fk_lot_codigo as Codigo_Lote FROM pru_lot WHERE pru_lot_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new pru_lot(rs.getInt("pru_lot_codigo"),rs.getDate("pru_lot_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_lot_codigo");
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    */

    public void setPru_lot_codigo(int pru_lot_codigo) {
        this.pru_lot_codigo = pru_lot_codigo;
    }

    public void setPru_lot_fecha_realizacion(Date pru_lot_fecha_realizacion) {
        this.pru_lot_fecha_realizacion = pru_lot_fecha_realizacion;
    }

    public void setFk_pru_codigo(int fk_pru_codigo) {
        this.fk_pru_codigo = fk_pru_codigo;
    }

    public void setFk_lot_codigo(int fk_lot_codigo) {
        this.fk_lot_codigo = fk_lot_codigo;
    }

    public int getPru_lot_codigo() {
        return pru_lot_codigo;
    }

    public Date getPru_lot_fecha_realizacion() {
        return pru_lot_fecha_realizacion;
    }

    public int getFk_pru_codigo() {
        return fk_pru_codigo;
    }

    public int getFk_lot_codigo() {
        return fk_lot_codigo;
    }

}

