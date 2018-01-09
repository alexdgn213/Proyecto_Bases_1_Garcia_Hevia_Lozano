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
import java.util.Calendar;
import javax.swing.JTable;

/**
 *
 * @author LAB_L11
 */
public class Pru_pie {
    
    int pru_pie_codigo;
    Date pru_pie_fecha_realizacion;
    int fk_pru_codigo;
    int fk_pie_codigo;
    int fk_est_codigo;

    public Pru_pie(int pru_pie_codigo,Date pru_pie_fecha_realizacion,int fk_pru_codigo,int fk_pie_codigo,int fk_est_codigo) {
        this.pru_pie_codigo = pru_pie_codigo;
        this.pru_pie_fecha_realizacion = pru_pie_fecha_realizacion;
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_pie_codigo = fk_pie_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }
    
    public Pru_pie(int fk_pru_codigo,int fk_pie_codigo,int fk_est_codigo) {
        this.pru_pie_fecha_realizacion = Date.valueOf(LocalDate.now());
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_pie_codigo = fk_pie_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }

    public Pru_pie(Date pru_pie_fecha_realizacion, int fk_pru_codigo, int fk_pie_codigo, int fk_est_codigo) {
        this.pru_pie_fecha_realizacion = pru_pie_fecha_realizacion;
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_pie_codigo = fk_pie_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }
    
    
    
    public void agregarADB(ConectorDB conector){
        try{
            int dias = 0;
            try {
            PreparedStatement pst = conector.conexion.prepareStatement("select pru_tiempo_estimado from prueba where pru_codigo = "+String.valueOf(fk_pru_codigo));
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                dias=rs.getInt("pru_tiempo_estimado");
            };
            } catch (SQLException ex) {
                System.out.print(ex.toString());
            }
            String stm = "INSERT INTO Pru_pie(pru_pie_fecha_realizacion,pru_pie_fecha_estimada,fk_pru_codigo,fk_pie_codigo,fk_est_codigo) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            Calendar c = Calendar.getInstance();
            c.setTime(pru_pie_fecha_realizacion);
            c.add(Calendar.DATE, dias);
            Date fechaFinal = new Date(c.getTimeInMillis());
            pst.setDate(1,fechaFinal);
            pst.setDate(2,fechaFinal);
            pst.setInt(3, fk_pru_codigo);
            pst.setInt(4,fk_pie_codigo);
            pst.setInt(5,fk_est_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Pru_pie SET pru_pie_fecha_realizacion =?, fk_pru_codigo = ?,fk_pie_codigo=?,fk_est_codigo=? WHERE pru_pie_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(5, pru_pie_codigo);
            pst.setDate(1, pru_pie_fecha_realizacion);
            pst.setInt(2, fk_pru_codigo);
            pst.setInt(3,fk_pie_codigo);
            pst.setInt(4,fk_est_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Pru_pie where pru_pie_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pru_pie_codigo);
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
    
    public static Pru_pie relacionDada(ConectorDB conector, int pru_codigo, int pie_codigo){
       Pru_pie respuesta = null; 
        try{
            String stm = "SELECT pru_pie_codigo, pru_pie_fecha_realizacion, fk_pru_codigo, fk_pie_codigo, fk_est_codigo FROM pru_pie "
                    + "WHERE fk_pie_codigo=? AND fk_pru_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pie_codigo);
            pst.setInt(2, pru_codigo);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                respuesta = new Pru_pie(rs.getInt("pru_pie_codigo"),rs.getDate("pru_pie_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_pie_codigo"),rs.getInt("fk_est_codigo"));
            }
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        };
        return respuesta;
    }

    public static List<Pru_pie> obtenerTodos(ConectorDB conector){
        List<Pru_pie> pp = new ArrayList<Pru_pie>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT pru_pie_codigo as codigo,pru_pie_fecha_realizacion as Fecha_Realizacion,fk_pru_codigo as Codigo_Prueba,fk_pie_codigo as Codigo_Pieza,fk_est_codigo as Codigo_Estatus FROM Pru_pie");
            while (rs.next()) {
                Pru_pie l = new Pru_pie(rs.getInt("pru_pie_codigo"),rs.getDate("pru_pie_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_pie_codigo"),rs.getInt("fk_est_codigo"));
                pp.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pp;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT pru_pie_codigo as Codigo,pru_pie_fecha_realizacion as Fecha_Realizacion,fk_pru_codigo as Codigo_Prueba,fk_pie_codigo as Codigo_Pieza,fk_est_codigo as Codigo_Estatus FROM Pru_pie");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
    
    public static void llenarTablaDePruebas(ConectorDB conector, JTable jTable, int id){
        ResultSet rs =obtenerResultSet(conector,"SELECT pru_pie_codigo as Codigo ,pru_pie_fecha_realizacion as Fecha_Realizacion,pru_nombre as Prueba,est_nombre as Estatus"
                + " From Pru_pie, prueba, estatus"
                + " WHERE fk_pru_codigo=pru_codigo AND fk_est_codigo=est_codigo "
                + " AND fk_pie_codigo="+String.valueOf(id));
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }
   
    
    public static Pru_pie buscarPorCodigo(ConectorDB conector, int codigo){
        Pru_pie tp = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pru_pie_codigo,pru_pie_fecha_realizacion, fk_pru_codigo,fk_pie_codigo,fk_est_codigo FROM pru_pie WHERE pru_pie_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tp = new Pru_pie(rs.getInt("pru_pie_codigo"),rs.getDate("pru_pie_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_pie_codigo"),rs.getInt("fk_est_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return tp;
    }

    public int getPru_pie_codigo() {
        return pru_pie_codigo;
    }

    public void setPru_pie_codigo(int pru_pie_codigo) {
        this.pru_pie_codigo = pru_pie_codigo;
    }

    public Date getPru_pie_fecha_realizacion() {
        return pru_pie_fecha_realizacion;
    }

    public void setPru_pie_fecha_realizacion(Date pru_pie_fecha_realizacion) {
        this.pru_pie_fecha_realizacion = pru_pie_fecha_realizacion;
    }

    public int getFk_pru_codigo() {
        return fk_pru_codigo;
    }

    public void setFk_pru_codigo(int fk_pru_codigo) {
        this.fk_pru_codigo = fk_pru_codigo;
    }

    public int getFk_pie_codigo() {
        return fk_pie_codigo;
    }

    public void setFk_pie_codigo(int fk_pie_codigo) {
        this.fk_pie_codigo = fk_pie_codigo;
    }

    public int getFk_est_codigo() {
        return fk_est_codigo;
    }

    public void setFk_est_codigo(int fk_est_codigo) {
        this.fk_est_codigo = fk_est_codigo;
    }
    
    
}