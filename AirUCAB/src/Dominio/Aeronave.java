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
    int aer_precio_compra;
    int fk_mod_codigo;
    int fk_fac_codigo;

    public Aeronave(int aer_codigo, int fk_cli_rif, Date aer_fecha_compra,int aer_precio_compra, int fk_mod_codigo, int fk_fac_codigo) {
        this.aer_codigo = aer_codigo;
        this.fk_cli_rif = fk_cli_rif;
        this.aer_fecha_compra = aer_fecha_compra;
        this.aer_precio_compra=aer_precio_compra;
        this.fk_mod_codigo = fk_mod_codigo;
        this.fk_fac_codigo = fk_fac_codigo;
    }

    public Aeronave(int fk_cli_rif, Date aer_fecha_compra,int aer_precio_compra, int fk_mod_codigo, int fk_fac_codigo) {
        this.fk_cli_rif = fk_cli_rif;
        this.aer_fecha_compra = aer_fecha_compra;
        this.aer_precio_compra=aer_precio_compra;
        this.fk_mod_codigo = fk_mod_codigo;
        this.fk_fac_codigo = fk_fac_codigo;
    }

    


    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Aeronave(aer_fecha_compra,aer_precio_compra,fk_cli_rif,fk_mod_codigo,fk_fac_codigo) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setDate(1, aer_fecha_compra);
            pst.setInt(2, aer_precio_compra);
            pst.setInt(3, fk_cli_rif);
            pst.setInt(4,fk_mod_codigo);
            pst.setInt(5,fk_fac_codigo);
            pst.executeUpdate();
            stm ="Select aer_codigo From Aeronave Where aer_fecha_compra=? AND aer_precio_compra=? AND fk_cli_rif=? AND fk_mod_codigo=? AND fk_fac_codigo=?";
            pst = conector.conexion.prepareStatement(stm);
            pst.setDate(1, aer_fecha_compra);
            pst.setInt(2, aer_precio_compra);
            pst.setInt(3, fk_cli_rif);
            pst.setInt(4,fk_mod_codigo);
            pst.setInt(5,fk_fac_codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.aer_codigo = rs.getInt("aer_codigo");
                
            }
            pst.close();
            for (Tip_mod tp :Tip_mod.obtenerTipoPiezasModelo(conector, fk_mod_codigo)){
                Pieza p = new Pieza(Date.valueOf(java.time.LocalDate.now()),Date.valueOf(java.time.LocalDate.now()),aer_codigo,tp.fk_tip_codigo,1);
                p.agregarPiezaADB(conector);
            }
            for (Mot_mod mm :Mot_mod.obtenerMotorModelo(conector, fk_mod_codigo)){
                Pieza p = new Pieza(Date.valueOf(java.time.LocalDate.now()),Date.valueOf(java.time.LocalDate.now()),aer_codigo,mm.fk_mot_codigo,2);
                p.agregarMotorADB(conector);
            }
            Pru_aer pr= new Pru_aer(Date.valueOf(java.time.LocalDate.now()),8,this.aer_codigo,1);
            pr.agregarADB(conector);
            Pru_aer pr2= new Pru_aer(Date.valueOf(java.time.LocalDate.now()),9,this.aer_codigo,1);
            pr2.agregarADB(conector);
            
            
           /*
           int pru_aer_codigo;
           Date pru_aer_fecha_realizacion;
           int fk_pru_codigo;
           int fk_aer_codigo;
           int fk_est_codigo;         
                    
                    */
                    
                    
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Aeronave SET fk_cli_rif = ?,aer_fecha_compra = ?,aer_precio_compra = ?,fk_mod_codigo = ? WHERE aer_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(5, aer_codigo);
            pst.setDate(2,aer_fecha_compra);
            pst.setInt(3,aer_precio_compra);
            pst.setInt(4,fk_mod_codigo);
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
            ResultSet rs = obtenerResultSet(conector,"SELECT aer_codigo,fk_cli_rif,aer_fecha_compra,aer_precio_compra,fk_mod_codigo FROM aeronave");
            while (rs.next()) {
                //Aeronave a = new Aeronave(rs.getInt("aer_codigo"),rs.getInt("fk_cli_rif"),rs.getDate("aer_fecha_compra"),rs.getInt("fk_mod_codigo"));
                //aeronaves.add(a);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return aeronaves;
    }
    
    public static void llenarTabla(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT aer_codigo as Codigo,mod_nombre as Modelo, cli_nombre as Cliente,aer_fecha_compra as Fecha_de_compra,aer_precio_compra as Precio_Compra FROM aeronave,cliente,modelo_aeronave "
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
    
    
    
   public static void llenarTablaAeronaveFinalizadas(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT aer_codigo as Codigo,mod_nombre as Modelo,cli_nombre as Nombre_Cliente,aer_fecha_compra as Fecha_Compra " +
"                 FROM aeronave a,modelo_aeronave m,cliente c" +
"                 WHERE a.fk_cli_rif=c.cli_rif AND a.fk_mod_codigo=m.mod_codigo" +
"                 AND(not exists(Select e.fk_est_codigo from  ensamblaje e, pieza p where e.fk_pie_codigo=p.pie_codigo AND e.fk_est_codigo!=4 and p.fk_aer_codigo=aer_codigo)" +
"                 AND not exists(Select pp.fk_est_codigo from  pru_pie pp, pieza p where p.pie_codigo = pp.fk_pie_codigo AND pp.fk_est_codigo!=4 AND p.fk_aer_codigo=aer_codigo)" +
"                 AND not exists(Select pa.fk_est_codigo from  pru_aer pa where pa.fk_est_codigo!=4 AND pa.fk_aer_codigo=aer_codigo));");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }
   
   public static void llenarTablaAeronaveNoFinalizadas(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT aer_codigo as Codigo,mod_nombre as Modelo,cli_nombre as Nombre_Cliente,aer_fecha_compra as Fecha_Compra " +
"                 FROM aeronave a,modelo_aeronave m,cliente c" +
"                 WHERE a.fk_cli_rif=c.cli_rif AND a.fk_mod_codigo=m.mod_codigo" +
"                 AND(exists(Select e.fk_est_codigo from  ensamblaje e, pieza p where e.fk_pie_codigo=p.pie_codigo AND e.fk_est_codigo!=4 and p.fk_aer_codigo=aer_codigo)" +
"                 OR exists(Select pp.fk_est_codigo from  pru_pie pp, pieza p where p.pie_codigo = pp.fk_pie_codigo AND pp.fk_est_codigo!=4 AND p.fk_aer_codigo=aer_codigo)" +
"                 OR exists(Select pa.fk_est_codigo from  pru_aer pa where pa.fk_est_codigo!=4 AND pa.fk_aer_codigo=aer_codigo));");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }
   
   public static void llenarTablaPruebasAeronave(ConectorDB conector, JTable jTable){
        ResultSet rs =obtenerResultSet(conector,"SELECT aer_codigo as Codigo,mod_nombre as Modelo,pru_aer_codigo as Codigo_Prueba_Aeronave,est_nombre as Estatus "
                + " FROM aeronave a,modelo_aeronave m c,pru_aer pa"
                + " WHERE a.fk_cli_rif=c.cli_rif AND a.fk_mod_codigo=m.mod_codigo AND a.aer_codigo=pa.fk_aer_codigo "
                + " AND exists(Select e.fk_est_codigo from  ensamblaje e,pru_pie pp where pp.fk_est_codigo= pa.fk_est_codigo AND e.fk_est_codigo=pa.fk_est_codigo AND e.fk_est_codigo==4)");
        AdaptadorSQLUI.llenarTabla(jTable, rs);
    }

    
    public static Aeronave buscarPorCodigo(ConectorDB conector, int codigo){
        Aeronave l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT aer_codigo,fk_cli_rif,aer_fecha_compra, aer_precio_compra,fk_mod_codigo, fk_fac_codigo FROM aeronave WHERE aer_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Aeronave(rs.getInt("aer_codigo"),rs.getInt("fk_cli_rif"),rs.getDate("aer_fecha_compra"),rs.getInt("aer_precio_compra"),rs.getInt("fk_mod_codigo"),rs.getInt("fk_fac_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }

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

    public int getAer_precio_compra() {
        return aer_precio_compra;
    }

    public void setAer_precio_compra(int aer_precio_compra) {
        this.aer_precio_compra = aer_precio_compra;
    }

    public int getFk_fac_codigo() {
        return fk_fac_codigo;
    }

    public void setFk_fac_codigo(int fk_fac_codigo) {
        this.fk_fac_codigo = fk_fac_codigo;
    }

    
    
}
    
