/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.ConectorDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO rol(rol_codigo,rol_nombre,rol_descripcion) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, rol_codigo);
            pst.setString(2, rol_nombre);
            pst.setString(3, rol_descripcion);
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
