/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
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
public class Inventario {
    
    int inv_codigo;
    String inv_descripcion;
    int fk_fab_codigo;

    public Inventario(int inv_codigo, String inv_descripcion, int fk_fab_codigo) {
        this.inv_codigo = inv_codigo;
        this.inv_descripcion = inv_descripcion;
        this.fk_fab_codigo = fk_fab_codigo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Inventario(inv_codigo,inv_descripcion,fk_fab_codigo) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, inv_codigo);
            pst.setString(2, inv_descripcion);
            pst.setInt(3,fk_fab_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Inventario SET inv_descripcion=?,fk_fab_codigo=? WHERE inv_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, inv_descripcion);
            pst.setInt(2,fk_fab_codigo);
            pst.setInt(3, inv_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Inventario where inv_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, inv_codigo);
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
    
    public static List<Inventario> obtenerTodos(ConectorDB conector){
        List<Inventario> inventarios = new ArrayList<Inventario>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT inv_codigo, inv_descripcion, fk_fab_codigo FROM inventario");
            ResultSet rs = obtenerResultSet(conector, "SELECT inv_codigo, inv_descripcion, fk_fab_codigo FROM inventario");
            while (rs.next()) {
                Inventario l = new Inventario(rs.getInt("inv_codigo"),rs.getString("inv_descripcion"),rs.getInt("fk_fab_codigo"));
                inventarios.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return inventarios;
    }
    
    public static Inventario buscarPorCodigo(ConectorDB conector, int codigo){
        Inventario l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT inv_codigo, inv_descripcion, fk_fab_codigo FROM inventario WHERE inv_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Inventario(rs.getInt("inv_codigo"),rs.getString("inv_descripcion"),rs.getInt("fk_fab_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    
    public static Inventario buscarPorFabrica(ConectorDB conector, int fk_fab_codigo){
        Inventario l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT inv_codigo, inv_descripcion, fk_fab_codigo FROM inventario WHERE fk_fab_codigo=?");
            pst.setInt(1, fk_fab_codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Inventario(rs.getInt("inv_codigo"),rs.getString("inv_descripcion"),rs.getInt("fk_fab_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    
     public static Mat_inv buscarPorFabricaYMaterial(ConectorDB conector, int fk_fab_codigo,int fk_mat_codigo){
        Mat_inv l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT inv_codigo, inv_descripcion, fk_fab_codigo FROM inventario i, mat_inv mi WHERE i.fk_fab_codigo=? AND mi.fk_mat_codigo=?");
            pst.setInt(1, fk_fab_codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Mat_inv(rs.getInt("fk_mat_inv_cantidad"),rs.getInt("mi.fk_mat_codigo"),rs.getInt("i.fk_inv_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    
     public static void llenarTablaDeMaterialesPorFabrica(ConectorDB conector, JTable jTable, String nombre){
        ResultSet rs =obtenerResultSet(conector,"SELECT mat_codigo as Codigo_Material ,mat_nombre as Material,mat_inv_cantidad as Cantidad FROM inventario, material,mat_inv,fabrica where fk_inv_codigo=inv_codigo AND fk_mat_codigo=mat_codigo AND fk_fab_codigo=fab_codigo AND fab_nombre='"+nombre+"'");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
     
    }