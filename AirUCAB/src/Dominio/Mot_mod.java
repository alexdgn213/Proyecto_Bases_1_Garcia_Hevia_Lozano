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
import java.time.LocalDate;
import javax.swing.JTable;

/**
 *
 * @author LAB_L11
 */
public class Mot_mod{
    
    int mot_mod_codigo;
    int mot_mod_cantidad;
    int fk_mot_codigo;
    int fk_mod_codigo;

    public Mot_mod(int mot_mod_cantidad,int fk_mot_codigo,int fk_mod_codigo) {
        this.mot_mod_cantidad = mot_mod_cantidad;
        this.fk_mot_codigo = fk_mot_codigo;
        this.fk_mod_codigo = fk_mod_codigo;
    }

    public Mot_mod(int mot_mod_codigo,int mot_mod_cantidad,int fk_mot_codigo,int fk_mod_codigo) {
        this.mot_mod_codigo = mot_mod_codigo;
        this.mot_mod_cantidad = mot_mod_cantidad;
        this.fk_mot_codigo = fk_mot_codigo;
        this.fk_mod_codigo = fk_mod_codigo;
    }
    
    



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Mot_mod(mot_mod_cantidad,fk_mot_codigo,fk_mod_codigo) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mot_mod_cantidad);
            pst.setInt(2, fk_mot_codigo);
            pst.setInt(3, fk_mod_codigo);
            pst.execute();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Mot_mod SET mot_mod_cantidad=?,fk_mot_codigo=?,fk_mod_codigo=? WHERE mot_mod_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(4, mot_mod_codigo);
            pst.setInt(1, mot_mod_cantidad);
            pst.setInt(2, fk_mot_codigo);
            pst.setInt(3, fk_mod_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from mot_mod where mot_mod_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mot_mod_codigo);
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
    
    public static List<Mot_mod> obtenerTodos(ConectorDB conector){
        List<Mot_mod> mot_mods = new ArrayList<Mot_mod>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT mot_mod_codigo,mot_mod_cantidad, fk_mot_codigo,fk_mod_codigo FROM mot_mod");
            while (rs.next()) {
                Mot_mod mm = new Mot_mod(rs.getInt("mot_mod_codigo"),rs.getInt("mot_mod_cantidad"),rs.getInt("fk_mot_codigo"),rs.getInt("fk_mod_codigo"));
                mot_mods.add(mm);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return mot_mods;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT mot_mod_codigo as Codigo,mot_mod_cantidad as Cantidad,fk_mot_codigo as Codigo_Motor,fk_mod_codigo as Codigo_Modelo_Aeronave FROM mot_mod");
        AdaptadorSQLUI.llenarTabla(jTable, rs); 
    }
    
    public static void llenarTablaDeModelo(ConectorDB conector, JTable jTable, int id){
        ResultSet rs =obtenerResultSet(conector,"SELECT mot_mod_codigo as Codigo,mot_marca ||' '||  mot_modelo as Motor ,mot_mod_cantidad as Cantidad FROM mot_mod, motor where fk_mot_codigo=mot_codigo AND fk_mod_codigo="+String.valueOf(id));
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
    
    public static Mot_mod buscarPorCodigo(ConectorDB conector, int codigo){
        Mot_mod mm = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mot_mod_codigo,mot_mod_cantidad, fk_mot_codigo, fk_mod_codigo FROM mot_mod WHERE mot_mod_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                mm = new Mot_mod(rs.getInt("mot_mod_codigo"),rs.getInt("mot_mod_cantidad"),rs.getInt("fk_mot_codigo"),rs.getInt("fk_mod_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return mm;
    }
    
    public static Mot_mod relacionDada(ConectorDB conector, int mot_codigo, int mod_codigo){
       Mot_mod respuesta = null; 
        try{
            String stm = "SELECT mot_mod_codigo, mot_mod_cantidad, fk_mot_codigo, fk_mod_codigo FROM mot_mod "
                    + "WHERE fk_mot_codigo=? AND fk_mod_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mot_codigo);
            pst.setInt(2, mod_codigo);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                respuesta = new Mot_mod(rs.getInt("mot_mod_codigo"),rs.getInt("mot_mod_cantidad"),rs.getInt("fk_mot_codigo"),rs.getInt("fk_mod_codigo"));
            }
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        };
        return respuesta;
    }
    
    /*
     public static Mot_mod buscarPorCodigo(ConectorDB conector, int codigo){
        Mot_mod mm = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mot_mod_codigo, mot_mod_cantidad,fk_mot_codigo,fk_mod_codigo FROM mot_mod WHERE mot_mod_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                mm = new Mot_mod(rs.getInt("mot_mod_codigo"),rs.getInt("mot_mod_cantidad"),rs.getInt("fk_mot_codigo"),rs.getInt("fk_mod_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return mm;
    }*/

    public int getMot_mod_codigo() {
        return mot_mod_codigo;
    }

    public void setMot_mod_codigo(int mot_mod_codigo) {
        this.mot_mod_codigo = mot_mod_codigo;
    }

    public int getMot_mod_cantidad() {
        return mot_mod_cantidad;
    }

    public void setMot_mod_cantidad(int mot_mod_cantidad) {
        this.mot_mod_cantidad = mot_mod_cantidad;
    }

    public int getFk_mot_codigo() {
        return fk_mot_codigo;
    }

    public void setFk_mot_codigo(int fk_mot_codigo) {
        this.fk_mot_codigo = fk_mot_codigo;
    }

    public int getFk_mod_codigo() {
        return fk_mod_codigo;
    }

    public void setFk_mod_codigo(int fk_mod_codigo) {
        this.fk_mod_codigo = fk_mod_codigo;
    }

}