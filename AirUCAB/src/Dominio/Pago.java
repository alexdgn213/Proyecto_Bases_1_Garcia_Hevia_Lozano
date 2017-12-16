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
 * @author Veronica Hevia
 */
public class Pago {
  
    int pag_codigo;
    int fk_for_codigo;
    int fk_mat_pro_codigo;
    int fk_aer_codigo;
    int fk_mat_codigo;
    int fk_pro_rif;
    int fk_cli_rif;

    public Pago(int pag_codigo, int fk_for_codigo, int fk_mat_pro_codigo,int fk_aer_codigo, int fk_mat_codigo, int fk_pro_rif,int fk_cli_rif) {
        this.pag_codigo = pag_codigo;
        this.fk_for_codigo = fk_for_codigo;
        this.fk_mat_pro_codigo = fk_mat_pro_codigo;
        this.fk_aer_codigo = fk_aer_codigo;
        this.fk_mat_codigo = fk_mat_codigo;
        this.fk_pro_rif = fk_pro_rif;
        this.fk_cli_rif = fk_cli_rif;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO pago(pag_codigo,fk_for_codigo,fk_mat_pro_codigo,fk_aer_codigo,fk_mat_codigo,fk_pro_rif,fk_cli_rif) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pag_codigo); //CODIGO SERIAL
            pst.setInt(2, fk_for_codigo);
            pst.setInt(3,fk_mat_pro_codigo);
            pst.setInt(4,fk_aer_codigo);
            pst.setInt(5,fk_mat_codigo);
            pst.setInt(6,fk_pro_rif);
            pst.setInt(7, fk_cli_rif);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE pago SET fk_for_codigo = ?, fk_mat_pro_codigo = ?,fk_aer_codigo = ?,fk_mat_codigo = ?,fk_pro_rif=?, fk_cli_rif=? WHERE pag_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(7, pag_codigo);
            pst.setInt(1, fk_for_codigo);
            pst.setInt(2,fk_mat_pro_codigo);
            pst.setInt(3,fk_aer_codigo);
            pst.setInt(4,fk_mat_codigo);
            pst.setInt(5,fk_pro_rif);
            pst.setInt(6, fk_cli_rif);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from pago where pag_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pag_codigo);
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
    
    public static List<Pago> obtenerTodos(ConectorDB conector){
        List<Pago> pagos = new ArrayList<Pago>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT pag_codigo as codigo,fk_for_codigo as forma_de_pago,fk_mat_pro_codigo as Codigo_compra_msteriales,fk_aer_codigo as Codigo_aeronave,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Proveedor, fk_cli_rif as Rif_cliente FROM pago");
            while (rs.next()) {
                Pago p = new Pago(rs.getInt("pag_codigo"),rs.getInt("fk_for_codigo"),rs.getInt("fk_mat_pro_codigo"),rs.getInt("fk_aer_codigo"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_pro_rif"),rs.getInt("fk_cli_rif"));
                pagos.add(p);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return pagos;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT pag_codigo as codigo,fk_for_codigo as forma_de_pago,fk_mat_pro_codigo as Codigo_compra_msteriales,fk_aer_codigo as Codigo_aeronave,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Proveedor, fk_cli_rif as Rif_cliente FROM pago");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    /*
    public static Pago buscarPorCodigo(ConectorDB conector, int codigo){
        Pago l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT pag_codigo as codigo,fk_for_codigo as forma_de_pago,fk_mat_pro_codigo as Codigo_compra_msteriales,fk_aer_codigo as Codigo_aeronave,fk_mat_codigo as Codigo_Material,fk_pro_rif as Rif_Proveedor, fk_cli_rif as Rif_cliente FROM pago WHERE pag_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Pago(rs.getInt("pag_codigo"),rs.getInt("fk_for_codigo"),rs.getInt("fk_mat_pro_codigo"),rs.getInt("fk_aer_codigo"),rs.getInt("fk_mat_codigo"),rs.getInt("fk_pro_rif"),rs.getInt(fk_cli_rif));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    */

    public void setPag_codigo(int pag_codigo) {
        this.pag_codigo = pag_codigo;
    }

    public void setFk_for_codigo(int fk_for_codigo) {
        this.fk_for_codigo = fk_for_codigo;
    }

    public void setFk_mat_pro_codigo(int fk_mat_pro_codigo) {
        this.fk_mat_pro_codigo = fk_mat_pro_codigo;
    }

    public void setFk_aer_codigo(int fk_aer_codigo) {
        this.fk_aer_codigo = fk_aer_codigo;
    }

    public void setFk_mat_codigo(int fk_mat_codigo) {
        this.fk_mat_codigo = fk_mat_codigo;
    }

    public void setFk_pro_rif(int fk_pro_rif) {
        this.fk_pro_rif = fk_pro_rif;
    }

    public void setFk_cli_rif(int fk_cli_rif) {
        this.fk_cli_rif = fk_cli_rif;
    }

    public int getPag_codigo() {
        return pag_codigo;
    }

    public int getFk_for_codigo() {
        return fk_for_codigo;
    }

    public int getFk_mat_pro_codigo() {
        return fk_mat_pro_codigo;
    }

    public int getFk_aer_codigo() {
        return fk_aer_codigo;
    }

    public int getFk_mat_codigo() {
        return fk_mat_codigo;
    }

    public int getFk_pro_rif() {
        return fk_pro_rif;
    }

    public int getFk_cli_rif() {
        return fk_cli_rif;
    }
    
}
