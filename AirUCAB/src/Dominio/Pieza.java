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
public class Pieza {
    
    int pie_codigo;
    Date pie_fecha_estimado;
    Date pie_fecha_entregado;

    public Pieza(int pie_codigo, Date pie_fecha_estimado, Date pie_fecha_entregado) {
        this.pie_codigo = pie_codigo;
        this.pie_fecha_estimado = pie_fecha_estimado;
        this.pie_fecha_entregado = pie_fecha_entregado;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Pieza(pie_codigo,pie_fecha_estimado,pie_fecha_entregado) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pie_codigo);
            pst.setDate(2, pie_fecha_estimado);
            pst.setDate(3,pie_fecha_entregado);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Pieza SET pie_fecha_estimado = ?,pie_fecha_entregado = ? WHERE pie_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(3, pie_codigo);
            pst.setDate(2,pie_fecha_entregado);
            pst.setDate(1,pie_fecha_estimado);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Pieza where pie_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pie_codigo);
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
    
    public static List<Pieza> obtenerTodos(ConectorDB conector){
        List<Pieza> piezas = new ArrayList<Pieza>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT pie_codigo,pie_fecha_estimado,pie_fecha_entregado FROM pieza");
            while (rs.next()) {
                Pieza p = new Pieza(rs.getInt("pie_codigo"),rs.getDate("pie_fecha_estimado"),rs.getDate("pie_fecha_entregado"));
                piezas.add(p);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return piezas;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT pie_codigo as Codigo,pie_fecha_estimado as Fecha_Estimada,pie_fecha_entregado as Fecha_de_Entrega FROM pieza");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }

    public int getPie_codigo() {
        return pie_codigo;
    }

    public void setPie_codigo(int pie_codigo) {
        this.pie_codigo = pie_codigo;
    }

    public Date getPie_fecha_estimado() {
        return pie_fecha_estimado;
    }

    public void setPie_fecha_estimado(Date pie_fecha_estimado) {
        this.pie_fecha_estimado = pie_fecha_estimado;
    }

    public Date getPie_fecha_entregado() {
        return pie_fecha_entregado;
    }

    public void setPie_fecha_entregado(Date pie_fecha_entregado) {
        this.pie_fecha_entregado = pie_fecha_entregado;
    }
}