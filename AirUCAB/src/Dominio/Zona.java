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
public class Zona {
    
        int zon_codigo;
	String zon_nombre;
	String zon_descripcion;
	int fk_fab_codigo;

    public Zona(int zon_codigo, String zon_nombre, String zon_descripcion, int fk_fab_codigo) {
        this.zon_codigo = zon_codigo;
        this.zon_nombre = zon_nombre;
        this.zon_descripcion=zon_descripcion;
        this.fk_fab_codigo = fk_fab_codigo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Zona(zon_codigo,zon_nombre,zon_descripcion,fk_fab_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, zon_codigo);
            pst.setString(2, zon_nombre);
            pst.setString(3, zon_descripcion);
            pst.setInt(4,fk_fab_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Zona SET zon_nombre=?,zon_descripcion=?,fk_fab_codigo=? WHERE zon_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, zon_nombre);
            pst.setString(2, zon_descripcion);
            pst.setInt(3,fk_fab_codigo);
            pst.setInt(4, zon_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Zona where zon_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, zon_codigo);
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
    
    public static List<Zona> obtenerTodos(ConectorDB conector){
        List<Zona> zonas = new ArrayList<Zona>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT zon_codigo, zon_nombre,zon_descripcion, fk_fab_codigo FROM zona");
            ResultSet rs = obtenerResultSet(conector, "SELECT zon_codigo, zon_nombre,zon_descripcion, fk_fab_codigo FROM zona");
            while (rs.next()) {
                Zona l = new Zona(rs.getInt("zon_codigo"),rs.getString("zon_nombre"),rs.getString("zon_descripcion"),rs.getInt("fk_fab_codigo"));
                zonas.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return zonas;
    }
    
    public static Zona buscarPorCodigo(ConectorDB conector, int codigo){
        Zona l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT zon_codigo, zon_nombre,zon_descripcion, fk_fab_codigo FROM zona WHERE zon_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Zona(rs.getInt("zon_codigo"),rs.getString("zon_nombre"),rs.getString("zon_descripcion"),rs.getInt("fk_fab_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    
    public static Zona buscarPorNombre(ConectorDB conector, String nombre){
        Zona l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT zon_codigo, zon_nombre,zon_descripcion, fk_fab_codigo FROM zona WHERE zon_nombre=?");
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Zona(rs.getInt("zon_codigo"),rs.getString("zon_nombre"),rs.getString("zon_descripcion"),rs.getInt("fk_fab_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    public static void llenarComboBox(ConectorDB conector, JComboBox jCombo,int id){
        PreparedStatement pst;
        try {
            pst = conector.conexion.prepareStatement("SELECT zon_nombre from zona,fabrica where fk_fab_codigo=fab_codigo AND fab_nombre="+String.valueOf(id));
            ResultSet rs = pst.executeQuery();
            AdaptadorSQLUI.llenarComboBox(jCombo, rs);
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        
    }

    public int getZon_codigo() {
        return zon_codigo;
    }

    public void setZon_codigo(int zon_codigo) {
        this.zon_codigo = zon_codigo;
    }

    public String getZon_nombre() {
        return zon_nombre;
    }

    public void setZon_nombre(String zon_nombre) {
        this.zon_nombre = zon_nombre;
    }

    public String getZon_descripcion() {
        return zon_descripcion;
    }

    public void setZon_descripcion(String zon_descripcion) {
        this.zon_descripcion = zon_descripcion;
    }

    public int getFk_fab_codigo() {
        return fk_fab_codigo;
    }

    public void setFk_fab_codigo(int fk_fab_codigo) {
        this.fk_fab_codigo = fk_fab_codigo;
    }
}