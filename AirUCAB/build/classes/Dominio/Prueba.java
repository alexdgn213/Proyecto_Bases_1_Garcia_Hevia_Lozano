/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
import java.sql.Date;
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
public class Prueba {
    
    int pru_codigo;
    String pru_nombre;
    String pru_descripcion;

    public Prueba(int pru_codigo, String pru_nombre, String pru_descripcion) {
        this.pru_codigo = pru_codigo;
        this.pru_nombre=pru_nombre;
        this.pru_descripcion=pru_descripcion;
    }

    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Prueba(pru_nombre,pru_descripcion) VALUES(?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pru_nombre);
            pst.setDate(2,pru_descripcion);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Prueba SET pru_nombre = ?,pru_descripcion=? WHERE pru_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(3, pru_codigo);
            pst.setInt(1, pru_nombre);
            pst.setDate(2,pru_descripcion);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Lote_material where pru_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pru_codigo);
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
    
    public static List<Prueba> obtenerTodos(ConectorDB conector){
        List<Prueba> pruebas = new ArrayList<Prueba>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT pru_codigo as codigo,pru_nombre as nombre,pru_descripcion FROM prueba");
            while (rs.next()) {
                Prueba p = new Lote_material(rs.getInt("pru_codigo"),rs.getString("pru_nombre"),rs.getString("pru_descripcion"));
                pruebas.add(p);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return lotes;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT pru_codigo as Codigo,pru_nombre as Nombre,pru_descripcion as Descripcion FROM prueba");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
