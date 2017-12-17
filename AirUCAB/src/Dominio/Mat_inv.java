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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author LAB_L11
 */
public class Mat_inv {
    
    int mat_inv_codigo;
    int mat_inv_cantidad;
    int fk_mat_codigo;
    int fk_inv_codigo;

    public Mat_inv(int mat_inv_cantidad, int fk_mat_codigo, int fk_inv_codigo) {
        this.mat_inv_cantidad = mat_inv_cantidad;
        this.fk_mat_codigo = fk_mat_codigo;
        this.fk_inv_codigo = fk_inv_codigo;
    }

 

    


    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Mat_inv(mat_inv_cantidad,fk_mat_codigo,fk_inv_codigo) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mat_inv_cantidad);
            pst.setInt(2,fk_mat_codigo);
            pst.setInt(3,fk_inv_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Mat_inv SET mat_inv_cantidad = ?,fk_mat_codigo = ?,fk_inv_codigo=? WHERE mat_inv_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(4, mat_inv_codigo);
            pst.setInt(1, mat_inv_cantidad);
            pst.setInt(2,fk_mat_codigo);
            pst.setInt(3,fk_inv_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Mat_inv where mat_inv_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mat_inv_codigo);
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

    public static List<Mat_inv> obtenerTodos(ConectorDB conector){
        List<Mat_inv> mps = new ArrayList<Mat_inv>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT mat_inv_codigo as codigo,mat_inv_cantidad as Cantidad,fk_mat_codigo as Codigo_Material,fk_inv_codigo as Codigo_Inventario FROM Mat_inv");
            while (rs.next()) {
                Mat_inv l = new Mat_inv(rs.getInt("mat_inv_cantidad"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_inv_codigo"));
                mps.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return mps;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT mat_inv_codigo as Codigo,mat_inv_cantidad as Cantidad,fk_mat_codigo as Codigo_Material,fk_inv_codigo as Codigo_Inventario FROM Mat_inv");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
    
    public static void eliminarDeInventario(ConectorDB conector, int inv_codigo){
        try{
            String stm = "Delete from Mat_inv where fk_inv_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, inv_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
   /*   
public static void llenarTablaMaterialesDeProveedor(ConectorDB conector, JTable jTable, int pro_rif){
         try{
            String stm = "SELECT mat_codigo as codigo, mat_nombre as nombre,mat_pro_precio_actual as Precio,mat_pro_codigo as Codigo_Compra FROM mat_pro, material WHERE fk_mat_codigo=mat_codigo AND fk_pro_rif=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pro_rif);
            ResultSet rs = pst.executeQuery();
            AdaptadorSQLUI.llenarTabla(jTable, rs);
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        };    
}*/

    public static Mat_inv relacionDada(ConectorDB conector, int inv_codigo, int mat_codigo){
       Mat_inv respuesta = null; 
        try{
            String stm = "SELECT Mat_inv_codigo, mat_inv_cantidad, fk_mat_codigo, fk_inv_codigo FROM Mat_inv WHERE fk_mat_codigo=? AND fk_inv_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mat_codigo);
            pst.setInt(2, inv_codigo);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                respuesta = new Mat_inv(rs.getInt("mat_inv_cantidad"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_inv_cantidad"));
            }
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        };
        return respuesta;
    }
    
    public static Mat_inv buscarPorCodigo(ConectorDB conector, int codigo){
       Mat_inv respuesta = null; 
        try{
            String stm = "SELECT Mat_inv_codigo, mat_inv_cantidad, fk_mat_codigo, fk_inv_codigo FROM Mat_inv WHERE mat_inv_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                respuesta = new Mat_inv(rs.getInt("mat_inv_cantidad"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_inv_codigo"));
            }
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        };
        return respuesta;
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

    public int getMat_inv_codigo() {
        return mat_inv_codigo;
    }

    public void setMat_inv_codigo(int mat_inv_codigo) {
        this.mat_inv_codigo = mat_inv_codigo;
    }

    public int getMat_inv_cantidad() {
        return mat_inv_cantidad;
    }

    public void setMat_inv_cantidad(int mat_inv_cantidad) {
        this.mat_inv_cantidad = mat_inv_cantidad;
    }

    public int getFk_mat_codigo() {
        return fk_mat_codigo;
    }

    public void setFk_mat_codigo(int fk_mat_codigo) {
        this.fk_mat_codigo = fk_mat_codigo;
    }

    public int getFk_inv_codigo() {
        return fk_inv_codigo;
    }

    public void setFk_inv_codigo(int fk_inv_codigo) {
        this.fk_inv_codigo = fk_inv_codigo;
    }
    
    
}