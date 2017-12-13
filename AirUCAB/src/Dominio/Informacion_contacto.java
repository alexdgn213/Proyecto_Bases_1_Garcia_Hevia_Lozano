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
public class Informacion_contacto {
    
    int inf_codigo;
    String inf_valor;
    String inf_tipo;
    int fk_pro_rif;
    int fk_per_ci;
    int fk_cli_rif;
    int fk_ben_ci;

    public Informacion_contacto(int inf_codigo, String inf_valor, String inf_tipo, int fk_pro_rif, int fk_per_ci, int fk_cli_rif, int fk_ben_ci) {
        this.inf_codigo = inf_codigo;
        this.inf_valor = inf_valor;
        this.inf_tipo = inf_tipo;
        this.fk_pro_rif = fk_pro_rif;
        this.fk_per_ci = fk_per_ci;
        this.fk_cli_rif = fk_cli_rif;
        this.fk_ben_ci = fk_ben_ci;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Informacion_contacto(inf_codigo,inf_valor,inf_tipo,fk_pro_rif,fk_per_ci,fk_cli_rif,fk_ben_ci) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, inf_codigo);
            pst.setString(2, inf_valor);
            pst.setString(3,inf_tipo);
            pst.setInt(4,fk_pro_rif);
            pst.setInt(5,fk_per_ci);
            pst.setInt(6,fk_cli_rif);
            pst.setInt(7,fk_ben_ci);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Informacion_contacto SET inf_valor = ?, inf_tipo = ?, fk_pro_rif = ?,fk_per_ci = ?, fk_cli_rif = ?, fk_ben_ci = ? WHERE inf_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(7, inf_codigo);
            pst.setString(1, inf_valor);
            pst.setString(2,inf_tipo);
            pst.setInt(3,fk_pro_rif);
            pst.setInt(4,fk_per_ci);
            pst.setInt(4,fk_cli_rif);
            pst.setInt(4,fk_ben_ci); 
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Informacion_contacto where inf_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, inf_codigo);
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
    
    public static List<Informacion_contacto> obtenerTodos(ConectorDB conector){
        List<Informacion_contacto> contactos = new ArrayList<Informacion_contacto>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT inf_codigo,inf_valor,inf_tipo,fk_pro_rif,fk_per_ci,fk_cli_rif,fk_ben_ci FROM Informacion_contacto");
            while (rs.next()) {
                Informacion_contacto c = new Informacion_contacto(rs.getInt("inf_codigo"),rs.getString("inf_valor"),rs.getString("inf_tipo"),rs.getInt("fk_pro_rif"),rs.getInt("fk_per_ci"),rs.getInt("fk_cli_rif"),rs.getInt("fk_ben_ci"));
                contactos.add(c);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return contactos;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT inf_codigo as Codigo,inf_valor as Valor,inf_tipo as Tipo,fk_pro_rif as Rif_Proveedor,fk_per_ci as CI_personal,fk_cli_rif as Rif_Cliente,fk_ben_ci as CI_Beneficiario FROM Informacion_contacto");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static Informacion_contacto buscarPorCodigo(ConectorDB conector, int codigo){
        Informacion_contacto c = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT inf_codigo,inf_valor,inf_tipo,fk_pro_rif,fk_per_ci,fk_cli_rif,fk_ben_ci FROM Informacion_contacto WHERE inf_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c = new Informacion_contacto(rs.getInt("inf_codigo"),rs.getString("inf_valor"),rs.getString("inf_tipo"),rs.getInt("fk_pro_rif"),rs.getInt("fk_per_ci"),rs.getInt("fk_cli_rif"),rs.getInt("fk_ben_ci"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return c;
    }

    public void setInf_codigo(int inf_codigo) {
        this.inf_codigo = inf_codigo;
    }

    public void setInf_valor(String inf_valor) {
        this.inf_valor = inf_valor;
    }

    public void setInf_tipo(String inf_tipo) {
        this.inf_tipo = inf_tipo;
    }

    public void setFk_pro_rif(int fk_pro_rif) {
        this.fk_pro_rif = fk_pro_rif;
    }

    public void setFk_per_ci(int fk_per_ci) {
        this.fk_per_ci = fk_per_ci;
    }

    public void setFk_cli_rif(int fk_cli_rif) {
        this.fk_cli_rif = fk_cli_rif;
    }

    public void setFk_ben_ci(int fk_ben_ci) {
        this.fk_ben_ci = fk_ben_ci;
    }

    public int getInf_codigo() {
        return inf_codigo;
    }

    public String getInf_valor() {
        return inf_valor;
    }

    public String getInf_tipo() {
        return inf_tipo;
    }

    public int getFk_pro_rif() {
        return fk_pro_rif;
    }

    public int getFk_per_ci() {
        return fk_per_ci;
    }

    public int getFk_cli_rif() {
        return fk_cli_rif;
    }

    public int getFk_ben_ci() {
        return fk_ben_ci;
    }
    
    
}
