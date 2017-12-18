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
    double mod_car_valor;
    String mod_car_descripcion;
    int fk_mod_codigo;
    int fk_car_codigo;

    public Mod_car(int mod_car_codigo, double mod_car_valor,String mod_car_descripcion, int fk_mod_codigo, int fk_car_codigo) {
        this.mod_car_codigo = mod_car_codigo;
        this.mod_car_valor = mod_car_valor;
        this.mod_car_descripcion=mod_car_descripcion;
        this.fk_mod_codigo = fk_mod_codigo;
        this.fk_car_codigo = fk_car_codigo;
    }

    public Mod_car(double mod_car_valor, String mod_car_descripcion, int fk_mod_codigo, int fk_car_codigo) {
        this.mod_car_valor = mod_car_valor;
        this.mod_car_descripcion = mod_car_descripcion;
        this.fk_mod_codigo = fk_mod_codigo;
        this.fk_car_codigo = fk_car_codigo;
    }
    
    



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO mod_car(mod_car_valor,mod_car_descripcion,fk_mod_codigo,fk_car_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setDouble(1, mod_car_valor);
            pst.setString(2,mod_car_descripcion);
            pst.setInt(3,fk_mod_codigo);
            pst.setInt(4,fk_car_codigo);
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
            pst.setDouble(1, mod_car_valor);
            pst.setString(2,mod_car_descripcion);
            pst.setInt(4,fk_car_codigo);
            pst.setInt(3,fk_mod_codigo);
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
    
    public static void llenarTablaDeModelo(ConectorDB conector, JTable jTable, int id){
        ResultSet rs =obtenerResultSet(conector,"SELECT mod_car_codigo as Codigo,car_nombre as Caracteristica ,mod_car_valor as Valor,mod_car_descripcion as Descripcion FROM mod_car, caracteristica where fk_car_codigo=car_codigo AND fk_mod_codigo="+String.valueOf(id));
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
    
    public static Mod_car buscarPorCodigo(ConectorDB conector, int codigo){
        Mod_car mc = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mod_car_codigo,mod_car_valor, mod_car_descripcion, fk_mod_codigo, fk_car_codigo FROM mod_car WHERE mod_car_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                mc = new Mod_car(rs.getInt("mod_car_codigo"),rs.getDouble("mod_car_valor"),rs.getString("mod_car_descripcion"),rs.getInt("fk_mod_codigo"),rs.getInt("fk_car_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return mc;
    }
     
    public static Mod_car relacionDada(ConectorDB conector, int mod_codigo, int car_codigo){
       Mod_car respuesta = null; 
        try{
            String stm = "SELECT mod_car_codigo, mod_car_valor, mod_car_descripcion, fk_mod_codigo, fk_car_codigo FROM mod_car "
                    + "WHERE fk_mod_codigo=? AND fk_car_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mod_codigo);
            pst.setInt(2, car_codigo);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                respuesta = new Mod_car(rs.getInt("mod_car_codigo"), rs.getDouble("mod_car_valor"),rs.getString("mod_car_descripcion"),rs.getInt("fk_mod_codigo") , rs.getInt("fk_car_codigo") );
            };
        }catch (SQLException ex){
           System.out.print(ex.toString());
        };
        return respuesta;
    }

    public int getMod_car_codigo() {
        return mod_car_codigo;
    }

    public void setMod_car_codigo(int mod_car_codigo) {
        this.mod_car_codigo = mod_car_codigo;
    }

    public double getMod_car_valor() {
        return mod_car_valor;
    }

    public void setMod_car_valor(double mod_car_valor) {
        this.mod_car_valor = mod_car_valor;
    }

    public String getMod_car_descripcion() {
        return mod_car_descripcion;
    }

    public void setMod_car_descripcion(String mod_car_descripcion) {
        this.mod_car_descripcion = mod_car_descripcion;
    }

    public int getFk_mod_codigo() {
        return fk_mod_codigo;
    }

    public void setFk_mod_codigo(int fk_mod_codigo) {
        this.fk_mod_codigo = fk_mod_codigo;
    }

    public int getFk_car_codigo() {
        return fk_car_codigo;
    }

    public void setFk_car_codigo(int fk_car_codigo) {
        this.fk_car_codigo = fk_car_codigo;
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

