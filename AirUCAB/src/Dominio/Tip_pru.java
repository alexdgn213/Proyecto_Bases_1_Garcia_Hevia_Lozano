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
public class Tip_pru {
    
    int tip_pru_codigo;
    int fk_tip_codigo;
    int fk_pru_codigo;
    

    public Tip_pru(int tip_pru_codigo,int fk_tip_codigo,int fk_pru_codigo) {
        this.tip_pru_codigo = tip_pru_codigo;
        this.fk_tip_codigo = fk_tip_codigo;
        this.fk_pru_codigo = fk_pru_codigo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Tip_pru(fk_tip_codigo,fk_pru_codigo) VALUES(?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, fk_tip_codigo);
            pst.setInt(2,fk_pru_codigo);
            pst.executeUpdate();
            
            
            
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Tip_pru SET fk_tip_codigo = ?,fk_pru_codigo=? WHERE tip_pru_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(3, tip_pru_codigo);
            pst.setInt(1, fk_tip_codigo);
            pst.setInt(2,fk_pru_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Tip_pru where tip_pru_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, tip_pru_codigo);
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

    public static List<Tip_pru> obtenerTodos(ConectorDB conector){
        List<Tip_pru> pls = new ArrayList<Tip_pru>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT tip_pru_codigo as codigo,fk_tip_codigo as Codigo_Tipo_Pieza,fk_pru_codigo as Codigo_Prueba, FROM Tip_pru");
            while (rs.next()) {
                Tip_pru l = new Tip_pru(rs.getInt("tip_pru_codigo"),rs.getInt("fk_tip_codigo"),rs.getInt("fk_pru_codigo"));
                pls.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pls;
    }
    
    public static List<Tip_pru> obtenerTodasPruebasPieza(ConectorDB conector,int fk_tip_codigo){
        List<Tip_pru> pls = new ArrayList<Tip_pru>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT tip_pru_codigo as codigo,fk_tip_codigo as Codigo_Tipo_Pieza,fk_pru_codigo as Codigo_Prueba" +
"                    FROM Tip_pru  " +
"                     Where fk_tip_codigo=?");
            pst.setInt(1, fk_tip_codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Tip_pru l = new Tip_pru(rs.getInt("tip_pru_codigo"),rs.getInt("fk_tip_codigo"),rs.getInt("fk_pru_codigo"));
                pls.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pls;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT tip_pru_codigo as Codigo,fk_tip_codigo as Codigo_Tipo_Pieza,fk_pru_codigo as Codigo_Prueba FROM Tip_pru");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
   
    
    public static Tip_pru buscarPorCodigo(ConectorDB conector, int codigo){
        Tip_pru tp = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT tip_pru_codigo, fk_tip_codigo,fk_pru_codigo FROM tip_pru WHERE tip_pru_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tp = new Tip_pru(rs.getInt("tip_pru_codigo"),rs.getInt("fk_tip_codigo"),rs.getInt("fk_pru_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return tp;
    }

    public int getTip_pru_codigo() {
        return tip_pru_codigo;
    }

    public void setTip_pru_codigo(int tip_pru_codigo) {
        this.tip_pru_codigo = tip_pru_codigo;
    }

    public int getFk_tip_codigo() {
        return fk_tip_codigo;
    }

    public void setFk_tip_codigo(int fk_tip_codigo) {
        this.fk_tip_codigo = fk_tip_codigo;
    }

    public int getFk_pru_codigo() {
        return fk_pru_codigo;
    }

    public void setFk_pru_codigo(int fk_pru_codigo) {
        this.fk_pru_codigo = fk_pru_codigo;
    }

}