/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Adaptadores.ConectorDB;
import Dominio.Cliente;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author alexd
 */
public class PantallaPrincipal extends javax.swing.JFrame {
    ConectorDB conector;
    JPanel nuevoPanel;

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal(ConectorDB conector) {
        this.conector= conector;
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        } 
        initComponents();
        Contenido.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LateralExpandido = new javax.swing.JPanel();
        fondoLateralExpandido = new javax.swing.JLabel();
        panelLateralExtendido = new javax.swing.JPanel();
        menuInicio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuClientes = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        menuProveedores = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Contenido = new javax.swing.JPanel();
        panelContenido = new javax.swing.JPanel();
        fondoContenido = new javax.swing.JLabel();
        jlTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondoLateralExpandido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/BarraLateralExtendida.png"))); // NOI18N

        menuInicio.setPreferredSize(new java.awt.Dimension(162, 30));
        menuInicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                menuInicioFocusGained(evt);
            }
        });
        menuInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuInicioMouseClicked(evt);
            }
        });
        menuInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 66, 66));
        jLabel1.setText("Inicio");
        jLabel1.setToolTipText("");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        menuInicio.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 6, -1, -1));

        menuClientes.setPreferredSize(new java.awt.Dimension(162, 30));
        menuClientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                menuClientesFocusGained(evt);
            }
        });
        menuClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuClientesMouseClicked(evt);
            }
        });
        menuClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(66, 66, 66));
        jLabel4.setText("Clientes");
        jLabel4.setToolTipText("");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        menuClientes.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 6, -1, -1));

        menuProveedores.setPreferredSize(new java.awt.Dimension(162, 30));
        menuProveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 66, 66));
        jLabel3.setText("Proveedores");
        jLabel3.setToolTipText("");
        menuProveedores.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 6, -1, -1));

        javax.swing.GroupLayout panelLateralExtendidoLayout = new javax.swing.GroupLayout(panelLateralExtendido);
        panelLateralExtendido.setLayout(panelLateralExtendidoLayout);
        panelLateralExtendidoLayout.setHorizontalGroup(
            panelLateralExtendidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLateralExtendidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLateralExtendidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLateralExtendidoLayout.setVerticalGroup(
            panelLateralExtendidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLateralExtendidoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(menuInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LateralExpandidoLayout = new javax.swing.GroupLayout(LateralExpandido);
        LateralExpandido.setLayout(LateralExpandidoLayout);
        LateralExpandidoLayout.setHorizontalGroup(
            LateralExpandidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LateralExpandidoLayout.createSequentialGroup()
                .addComponent(panelLateralExtendido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(fondoLateralExpandido)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        LateralExpandidoLayout.setVerticalGroup(
            LateralExpandidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LateralExpandidoLayout.createSequentialGroup()
                .addComponent(fondoLateralExpandido)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(LateralExpandidoLayout.createSequentialGroup()
                .addComponent(panelLateralExtendido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(LateralExpandido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 43, 180, 672));

        Contenido.setOpaque(false);
        Contenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setOpaque(false);

        javax.swing.GroupLayout panelContenidoLayout = new javax.swing.GroupLayout(panelContenido);
        panelContenido.setLayout(panelContenidoLayout);
        panelContenidoLayout.setHorizontalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        panelContenidoLayout.setVerticalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        Contenido.add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 850, 580));

        fondoContenido.setBackground(new java.awt.Color(255, 255, 255));
        fondoContenido.setOpaque(true);
        Contenido.add(fondoContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 1000));

        getContentPane().add(Contenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 870, 600));

        jlTitulo.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jlTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jlTitulo.setText("Inicio");
        jlTitulo.setToolTipText("");
        getContentPane().add(jlTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/FondoPrincipal.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 710));
        jLabel2.getAccessibleContext().setAccessibleName("Fondo");
        jLabel2.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuInicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_menuInicioFocusGained

    }//GEN-LAST:event_menuInicioFocusGained

    private void menuClientesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_menuClientesFocusGained
        
    }//GEN-LAST:event_menuClientesFocusGained

    private void menuClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuClientesMouseClicked
        Contenido.setVisible(true);
        nuevoPanel = new PrincipalClientes(conector);
        panelContenido.add(nuevoPanel);
        panelContenido.updateUI();
        
    }//GEN-LAST:event_menuClientesMouseClicked

    private void menuInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuInicioMouseClicked
        Contenido.setVisible(false);
    }//GEN-LAST:event_menuInicioMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Contenido.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Contenido.setVisible(true);
        nuevoPanel = new PrincipalClientes(conector);
        panelContenido.add(nuevoPanel);
        panelContenido.updateUI();
    }//GEN-LAST:event_jLabel4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Contenido;
    private javax.swing.JPanel LateralExpandido;
    private javax.swing.JLabel fondoContenido;
    private javax.swing.JLabel fondoLateralExpandido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JPanel menuClientes;
    private javax.swing.JPanel menuInicio;
    private javax.swing.JPanel menuProveedores;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelLateralExtendido;
    // End of variables declaration//GEN-END:variables
}
