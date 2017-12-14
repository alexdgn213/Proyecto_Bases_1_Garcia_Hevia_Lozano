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
public class Factura{
    
    int fac_codigo;
    int fac_monto_total;
    Date fac_fecha;

    public Factura(int fac_monto_total) {
        this.fac_monto_total = fac_monto_total;
        this.fac_fecha = Date.valueOf(LocalDate.now());
    }

    public Factura(int fac_codigo, int fac_monto_total, Date fac_fecha) {
        this.fac_codigo = fac_codigo;
        this.fac_monto_total = fac_monto_total;
        this.fac_fecha = fac_fecha;
    }
    
    



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Factura(fac_monto_total,fac_fecha) VALUES(?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, fac_monto_total);
            pst.setDate(2, fac_fecha);
            stm = "SELECT TOP 1 fac_codigo FROM factura WHERE fac_monto_total=? AND fac_fecha=?";
            pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, fac_monto_total);
            pst.setDate(2, fac_fecha);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.fac_codigo = rs.getInt("fac_codigo");
            }
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Factura SET fac_monto_total = ? WHERE fac_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(2, fac_codigo);
            pst.setInt(1, fac_monto_total);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from factura where fac_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, fac_codigo);
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
    
    public static List<Factura> obtenerTodos(ConectorDB conector){
        List<Factura> facturas = new ArrayList<Factura>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT fac_codigo,fac_monto_total, fac_fecha FROM factura");
            while (rs.next()) {
                Factura f = new Factura(rs.getInt("fac_codigo"),rs.getInt("fac_monto_total"),rs.getDate("fac_fecha"));
                facturas.add(f);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return facturas;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT fac_codigo as Codigo,fac_monto_total as Monto_Total FROM factura");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static Factura buscarPorCodigo(ConectorDB conector, int codigo){
        Factura f = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT fac_codigo,fac_monto_total, fac_fecha FROM factura WHERE fac_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                f = new Factura(rs.getInt("fac_codigo"),rs.getInt("fac_monto_total"),rs.getDate("fac_fecha"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return f;
    }

    public int getFac_codigo() {
        return fac_codigo;
    }

    public void setFac_codigo(int fac_codigo) {
        this.fac_codigo = fac_codigo;
    }

    public int getFac_monto_total() {
        return fac_monto_total;
    }

    public void setFac_monto_total(int fac_monto_total) {
        this.fac_monto_total = fac_monto_total;
    }
}