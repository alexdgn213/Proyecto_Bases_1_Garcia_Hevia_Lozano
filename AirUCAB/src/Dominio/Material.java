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
public class Material {
    
    int mat_codigo;
    String mat_nombre;
    int mat_tiempo_estimado;
    
    public Material(int mat_codigo, String mat_nombre, int mat_tiempo_estimado) {
        this.mat_codigo = mat_codigo;
        this.mat_nombre = mat_nombre;
        this.mat_tiempo_estimado = mat_tiempo_estimado;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Material(mat_codigo,mat_nombre,mat_tiempo_estimado) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mat_codigo);
            pst.setString(2, mat_nombre);
            pst.setInt(3, mat_tiempo_estimado);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Material SET mat_nombre = ?, mat_tiempo_estimado = ? WHERE mat_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(3, mat_codigo);
            pst.setString(1, mat_nombre);
            pst.setInt(2, mat_tiempo_estimado);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Material where mat_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mat_codigo);
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
    
    public static List<Material> obtenerTodos(ConectorDB conector){
        List<Material> materiales = new ArrayList<Material>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT mat_codigo,mat_nombre, mat_tiempo_estimado FROM Material");
            while (rs.next()) {
                Material m = new Material(rs.getInt("mat_codigo"),rs.getString("mat_nombre"),rs.getInt("mat_tiempo_estimado"));
                materiales.add(m);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return materiales;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT mat_codigo as Codigo,mat_nombre as Material, mat_tiempo_estimado as Tiempo_estimado FROM Material");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static void llenarComboBox(ConectorDB conector, JComboBox jCombo){
        PreparedStatement pst;
        try {
            pst = conector.conexion.prepareStatement("SELECT mat_nombre from material");
            ResultSet rs = pst.executeQuery();
            AdaptadorSQLUI.llenarComboBox(jCombo, rs);
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        
    }
    
    public static Material buscarPorNombre(ConectorDB conector, String nombre){
        Material m = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mat_codigo, mat_nombre, mat_tiempo_estimado FROM Material WHERE mat_nombre=?");
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                m = new Material(rs.getInt("mat_codigo"),rs.getString("mat_nombre"),rs.getInt("mat_tiempo_estimado"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return m;
    }

    public void setMat_codigo(int mat_codigo) {
        this.mat_codigo = mat_codigo;
    }

    public void setMat_nombre(String mat_nombre) {
        this.mat_nombre = mat_nombre;
    }

    public void setMat_tiempo_estimado(int mat_tiempo_estimado) {
        this.mat_tiempo_estimado = mat_tiempo_estimado;
    }

    public int getMat_codigo() {
        return mat_codigo;
    }

    public String getMat_nombre() {
        return mat_nombre;
    }

    public int getMat_tiempo_estimado() {
        return mat_tiempo_estimado;
    }

    
}
