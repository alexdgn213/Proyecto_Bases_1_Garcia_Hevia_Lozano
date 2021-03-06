/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
import Adaptadores.MensajeUI;
import Dominio.Cliente;
import Dominio.Fabrica;
import Dominio.Factura;
import Dominio.Inventario;
import Dominio.Lote_material;
import Dominio.Mat_inv;
import Dominio.Material;
import Dominio.Proveedor;
import Dominio.Solicitud;
import Dominio.Tipo_pieza;
import Dominio.mat_pro;
import java.awt.Color;
import java.sql.Date;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexd
 */
public class PrincipaSolicitudes extends javax.swing.JPanel {
    ConectorDB conector;
    JPanel contenedor;
    Proveedor p;
    int lock;
    JPanel panelMensaje;
    List<Lote_material> compras;
    int montoTotal;
    List<String> permisos;
    int fabricaActual;

    /**
     * Creates new form PrincipalClientes
     */
    public PrincipaSolicitudes(ConectorDB conector,JPanel contenedor,JPanel panelMensaje, List<String> permisos) {
        this.conector = conector;
        this.contenedor = contenedor;
        this.panelMensaje = panelMensaje;
        this.permisos=permisos;
        initComponents();
        this.setBackground(AdaptadorSQLUI.fondoScrolls);
        this.setSize(870, 610);
        jScrollPane1.getViewport().setBackground(AdaptadorSQLUI.fondoTablas);
        jScrollPane2.getViewport().setBackground(AdaptadorSQLUI.fondoTablas);
        lock=1;
        fabricaActual = 1;
        Fabrica.llenarComboBox(conector, jcbFabrica);
        Fabrica.llenarComboBox(conector,jcDestino);
        lock=0;
        panelComprar.setVisible(false);
        this.compras = new ArrayList<Lote_material>();
        panelAgregar.setVisible(permisos.contains("csolicitud"));
        botonEliminar.setEnabled(permisos.contains("dsolicitud"));
        botonCompletar.setEnabled(permisos.contains("usolicitud"));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jcbFabrica = new javax.swing.JComboBox<>();
        panelComprar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEnviadas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaRecibidas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        botonCompletar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        panelAgregar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcTipo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jcProducto = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jtfCantidad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jcDestino = new javax.swing.JComboBox<>();
        botonAgregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(850, 580));
        setPreferredSize(new java.awt.Dimension(850, 580));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(66, 66, 66));
        jLabel2.setText("Solicitudes");
        jLabel2.setToolTipText("");

        jcbFabrica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbFabrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFabricaActionPerformed(evt);
            }
        });

        panelComprar.setOpaque(false);

        tablaEnviadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaEnviadas.setMaximumSize(new java.awt.Dimension(2147483647, 5000));
        jScrollPane1.setViewportView(tablaEnviadas);

        tablaRecibidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaRecibidas.setMaximumSize(new java.awt.Dimension(2147483647, 5000));
        jScrollPane2.setViewportView(tablaRecibidas);

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(66, 66, 66));
        jLabel4.setText("Recibidas:");
        jLabel4.setToolTipText("");

        botonCompletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_check_black_24dp_1x.png"))); // NOI18N
        botonCompletar.setContentAreaFilled(false);
        botonCompletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCompletarActionPerformed(evt);
            }
        });

        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_delete_black_24dp_1x.png"))); // NOI18N
        botonEliminar.setContentAreaFilled(false);
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(66, 66, 66));
        jLabel5.setText("Enviadas:");
        jLabel5.setToolTipText("");

        panelAgregar.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 66, 66));
        jLabel1.setText("Nueva:");
        jLabel1.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(66, 66, 66));
        jLabel6.setText("Tipo:");
        jLabel6.setToolTipText("");

        jcTipo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jcTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción...", "Material", "Pieza" }));
        jcTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcTipoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(66, 66, 66));
        jLabel8.setText("Producto:");
        jLabel8.setToolTipText("");

        jcProducto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jcProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción...", "Material", "Pieza" }));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(66, 66, 66));
        jLabel7.setText("Cantidad:");
        jLabel7.setToolTipText("");

        jtfCantidad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(66, 66, 66));
        jLabel9.setText("Destino:");
        jLabel9.setToolTipText("");

        jcDestino.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jcDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción...", "Material", "Pieza" }));

        botonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_add_black_24dp_1x.png"))); // NOI18N
        botonAgregar.setContentAreaFilled(false);
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarLayout = new javax.swing.GroupLayout(panelAgregar);
        panelAgregar.setLayout(panelAgregarLayout);
        panelAgregarLayout.setHorizontalGroup(
            panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jcDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelAgregarLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCantidad))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelAgregarLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAgregarLayout.setVerticalGroup(
            panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelComprarLayout = new javax.swing.GroupLayout(panelComprar);
        panelComprar.setLayout(panelComprarLayout);
        panelComprarLayout.setHorizontalGroup(
            panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComprarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComprarLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelComprarLayout.createSequentialGroup()
                        .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelComprarLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCompletar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelComprarLayout.createSequentialGroup()
                                .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        panelComprarLayout.setVerticalGroup(
            panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComprarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComprarLayout.createSequentialGroup()
                        .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCompletar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panelAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 66, 66));
        jLabel3.setText("Seleccione una fabrica: ");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(0, 710, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbFabricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFabricaActionPerformed
        if(lock==0){
            if(jcbFabrica.getSelectedIndex()>0){
                fabricaActual= jcbFabrica.getSelectedIndex();
                Solicitud.llenarTablaEnviadasFabrica(conector, tablaEnviadas, fabricaActual);
                Solicitud.llenarTablaRecibidasFabrica(conector, tablaRecibidas, fabricaActual);
                panelComprar.setVisible(true);           
            }
            else{
                panelComprar.setVisible(false);
            }

        }
    }//GEN-LAST:event_jcbFabricaActionPerformed

    private void botonCompletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCompletarActionPerformed
        int fila = tablaRecibidas.getSelectedRow();
        if (fila>=0){
            int id = (Integer) tablaRecibidas.getValueAt(fila, 0);
            Solicitud.aceptarSOlicitud(conector, id);
            new Thread(new MensajeUI(panelMensaje,"Se completo la solicitud",1)).start();
        }
        else{
            new Thread(new MensajeUI(panelMensaje,"No se selecciono ninguna solicitud",0)).start();
        }
        Solicitud.llenarTablaEnviadasFabrica(conector, tablaEnviadas, fabricaActual);
        Solicitud.llenarTablaRecibidasFabrica(conector, tablaRecibidas, fabricaActual);
    }//GEN-LAST:event_botonCompletarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        int fila = tablaEnviadas.getSelectedRow();
        if (fila>=0){
            int id = (Integer) tablaEnviadas.getValueAt(fila, 0);
            Solicitud.eliminarDeDB(conector, id);
            new Thread(new MensajeUI(panelMensaje,"Se elimino la solicitud",1)).start();
        }
        else{
            new Thread(new MensajeUI(panelMensaje,"No se selecciono ninguna solicitud",0)).start();
        }      
        Solicitud.llenarTablaEnviadasFabrica(conector, tablaEnviadas, fabricaActual);
        Solicitud.llenarTablaRecibidasFabrica(conector, tablaRecibidas, fabricaActual);
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        if(jcTipo.getSelectedIndex()==1){
            Solicitud.nuevaSolicitudMaterial(conector, fabricaActual, jcDestino.getSelectedIndex(), jcProducto.getSelectedIndex(), Integer.parseInt(jtfCantidad.getText()));
        }
        else if(jcTipo.getSelectedIndex()==2){
            Solicitud.nuevaSolicitudPieza(conector, fabricaActual, jcDestino.getSelectedIndex(), jcProducto.getSelectedIndex(), Integer.parseInt(jtfCantidad.getText()));
        }
        Solicitud.llenarTablaEnviadasFabrica(conector, tablaEnviadas, fabricaActual);
        Solicitud.llenarTablaRecibidasFabrica(conector, tablaRecibidas, fabricaActual);
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void jcTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcTipoActionPerformed
        if(jcTipo.getSelectedIndex()==1){
            Material.llenarComboBox(conector, jcProducto);
        }
        else if(jcTipo.getSelectedIndex()==2){
            Tipo_pieza.llenarComboBox(conector, jcProducto);
        }
    }//GEN-LAST:event_jcTipoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonCompletar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcDestino;
    private javax.swing.JComboBox<String> jcProducto;
    private javax.swing.JComboBox<String> jcTipo;
    private javax.swing.JComboBox<String> jcbFabrica;
    private javax.swing.JTextField jtfCantidad;
    private javax.swing.JPanel panelAgregar;
    private javax.swing.JPanel panelComprar;
    private javax.swing.JTable tablaEnviadas;
    private javax.swing.JTable tablaRecibidas;
    // End of variables declaration//GEN-END:variables
    
}
