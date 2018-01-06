/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
import static Dominio.Rol.obtenerResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Veronica Hevia
 */
public class Rol_pri {
    int rol_pri_codigo;
    int fk_pri_codigo;
    int fk_rol_codigo;

    public Rol_pri(int rol_pri_codigo, int fk_pri_codigo, int fk_rol_codigo) {
        this.rol_pri_codigo = rol_pri_codigo;
        this.fk_pri_codigo = fk_pri_codigo;
        this.fk_rol_codigo = fk_rol_codigo;
    }
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO rol(rol_pri_codigo,fk_pri_codigo,fk_rol_codigo) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, rol_pri_codigo);
            pst.setInt(2, fk_pri_codigo);
            pst.setInt(3, fk_rol_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE rol_pri SET fk_rol_codigo=?,fk_pri_codigo=? WHERE rol_pri_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, fk_rol_codigo);
            pst.setInt(2, fk_pri_codigo);
            pst.setInt(3, rol_pri_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from rol_pri where rol_pri_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, rol_pri_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public static List<Rol_pri> obtenerTodos(ConectorDB conector){
        List<Rol_pri> rol_pris = new ArrayList<Rol_pri>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT rol_pri_codigo, fk_rol_codigo, fk_pri_codigo FROM rol_pri");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Rol_pri l = new Rol_pri(rs.getInt("rol_pri_codigo"),rs.getInt("fk_rol_codigo"),rs.getInt("fk_pri_codigo"));
                rol_pris.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return rol_pris;
    }
    
    public static void llenarTablaPuede(ConectorDB conector, JTable jTable, int rol){
        ResultSet rs =obtenerResultSet(conector,"SELECT DISTINCT pri_codigo as Codigo, pri_accion as Accion,pri_descripcion as Descripcion FROM privilegio, rol_pri "
                + "WHERE fk_pri_codigo=pri_codigo AND fk_rol_codigo="+String.valueOf(rol));
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }
    
    public static void llenarTablaNoPuede(ConectorDB conector, JTable jTable, int rol){
        ResultSet rs =obtenerResultSet(conector,"SELECT pri_codigo as Codigo, pri_accion as Accion,pri_descripcion as Descripcion FROM privilegio "
                + "WHERE pri_codigo not in(Select fk_pri_codigo from rol_pri where fk_rol_codigo="+String.valueOf(rol)+")");
        AdaptadorSQLUI.llenarTabla(jTable, rs);   
    }

    public int getRol_pri_codigo() {
        return rol_pri_codigo;
    }

    public void setRol_pri_codigo(int rol_pri_codigo) {
        this.rol_pri_codigo = rol_pri_codigo;
    }

    public int getFk_pri_codigo() {
        return fk_pri_codigo;
    }

    public void setFk_pri_codigo(int fk_pri_codigo) {
        this.fk_pri_codigo = fk_pri_codigo;
    }

    public int getFk_rol_codigo() {
        return fk_rol_codigo;
    }

    public void setFk_rol_codigo(int fk_rol_codigo) {
        this.fk_rol_codigo = fk_rol_codigo;
    }
    
}
