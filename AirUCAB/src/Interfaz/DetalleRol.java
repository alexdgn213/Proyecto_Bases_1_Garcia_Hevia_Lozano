/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Adaptadores.AdaptadorSQLUI;
import Adaptadores.Comprobador;
import Adaptadores.ConectorDB;
import Adaptadores.MensajeUI;
import Dominio.Cliente;
import Dominio.Informacion_contacto;
import Dominio.Lugar;
import Dominio.Material;
import Dominio.Proveedor;
import Dominio.Rol;
import Dominio.Rol_pri;
import Dominio.mat_pro;
import java.awt.Color;
import java.sql.Date;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author alexd
 */
public class DetalleRol extends javax.swing.JPanel {
    ConectorDB conector;
    JPanel contenedor;
    JPanel panelMensaje;
    Rol r;

    /**
     * Creates new form PrincipalClientes
     */
    public DetalleRol(ConectorDB conector,JPanel contenedor,int id,JPanel panelMensaje) {
        this.conector = conector;
        this.contenedor = contenedor;
        this.panelMensaje = panelMensaje;
        initComponents();
        jScrollPane2.getViewport().setBackground(AdaptadorSQLUI.fondoScrolls);
        jScrollPane1.getViewport().setBackground(AdaptadorSQLUI.fondoTablas);
        jScrollPane3.getViewport().setBackground(AdaptadorSQLUI.fondoTablas);
        this.setSize(870, 610);
        r = Rol.buscarPorCodigo(conector, id);
        if (r==null){
            jPanel2.setVisible(false);
        }
        else{
            llenarDatosCliente();    
        }
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
        panelMateriales = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfRif = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPuede = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNoPuede = new javax.swing.JTable();
        botonGuardar1 = new javax.swing.JButton();
        botonGuardar2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtaNombre = new javax.swing.JTextArea();

        setMaximumSize(new java.awt.Dimension(1870, 1610));
        setMinimumSize(new java.awt.Dimension(870, 610));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(870, 610));

        jScrollPane2.setMaximumSize(new java.awt.Dimension(1870, 1610));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(870, 610));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(850, 920));

        panelMateriales.setOpaque(false);
        panelMateriales.setPreferredSize(new java.awt.Dimension(850, 530));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 66, 66));
        jLabel1.setText("Rol:");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel2.setText("Nombre:");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("Descripcion:");
        jLabel3.setToolTipText("");

        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_check_black_48dp_1x.png"))); // NOI18N
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_close_black_48dp_1x.png"))); // NOI18N
        botonCancelar.setToolTipText("");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_delete_black_48dp_1x.png"))); // NOI18N
        botonEliminar.setMaximumSize(new java.awt.Dimension(69, 48));
        botonEliminar.setMinimumSize(new java.awt.Dimension(69, 48));
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        jPanel2.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Este rol permite:");
        jLabel8.setToolTipText("");

        tablaPuede.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPuede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPuedeMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaPuede);

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Este rol no permite:");
        jLabel9.setToolTipText("");

        tablaNoPuede.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaNoPuede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaNoPuedeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaNoPuede);

        botonGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_arrow_upward_black_24dp_1x.png"))); // NOI18N
        botonGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardar1ActionPerformed(evt);
            }
        });

        botonGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_arrow_downward_black_24dp_1x.png"))); // NOI18N
        botonGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 67, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(botonGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botonGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        jtaNombre.setColumns(20);
        jtaNombre.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jtaNombre.setRows(5);
        jScrollPane4.setViewportView(jtaNombre);

        javax.swing.GroupLayout panelMaterialesLayout = new javax.swing.GroupLayout(panelMateriales);
        panelMateriales.setLayout(panelMaterialesLayout);
        panelMaterialesLayout.setHorizontalGroup(
            panelMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMaterialesLayout.createSequentialGroup()
                .addGroup(panelMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMaterialesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 758, Short.MAX_VALUE)
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMaterialesLayout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addGroup(panelMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMaterialesLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelMaterialesLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfRif, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(panelMaterialesLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMaterialesLayout.setVerticalGroup(
            panelMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMaterialesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(panelMaterialesLayout.createSequentialGroup()
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(panelMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMaterialesLayout.createSequentialGroup()
                                .addGroup(panelMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfRif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelMaterialesLayout.createSequentialGroup()
                                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(panelMateriales);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        PrincipalUsuarios nuevoPanel = new PrincipalUsuarios(conector,contenedor,panelMensaje);
        contenedor.removeAll();
        contenedor.add(nuevoPanel);
        contenedor.updateUI();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        if (r==null){
           r = new Rol(jtfRif.getText(),jtaNombre.getText());
           r.agregarADB(conector);
           new Thread(new MensajeUI(panelMensaje,"Rol creado exitosamente",1)).start();
        }
        PrincipalUsuarios nuevoPanel = new PrincipalUsuarios(conector,contenedor,panelMensaje);
        contenedor.removeAll();
        contenedor.add(nuevoPanel);
        contenedor.updateUI();
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        if (r==null){
        }
        else{
            r.eliminarDeDB(conector);
            new Thread(new MensajeUI(panelMensaje,"Rol eliminado exitosamente",1)).start();
        }
        PrincipalUsuarios nuevoPanel = new PrincipalUsuarios(conector,contenedor,panelMensaje);
        contenedor.removeAll();
        contenedor.add(nuevoPanel);
        contenedor.updateUI();
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void tablaPuedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPuedeMouseClicked

    }//GEN-LAST:event_tablaPuedeMouseClicked

    private void tablaNoPuedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaNoPuedeMouseClicked

    }//GEN-LAST:event_tablaNoPuedeMouseClicked

    private void botonGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardar1ActionPerformed
        int fila = tablaNoPuede.getSelectedRow();
        if (fila>=0){
            int id = (Integer) tablaNoPuede.getValueAt(fila, 0);
            r.agregarPrivilegio(conector, id); 
        }
        else{
            new Thread(new MensajeUI(panelMensaje,"No se selecciono ningun privilegio",0)).start();
        }
        Rol_pri.llenarTablaPuede(conector, tablaPuede, r.getRol_codigo());
        Rol_pri.llenarTablaNoPuede(conector, tablaNoPuede, r.getRol_codigo());
    }//GEN-LAST:event_botonGuardar1ActionPerformed

    private void botonGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardar2ActionPerformed
        int fila = tablaPuede.getSelectedRow();
        if (fila>=0){
            int id = (Integer) tablaPuede.getValueAt(fila, 0);
            r.quitarPrivilegio(conector, id); 
        }
        else{
            new Thread(new MensajeUI(panelMensaje,"No se selecciono ningun privilegio",0)).start();
        }
        Rol_pri.llenarTablaPuede(conector, tablaPuede, r.getRol_codigo());
        Rol_pri.llenarTablaNoPuede(conector, tablaNoPuede, r.getRol_codigo());
    }//GEN-LAST:event_botonGuardar2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonGuardar1;
    private javax.swing.JButton botonGuardar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jtaNombre;
    private javax.swing.JTextField jtfRif;
    private javax.swing.JPanel panelMateriales;
    private javax.swing.JTable tablaNoPuede;
    private javax.swing.JTable tablaPuede;
    // End of variables declaration//GEN-END:variables

    private void llenarDatosCliente() {
        jtfRif.setText(String.valueOf(r.getRol_nombre()));
        jtaNombre.setText(r.getRol_descripcion());
        Rol_pri.llenarTablaPuede(conector, tablaPuede, r.getRol_codigo());
        Rol_pri.llenarTablaNoPuede(conector, tablaNoPuede, r.getRol_codigo());
    }
    
       

}