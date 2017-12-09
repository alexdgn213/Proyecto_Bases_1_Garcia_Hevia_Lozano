/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Veronica Hevia
 */
public class modelo_aeronave {
    int mod_codigo;
    String mod_nombre;
    
    public modelo_aeronave(int mod_codigo, String mod_nombre) {
        this.mod_codigo = mod_codigo;
        this.mod_nombre = mod_nombre;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO modelo_aeronave(mod_codigo,mod_nombre) VALUES(?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mod_codigo);
            pst.setString(2, mod_nombre);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE modelo_aeronave SET mod_nombre = ? WHERE mod_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(5, mod_codigo);
            pst.setString(1, mod_nombre);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from modelo_aeronave where mod_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mod_codigo);
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
    
    public static List<modelo_aeronave> obtenerTodos(ConectorDB conector){
        List<modelo_aeronave> modelos = new ArrayList<modelo_aeronave>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT mod_codigo,mod_nombre FROM modelo_aeronave");
            while (rs.next()) {
                modelo_aeronave m = new modelo_aeronave(rs.getInt("mod_codigo"),rs.getString("mod_nombre"));
                modelos.add(m);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return modelos;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT mod_codigo as Modelo,mod_nombre as nombre FROM proveedor");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    /*
    public static modelo_Aeronave buscarPorCodigo(ConectorDB conector, int codigo){
        Proveedor l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mod_codigo,mod_nombre FROM modelo_aeronave WHERE mod_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Proveedor(rs.getInt("mod_codigo"),rs.getString("mod_nombre"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    */
}
