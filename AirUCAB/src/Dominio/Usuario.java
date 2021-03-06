/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
import static Dominio.modelo_aeronave.obtenerResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JTable;

/**
 *
 * @author LAB_L11
 */
public class Usuario {
    
    int usu_codigo;
    String usu_nombre;
    String usu_clave;
    int fk_rol_codigo;

    public Usuario(String usu_nombre, String usu_clave, int fk_rol_codigo) {
        this.usu_nombre = usu_nombre;
        this.usu_clave = usu_clave;
        this.fk_rol_codigo = fk_rol_codigo;
    }

    public Usuario(int usu_codigo, String usu_nombre, String usu_clave, int fk_rol_codigo) {
        this.usu_codigo = usu_codigo;
        this.usu_nombre = usu_nombre;
        this.usu_clave = usu_clave;
        this.fk_rol_codigo = fk_rol_codigo;
    }
        
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO usuario(usu_nombre,usu_clave,fk_rol_codigo) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, usu_nombre);
            pst.setString(2, usu_clave);
            pst.setInt(3, fk_rol_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE usuario SET usu_nombre=?,usu_clave=?,fk_rol_codigo=? WHERE usu_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, usu_nombre);
            pst.setString(2, usu_clave);
            pst.setInt(3, fk_rol_codigo);
            pst.setInt(4, usu_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from usuario where usu_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, usu_codigo);
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
    
    public static List<Usuario> obtenerTodos(ConectorDB conector){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT usu_codigo, usu_nombre, fk_rol_codigo FROM usuario");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Usuario l = new Usuario(rs.getInt("usu_codigo"),rs.getString("usu_nombre"),"",rs.getInt("fk_rol_codigo"));
                usuarios.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return usuarios;
    }
    
    public static Usuario buscarPorNombre(ConectorDB conector,String nombre){
        Usuario u = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT usu_codigo, usu_nombre,usu_clave, fk_rol_codigo FROM usuario WHERE usu_nombre='"+nombre+"'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                u = new Usuario(rs.getInt("usu_codigo"),rs.getString("usu_nombre"),rs.getString("usu_clave"),rs.getInt("fk_rol_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return u;
    }
    
    public static Usuario buscarPorCodigo(ConectorDB conector,int codigo){
        Usuario u = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT usu_codigo, usu_nombre,usu_clave, fk_rol_codigo FROM usuario WHERE usu_codigo="+String.valueOf(codigo));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                u = new Usuario(rs.getInt("usu_codigo"),rs.getString("usu_nombre"),rs.getString("usu_clave"),rs.getInt("fk_rol_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return u;
    }
    
    public static Usuario loguearUsuario(ConectorDB conector, String nombre){
        Usuario u = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT usu_codigo, usu_nombre, usu_clave, fk_rol_codigo FROM usuario WHERE usu_nombre=?");
            pst.setString(1,nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                u = new Usuario(rs.getInt("usu_codigo"),rs.getString("usu_nombre"),rs.getString("usu_clave"),rs.getInt("fk_rol_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return u;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT usu_codigo as Codigo,usu_nombre as nombre, rol_nombre as Rol "
                + "FROM usuario, Rol where fk_rol_codigo=rol_codigo "
                + "ORDER BY rol_codigo");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
    
    public static void llenarPrivilegios(ConectorDB conector, ArrayList<String> privilegios, String usuario){
        privilegios.clear();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pri_nombre_clave FROM usuario as u, rol as r, rol_pri as rp, privilegio as p WHERE usu_nombre= ? AND u.fk_rol_codigo = r.rol_codigo AND r.rol_codigo = rp.fk_rol_codigo AND rp.fk_pri_codigo = p.pri_codigo");
            pst.setString(1, usuario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                privilegios.add(rs.getString("pri_nombre_clave"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
    }
    
    public int getUsu_codigo() {
        return usu_codigo;
    }

    public void setUsu_codigo(int usu_codigo) {
        this.usu_codigo = usu_codigo;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getUsu_clave() {
        return usu_clave;
    }

    public void setUsu_clave(String usu_clave) {
        this.usu_clave = usu_clave;
    }

    public int getFk_rol_codigo() {
        return fk_rol_codigo;
    }

    public void setFk_rol_codigo(int fk_rol_codigo) {
        this.fk_rol_codigo = fk_rol_codigo;
    }

    
}
