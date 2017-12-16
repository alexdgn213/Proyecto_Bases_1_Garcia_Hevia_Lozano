/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author LAB_L11
 */
public class Fabrica {
    
    int fab_codigo;
    String fab_nombre;
    int fk_lug_codigo;

    public Fabrica(int fab_codigo, String fab_nombre, int fk_lug_codigo) {
        this.fab_codigo = fab_codigo;
        this.fab_nombre = fab_nombre;
        this.fk_lug_codigo = fk_lug_codigo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Fabrica(fab_codigo,fab_nombre,fk_lug_codigo) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, fab_codigo);
            pst.setString(2, fab_nombre);
            pst.setInt(3,fk_lug_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Fabrica SET fab_nombre=?,fk_lug_codigo=? WHERE fab_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, fab_nombre);
            pst.setInt(2,fk_lug_codigo);
            pst.setInt(3, fab_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Fabrica where fab_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, fab_codigo);
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
    
    public static List<Fabrica> obtenerTodos(ConectorDB conector){
        List<Fabrica> fabricas = new ArrayList<Fabrica>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT fab_codigo, fab_nombre, fk_lug_codigo FROM fabrica");
            ResultSet rs = obtenerResultSet(conector, "SELECT fab_codigo, fab_nombre, fk_lug_codigo FROM fabrica");
            while (rs.next()) {
                Fabrica l = new Fabrica(rs.getInt("fab_codigo"),rs.getString("fab_nombre"),rs.getInt("fk_lug_codigo"));
                fabricas.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return fabricas;
    }
    
    public static Fabrica buscarPorCodigo(ConectorDB conector, int codigo){
        Fabrica l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT fab_codigo, fab_nombre, fk_lug_codigo FROM fabrica WHERE fab_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Fabrica(rs.getInt("fab_codigo"),rs.getString("fab_nombre"),rs.getInt("fk_lug_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    
    public static Fabrica buscarPorNombre(ConectorDB conector, String nombre){
        Fabrica l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT fab_codigo, fab_nombre, fk_lug_codigo FROM fabrica WHERE fab_nombre=?");
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Fabrica(rs.getInt("fab_codigo"),rs.getString("fab_nombre"),rs.getInt("fk_lug_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    public static void llenarComboBox(ConectorDB conector, JComboBox jCombo){
        PreparedStatement pst;
        try {
            pst = conector.conexion.prepareStatement("SELECT fab_nombre from fabrica");
            ResultSet rs = pst.executeQuery();
            AdaptadorSQLUI.llenarComboBox(jCombo, rs);
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        
    }

    public int getFab_codigo() {
        return fab_codigo;
    }

    public void setFab_codigo(int fab_codigo) {
        this.fab_codigo = fab_codigo;
    }

    public String getFab_nombre() {
        return fab_nombre;
    }

    public void setFab_nombre(String fab_nombre) {
        this.fab_nombre = fab_nombre;
    }

    public int getFk_lug_codigo() {
        return fk_lug_codigo;
    }

    public void setFk_lug_codigo(int fk_lug_codigo) {
        this.fk_lug_codigo = fk_lug_codigo;
    }
}