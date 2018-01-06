/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
import static Dominio.Usuario.obtenerResultSet;
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
public class Rol {
    int rol_codigo;
    String rol_nombre;
    String rol_descripcion;
    
    public Rol (int rol_codigo, String rol_nombre, String rol_descripcion){
        this.rol_codigo = rol_codigo;
        this.rol_descripcion = rol_descripcion;
        this.rol_nombre = rol_nombre;
    }

    public Rol(String rol_nombre, String rol_descripcion) {
        this.rol_nombre = rol_nombre;
        this.rol_descripcion = rol_descripcion;
    }
    
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO rol(rol_nombre,rol_descripcion) VALUES(?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, rol_nombre);
            pst.setString(2, rol_descripcion);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void agregarPrivilegio(ConectorDB conector, int codigoPrivilegio){
        try{
            String stm = "INSERT INTO rol_pri(fk_rol_codigo,fk_pri_codigo) VALUES(?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, rol_codigo);
            pst.setInt(2, codigoPrivilegio);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void quitarPrivilegio(ConectorDB conector, int codigoPrivilegio){
        try{
            String stm = "DELETE FROM rol_pri WHERE fk_rol_codigo=? AND fk_pri_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, rol_codigo);
            pst.setInt(2, codigoPrivilegio);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE rol SET rol_nombre=?,rol_descripcion=? WHERE rol_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, rol_nombre);
            pst.setString(2, rol_descripcion);
            pst.setInt(3, rol_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from rol where rol_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, rol_codigo);
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
    
    public static List<Rol> obtenerTodos(ConectorDB conector){
        List<Rol> roles = new ArrayList<Rol>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT rol_codigo, rol_nombre, rol_descripcion FROM rol");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Rol l = new Rol(rs.getInt("rol_codigo"),rs.getString("rol_nombre"),rs.getString("rol_descripcion"));
                roles.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return roles;
    }
    
    public static Rol buscarPorCodigo(ConectorDB conector, int codigo){
        Rol r = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT rol_codigo, rol_nombre, rol_descripcion FROM rol WHERE rol_codigo =?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                r = new Rol(rs.getInt("rol_codigo"),rs.getString("rol_nombre"),rs.getString("rol_descripcion"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return r;
    }
    
    public static int obtenerCodigoPorNombre(ConectorDB conector, String nombre){
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT rol_codigo FROM rol WHERE rol_nombre =?");
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getInt("rol_codigo");
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return 0;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT rol_codigo as Codigo,rol_nombre as nombre, rol_descripcion as Descripcion FROM rol");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
    
    public static void llenarComboBox(ConectorDB conector, JComboBox jCombo){
        PreparedStatement pst;
        try {
            pst = conector.conexion.prepareStatement("SELECT rol_nombre from rol");
            ResultSet rs = pst.executeQuery();
            AdaptadorSQLUI.llenarComboBox(jCombo, rs);
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        
    }

    public int getRol_codigo() {
        return rol_codigo;
    }

    public void setRol_codigo(int rol_codigo) {
        this.rol_codigo = rol_codigo;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }

    public String getRol_descripcion() {
        return rol_descripcion;
    }

    public void setRol_descripcion(String rol_descripcion) {
        this.rol_descripcion = rol_descripcion;
    }
        
}
