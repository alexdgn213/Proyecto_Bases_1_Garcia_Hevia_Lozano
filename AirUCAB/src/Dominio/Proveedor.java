/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.AdaptadorTabla;
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
public class Proveedor {
    
    int pro_rif;
    String pro_nombre;
    int pro_monto_acreditado;
    Date pro_fecha_inicio;
    int fk_lug_codigo;

    public Proveedor(int pro_rif, String pro_nombre, int pro_monto_acreditado, Date pro_fecha_inicio, int fk_lug_codigo) {
        this.pro_rif = pro_rif;
        this.pro_nombre = pro_nombre;
        this.pro_monto_acreditado = pro_monto_acreditado;
        this.pro_fecha_inicio = pro_fecha_inicio;
        this.fk_lug_codigo = fk_lug_codigo;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Proveedor(pro_rif,pro_nombre,pro_monto_acreditado,pro_fecha_inicio,fk_lug_codigo) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pro_rif);
            pst.setString(2, pro_nombre);
            pst.setInt(2,pro_monto_acreditado);
            pst.setDate(4,pro_fecha_inicio);
            pst.setInt(5,fk_lug_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Proveedor SET pro_nombre = ?, pro_monto_acreditado = ?,pro_fecha_inicio = ?,fk_lug_codigo = ? WHERE pro_rif=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(5, pro_rif);
            pst.setString(1, pro_nombre);
            pst.setInt(2,pro_monto_acreditado);
            pst.setDate(3,pro_fecha_inicio);
            pst.setInt(4,fk_lug_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Proveedor where pro_rif=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pro_rif);
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
    
    public static List<Proveedor> obtenerTodos(ConectorDB conector){
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT pro_rif,pro_nombre,pro_monto_acreditado,pro_fecha_inicio,fk_lug_codigo FROM proveedor");
            while (rs.next()) {
                Proveedor p = new Proveedor(rs.getInt("pro_rif"),rs.getString("pro_nombre"),rs.getInt("pro_monto_acreditado"),rs.getDate("pro_fecha_inicio"),rs.getInt("fk_lug_codigo"));
                proveedores.add(p);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return proveedores;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT pro_rif as Rif,pro_nombre as nombre,pro_monto_acreditado as Monto_Acreditado,pro_fecha_inicio as Inicio_de_operaciones FROM proveedor");
        AdaptadorTabla.llenarTabla(jTable, rs);
        
    }
    /*
    public static Proveedor buscarPorCodigo(ConectorDB conector, int codigo){
        Proveedor l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pro_rif,pro_nombre,pro_monto_acreditado,pro_fecha_inicio,fk_lug_codigo FROM proveedor WHERE pro_rif=?");
            pst.setInt(1, rif);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Proveedor(rs.getInt("pro_rif"),rs.getString("pro_nombre"),rs.getInt("pro_monto_acreditado"),rs.getDate("pro_fecha_inicio"),rs.getInt("fk_lug_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    */

    public int getPro_rif() {
        return pro_rif;
    }

    public String getPro_nombre() {
        return pro_nombre;
    }

    public int getPro_monto_acreditado() {
        return pro_monto_acreditado;
    }

    public Date getPro_fecha_inicio() {
        return pro_fecha_inicio;
    }

    public void setPro_rif(int pro_rif) {
        this.pro_rif = pro_rif;
    }

    public void setPro_nombre(String pro_nombre) {
        this.pro_nombre = pro_nombre;
    }

    public void setPro_monto_acreditado(int pro_monto_acreditado) {
        this.pro_monto_acreditado = pro_monto_acreditado;
    }

    public void setPro_fecha_inicio(Date pro_fecha_inicio) {
        this.pro_fecha_inicio = pro_fecha_inicio;
    }
    
    public int getFk_lug_codigo() {
        return fk_lug_codigo;
    }

    public void setFk_lug_codigo(int fk_lug_codigo) {
        this.fk_lug_codigo = fk_lug_codigo;
    }
    
}
