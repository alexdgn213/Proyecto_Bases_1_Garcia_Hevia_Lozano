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
public class Privilegio {
    int pri_codigo;
    String pri_accion;
    String pri_descripcion;
    String pri_nombre_clave;
    
    public Privilegio (int pri_codigo, String pri_accion, String pri_descripcion, String pri_nombre_clave){
        this.pri_codigo = pri_codigo;
        this.pri_accion = pri_accion;
        this.pri_descripcion = pri_descripcion;
        this.pri_nombre_clave = pri_nombre_clave;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO privilegio(pri_codigo,pri_accion,pri_descripcion,pri_nombre_clave) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pri_codigo);
            pst.setString(2, pri_accion);
            pst.setString(3, pri_descripcion);
            pst.setString(3, pri_nombre_clave);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE privilegio SET pri_accion=?,pri_descripcion=?,pri_nombre_clave=? WHERE pri_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, pri_accion);
            pst.setString(2, pri_descripcion);
            pst.setString(3, pri_nombre_clave);
            pst.setInt(4, pri_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from privilegio where pri_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pri_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public static List<Privilegio> obtenerTodos(ConectorDB conector){
        List<Privilegio> privilegios = new ArrayList<Privilegio>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pri_codigo,pri_accion,pri_descripcion,pri_nombre_clave FROM privilegio");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Privilegio l = new Privilegio(rs.getInt("pri_codigo"),rs.getString("pri_accion"),rs.getString("pri_descripcion"),rs.getString("pri_nombre_clave"));
                privilegios.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return privilegios;
    }

    public int getPri_codigo() {
        return pri_codigo;
    }

    public void setPri_codigo(int pri_codigo) {
        this.pri_codigo = pri_codigo;
    }

    public String getPri_accion() {
        return pri_accion;
    }

    public void setPri_accion(String pri_accion) {
        this.pri_accion = pri_accion;
    }

    public String getPri_descripcion() {
        return pri_descripcion;
    }

    public void setPri_descripcion(String pri_descripcion) {
        this.pri_descripcion = pri_descripcion;
    }

    public String getPri_nombre_clave() {
        return pri_nombre_clave;
    }

    public void setPri_nombre_clave(String pri_nombre_clave) {
        this.pri_nombre_clave = pri_nombre_clave;
    }
        
}
