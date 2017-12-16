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
public class Ensamblaje{
    
    int ens_codigo;
    String ens_descripcion;
    int fk_zon_codigo;
    int fk_fab_codigo;
    int fk_pie_codigo;
    int fk_tip_codigo;
    int fk_mot_codigo;
    int fk_est_codigo;

    public Ensamblaje(String ens_descripcion,int fk_zon_codigo,int fk_fab_codigo, int fk_pie_codigo, int fk_tip_codigo, int fk_mot_codigo,int fk_est_codigo) {
        this.ens_descripcion = ens_descripcion;
        this.fk_zon_codigo = fk_zon_codigo;
        this.fk_fab_codigo = fk_fab_codigo;
	      this.fk_pie_codigo = fk_pie_codigo;
        this.fk_tip_codigo = fk_tip_codigo;
        this.fk_mot_codigo = fk_mot_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }

    public Ensamblaje(int ens_codigo, String ens_descripcion,int fk_zon_codigo,int fk_fab_codigo, int fk_pie_codigo, int fk_tip_codigo, int fk_mot_codigo,int fk_est_codigo) {
        this.ens_codigo = ens_codigo;
        this.ens_descripcion = ens_descripcion;
        this.fk_zon_codigo = fk_zon_codigo;
        this.fk_fab_codigo = fk_fab_codigo;
	      this.fk_pie_codigo = fk_pie_codigo;
        this.fk_tip_codigo = fk_tip_codigo;
        this.fk_mot_codigo = fk_mot_codigo;
        this.fk_est_codigo = fk_est_codigo;
    }
    
    



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Ensamblaje(ens_descripcion,fk_zon_codigo,fk_fab_codigo,fk_pie_codigo,fk_tip_codigo,fk_mot_codigo,fk_est_codigo) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, ens_descripcion);
            pst.setInt(2, fk_zon_codigo);
            pst.setInt(3, fk_fab_codigo);
            pst.setInt(4, fk_pie_codigo);
            pst.setInt(5, fk_tip_codigo);
            pst.setInt(6, fk_mot_codigo);
            pst.setInt(7, fk_est_codigo);
            stm = "SELECT TOP 1 ens_codigo FROM ensamblaje WHERE ens_descripcion=? AND fk_zon_codigo=? AND fk_fab_codigo=? AND fk_pie_codigo=? AND fk_tip_codigo=? AND fk_mot_codigo=? AND fk_est_codigo=?";
            pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, ens_descripcion);
            pst.setInt(2, fk_zon_codigo);
            pst.setInt(3, fk_fab_codigo);
            pst.setInt(4, fk_pie_codigo);
            pst.setInt(5, fk_tip_codigo);
            pst.setInt(6, fk_mot_codigo);
            pst.setInt(7, fk_est_codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.ens_codigo = rs.getInt("ens_codigo");
            }
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Ensamblaje SET ens_descripcion = ?,fk_zon_codigo=?, fk_fab_codigo=?, fk_pie_codigo=?, fk_tip_codigo=?, fk_mot_codigo=?, fk_est_codigo=? WHERE ens_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(8, ens_codigo);
            pst.setString(1, ens_descripcion);
            pst.setInt(2, fk_zon_codigo);
            pst.setInt(3, fk_fab_codigo);
            pst.setInt(4, fk_pie_codigo);
            pst.setInt(5, fk_tip_codigo);
            pst.setInt(6, fk_mot_codigo);
            pst.setInt(7, fk_est_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from ensamblaje where ens_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, ens_codigo);
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
    
    public static List<Ensamblaje> obtenerTodos(ConectorDB conector){
        List<Ensamblaje> ensamblajes = new ArrayList<Ensamblaje>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT ens_codigo,ens_descripcion,fk_zon_codigo, fk_fab_codigo, fk_pie_codigo, fk_tip_codigo, fk_mot_codigo, fk_est_codigo FROM ensamblaje");
            while (rs.next()) {
                Ensamblaje e = new Ensamblaje(rs.getInt("ens_codigo"),rs.getString("ens_descripcion"),rs.getInt("fk_zon_codigo"),rs.getInt("fk_fab_codigo"),rs.getInt("fk_pie_codigo"),rs.getInt("fk_tip_codigo"),rs.getInt("fk_mot_codigo"),rs.getInt("fk_est_codigo"));
                ensamblajes.add(e);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return ensamblajes;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT ens_codigo as Codigo,ens_descripcion as Descripcion,fk_zon_codigo as Codigo_Zona,fk_fab_codigo as Codigo_Fabrica,fk_pie_codigo as Codigo_Pieza,fk_tip_codigo as Codigo_Tipo_Pieza,fk_mot_codigo as Codigo_Motor,fk_est_codigo as Codigo_Estatus FROM ensamblaje");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static Ensamblaje buscarPorCodigo(ConectorDB conector, int codigo){
        Ensamblaje e = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT ens_codigo,ens_descripcion,fk_zon_codigo, fk_fab_codigo, fk_pie_codigo, fk_tip_codigo, fk_mot_codigo, fk_est_codigo FROM ensamblaje WHERE ens_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                e = new Ensamblaje(rs.getInt("ens_codigo"),rs.getString("ens_descripcion"),rs.getInt("fk_zon_codigo"),rs.getInt("fk_fab_codigo"),rs.getInt("fk_pie_codigo"),rs.getInt("fk_tip_codigo"),rs.getInt("fk_mot_codigo"),rs.getInt("fk_est_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return e;
    }

}