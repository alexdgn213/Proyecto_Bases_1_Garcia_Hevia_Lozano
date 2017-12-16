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
import java.time.LocalDate;
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
    int fk_est_codigo;

    public pru_lot(int pru_lot_codigo, Date pru_lot_fecha_realizacion, int fk_pru_codigo, int fk_lot_codigo, int fk_est_codigo) {
        this.pru_lot_codigo = pru_lot_codigo;
        this.pru_lot_fecha_realizacion = pru_lot_fecha_realizacion;
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_lot_codigo = fk_lot_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }

    public pru_lot( int fk_pru_codigo, int fk_lot_codigo, int fk_est_codigo) {
        this.pru_lot_fecha_realizacion = Date.valueOf(LocalDate.now());
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_lot_codigo = fk_lot_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }

    public pru_lot(Date pru_lot_fecha_realizacion, int fk_pru_codigo, int fk_lot_codigo, int fk_est_codigo) {
        this.pru_lot_fecha_realizacion = pru_lot_fecha_realizacion;
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_lot_codigo = fk_lot_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO pru_lot(pru_lot_fecha_realizacion,fk_pru_codigo,fk_lot_codigo,fk_est_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setDate(1, pru_lot_fecha_realizacion);
            pst.setInt(2,fk_pru_codigo);
            pst.setInt(3,fk_lot_codigo);
            pst.setInt(4,fk_est_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE pru_lot SET pru_lot_fecha_realizacion = ?,fk_est_codigo=? WHERE pru_lot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(3, pru_lot_codigo);
            pst.setDate(1, pru_lot_fecha_realizacion);
            pst.setInt(2,fk_est_codigo);
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
    
    public static void eliminarDeLote(ConectorDB conector, int id){
        try{
            String stm = "Delete from pru_lot where fk_lot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, id);
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
            ResultSet rs = obtenerResultSet(conector,"SELECT pru_lot_codigo as codigo,pru_lot_fecha_realizacion as Fecha_Realizacion,fk_pru_codigo as Codigo_Prueba,fk_lot_codigo as Codigo_lote, fk_est_codigo FROM pru_lot");
            while (rs.next()) {
                pru_lot l = new pru_lot(rs.getInt("pru_lot_codigo"),rs.getDate("pru_lot_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_lot_codigo"),rs.getInt("fk_est_codigo"));
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
    
    public static void llenarTablaLote(ConectorDB conector, JTable jTable, int id){
        ResultSet rs =obtenerResultSet(conector,"SELECT pl.pru_lot_codigo as Codigo,pl.pru_lot_fecha_realizacion as Fecha_Realizacion,p.pru_nombre as Prueba,e.est_nombre as Estatus "
                + " FROM pru_lot pl left join prueba p on pl.fk_pru_codigo=p.pru_codigo left join estatus e on e.est_codigo=pl.fk_est_codigo"
                + " WHERE fk_est_codigo!=6 AND fk_lot_codigo="+String.valueOf(id));
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static pru_lot relacionDada(ConectorDB conector, int lot_codigo, int pru_codigo){
       pru_lot respuesta = null; 
        try{
            String stm = "SELECT pru_lot_codigo, pru_lot_fecha_realizacion, fk_pru_codigo, fk_lot_codigo, fk_est_codigo FROM pru_lot "
                    + "WHERE fk_lot_codigo=? AND fk_pru_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, lot_codigo);
            pst.setInt(2, pru_codigo);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                respuesta = new pru_lot(rs.getInt("pru_lot_codigo"),rs.getDate("pru_lot_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_lot_codigo"),rs.getInt("fk_est_codigo"));
            }
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        };
        return respuesta;
    }
    
    public static pru_lot buscarPorCodigo(ConectorDB conector, int codigo){
        pru_lot pl = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pru_lot_codigo, pru_lot_fecha_realizacion,fk_pru_codigo,fk_lot_codigo, fk_est_codigo FROM pru_lot WHERE pru_lot_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pl = new pru_lot(rs.getInt("pru_lot_codigo"),rs.getDate("pru_lot_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_lot_codigo"),rs.getInt("fk_est_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pl;
    }

     public static boolean pruebasListasLote(ConectorDB conector, int lote){
        boolean pl = true;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT fk_est_codigo FROM pru_lot WHERE fk_lot_codigo=?");
            pst.setInt(1, lote);
            ResultSet rs = pst.executeQuery();
            while (rs.next() && pl) {
                int est = rs.getInt("fk_est_codigo");
                if(est<4){
                    pl=false;
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pl;
    }
     
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

    public int getFk_est_codigo() {
        return fk_est_codigo;
    }

    public void setFk_est_codigo(int fk_est_codigo) {
        this.fk_est_codigo = fk_est_codigo;
    }
    
    

}

