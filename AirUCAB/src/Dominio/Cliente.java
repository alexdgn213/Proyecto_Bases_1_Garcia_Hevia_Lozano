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
public class Cliente {
    
    int cli_rif;
    String cli_nombre;
    int cli_monto_acreditado;
    Date cli_fecha_inicio;
    int fk_lug_codigo;

    public Cliente(int cli_rif, String cli_nombre, int cli_monto_acreditado, Date cli_fecha_inicio, int fk_lug_codigo) {
        this.cli_rif = cli_rif;
        this.cli_nombre = cli_nombre;
        this.cli_monto_acreditado = cli_monto_acreditado;
        this.cli_fecha_inicio = cli_fecha_inicio;
        this.fk_lug_codigo = fk_lug_codigo;
    }



    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Cliente(cli_rif,cli_nombre,cli_monto_acreditado,cli_fecha_inicio,fk_lug_codigo) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, cli_rif);
            pst.setString(2, cli_nombre);
            pst.setInt(2,cli_monto_acreditado);
            pst.setDate(4,cli_fecha_inicio);
            pst.setInt(5,fk_lug_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Cliente SET cli_nombre = ?, cli_monto_acreditado = ?,cli_fecha_inicio = ?,fk_lug_codigo = ? WHERE cli_rif=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(5, cli_rif);
            pst.setString(1, cli_nombre);
            pst.setInt(2,cli_monto_acreditado);
            pst.setDate(3,cli_fecha_inicio);
            pst.setInt(4,fk_lug_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Cliente where cli_rif=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, cli_rif);
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
    
    public static List<Cliente> obtenerTodos(ConectorDB conector){
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT cli_rif,cli_nombre,cli_monto_acreditado,cli_fecha_inicio,fk_lug_codigo FROM cliente");
            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt("cli_rif"),rs.getString("cli_nombre"),rs.getInt("cli_monto_acreditado"),rs.getDate("cli_fecha_inicio"),rs.getInt("fk_lug_codigo"));
                clientes.add(c);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return clientes;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT cli_rif as Rif,cli_nombre as nombre,cli_monto_acreditado as Monto_Acreditado,cli_fecha_inicio as Inicio_de_operaciones FROM cliente");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
        
    }
    
    public static Cliente buscarPorCodigo(ConectorDB conector, int rif){
        Cliente c = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT cli_rif,cli_nombre,cli_monto_acreditado,cli_fecha_inicio,fk_lug_codigo FROM cliente WHERE cli_rif=?");
            pst.setInt(1, rif);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c = new Cliente(rs.getInt("cli_rif"),rs.getString("cli_nombre"),rs.getInt("cli_monto_acreditado"),rs.getDate("cli_fecha_inicio"),rs.getInt("fk_lug_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return c;
    }
    
    public int getCli_rif() {
        return cli_rif;
    }

    public void setCli_rif(int cli_rif) {
        this.cli_rif = cli_rif;
    }

    public String getCli_nombre() {
        return cli_nombre;
    }

    public void setCli_nombre(String cli_nombre) {
        this.cli_nombre = cli_nombre;
    }

    public int getCli_monto_acreditado() {
        return cli_monto_acreditado;
    }

    public void setCli_monto_acreditado(int cli_monto_acreditado) {
        this.cli_monto_acreditado = cli_monto_acreditado;
    }

    public Date getCli_fecha_inicio() {
        return cli_fecha_inicio;
    }

    public void setCli_fecha_inicio(Date cli_fecha_inicio) {
        this.cli_fecha_inicio = cli_fecha_inicio;
    }

    public int getFk_lug_codigo() {
        return fk_lug_codigo;
    }

    public void setFk_lug_codigo(int fk_lug_codigo) {
        this.fk_lug_codigo = fk_lug_codigo;
    }
    
}
