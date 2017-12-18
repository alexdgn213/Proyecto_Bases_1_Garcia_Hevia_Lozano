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
public class Pru_mot {
    
    int pru_mot_codigo;
    int fk_pru_codigo;
    int fk_mot_codigo;
    

    public Pru_mot(int pru_mot_codigo,int fk_pru_codigo,int fk_mot_codigo) {
        this.pru_mot_codigo = pru_mot_codigo;
        this.fk_pru_codigo = fk_pru_codigo;
        this.fk_mot_codigo = fk_mot_codigo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Pru_mot(fk_pru_codigo,fk_mot_codigo) VALUES(?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, fk_pru_codigo);
            pst.setInt(2,fk_mot_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Pru_mot SET fk_pru_codigo = ?,fk_mot_codigo=? WHERE pru_mot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(3, pru_mot_codigo);
            pst.setInt(1, fk_pru_codigo);
            pst.setInt(2,fk_mot_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Pru_mot where pru_mot_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pru_mot_codigo);
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

    public static List<Pru_mot> obtenerTodos(ConectorDB conector){
        List<Pru_mot> pm = new ArrayList<Pru_mot>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT pru_mot_codigo as codigo,fk_pru_codigo as Codigo_Prueba,fk_mot_codigo as Codigo_Motor FROM Pru_mot");
            while (rs.next()) {
                Pru_mot l = new Pru_mot(rs.getInt("pru_mot_codigo"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_mot_codigo"));
                pm.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pm;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT pru_mot_codigo as Codigo,fk_pru_codigo as Codigo_Prueba,fk_mot_codigo as Codigo_Motor FROM Pru_mot");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
   
    
    public static Pru_mot buscarPorCodigo(ConectorDB conector, int codigo){
        Pru_mot tp = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pru_mot_codigo, fk_pru_codigo,fk_mot_codigo FROM pru_mot WHERE pru_mot_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tp = new Pru_mot(rs.getInt("pru_mot_codigo"),rs.getInt("fk_pru_codigo"),rs.getInt("fk_mot_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return tp;
    }
}