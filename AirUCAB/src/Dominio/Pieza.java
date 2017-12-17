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
public class Pieza {
    
    int pie_codigo;
    Date pie_fecha_estimado;
    Date pie_fecha_entregado;
    int fk_aer_codigo;
    int fk_tip_codigo;
    int fk_pie_codigo;
    int fk_mot_codigo; 

    public Pieza(int pie_codigo, Date pie_fecha_estimado, Date pie_fecha_entregado,int fk_aer_codigo,int fk_tip_codigo,int fk_pie_codigo,int fk_mot_codigo) {
        this.pie_codigo = pie_codigo;
        this.pie_fecha_estimado = pie_fecha_estimado;
        this.pie_fecha_entregado = pie_fecha_entregado;
        this.fk_aer_codigo=fk_aer_codigo;
        this.fk_tip_codigo=fk_tip_codigo;
        this.fk_pie_codigo=fk_pie_codigo;
        this.fk_mot_codigo=fk_mot_codigo;
    }

    public Pieza(Date pie_fecha_estimado, Date pie_fecha_entregado, int fk_aer_codigo, int fk_tip_codigo) {
        this.pie_fecha_estimado = pie_fecha_estimado;
        this.pie_fecha_entregado = pie_fecha_entregado;
        this.fk_aer_codigo = fk_aer_codigo;
        this.fk_tip_codigo = fk_tip_codigo;
        this.fk_pie_codigo = fk_pie_codigo;
    }

    


    public void agregarPiezaADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Pieza(pie_fecha_estimada,pie_fecha_entregada,fk_aer_codigo,fk_tip_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setDate(1, pie_fecha_estimado);
            pst.setDate(2,pie_fecha_entregado);
            pst.setInt(3,fk_aer_codigo);
            pst.setInt(4,fk_tip_codigo);
            pst.executeUpdate();
            stm = "SELECT pie_codigo FROM pieza WHERE pie_fecha_estimada=? AND pie_fecha_entregada=? AND fk_aer_codigo=? AND fk_tip_codigo = ?";
            pst = conector.conexion.prepareStatement(stm);
            pst.setDate(1, pie_fecha_estimado);
            pst.setDate(2,pie_fecha_entregado);
            pst.setInt(3,fk_aer_codigo);
            pst.setInt(4,fk_tip_codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.pie_codigo = rs.getInt("pie_codigo");
            }
            pst.close();
            for (Tip_pru tp:Tip_pru.obtenerTodasPruebasPieza(conector, fk_tip_codigo))
            {
               Pru_pie pl1 = new Pru_pie(tp.getFk_pru_codigo(),pie_codigo,1);
               pl1.agregarADB(conector); 
            } 
       //     Pru_pie pl2 = new pru_lot(2,pie_codigo,1);
       //     pl2.agregarADB(conector);
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void agregarMotorADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Pieza(pie_fecha_estimada,pie_fecha_entregada,fk_aer_codigo,fk_mot_codigo) VALUES(?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setDate(1, pie_fecha_estimado);
            pst.setDate(2,pie_fecha_entregado);
            pst.setInt(3,fk_aer_codigo);
            pst.setInt(4,fk_tip_codigo);
            pst.executeUpdate();
            stm = "SELECT pie_codigo FROM pieza WHERE pie_fecha_estimada=? AND pie_fecha_entregada=? AND fk_aer_codigo=? AND fk_mot_codigo = ?";
            pst = conector.conexion.prepareStatement(stm);
            pst.setDate(1, pie_fecha_estimado);
            pst.setDate(2,pie_fecha_entregado);
            pst.setInt(3,fk_aer_codigo);
            pst.setInt(4,fk_tip_codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.pie_codigo = rs.getInt("pie_codigo");
            }
            pst.close();
            /*
            for (Pru_mot pm:Pru_mot.obtenerTodasPruebasMotor(conector, fk_tip_codigo))
            {
               Pru_pie pl1 = new Pru_pie(tp.getFk_pru_codigo(),pie_codigo,1);
               pl1.agregarADB(conector); 
            } 
            */
       //     Pru_pie pl2 = new pru_lot(2,pie_codigo,1);
       //     pl2.agregarADB(conector);
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Pieza SET pie_fecha_estimada = ?,pie_fecha_entregada = ?,fk_aer_codigo=?,fk_tip_codigo=?,fk_pie_codigo=?,fk_mot_codigo=? WHERE pie_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(7, pie_codigo);
            pst.setInt(6, fk_mot_codigo);
            pst.setInt(5,fk_pie_codigo);
             pst.setInt(4,fk_tip_codigo);
            pst.setInt(3,fk_aer_codigo);
            pst.setDate(2,pie_fecha_entregado);
            pst.setDate(1,pie_fecha_estimado);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Pieza where pie_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, pie_codigo);
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
    
    public static List<Pieza> obtenerTodos(ConectorDB conector){
        List<Pieza> piezas = new ArrayList<Pieza>();
        try {
            ResultSet rs = obtenerResultSet(conector,"SELECT pie_codigo,pie_fecha_estimada,pie_fecha_entregada FROM pieza");
            while (rs.next()) {
                Pieza p = new Pieza(rs.getInt("pie_codigo"),rs.getDate("pie_fecha_estimado"),rs.getDate("pie_fecha_entregado"),rs.getInt("fk_aer_codigo"),rs.getInt("fk_tip_codigo"),rs.getInt("fk_pie_codigo"),rs.getInt("fk_mot_codigo"));
                piezas.add(p);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return piezas;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT pie_codigo as Codigo,pie_fecha_estimada as Fecha_Estimada,pie_fecha_entregada as Fecha_de_Entrega,fk_aer_codigo as Codigo_Aeronave,fk_tip_codigo as Codigo_Tipo_Pieza,fk_pie_codigo as Codigo_Pieza,fk_mot_codigo as Codigo_Motor FROM pieza");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }
    
    public static void llenarTablaDeAvion(ConectorDB conector, JTable jTable, int id){
        ResultSet rs =obtenerResultSet(conector,"SELECT p.pie_codigo as Codigo,p.pie_fecha_estimada as Fecha, tp.tip_nombre as Nombre, 'Pieza' as Tipo FROM pieza p ,tipo_pieza tp WHERE p.fk_tip_codigo = tp.tip_codigo "
                + " UNION"
                + " SELECT p.pie_codigo as Codigo,p.pie_fecha_estimada as Fecha, m.mot_modelo ||' '|| m.mot_marca as Nombre, 'Motor' as Tipo FROM pieza p,motor m WHERE p.fk_mot_codigo = m.mot_codigo ");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
   
    }

    public int getPie_codigo() {
        return pie_codigo;
    }

    public void setPie_codigo(int pie_codigo) {
        this.pie_codigo = pie_codigo;
    }

    public Date getPie_fecha_estimado() {
        return pie_fecha_estimado;
    }

    public void setPie_fecha_estimado(Date pie_fecha_estimado) {
        this.pie_fecha_estimado = pie_fecha_estimado;
    }

    public Date getPie_fecha_entregado() {
        return pie_fecha_entregado;
    }

    public void setPie_fecha_entregado(Date pie_fecha_entregado) {
        this.pie_fecha_entregado = pie_fecha_entregado;
    }
}