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
public class mat_pro {
    
    int mat_pro_codigo;
    int mat_pro_precio_actual;
    int fk_mat_codigo;
    int fk_pro_rif;

    public mat_pro(int mat_pro_codigo, int mat_pro_precio_actual, int fk_mat_codigo, int fk_pro_rif) {
        this.mat_pro_codigo = mat_pro_codigo;
        this.mat_pro_precio_actual = mat_pro_precio_actual;
        this.fk_mat_codigo = fk_mat_codigo;
        this.fk_pro_rif = fk_pro_rif;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO mat_pro(mat_pro_codigo,mat_pro_precio_actual,fk_mat_codigo,fk_pro_rif) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mat_pro_codigo);
            pst.setInt(2, mat_pro_precio_actual);
            pst.setInt(5,fk_mat_codigo);
            pst.setInt(6,fk_pro_rif);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE mat_pro SET mat_pro_precio_actual = ?,fk_mat_codigo = ?,fk_pro_rif=? WHERE mat_pro_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(4, mat_pro_codigo);
            pst.setInt(1, mat_pro_precio_actual);
            pst.setInt(2,fk_mat_codigo);
            pst.setInt(3,fk_pro_rif);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from mat_pro where mat_pro_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mat_pro_codigo);
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

    public static List<mat_pro> obtenerTodos(ConectorDB conector){
        List<mat_pro> mps = new ArrayList<mat_pro>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT mat_pro_codigo as codigo,mat_pro_precio_actual as precio,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Proveedor FROM mat_pro");
            while (rs.next()) {
                mat_pro l = new mat_pro(rs.getInt("mat_pro_codigo"),rs.getInt("mat_pro_precio_actual"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_pro_rif"));
                mps.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return mps;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT mat_pro_codigo as Codigo,mat_pro_precio_actual as Precio,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Proveedor FROM mat_pro");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    /*
    public static Mat_pro buscarPorCodigo(ConectorDB conector, int codigo){
        Mat_pro l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mat_pro_codigo as codigo,mat_pro_precio_actual as precio,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Cliente FROM mat_pro WHERE mat_pro_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Mat_pro(rs.getInt("mat_pro_codigo"),rs.getInt("mat_pro_precio_actual"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_pro_rif");
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    */

    public void setMat_pro_codigo(int mat_pro_codigo) {
        this.mat_pro_codigo = mat_pro_codigo;
    }

    public void setMat_pro_precio_actual(int mat_pro_precio_actual) {
        this.mat_pro_precio_actual = mat_pro_precio_actual;
    }

    public void setFk_mat_codigo(int fk_mat_codigo) {
        this.fk_mat_codigo = fk_mat_codigo;
    }

    public void setFk_pro_rif(int fk_pro_rif) {
        this.fk_pro_rif = fk_pro_rif;
    }

    public int getMat_pro_codigo() {
        return mat_pro_codigo;
    }

    public int getMat_pro_precio_actual() {
        return mat_pro_precio_actual;
    }

    public int getFk_mat_codigo() {
        return fk_mat_codigo;
    }

    public int getFk_pro_rif() {
        return fk_pro_rif;
    }

}

