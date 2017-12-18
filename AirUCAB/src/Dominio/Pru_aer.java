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
public class Pru_aer {
    
    int pru_aer_codigo;
    Date pru_aer_fecha_realizacion;
    int fk_pru_codigo;
    int fk_aer_codigo;
    int fk_est_codigo;

    public Pru_aer(int pru_aer_codigo,Date pru_aer_fecha_realizacion,int fk_pru_codigo,int fk_aer_codigo,int fk_est_codigo) {
        this.pru_aer_codigo = pru_aer_codigo;
        this.pru_aer_fecha_realizacion = pru_aer_fecha_realizacion;
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_aer_codigo = fk_aer_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }
    
    public Pru_aer(Date pru_aer_fecha_realizacion,int fk_pru_codigo,int fk_aer_codigo,int fk_est_codigo) {
        this.pru_aer_codigo = pru_aer_codigo;
        this.pru_aer_fecha_realizacion = pru_aer_fecha_realizacion;
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_aer_codigo = fk_aer_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }
    
    public Pru_aer(int fk_pru_codigo,int fk_aer_codigo,int fk_est_codigo) {
        this.pru_aer_fecha_realizacion = Date.valueOf(LocalDate.now());
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_aer_codigo = fk_aer_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Pru_aer(pru_aer_fecha_realizacion,fk_pru_codigo,fk_aer_codigo,fk_est_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setDate(1,pru_aer_fecha_realizacion);
            pst.setInt(2, fk_pru_codigo);
            pst.setInt(3,fk_aer_codigo);
            pst.setInt(4,fk_est_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Pru_aer SET pru_aer_fecha_realizacion =?, fk_pru_codigo = ?,fk_aer_codigo=?,fk_est_codigo=? WHERE pru_aer_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(5, pru_aer_codigo);
            pst.setDate(1, pru_aer_fecha_realizacion);
            pst.setInt(2, fk_pru_codigo);
            pst.setInt(3,fk_aer_codigo);
            pst.setInt(4,fk_est_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Pru_aer where pru_aer_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pru_aer_codigo);
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

    public static List<Pru_aer> obtenerTodos(ConectorDB conector){
        List<Pru_aer> pp = new ArrayList<Pru_aer>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT pru_aer_codigo as codigo,pru_aer_fecha_realizacion as Fecha_Realizacion,fk_pru_codigo as Codigo_Prueba,fk_aer_codigo as Codigo_Aeronave,fk_est_codigo as Codigo_Estatus FROM Pru_aer");
            while (rs.next()) {
                Pru_aer l = new Pru_aer(rs.getInt("pru_aer_codigo"),rs.getDate("pru_aer_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_aer_codigo"),rs.getInt("fk_est_codigo"));
                pp.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pp;
    }
    
     public static List<Pru_aer> obtenerTodasPruebasAeronave(ConectorDB conector,int fk_aer_codigo){
        List<Pru_aer> pls = new ArrayList<Pru_aer>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pru_aer_codigo,pru_aer_fecha_realizacion,fk_aer_codigo,fk_pru_codigo,fk_est_codigo " +
"                    FROM Pru_aer  " +
"                     Where fk_aer_codigo=?");
            pst.setInt(1, fk_aer_codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pru_aer l = new Pru_aer(rs.getInt("Pru_aer_codigo"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_aer_codigo"));
                pls.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pls;
    }
     
     public static Pru_aer relacionDada(ConectorDB conector, int pru_codigo, int aer_codigo){
       Pru_aer respuesta = null; 
        try{
            String stm = "SELECT pru_aer_codigo, pru_aer_fecha_realizacion, fk_pru_codigo, fk_aer_codigo, fk_est_codigo FROM pru_aer "
                    + "WHERE fk_aer_codigo=? AND fk_pru_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, aer_codigo);
            pst.setInt(2, pru_codigo);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                respuesta = new Pru_aer(rs.getInt("pru_aer_codigo"),rs.getDate("pru_aer_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_aer_codigo"),rs.getInt("fk_est_codigo"));
            }
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        };
        return respuesta;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT pru_aer_codigo as Codigo,pru_aer_fecha_realizacion as Fecha_Realizacion,fk_pru_codigo as Codigo_Prueba,fk_aer_codigo as Codigo_Pieza,fk_est_codigo as Codigo_Estatus FROM Pru_aer");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
   
    
    public static void llenarTablaAvion(ConectorDB conector, JTable jTable, int id){
        ResultSet rs =obtenerResultSet(conector,"SELECT pa.pru_aer_codigo as Codigo,pa.pru_aer_fecha_realizacion as Fecha_Realizacion,p.pru_nombre as Prueba,e.est_nombre as Estatus "
                + " FROM pru_aer pa left join prueba p on pa.fk_pru_codigo=p.pru_codigo left join estatus e on e.est_codigo=pa.fk_est_codigo"
                + " WHERE fk_aer_codigo="+String.valueOf(id));
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
    
    public static Pru_aer buscarPorCodigo(ConectorDB conector, int codigo){
        Pru_aer tp = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pru_aer_codigo,pru_aer_fecha_realizacion, fk_pru_codigo,fk_aer_codigo,fk_est_codigo FROM Pru_aer WHERE pru_aer_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tp = new Pru_aer(rs.getInt("pru_aer_codigo"),rs.getDate("pru_aer_fecha_realizacion"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_aer_codigo"),rs.getInt("fk_est_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return tp;
    }

    public int getPru_aer_codigo() {
        return pru_aer_codigo;
    }

    public void setPru_aer_codigo(int pru_aer_codigo) {
        this.pru_aer_codigo = pru_aer_codigo;
    }

    public Date getPru_aer_fecha_realizacion() {
        return pru_aer_fecha_realizacion;
    }

    public void setPru_aer_fecha_realizacion(Date pru_aer_fecha_realizacion) {
        this.pru_aer_fecha_realizacion = pru_aer_fecha_realizacion;
    }

    public int getFk_pru_codigo() {
        return fk_pru_codigo;
    }

    public void setFk_pru_codigo(int fk_pru_codigo) {
        this.fk_pru_codigo = fk_pru_codigo;
    }

    public int getFk_aer_codigo() {
        return fk_aer_codigo;
    }

    public void setFk_aer_codigo(int fk_aer_codigo) {
        this.fk_aer_codigo = fk_aer_codigo;
    }

    public int getFk_est_codigo() {
        return fk_est_codigo;
    }

    public void setFk_est_codigo(int fk_est_codigo) {
        this.fk_est_codigo = fk_est_codigo;
    }
    
    
}