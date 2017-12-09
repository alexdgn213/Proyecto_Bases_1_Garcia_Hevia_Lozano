/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adaptadores;

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
    
    public static void llenarTabla(JTable jTable, ResultSet rs) {
        try {
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
         Object[] fila = new Object[cantidadColumnas];
         for (int i = 0; i < cantidadColumnas; i++) {
           fila[i]=rs.getObject(i+1);
         }
         modelo.addRow(fila);
        }
        rs.close();
        jTable.setModel(modelo);
       } catch (Exception ex) {
        ex.printStackTrace();
       }
    }
    
    public static void llenarComboBox(JComboBox jCombo, ResultSet rs) {
        try {
        jCombo.removeAllItems();
        jCombo.addItem("Seleccione una opción..");
        while (rs.next()) {
            jCombo.addItem(rs.getString(1));
        }
        rs.close();
       } catch (Exception ex) {
        ex.printStackTrace();
       }
    }
}
