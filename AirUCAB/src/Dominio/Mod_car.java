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
public class Mod_car {
    
    int mod_car_codigo;
    int mod_car_valor;
    String mod_car_descripcion;
    int fk_mod_codigo;
    int fk_car_codigo;

    public Mod_car(int mod_car_codigo, int mod_car_valor,String mod_car_descripcion, int fk_mod_codigo, int fk_car_codigo) {
        this.mod_car_codigo = mod_car_codigo;
        this.mod_car_valor = mod_car_valor;
        this.mod_car_descripcion=mod_car_descripcion;
        this.fk_mod_codigo = fk_mod_codigo;
        this.fk_car_codigo = fk_car_codigo;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO mod_car(mod_car_codigo,mod_car_valor,mod_car_descripcion,fk_mod_codigo,fk_car_codigo) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mod_car_codigo);
            pst.setInt(2, mod_car_valor);
            pst.setString(3,mod_car_descripcion);
            pst.setInt(4,fk_mod_codigo);
            pst.setInt(5,fk_car_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE mod_car SET mod_car_valor = ?,mod_car_descripcion = ?,fk_mod_codigo = ?,fk_car_codigo=? WHERE mod_car_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(5, mod_car_codigo);
            pst.setInt(1, mod_car_valor);
            pst.setString(2,mod_car_descripcion);
            pst.setInt(3,fk_car_codigo);
            pst.setInt(4,fk_mod_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from mod_car where mod_car_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mod_car_codigo);
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

    public static List<Mod_car> obtenerTodos(ConectorDB conector){
        List<Mod_car> mc = new ArrayList<Mod_car>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT mod_car_codigo as codigo,mod_car_valor as valor,mod_car_descripcion,fk_mod_codigo as Codigo_Modelo_Aeronave,fk_car_codigo as Codigo_Caracteristica FROM mod_car");
            while (rs.next()) {
                Mod_car l = new Mod_car(rs.getInt("mod_car_codigo"),rs.getInt("mod_car_valor"),rs.getString("mod_car_descripcion"),rs.getInt("fk_mod_codigo"),rs.getInt("fk_car_codigo"));
                mc.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return mc;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT mod_car_codigo as Codigo,mod_car_valor as Valor,mod_car_descripcion,fk_mod_codigo as Codigo_Modelo_Aeronave,fk_car_codigo as Codigo_Caracteristica FROM mod_car");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
  
}
    /*
    public static Mod_car buscarPorCodigo(ConectorDB conector, int codigo){
        Mod_car l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mod_car_codigo as codigo,mod_car_valor as valor,mod_car_descripcion as descripcion,fk_mod_codigo as Codigo_Modelo_Aeronave,fk_car_codigo as Codigo_Caracteristica FROM mod_car WHERE mod_car_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Mod_car(rs.getInt("mod_car_codigo"),rs.getInt("mod_car_valor"),rs.getString("mod_car_descripcion"),rs.getInt("fk_mod_codigo"),rs.getInt("fk_car_codigo");
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    */