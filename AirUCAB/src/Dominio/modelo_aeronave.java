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
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author Veronica Hevia
 */
public class modelo_aeronave {
    int mod_codigo;
    String mod_nombre;
    int mod_precio_compra;
    
    public modelo_aeronave(int mod_codigo, String mod_nombre, int mod_precio_compra) {
        this.mod_codigo = mod_codigo;
        this.mod_nombre = mod_nombre;
        this.mod_precio_compra = mod_precio_compra;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO modelo_aeronave(mod_codigo,mod_nombre,mod_precio_compra) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mod_codigo);
            pst.setString(2, mod_nombre);
            pst.setInt(1, mod_precio_compra);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE modelo_aeronave SET mod_nombre = ?, mod_precio_compra=? WHERE mod_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(3, mod_codigo);
            pst.setString(1, mod_nombre);
            pst.setInt(2, mod_precio_compra);
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
            ResultSet rs = obtenerResultSet(conector,"SELECT mod_codigo,mod_nombre,mod_precio_compra FROM modelo_aeronave");
            while (rs.next()) {
                modelo_aeronave m = new modelo_aeronave(rs.getInt("mod_codigo"),rs.getString("mod_nombre"),rs.getInt("mod_precio_compra"));
                modelos.add(m);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return modelos;
    }
    
    public static void llenarComboBox(ConectorDB conector, JComboBox jCombo){
        PreparedStatement pst;
        try {
            pst = conector.conexion.prepareStatement("SELECT mod_nombre from modelo_aeronave");
            ResultSet rs = pst.executeQuery();
            AdaptadorSQLUI.llenarComboBox(jCombo, rs);
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT mod_codigo as Codigo,mod_nombre as nombre,mod_precio_compra as precio  FROM modelo_aeronave");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static modelo_aeronave buscarPorCodigo(ConectorDB conector, int codigo){
        modelo_aeronave m = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mod_codigo,mod_nombre,mod_precio_compra FROM modelo_aeronave WHERE mod_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                m = new modelo_aeronave(rs.getInt("mod_codigo"),rs.getString("mod_nombre"),rs.getInt("mod_precio_compra"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return m;
    }
    
    public static modelo_aeronave buscarPorNombre(ConectorDB conector, String nombre){
        modelo_aeronave m = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mod_codigo,mod_nombre,mod_precio_compra FROM modelo_aeronave WHERE mod_nombre=?");
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                m = new modelo_aeronave(rs.getInt("mod_codigo"),rs.getString("mod_nombre"),rs.getInt("mod_precio_compra"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return m;
    }

    public int getMod_codigo() {
        return mod_codigo;
    }

    public void setMod_codigo(int mod_codigo) {
        this.mod_codigo = mod_codigo;
    }

    public String getMod_nombre() {
        return mod_nombre;
    }

    public void setMod_nombre(String mod_nombre) {
        this.mod_nombre = mod_nombre;
    }

    public int getMod_precio_compra() {
        return mod_precio_compra;
    }

    public void setMod_precio_compra(int mod_precio_compra) {
        this.mod_precio_compra = mod_precio_compra;
    }
    
    
    
}
