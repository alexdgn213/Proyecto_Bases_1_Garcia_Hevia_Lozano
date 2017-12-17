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
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author LAB_L11
 */
public class Caracteristica {
    
    int car_codigo;
    int car_valor;

    public Caracteristica(int car_codigo,int car_valor) {
        this.car_codigo = car_codigo;
        this.car_valor = car_valor;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Caracteristica(car_codigo,car_valor) VALUES(?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, car_codigo);
            pst.setInt(2, car_valor);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Caracteristica SET car_valor = ? WHERE car_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(2, car_codigo);
            pst.setInt(1, car_valor);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from caracteristica where car_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, car_codigo);
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
    
    public static List<Caracteristica> obtenerTodos(ConectorDB conector){
        List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT car_codigo,car_valor FROM caracteristica");
            while (rs.next()) {
                Caracteristica c = new Caracteristica(rs.getInt("car_codigo"),rs.getInt("car_valor"));
                caracteristicas.add(c);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return caracteristicas;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT car_codigo as Codigo,car_valor as Valor FROM caracteristica");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static Caracteristica buscarPorCodigo(ConectorDB conector, int codigo){
        Caracteristica c = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT car_codigo,car_valor FROM caracteristica WHERE car_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c = new Caracteristica(rs.getInt("car_codigo"),rs.getInt("car_valor"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return c;
    }
    
    public static void llenarComboBox(ConectorDB conector, JComboBox jCombo){
        PreparedStatement pst;
        try {
            pst = conector.conexion.prepareStatement("SELECT car_nombre from caracteristica");
            ResultSet rs = pst.executeQuery();
            AdaptadorSQLUI.llenarComboBox(jCombo, rs);
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        
    }

    public void setCar_codigo(int car_codigo) {
        this.car_codigo = car_codigo;
    }

    public void setCar_valor(int car_valor) {
        this.car_valor = car_valor;
    }

    public int getCar_codigo() {
        return car_codigo;
    }

    public int getCar_valor() {
        return car_valor;
    }
}    