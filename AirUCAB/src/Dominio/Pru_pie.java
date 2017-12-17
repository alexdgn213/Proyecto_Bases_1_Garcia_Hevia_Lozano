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
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Pru_pie(pru_pie_fecha_realizacion,fk_pru_codigo,fk_pie_codigo,fk_est_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setDate(1,pru_pie_fecha_realizacion);
            pst.setInt(2, fk_pru_codigo);
            pst.setInt(3,fk_pie_codigo);
            pst.setInt(4,fk_est_codigo);
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
            pst.setInt(4, pru_pie_codigo);
            pst.setDate(1, pru_pie_fecha_realizacion);
            pst.setInt(2, fk_pru_codigo);
            pst.setInt(3,fk_pie_codigo);
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
}