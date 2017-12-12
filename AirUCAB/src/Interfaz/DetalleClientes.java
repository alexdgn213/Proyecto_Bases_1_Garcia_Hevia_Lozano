/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Adaptadores.ConectorDB;
import Dominio.Cliente;
import Dominio.Lugar;
import Dominio.Proveedor;
import javax.swing.JPanel;

/**
 *
 * @author alexd
 */
public class DetalleClientes extends javax.swing.JPanel {
    ConectorDB conector;
    JPanel contenedor;
    Cliente c;

    /**
     * Creates new form PrincipalClientes
     */
    public DetalleClientes(ConectorDB conector,JPanel contenedor,int id) {
        this.conector = conector;
        this.contenedor = contenedor;
        initComponents();
        jlErrorFecha.setVisible(false);
        jlErrorMonto.setVisible(false);
        jlErrorNombre.setVisible(false);
        jlErrorRif.setVisible(false);
        jlErrorUbicacion.setVisible(false);
        this.setSize(850,600);
        c = Cliente.buscarPorCodigo(conector, id);
        if (c==null){
            Lugar.llenarComboPaises(conector, jcbPais);
            Lugar.llenarComboEstados(conector, jcbEstado,"");
            Lugar.llenarComboMunicipios(conector, jcbMunicipio, "");
            Lugar.llenarComboParroquias(conector, jcbParroquia, "","");
            jcbMunicipio.setEnabled(false);
            jcbEstado.setEnabled(false);
            jcbParroquia.setEnabled(false);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfRif = new javax.swing.JTextField();
        jtfNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfMontoAcreditado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfFechaInicio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jcbPais = new javax.swing.JComboBox<>();
        jcbEstado = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jcbMunicipio = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jcbParroquia = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jlErrorRif = new javax.swing.JLabel();
        jlErrorNombre = new javax.swing.JLabel();
        jlErrorMonto = new javax.swing.JLabel();
        jlErrorFecha = new javax.swing.JLabel();
        jlErrorUbicacion = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(850, 580));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(850, 580));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 66, 66));
        jLabel1.setText("Proveedor:");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel2.setText("RIF:");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("Nombre:");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText("Monto Acreditado:");
        jLabel4.setToolTipText("");

        jtfFechaInicio.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setText("Fecha Inicio Operaciones:");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Pais:");
        jLabel6.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setText("Ubicación:");
        jLabel7.setToolTipText("");

        jcbPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbPais.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbPaisItemStateChanged(evt);
            }
        });
        jcbPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPaisActionPerformed(evt);
            }
        });

        jcbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbEstadoActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel18.setText("Estado:");
        jLabel18.setToolTipText("");

        jcbMunicipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMunicipioActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel19.setText("Municipio:");
        jLabel19.setToolTipText("");

        jcbParroquia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbParroquia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbParroquiaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel20.setText("Parroquia:");
        jLabel20.setToolTipText("");

        jButton2.setText("Aceptar");

        jButton3.setText("Cancelar");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");

        jlErrorRif.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jlErrorRif.setForeground(new java.awt.Color(255, 0, 0));
        jlErrorRif.setText("Error en el rif");
        jlErrorRif.setToolTipText("");

        jlErrorNombre.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jlErrorNombre.setForeground(new java.awt.Color(255, 0, 0));
        jlErrorNombre.setText("Error en el rif");
        jlErrorNombre.setToolTipText("");

        jlErrorMonto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jlErrorMonto.setForeground(new java.awt.Color(255, 0, 0));
        jlErrorMonto.setText("Error en el rif");
        jlErrorMonto.setToolTipText("");

        jlErrorFecha.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jlErrorFecha.setForeground(new java.awt.Color(255, 0, 0));
        jlErrorFecha.setText("Error en el rif");
        jlErrorFecha.setToolTipText("");

        jlErrorUbicacion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jlErrorUbicacion.setForeground(new java.awt.Color(255, 0, 0));
        jlErrorUbicacion.setText("Error en el rif");
        jlErrorUbicacion.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbPais, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbMunicipio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbParroquia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(279, 279, 279))
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlErrorUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtfMontoAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlErrorMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfRif, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlErrorRif, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlErrorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtfFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlErrorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfRif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlErrorRif)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlErrorNombre)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfMontoAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlErrorMonto)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlErrorFecha)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlErrorUbicacion))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbPais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbPaisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbPaisItemStateChanged
        
    }//GEN-LAST:event_jcbPaisItemStateChanged

    private void jcbPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPaisActionPerformed
        Lugar.llenarComboEstados(conector, jcbEstado,(String)jcbPais.getSelectedItem());
        Lugar.llenarComboMunicipios(conector, jcbMunicipio, "");
        Lugar.llenarComboParroquias(conector, jcbParroquia, "","");
        if(jcbPais.getSelectedIndex()>0){
            jcbEstado.setEnabled(true);
            jcbMunicipio.setEnabled(false);
            jcbParroquia.setEnabled(false);
        }
        else{
            jcbEstado.setEnabled(false);
            jcbMunicipio.setEnabled(false);
            jcbParroquia.setEnabled(false);
        }
        
    }//GEN-LAST:event_jcbPaisActionPerformed

    private void jcbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbEstadoActionPerformed
        Lugar.llenarComboMunicipios(conector, jcbMunicipio, (String) jcbEstado.getSelectedItem());
        Lugar.llenarComboParroquias(conector, jcbParroquia, "","");
        if(jcbPais.getSelectedIndex()>0){
            jcbMunicipio.setEnabled(true);
            jcbParroquia.setEnabled(false);
        }
        else{
            jcbMunicipio.setEnabled(false);
            jcbParroquia.setEnabled(false);
        }
    }//GEN-LAST:event_jcbEstadoActionPerformed

    private void jcbParroquiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbParroquiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbParroquiaActionPerformed

    private void jcbMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMunicipioActionPerformed
        Lugar.llenarComboParroquias(conector, jcbParroquia,(String) jcbMunicipio.getSelectedItem() ,(String)jcbEstado.getSelectedItem());
        if(jcbPais.getSelectedIndex()>0){
            jcbParroquia.setEnabled(true);
        }
        else{
            jcbParroquia.setEnabled(false);
        }
    }//GEN-LAST:event_jcbMunicipioActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        PrincipalClientes nuevoPanel = new PrincipalClientes(conector,contenedor);
        contenedor.removeAll();
        contenedor.add(nuevoPanel);
        contenedor.updateUI();  
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> jcbEstado;
    private javax.swing.JComboBox<String> jcbMunicipio;
    private javax.swing.JComboBox<String> jcbPais;
    private javax.swing.JComboBox<String> jcbParroquia;
    private javax.swing.JLabel jlErrorFecha;
    private javax.swing.JLabel jlErrorMonto;
    private javax.swing.JLabel jlErrorNombre;
    private javax.swing.JLabel jlErrorRif;
    private javax.swing.JLabel jlErrorUbicacion;
    private javax.swing.JTextField jtfFechaInicio;
    private javax.swing.JTextField jtfMontoAcreditado;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfRif;
    // End of variables declaration//GEN-END:variables

    private void llenarDatosCliente() {
        jtfRif.setText(String.valueOf(c.getCli_rif()));
        jtfRif.setEnabled(false);
        jtfNombre.setText(c.getCli_nombre());
        jtfMontoAcreditado.setText(String.valueOf(c.getCli_monto_acreditado()));
        jtfFechaInicio.setText(c.getCli_fecha_inicio().toString());
    }
}
