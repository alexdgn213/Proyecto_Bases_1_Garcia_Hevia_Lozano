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
public class Mat_pro {
    
    int mat_pro_codigo;
    int mat_pro_precio;
    Date mat_pro_fecha_compra;
    int mat_pro_cantidad;
    int fk_mat_codigo;
    int fk_pro_rif;

    public Mat_pro(int mat_pro_codigo, int mat_pro_precio, Date mat_pro_fecha_compra,int mat_pro_cantidad, int fk_mat_codigo, int fk_pro_rif) {
        this.mat_pro_codigo = mat_pro_codigo;
        this.mat_pro_precio = mat_pro_precio;
        this.mat_pro_fecha_compra = mat_pro_fecha_compra;
        this.mat_pro_cantidad=mat_pro_cantidad;
        this.fk_mat_codigo = fk_mat_codigo;
        this.fk_pro_rif = fk_pro_rif;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Mat_pro(mat_pro_codigo,mat_pro_precio,mat_pro_fecha_compra,mat_pro_cantidad,fk_mat_codigo,fk_pro_rif) VALUES(?,?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, mat_pro_codigo);
            pst.setInt(2, mat_pro_precio);
            pst.setDate(3,mat_pro_fecha_compra);
            pst.setInt(4,mat_pro_cantidad);
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
            String stm = "UPDATE Mat_pro SET mat_pro_precio = ?, mat_pro_fecha_compra = ?,mat_pro_cantidad = ?,fk_mat_codigo = ?,fk_pro_rif=? WHERE mat_pro_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(6, mat_pro_codigo);
            pst.setInt(1, mat_pro_precio);
            pst.setDate(2,mat_pro_fecha_compra);
            pst.setInt(3,mat_pro_cantidad);
            pst.setInt(4,fk_mat_codigo);
            pst.setInt(5,fk_pro_rif);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Mat_pro where mat_pro_codigo=?";
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
    
    public static List<Mat_pro> obtenerTodos(ConectorDB conector){
        List<Mat_pro> compras = new ArrayList<Mat_pro>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT mat_pro_codigo as codigo,mat_pro_precio as precio,mat_pro_fecha_compra as Fecha_Compra,mat_pro_cantidad as Cantidad,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Proveedor FROM mat_pro");
            while (rs.next()) {
                Mat_pro mp = new Mat_pro(rs.getInt("mat_pro_codigo"),rs.getInt("mat_pro_precio"),rs.getDate("mat_pro_fecha_compra"),rs.getInt("mat_pro_cantidad"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_pro_rif"));
                compras.add(mp);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return compras;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT mat_pro_codigo as Codigo,mat_pro_precio as Precio,mat_pro_fecha_compra as Fecha_Compra,mat_pro_cantidad as Cantidad,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Proveedor FROM mat_pro");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    /*
    public static Mat_pro buscarPorCodigo(ConectorDB conector, int codigo){
        Mat_pro l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT mat_pro_codigo as codigo,mat_pro_precio as precio,mat_pro_fecha_compra as Fecha_Compra,mat_pro_cantidad as Cantidad,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Cliente FROM mat_pro WHERE mat_pro_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Mat_pro(rs.getInt("mat_pro_codigo"),rs.getInt("mat_pro_precio"),rs.getDate("mat_pro_fecha_compra"),rs.getInt("mat_pro_cantidad"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_pro_rif");
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

    public void setMat_pro_precio(int mat_pro_precio) {
        this.mat_pro_precio = mat_pro_precio;
    }

    public void setMat_pro_fecha_compra(Date mat_pro_fecha_compra) {
        this.mat_pro_fecha_compra = mat_pro_fecha_compra;
    }

    public void setMat_pro_cantidad(int mat_pro_cantidad) {
        this.mat_pro_cantidad = mat_pro_cantidad;
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

    public int getMat_pro_precio() {
        return mat_pro_precio;
    }

    public Date getMat_pro_fecha_compra() {
        return mat_pro_fecha_compra;
    }

    public int getMat_pro_cantidad() {
        return mat_pro_cantidad;
    }

    public int getFk_mat_codigo() {
        return fk_mat_codigo;
    }

    public int getFk_pro_rif() {
        return fk_pro_rif;
    }
}

