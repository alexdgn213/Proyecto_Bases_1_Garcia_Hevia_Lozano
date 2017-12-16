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
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author LAB_L11
 */
public class Estatus{
    
    int est_codigo;
    String est_nombre;

    public Estatus(String est_nombre) {
        this.est_nombre = est_nombre;
    }

    public Estatus(int est_codigo, String est_nombre) {
        this.est_codigo = est_codigo;
        this.est_nombre =est_nombre ;
    }
    
    



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Estatus(est_nombre) VALUES(?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, est_nombre);
            stm = "SELECT TOP 1 est_codigo FROM factura WHERE est_nombre=?";
            pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, est_nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.est_codigo = rs.getInt("est_codigo");
            }
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Estatus SET est_nombre = ? WHERE est_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(2, est_codigo);
            pst.setString(1, est_nombre);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from factura where est_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, est_codigo);
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
    
    public static List<Estatus> obtenerTodos(ConectorDB conector){
        List<Estatus> estatuses = new ArrayList<Estatus>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT est_codigo,est_nombre FROM estatus");
            while (rs.next()) {
                Estatus e = new Estatus(rs.getInt("est_codigo"),rs.getString("est_nombre"));
                estatuses.add(e);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return estatuses;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT est_codigo as Codigo,est_nombre as Nombre FROM estatus");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static void llenarComboBox(ConectorDB conector, JComboBox jCombo){
        ResultSet rs =obtenerResultSet(conector,"SELECT est_nombre as Nombre FROM estatus");
        AdaptadorSQLUI.llenarComboBox(jCombo, rs);
        
    }
    
    public static Estatus buscarPorCodigo(ConectorDB conector, int codigo){
        Estatus e = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT est_codigo,est_nombre FROM estatus WHERE est_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                e = new Estatus(rs.getInt("est_codigo"),rs.getString("est_nombre"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return e;
    }
    
}
