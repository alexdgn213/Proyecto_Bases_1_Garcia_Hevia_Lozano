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
import Dominio.Factura;
import Dominio.Lote_material;
import Dominio.Proveedor;
import Dominio.mat_pro;
import Dominio.modelo_aeronave;
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
public class PrincipalVender extends javax.swing.JPanel {
    ConectorDB conector;
    JPanel contenedor;
    modelo_aeronave m;
    Cliente c;
    int lock;
    JPanel panelMensaje;
    List<Lote_material> compras;
    int montoTotal;

    /**
     * Creates new form PrincipalClientes
     */
    public PrincipalVender(ConectorDB conector,JPanel contenedor,JPanel panelMensaje) {
        this.conector = conector;
        this.contenedor = contenedor;
        this.panelMensaje = panelMensaje;
        initComponents();
        this.setSize(850,580);
        jScrollPane1.getViewport().setBackground(AdaptadorSQLUI.fondoTablas);
        lock=1;
        modelo_aeronave.llenarComboBox(conector, jcbModelos);
        Cliente.llenarComboBox(conector, jcbCliente);
        lock=0;
        panelComprar.setVisible(false);
        this.compras = new ArrayList<Lote_material>();

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
        jcbCliente = new javax.swing.JComboBox<>();
        panelComprar = new javax.swing.JPanel();
        jbNuevo = new javax.swing.JButton();
        jbEliminat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompras = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtfcantidad = new javax.swing.JTextField();
        jcbModelos = new javax.swing.JComboBox<>();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(850, 580));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(850, 580));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 66, 66));
        jLabel1.setText("Seleccione un cliente: ");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(66, 66, 66));
        jLabel2.setText("Realizar una venta:");
        jLabel2.setToolTipText("");

        jcbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbClienteActionPerformed(evt);
            }
        });

        jbNuevo.setBackground(new java.awt.Color(255, 255, 255));
        jbNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_add_black_48dp_1x.png"))); // NOI18N
        jbNuevo.setContentAreaFilled(false);
        jbNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNuevoActionPerformed(evt);
            }
        });

        jbEliminat.setBackground(new java.awt.Color(255, 255, 255));
        jbEliminat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_delete_black_48dp_1x.png"))); // NOI18N
        jbEliminat.setToolTipText("");
        jbEliminat.setContentAreaFilled(false);
        jbEliminat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEliminatActionPerformed(evt);
            }
        });

        tablaCompras.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaCompras.setMaximumSize(new java.awt.Dimension(2147483647, 5000));
        jScrollPane1.setViewportView(tablaCompras);

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 66, 66));
        jLabel3.setText("Lista Modelos:");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(66, 66, 66));
        jLabel4.setText("Cantidad: ");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(66, 66, 66));
        jLabel5.setText("Agregar Modelo: ");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(66, 66, 66));
        jLabel6.setText("Modelo: ");
        jLabel6.setToolTipText("");

        jtfcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfcantidadActionPerformed(evt);
            }
        });

        jcbModelos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelComprarLayout = new javax.swing.GroupLayout(panelComprar);
        panelComprar.setLayout(panelComprarLayout);
        panelComprarLayout.setHorizontalGroup(
            panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComprarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelComprarLayout.createSequentialGroup()
                        .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelComprarLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfcantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jcbModelos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEliminat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
        );
        panelComprarLayout.setVerticalGroup(
            panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComprarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelComprarLayout.createSequentialGroup()
                        .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbModelos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jbNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelComprarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbEliminat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(163, 163, 163))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel2)
                    .addContainerGap(626, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(panelComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel2)
                    .addContainerGap(530, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuevoActionPerformed
        /*
        int fila = tablaModelos.getSelectedRow();
        if (fila>=0){
            int id = (Integer) tablaModelos.getValueAt(fila, 0);
            int cantidad = Integer.parseInt(jtfcantidad.getText());
            int codigoCompra= (Integer)tablaModelos.getValueAt(fila,3);
            int precio=(Integer)tablaModelos.getValueAt(fila,2);
            //Lote_material l = new Lote_material(precio*cantidad,Date.valueOf(LocalDate.now()),cantidad,id,p.getPro_rif(),codigoCompra);
            //añadirAFactura(l);
        }
        */
        
        
    }//GEN-LAST:event_jbNuevoActionPerformed

    private void jbEliminatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEliminatActionPerformed
        int fila = tablaCompras.getSelectedRow();
        if (fila>=0){
            int id = (Integer) tablaCompras.getValueAt(fila, 0);
            borrarDeFactura(id); 
        }
        
    }//GEN-LAST:event_jbEliminatActionPerformed

    private void jcbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbClienteActionPerformed
        if(lock==0){            
            String nombreCliente = jcbCliente.getSelectedItem().toString();
            c = Cliente.buscarPorNombre(conector, nombreCliente);
            cargarCliente();
        }
    }//GEN-LAST:event_jcbClienteActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        contenedor.setVisible(false);
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void jtfcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfcantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfcantidadActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        comprar();
        contenedor.setVisible(false);
    }//GEN-LAST:event_botonGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbEliminat;
    private javax.swing.JButton jbNuevo;
    private javax.swing.JComboBox<String> jcbCliente;
    private javax.swing.JComboBox<String> jcbModelos;
    private javax.swing.JTextField jtfcantidad;
    private javax.swing.JPanel panelComprar;
    private javax.swing.JTable tablaCompras;
    // End of variables declaration//GEN-END:variables

    private void cargarCliente() {
        panelComprar.setVisible(true);
        //mat_pro.llenarTablaMaterialesDeProveedor(conector, tablaModelos, m.get_mod_codigo());
        this.compras.clear();
        actualizarTabla();
    }
    
    public void comprar(){
        Factura f = new Factura(montoTotal);
        f.agregarADB(conector);
        for(Lote_material l : compras){
            l.setFk_fac_codigo(f.getFac_codigo());
            l.agregarADB(conector);
        }
        new Thread(new MensajeUI(panelMensaje,"Se han generado la factura y las pruebas.",1)).start();
    }
    
    private void actualizarTabla() {
        try {
            montoTotal = 0;
        int lleno = 0;
        //Para establecer el modelo al JTable
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Material");
        modelo.addColumn("Cantidad");
        modelo.addColumn("PrecioTotal");
        
        //Creando las filas para el JTable
        for(Lote_material l: compras) {
            lleno=1;
            Object[] fila = new Object[3];
            fila[0] = l.getFk_mat_codigo();
            fila[1] = l.getLot_cantidad();
            fila[2] = l.getLot_precio();
            montoTotal+=l.getLot_precio();
            modelo.addRow(fila);
           }
        if(lleno==1) tablaCompras.setModel(modelo);
        else{
            modelo = new DefaultTableModel();
            modelo.addColumn("Esta Tabla esta Vacia");
            tablaCompras.setModel(modelo);
        }
       } catch (Exception ex) {
        ex.printStackTrace();
       }
    }

    private void añadirAFactura(Lote_material l) {
        int nuevo = 1;
        for(Lote_material lote: compras){
            if (lote.getFk_mat_codigo()==l.getFk_mat_codigo()){
                lote.setLot_cantidad(l.getLot_cantidad());
                lote.setLot_precio(l.getLot_precio());
                nuevo=0;
            }
        }
        if(nuevo==1) compras.add(l);
        actualizarTabla();
    }
    
    private void borrarDeFactura(int codigo) {
        Lote_material loteBorrar= null;
        for(Lote_material lote: compras){
            if (lote.getFk_mat_codigo()==codigo){
                loteBorrar = lote;
            }
        }
        if(loteBorrar!=null) compras.remove(loteBorrar);
        actualizarTabla();
    }
}
