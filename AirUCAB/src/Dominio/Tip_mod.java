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
public class Tip_mod {
    
    int tip_mod_codigo;
    int tip_mod_cantidad;
    int fk_mod_codigo;
    int fk_tip_codigo;

    public Tip_mod(int tip_mod_codigo, int tip_mod_cantidad,int fk_mod_codigo, int fk_tip_codigo) {
        this.tip_mod_codigo = tip_mod_codigo;
        this.tip_mod_cantidad = tip_mod_cantidad;
        this.fk_mod_codigo=fk_mod_codigo;
        this.fk_tip_codigo = fk_tip_codigo;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO tip_mod(tip_mod_codigo,tip_mod_cantidad,fk_mod_codigo,fk_tip_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, tip_mod_codigo);
            pst.setInt(2, tip_mod_cantidad);
            pst.setInt(3,fk_mod_codigo);
            pst.setInt(4,fk_tip_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE tip_mod SET tip_mod_cantidad = ?,fk_mod_codigo = ?,fk_tip_codigo=? WHERE tip_mod_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(4, tip_mod_codigo);
            pst.setInt(1, tip_mod_cantidad);
            pst.setInt(2,fk_mod_codigo);
            pst.setInt(3,fk_tip_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from tip_mod where tip_mod_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, tip_mod_codigo);
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

    public static List<Tip_mod> obtenerTodos(ConectorDB conector){
        List<Tip_mod> tm = new ArrayList<Tip_mod>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT tip_mod_codigo as codigo,tip_mod_cantidad as cantidad,fk_mod_codigo as Codigo_Modelo_Aeronave,fk_tip_codigo as Codigo_Tipo_Pieza FROM tip_mod");
            while (rs.next()) {
                Tip_mod l = new Tip_mod(rs.getInt("tip_mod_codigo"),rs.getInt("tip_mod_cantidad"),rs.getInt("fk_mod_codigo"),rs.getInt("fk_tip_codigo"));
                tm.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return tm;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT tip_mod_codigo as Codigo,tip_mod_cantidad as Cantidad,fk_mod_codigo as Codigo_Modelo_Aeronave,fk_tip_codigo as Codigo_Tipo_Pieza FROM tip_mod");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
    
    public static void llenarTablaDeModelo(ConectorDB conector, JTable jTable, int id){
        ResultSet rs =obtenerResultSet(conector,"SELECT tm.tip_mod_codigo as Codigo, t.tip_nombre as Pieza,tm.tip_mod_cantidad as Cantidad  FROM tip_mod tm, tipo_pieza t where tm.fk_tip_codigo=t.tip_codigo AND fk_mod_codigo="+String.valueOf(id));
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
