/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adaptadores;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexd
 */
public class AdaptadorSQLUI {
    
    public static Color fondoTablas = new Color(255,255,255);
    public static Color fondoScrolls = new Color(240,240,240);
    public static Color fondoExito = new Color(102,255,102);
    public static Color fondoError = new Color(255,102,102);
    
    public static void llenarTabla(JTable jTable, ResultSet rs) {
        try {
        int lleno = 0;
        //Para establecer el modelo al JTable
        DefaultTableModel modelo = new DefaultTableModel();
        //Obteniendo la informacion de las columnas que estan siendo consultadas
        ResultSetMetaData rsMd = rs.getMetaData();
        //La cantidad de columnas que tiene la consulta
        int cantidadColumnas = rsMd.getColumnCount();
        //Establecer como cabezeras el nombre de las colimnas
        for (int i = 1; i <= cantidadColumnas; i++) {
         modelo.addColumn(rsMd.getColumnLabel(i));
        }
        //Creando las filas para el JTable
        while (rs.next()) {
         lleno=1;
         Object[] fila = new Object[cantidadColumnas];
         for (int i = 0; i < cantidadColumnas; i++) {
           fila[i]=rs.getObject(i+1);
         }
         modelo.addRow(fila);
        }
        rs.close();
        if(lleno==1) jTable.setModel(modelo);
        else{
            modelo = new DefaultTableModel();
            modelo.addColumn("Esta Tabla esta Vacia");
            jTable.setModel(modelo);
        }
       } catch (Exception ex) {
        ex.printStackTrace();
       }
    }
    
    public static void llenarComboBox(JComboBox jCombo, ResultSet rs) {
        try {
        jCombo.removeAllItems();
        jCombo.addItem("Seleccione una opción...");
        while (rs.next()) {
            jCombo.addItem(rs.getString(1));
        }
        rs.close();
       } catch (Exception ex) {
        ex.printStackTrace();
       }
    }
}
