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
import Dominio.Aeronave;
import Dominio.Cliente;
import Dominio.Estatus;
import Dominio.Informacion_contacto;
import Dominio.Lote_material;
import Dominio.Lugar;
import Dominio.Material;
import Dominio.Pieza;
import Dominio.Proveedor;
import Dominio.Pru_aer;
import Dominio.Pru_pie;
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
public class DetalleAvion extends javax.swing.JPanel {
    ConectorDB conector;
    JPanel contenedor;
    Aeronave a;
    JPanel panelMensaje;
    String modelo;
    String cliente;
    ArrayList<String> permisos;

    /**
     * Creates new form PrincipalClientes
     */
    public DetalleAvion(ConectorDB conector,JPanel contenedor,int id,String modelo, String cliente, JPanel panelMensaje, boolean pendiente,ArrayList<String> permisos) {
        this.conector = conector;
        this.contenedor = contenedor;
        this.modelo=modelo;
        this.cliente=cliente;
        this.panelMensaje = panelMensaje;
        this.permisos=permisos;
        initComponents();
        jlErrorFecha.setVisible(false);
        jScrollPane2.getViewport().setBackground(AdaptadorSQLUI.fondoScrolls);
        jScrollPane3.getViewport().setBackground(AdaptadorSQLUI.fondoTablas);
        jbModificar1.setEnabled(permisos.contains("rpieza"));
        bAddInf.setEnabled(permisos.contains("cpru_aer"));
        Estatus.llenarComboBox(conector, jcbEstatus);
        Prueba.llenarComboBox(conector, jcbPrueba);
        this.setSize(870, 610);
        a = Aeronave.buscarPorCodigo(conector, id);
        if (a==null){
            panelInformacion.setVisible(false);
        }
        else{
            llenarDatosLote();    
        }
        if(!pendiente){
            botonGuardar.setVisible(false);
            bAddInf.setVisible(false);
            panelNueva.setVisible(false);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtCodigo = new javax.swing.JTextField();
        jtfMaterial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfProveedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfFechaCompra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        panelInformacion = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPruebas = new javax.swing.JTable();
        panelNueva = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jcbPrueba = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jlEstimado = new javax.swing.JLabel();
        jcbEstatus = new javax.swing.JComboBox<>();
        jtfFechaRealizacion = new javax.swing.JTextField();
        jlErrorFecha = new javax.swing.JLabel();
        bAddInf = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaPiezas = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jbModificar1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jtfCantidad = new javax.swing.JTextField();
        botonVolver = new javax.swing.JButton();
        jl = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(870, 610));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(870, 610));

        jScrollPane2.setMaximumSize(new java.awt.Dimension(1870, 1610));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(870, 610));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(850, 810));

        jPanel1.setMaximumSize(new java.awt.Dimension(850, 32767));
        jPanel1.setMinimumSize(new java.awt.Dimension(850, 740));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(850, 840));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 66, 66));
        jLabel1.setText("Avion:");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel2.setText("Codigo:");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("Modelo:");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText("Cliente:");
        jLabel4.setToolTipText("");

        jtfFechaCompra.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setText("Fecha Compra:");
        jLabel5.setToolTipText("");

        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_check_black_48dp_1x.png"))); // NOI18N
        botonGuardar.setContentAreaFilled(false);
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        panelInformacion.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Pruebas:");
        jLabel8.setToolTipText("");

        tablaPruebas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPruebas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPruebasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaPruebas);

        panelNueva.setOpaque(false);

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel22.setText("Nueva:");
        jLabel22.setToolTipText("");

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel23.setText("Prueba:");
        jLabel23.setToolTipText("");

        jcbPrueba.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel24.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel24.setText("Estatus:");
        jLabel24.setToolTipText("");

        jlEstimado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jlEstimado.setText("Estimado:");
        jlEstimado.setToolTipText("");

        jcbEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlErrorFecha.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jlErrorFecha.setForeground(new java.awt.Color(255, 0, 0));
        jlErrorFecha.setText("Error en el rif");
        jlErrorFecha.setToolTipText("");

        bAddInf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_arrow_upward_black_24dp_1x.png"))); // NOI18N
        bAddInf.setContentAreaFilled(false);
        bAddInf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddInfActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel25.setText("Fecha Realizacion:");
        jLabel25.setToolTipText("");

        javax.swing.GroupLayout panelNuevaLayout = new javax.swing.GroupLayout(panelNueva);
        panelNueva.setLayout(panelNuevaLayout);
        panelNuevaLayout.setHorizontalGroup(
            panelNuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNuevaLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bAddInf, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNuevaLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(panelNuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelNuevaLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbEstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelNuevaLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelNuevaLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelNuevaLayout.createSequentialGroup()
                                .addComponent(jtfFechaRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlErrorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(21, Short.MAX_VALUE))))
        );
        panelNuevaLayout.setVerticalGroup(
            panelNuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAddInf, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFechaRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlErrorFecha)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jlEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tablaPiezas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPiezas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPiezasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaPiezas);

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Piezas:");
        jLabel9.setToolTipText("");

        jbModificar1.setBackground(new java.awt.Color(255, 255, 255));
        jbModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_search_black_24dp_1x.png"))); // NOI18N
        jbModificar1.setToolTipText("");
        jbModificar1.setContentAreaFilled(false);
        jbModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbModificar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInformacionLayout = new javax.swing.GroupLayout(panelInformacion);
        panelInformacion.setLayout(panelInformacionLayout);
        panelInformacionLayout.setHorizontalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformacionLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformacionLayout.createSequentialGroup()
                        .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3))
                        .addGap(18, 18, 18)
                        .addComponent(jbModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(panelInformacionLayout.createSequentialGroup()
                        .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelInformacionLayout.setVerticalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformacionLayout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setText("Precio:");
        jLabel6.setToolTipText("");

        jtfCantidad.setToolTipText("");

        botonVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_arrow_back_black_24dp_2x.png"))); // NOI18N
        botonVolver.setContentAreaFilled(false);
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        jl.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jl.setText("Fecha Compra:");
        jl.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 157, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jtfMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(panelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtfProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtfFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jl, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(86, 86, 86))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        PrincipalAviones nuevoPanel = new PrincipalAviones(conector,contenedor,panelMensaje,permisos);
        contenedor.removeAll();
        contenedor.add(nuevoPanel);
        contenedor.updateUI();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void jbModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificar1ActionPerformed
        int fila = tablaPiezas.getSelectedRow();
        if (fila>=0){
            int id = (Integer) tablaPiezas.getValueAt(fila, 0);
            String nombre = tablaPiezas.getValueAt(fila, 2).toString();
            String tipo = tablaPiezas.getValueAt(fila, 3).toString();
            DetallePieza nuevoPanel = new DetallePieza(conector,contenedor,id,panelMensaje,nombre,tipo,permisos);
            contenedor.removeAll();
            contenedor.add(nuevoPanel);
            contenedor.updateUI();
        }
    }//GEN-LAST:event_jbModificar1ActionPerformed

    private void tablaPiezasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPiezasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaPiezasMouseClicked

    private void bAddInfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddInfActionPerformed

        jlErrorFecha.setVisible(false);
        boolean A = Comprobador.ComprobarDate(jtfFechaRealizacion, jlErrorFecha);
        if (A) {
            if(jcbPrueba.getSelectedIndex()>0 && jcbEstatus.getSelectedIndex()>0){
                Pru_aer relacion = Pru_aer.relacionDada(conector,jcbPrueba.getSelectedIndex(), a.getAer_codigo());
                if(relacion == null){
                    relacion = new Pru_aer( Date.valueOf(jtfFechaRealizacion.getText()),
                        jcbPrueba.getSelectedIndex(),jcbEstatus.getSelectedIndex(),a.getAer_codigo());
                    relacion.agregarADB(conector);
                }
                else{
                    relacion.setFk_est_codigo(jcbEstatus.getSelectedIndex());
                    relacion.setPru_aer_fecha_realizacion(Date.valueOf(jtfFechaRealizacion.getText()));
                    relacion.modificarEnDB(conector);
                }
            }
            Pru_aer.llenarTablaAvion(conector, tablaPruebas, a.getAer_codigo());
        }else {new Thread(new MensajeUI(panelMensaje,"Los datos ingresados no son correctos",0)).start();}
    }//GEN-LAST:event_bAddInfActionPerformed

    private void tablaPruebasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPruebasMouseClicked
        int fila = tablaPruebas.getSelectedRow();
        if (fila>=0){
            int id = (Integer) tablaPruebas.getValueAt(fila, 0);
            Pru_aer pa = Pru_aer.buscarPorCodigo(conector, id);
            jlEstimado.setText("Estimado: "+pa.getPru_aer_fecha_estimada().toString());
            jcbPrueba.setSelectedIndex(pa.getFk_pru_codigo());
            jcbEstatus.setSelectedIndex(pa.getFk_est_codigo());
            jtfFechaRealizacion.setText(pa.getPru_aer_fecha_realizacion().toString());
        }
    }//GEN-LAST:event_tablaPruebasMouseClicked

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        /*
        int fk_lugar = 0;
        if (c==null){
            Cliente c = new Cliente(Integer.parseInt(jtCodigo.getText()),jtfMaterial.getText(),Integer.parseInt(jtfProveedor.getText()),Date.valueOf(jtfFechaCompra.getText()),fk_lugar);
            c.agregarADB(conector);
            new Thread(new MensajeUI(panelMensaje,"Cliente agregado exitosamente",1)).start();
        }
        else{
            c.setCli_rif(Integer.parseInt(jtCodigo.getText()));
            c.setCli_nombre(jtfMaterial.getText());
            c.setCli_monto_acreditado(Integer.parseInt(jtfProveedor.getText()));
            c.setCli_fecha_inicio(Date.valueOf(jtfFechaCompra.getText()));
            c.setFk_lug_codigo(fk_lugar);
            c.modificarEnDB(conector);
            new Thread(new MensajeUI(panelMensaje,"Cliente modificado exitosamente",1)).start();
        }

        if(pru_lot.pruebasListasLote(conector, l.getLot_codigo())){
            PrincipalLotes nuevoPanel = new PrincipalLotes(conector,contenedor,panelMensaje);
            contenedor.removeAll();
            contenedor.add(nuevoPanel);
            contenedor.updateUI();
            //new Thread(new MensajeUI(panelMensaje,"Se han cargado al inventario los materiales",0)).start();
        }
        else{
            new Thread(new MensajeUI(panelMensaje,"Aun no se han completado todas las pruebas",0)).start();

        }
        */

    }//GEN-LAST:event_botonGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddInf;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jbModificar1;
    private javax.swing.JComboBox<String> jcbEstatus;
    private javax.swing.JComboBox<String> jcbPrueba;
    private javax.swing.JLabel jl;
    private javax.swing.JLabel jlErrorFecha;
    private javax.swing.JLabel jlEstimado;
    private javax.swing.JTextField jtCodigo;
    private javax.swing.JTextField jtfCantidad;
    private javax.swing.JTextField jtfFechaCompra;
    private javax.swing.JTextField jtfFechaRealizacion;
    private javax.swing.JTextField jtfMaterial;
    private javax.swing.JTextField jtfProveedor;
    private javax.swing.JPanel panelInformacion;
    private javax.swing.JPanel panelNueva;
    private javax.swing.JTable tablaPiezas;
    private javax.swing.JTable tablaPruebas;
    // End of variables declaration//GEN-END:variables

    private void llenarDatosLote() {
        jtCodigo.setText(String.valueOf(a.getAer_codigo()));
        jtCodigo.setEnabled(false);
        jtfMaterial.setText(modelo);
        jtfMaterial.setEnabled(false);
        jtfProveedor.setText(cliente);
        jtfProveedor.setEnabled(false);
        jtfFechaCompra.setText(a.getAer_fecha_compra().toString());
        jtfFechaCompra.setEnabled(false);
        jtfCantidad.setText(String.valueOf(a.getAer_precio_compra()));
        jtfCantidad.setEnabled(false);
        Pru_aer.llenarTablaAvion(conector, tablaPruebas, a.getAer_codigo());
        Pieza.llenarTablaDeAvion(conector, tablaPiezas,a.getAer_codigo());
        
    }
    
}
