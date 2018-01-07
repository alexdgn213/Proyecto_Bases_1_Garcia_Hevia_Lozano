/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.ConectorDB;
import Adaptadores.MensajeUI;
import Dominio.Aeronave;
import Dominio.Cliente;
import Dominio.Estatus;
import Dominio.Factura;
import Dominio.Informacion_contacto;
import Dominio.Lote_material;
import Dominio.Lugar;
import Dominio.Material;
import Dominio.Proveedor;
import Dominio.Prueba;
import Dominio.pru_lot;
import java.awt.Color;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author alexd
 */
public class DetalleFacturaVenta extends javax.swing.JPanel {
    ConectorDB conector;
    JPanel contenedor;
    Factura f;
    JPanel panelMensaje;

    /**
     * Creates new form PrincipalClientes
     */
    public DetalleFacturaVenta(ConectorDB conector,JPanel contenedor,int id, String proveedor, String fecha,JPanel panelMensaje) {
        this.conector = conector;
        this.contenedor = contenedor;
        this.panelMensaje = panelMensaje;
        initComponents();
        jScrollPane2.getViewport().setBackground(AdaptadorSQLUI.fondoScrolls);
        jScrollPane3.getViewport().setBackground(AdaptadorSQLUI.fondoTablas);
        jtfCodigo.setEnabled(false);
        jtfCodigo.setText(String.valueOf(id));
        jtfProveedor.setEnabled(false);
        jtfProveedor.setText(proveedor);
        jtfFechaCompra.setEnabled(false);
        jtfFechaCompra.setText(fecha);
        jtfPrecioTotal.setEnabled(false);
        this.setSize(870, 610);
        f = Factura.buscarPorCodigo(conector, id);
        llenarDatosFactura();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfCodigo = new javax.swing.JTextField();
        jtfProveedor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfFechaCompra = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        panelInformacion = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaItems = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jtfPrecioTotal = new javax.swing.JTextField();
        botonVolver = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(870, 610));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(870, 610));

        jScrollPane2.setMaximumSize(new java.awt.Dimension(1870, 1610));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(870, 610));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(850, 810));

        jPanel1.setMaximumSize(new java.awt.Dimension(850, 32767));
        jPanel1.setMinimumSize(new java.awt.Dimension(850, 730));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(850, 730));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 66, 66));
        jLabel1.setText("Factura de compra:");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel2.setText("Codigo:");
        jLabel2.setToolTipText("");

        jtfProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfProveedorActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("Cliente:");
        jLabel3.setToolTipText("");

        jtfFechaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfFechaCompraActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText("Fecha compra:");
        jLabel4.setToolTipText("");

        panelInformacion.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Aviones:");
        jLabel8.setToolTipText("");

        tablaItems.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaItemsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaItems);

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setText("Precio Total:");
        jLabel7.setToolTipText("");

        jtfPrecioTotal.setToolTipText("");

        javax.swing.GroupLayout panelInformacionLayout = new javax.swing.GroupLayout(panelInformacion);
        panelInformacion.setLayout(panelInformacionLayout);
        panelInformacionLayout.setHorizontalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformacionLayout.createSequentialGroup()
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInformacionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtfPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
                .addGap(94, 94, 94))
        );
        panelInformacionLayout.setVerticalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        botonVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_arrow_back_black_24dp_2x.png"))); // NOI18N
        botonVolver.setBorderPainted(false);
        botonVolver.setContentAreaFilled(false);
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtfFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtfProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(panelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaItemsMouseClicked
        
    }//GEN-LAST:event_tablaItemsMouseClicked

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        PrincipalFactura nuevoPanel = new PrincipalFactura(conector,contenedor,panelMensaje);
        contenedor.removeAll();
        contenedor.add(nuevoPanel);
        contenedor.updateUI();    
    }//GEN-LAST:event_botonVolverActionPerformed

    private void jtfProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfProveedorActionPerformed

    private void jtfFechaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfFechaCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfFechaCompraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfFechaCompra;
    private javax.swing.JTextField jtfPrecioTotal;
    private javax.swing.JTextField jtfProveedor;
    private javax.swing.JPanel panelInformacion;
    private javax.swing.JTable tablaItems;
    // End of variables declaration//GEN-END:variables

    private void llenarDatosFactura() {
        jtfCodigo.setText(String.valueOf(f.getFac_codigo()));
        jtfCodigo.setEnabled(false);
        //jtfProveedor.setText(m.getMat_nombre());
        //jtfProveedor.setEnabled(false);
        //jtfFechaCompra.setText(f.getFac_fecha_compra());
        //jtfFechaCompra.setEnabled(false);
        //jtfFechaCompra.setText(l.getLot_fecha_compra().toString());
        //jtfFechaCompra.setEnabled(false);
        jtfPrecioTotal.setText(String.valueOf(f.getFac_monto_total()));
        jtfPrecioTotal.setEnabled(false);
        Aeronave.llenarTablaDeFactura(conector, tablaItems, f.getFac_codigo());
        
    }
    
}
