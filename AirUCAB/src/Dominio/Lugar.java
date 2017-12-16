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

/**
 *
 * @author LAB_L11
 */
public class Lugar {
    
    int lug_codigo;
    String lug_nombre;
    String lug_tipo;
    int fk_lug_codigo;

    public Lugar(int lug_codigo, String lug_nombre, String lug_tipo, int fk_lug_codigo) {
        this.lug_codigo = lug_codigo;
        this.lug_nombre = lug_nombre;
        this.lug_tipo = lug_tipo;
        this.fk_lug_codigo = fk_lug_codigo;
    }

    public Lugar(int lug_codigo, String lug_nombre, String lug_tipo) {
        this.lug_codigo = lug_codigo;
        this.lug_nombre = lug_nombre;
        this.lug_tipo = lug_tipo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Lugar(lug_codigo,lug_nombre,lug_tipo) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, lug_codigo);
            pst.setString(2, lug_nombre);
            pst.setString(3,lug_tipo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Lugar SET lug_nombre=?,lug_tipo=? WHERE lug_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, lug_nombre);
            pst.setString(2,lug_tipo);
            pst.setInt(3, lug_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Lugar where lug_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, lug_codigo);
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
    
    public static List<Lugar> obtenerTodos(ConectorDB conector){
        List<Lugar> lugares = new ArrayList<Lugar>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT lug_codigo, lug_nombre , lug_tipo, fk_lug_codigo FROM lugar");
            ResultSet rs = obtenerResultSet(conector, "SELECT lug_codigo, lug_nombre , lug_tipo, fk_lug_codigo FROM lugar");
            while (rs.next()) {
                Lugar l = new Lugar(rs.getInt("lug_codigo"),rs.getString("lug_nombre"),rs.getString("lug_tipo"),rs.getInt("fk_lug_codigo"));
                lugares.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return lugares;
    }
    
    public static Lugar buscarPorCodigo(ConectorDB conector, int codigo){
        Lugar l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT lug_codigo, lug_nombre , lug_tipo, fk_lug_codigo FROM lugar WHERE lug_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Lugar(rs.getInt("lug_codigo"),rs.getString("lug_nombre"),rs.getString("lug_tipo"),rs.getInt("fk_lug_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    
    public static Lugar buscarPorNombre(ConectorDB conector, String nombre){
        Lugar l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT lug_codigo, lug_nombre , lug_tipo, fk_lug_codigo FROM lugar WHERE lug_nombre=?");
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Lugar(rs.getInt("lug_codigo"),rs.getString("lug_nombre"),rs.getString("lug_tipo"),rs.getInt("fk_lug_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    
    public static void llenarComboPaises(ConectorDB conector, JComboBox jCombo){
        AdaptadorSQLUI.llenarComboBox(jCombo,obtenerResultSet(conector,"SELECT lug_nombre FROM lugar WHERE lug_tipo='Pais'"));
    }
    
    public static void llenarComboEstados(ConectorDB conector, JComboBox jCombo, String pais){
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT l1.lug_nombre FROM lugar l1, lugar l2 WHERE l1.fk_lug_codigo=l2.lug_codigo AND l1.lug_tipo ='Estado' AND l2.lug_nombre=? ORDER BY lug_nombre");
            pst.setString(1, pais);
            AdaptadorSQLUI.llenarComboBox(jCombo,pst.executeQuery());
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
    }
    
    public static void llenarComboMunicipios(ConectorDB conector, JComboBox jCombo, String estado){
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT l1.lug_nombre FROM lugar l1, lugar l2 WHERE l1.fk_lug_codigo=l2.lug_codigo AND l1.lug_tipo ='Municipio' AND l2.lug_nombre=? ORDER BY lug_nombre");
            pst.setString(1, estado);
            AdaptadorSQLUI.llenarComboBox(jCombo,pst.executeQuery());
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
    }
    
    public static void llenarComboParroquias(ConectorDB conector, JComboBox jCombo, String municipio, String estado){
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT l1.lug_nombre FROM lugar l1, lugar l2, lugar l3 WHERE l1.fk_lug_codigo=l2.lug_codigo AND l2.fk_lug_codigo=l3.lug_codigo AND l1.lug_tipo ='Parroquia' AND l2.lug_nombre=? AND l3.lug_nombre=? ORDER BY lug_nombre");
            pst.setString(1, municipio);
            pst.setString(2, estado);
            AdaptadorSQLUI.llenarComboBox(jCombo,pst.executeQuery());
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
    }
    
    public static List<Lugar> obtenerDireccion(ConectorDB conector, int idParroquia){
        List<Lugar> direccion = new ArrayList<Lugar>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT l1.lug_codigo as c1, l1.lug_nombre as n1, l1.lug_tipo as t1, l1.fk_lug_codigo as f1,"
                    +" l2.lug_codigo as c2, l2.lug_nombre as n2, l2.lug_tipo as t2, l2.fk_lug_codigo as f2,"
                    +" l3.lug_codigo as c3, l3.lug_nombre as n3, l3.lug_tipo as t3, l3.fk_lug_codigo as f3,"
                    +" l4.lug_codigo as c4, l4.lug_nombre as n4, l4.lug_tipo as t4, l4.fk_lug_codigo as f4"
                    +" FROM lugar l1, lugar l2, lugar l3, lugar l4"
                    +" WHERE l1.lug_codigo = l2.fk_lug_codigo AND l2.lug_codigo = l3.fk_lug_codigo AND l3.lug_codigo = l4.fk_lug_codigo AND l4.lug_codigo = ?");
            pst.setInt(1, idParroquia);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Lugar l1 = new Lugar(rs.getInt("c1"),rs.getString("n1"),rs.getString("t1"),0);
                Lugar l2 = new Lugar(rs.getInt("c2"),rs.getString("n2"),rs.getString("t2"),rs.getInt("f2"));
                Lugar l3 = new Lugar(rs.getInt("c3"),rs.getString("n3"),rs.getString("t3"),rs.getInt("f3"));
                Lugar l4 = new Lugar(rs.getInt("c4"),rs.getString("n4"),rs.getString("t4"),rs.getInt("f4"));
                direccion.add(l1);
                direccion.add(l2);
                direccion.add(l3);
                direccion.add(l4);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return direccion;
    }

    public static int fkDireccion(ConectorDB conector, String pais, String estado, String municipio, String parroquia){
        int fk = -1;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT l4.lug_codigo as codigo"
                    +" FROM lugar l1, lugar l2, lugar l3, lugar l4"
                    +" WHERE l1.lug_codigo = l2.fk_lug_codigo AND l2.lug_codigo = l3.fk_lug_codigo AND l3.lug_codigo = l4.fk_lug_codigo"
                    +" AND l1.lug_nombre = ? AND l2.lug_nombre = ? AND l3.lug_nombre = ? AND l4.lug_nombre = ?");
            pst.setString(1, pais);
            pst.setString(2, estado);
            pst.setString(3, municipio);
            pst.setString(4, parroquia);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                fk = rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return fk;
    }

    public int getLug_codigo() {
        return lug_codigo;
    }

    public void setLug_codigo(int lug_codigo) {
        this.lug_codigo = lug_codigo;
    }

    public String getLug_nombre() {
        return lug_nombre;
    }

    public void setLug_nombre(String lug_nombre) {
        this.lug_nombre = lug_nombre;
    }

    public String getLug_tipo() {
        return lug_tipo;
    }

    public void setLug_tipo(String lug_tipo) {
        this.lug_tipo = lug_tipo;
    }

    public int getFk_lug_codigo() {
        return fk_lug_codigo;
    }

    public void setFk_lug_codigo(int fk_lug_codigo) {
        this.fk_lug_codigo = fk_lug_codigo;
    }
    
    
    
    
}
