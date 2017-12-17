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
public class Motor {
    
    int mot_codigo;
    String mot_modelo;
    String mot_marca;
    int fk_mod_codigo;
    
    public Motor(int mot_codigo, String mot_modelo, String mot_marca, int fk_mod_codigo) {
        this.mot_codigo = mot_codigo;
        this.mot_modelo = mot_modelo;
        this.mot_marca = mot_marca;
        this.fk_mod_codigo = fk_mod_codigo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO motor(mot_codigo,mot_modelo,mot_marca,fk_mod_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mot_codigo);
            pst.setString(2, mot_modelo);
            pst.setString(3, mot_marca);
            pst.setInt(4, fk_mod_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE motor SET mot_modelo = ?, mot_marca = ?, fk_mod_codigo = ? WHERE mod_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(4, mot_codigo);
            pst.setString(1, mot_modelo);
            pst.setString(2, mot_marca);
            pst.setInt(3, fk_mod_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public static void llenarComboBox(ConectorDB conector, JComboBox jCombo){
        PreparedStatement pst;
        try {
            pst = conector.conexion.prepareStatement("SELECT mot_marca || ' ' || mod_modelo from motor");
            ResultSet rs = pst.executeQuery();
            AdaptadorSQLUI.llenarComboBox(jCombo, rs);
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from motor where mot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mot_codigo);
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
    
    public static List<Motor> obtenerTodos(ConectorDB conector){
        List<Motor> motores = new ArrayList<Motor>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT mot_codigo,mot_modelo, mot_marca, fk_mod_codigo FROM Motor");
            while (rs.next()) {
                Motor m = new Motor(rs.getInt("mot_codigo"),rs.getString("mot_modelo"),rs.getString("mot_marca"),rs.getInt("fk_mod_codigo"));
                motores.add(m);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return motores;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT mot_codigo as Motor,mot_modelo as Nombre, mot_marca as Marca, fk_mod_codigo as Modelo FROM motor");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    /*
    public static Motor buscarPorCodigo(ConectorDB conector, int codigo){
        Proveedor l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mot_modelo, mot_marca, fk_mod_codigo FROM motor WHERE mot_codigo=?");
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

    
    public void setMot_codigo(int mot_codigo) {
        this.mot_codigo = mot_codigo;
    }

    public void setMot_modelo(String mot_modelo) {
        this.mot_modelo = mot_modelo;
    }

    public void setMot_marca(String mot_marca) {
        this.mot_marca = mot_marca;
    }

    public void setFk_mod_codigo(int fk_mod_codigo) {
        this.fk_mod_codigo = fk_mod_codigo;
    }

    public int getMot_codigo() {
        return mot_codigo;
    }

    public String getMot_modelo() {
        return mot_modelo;
    }

    public String getMot_marca() {
        return mot_marca;
    }

    public int getFk_mod_codigo() {
        return fk_mod_codigo;
    }
    
}
