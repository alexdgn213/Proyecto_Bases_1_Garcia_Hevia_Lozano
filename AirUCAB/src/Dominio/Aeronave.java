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
public class Aeronave {
    
    int aer_codigo;
    int fk_cli_rif;
    Date aer_fecha_compra;
    int fk_mod_codigo;

    public Aeronave(int aer_codigo, int fk_cli_rif, Date aer_fecha_compra, int fk_mod_codigo) {
        this.aer_codigo = aer_codigo;
        this.fk_cli_rif = fk_cli_rif;
        this.aer_fecha_compra = aer_fecha_compra;
        this.fk_mod_codigo = fk_mod_codigo;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Aeronave(aer_codigo,fk_cli_rif,aer_fecha_compra,fk_mod_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, aer_codigo);
            pst.setInt(2, fk_cli_rif);
            pst.setDate(4,aer_fecha_compra);
            pst.setInt(5,fk_mod_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Aeronave SET fk_cli_rif = ?,aer_fecha_compra = ?,fk_mod_codigo = ? WHERE aer_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(4, aer_codigo);
            pst.setDate(2,aer_fecha_compra);
            pst.setInt(3,fk_mod_codigo);
            pst.setInt(1,fk_cli_rif);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Aeronave where aer_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, aer_codigo);
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
    
    public static List<Aeronave> obtenerTodos(ConectorDB conector){
        List<Aeronave> aeronaves = new ArrayList<Aeronave>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT aer_codigo,fk_cli_rif,aer_fecha_compra,fk_mod_codigo FROM aeronave");
            while (rs.next()) {
                Aeronave a = new Aeronave(rs.getInt("aer_codigo"),rs.getInt("fk_cli_rif"),rs.getDate("aer_fecha_compra"),rs.getInt("fk_mod_codigo"));
                aeronaves.add(a);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return aeronaves;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT aer_codigo as Codigo,mod_nombre as Modelo, cli_nombre as Cliente,aer_fecha_compra as Fecha_de_compra FROM aeronave,cliente,modelo_aeronave "
                + " WHERE fk_cli_rif=cli_rif AND fk_mod_codigo=mod_codigo");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }
    
    public static void llenarTablaDeFactura(ConectorDB conector, JTable jTable, int id){
        ResultSet rs =obtenerResultSet(conector,"SELECT a.aer_codigo as Codigo ,m.mod_nombre as Modelo,a.aer_precio_compra as Precio "
                + " FROM aeronave a, modelo_aeronave m"
                + " WHERE a.fk_mod_codigo=m.mod_codigo"
                + " AND fk_fac_codigo="+String.valueOf(id));
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }

    /*
    public static Aeronave buscarPorCodigo(ConectorDB conector, int codigo){
        Aeronave l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT aer_codigo,fk_cli_rif,aer_fecha_compra,fk_mod_codigo FROM aeronave WHERE aer_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Aeronave(rs.getInt("aer_codigo"),rs.getInt("fk_cli_rif"),rs.getDate("aer_fecha_compra"),rs.getInt("fk_mod_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    */

    public void setAer_codigo(int aer_codigo) {
        this.aer_codigo = aer_codigo;
    }

    public void setFk_cli_rif(int fk_cli_rif) {
        this.fk_cli_rif = fk_cli_rif;
    }

    public void setAer_fecha_compra(Date aer_fecha_compra) {
        this.aer_fecha_compra = aer_fecha_compra;
    }

    public void setFk_mod_codigo(int fk_mod_codigo) {
        this.fk_mod_codigo = fk_mod_codigo;
    }

    public int getAer_codigo() {
        return aer_codigo;
    }

    public int getFk_cli_rif() {
        return fk_cli_rif;
    }

    public Date getAer_fecha_compra() {
        return aer_fecha_compra;
    }

    public int getFk_mod_codigo() {
        return fk_mod_codigo;
    }
}
    
