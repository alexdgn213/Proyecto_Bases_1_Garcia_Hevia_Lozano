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
import javax.swing.JTable;

/**
 *
 * @author Veronica Hevia
 */
public class Tipo_pieza {
    
    int tip_codigo;
    String tip_nombre;
    int tip_tiempo_fabricacion;
    
    public Tipo_pieza(int tip_codigo, String tip_nombre, int tip_tiempo_fabricacion) {
        this.tip_codigo = tip_codigo;
        this.tip_nombre = tip_nombre;
        this.tip_tiempo_fabricacion = tip_tiempo_fabricacion;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Tipo_pieza(tip_codigo,tip_nombre,tip_tiempo_fabricacion) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, tip_codigo);
            pst.setString(2, tip_nombre);
            pst.setInt(3, tip_tiempo_fabricacion);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Tipo_pieza SET tip_nombre = ?, tip_tiempo_fabricacion = ? WHERE tip_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(3, tip_codigo);
            pst.setString(1, tip_nombre);
            pst.setInt(2, tip_tiempo_fabricacion);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Tipo_pieza where tip_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, tip_codigo);
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
    
    public static List<Tipo_pieza> obtenerTodos(ConectorDB conector){
        List<Tipo_pieza> tipo_piezas = new ArrayList<Tipo_pieza>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT tip_codigo,tip_nombre, tip_tiempo_fabricacion FROM Tipo_pieza");
            while (rs.next()) {
                Tipo_pieza tp = new Tipo_pieza(rs.getInt("tip_codigo"),rs.getString("tip_nombre"),rs.getInt("tip_tiempo_fabricacion"));
                tipo_piezas.add(tp);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return tipo_piezas;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT tip_codigo as Codigo,tip_nombre as Nombre_Tipo_Pieza, tip_tiempo_fabricacion as Tiempo_fabricacion FROM Tipo_pieza");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }

    public int getTip_codigo() {
        return tip_codigo;
    }

    public void setTip_codigo(int tip_codigo) {
        this.tip_codigo = tip_codigo;
    }

    public String getTip_nombre() {
        return tip_nombre;
    }

    public void setTip_nombre(String tip_nombre) {
        this.tip_nombre = tip_nombre;
    }

    public int getTip_tiempo_fabricacion() {
        return tip_tiempo_fabricacion;
    }

    public void setTip_tiempo_fabricacion(int tip_tiempo_fabricacion) {
        this.tip_tiempo_fabricacion = tip_tiempo_fabricacion;
    }
    /*
    public static Motor buscarPorCodigo(ConectorDB conector, int codigo){
        Proveedor l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mat_nombre, mat_tiempo_estido FROM Material WHERE mat_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Motor(rs.getInt("mat_codigo"),rs.getString("mat_nombre"),rs.getInt("mat_tiempo_estimado"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    */
}