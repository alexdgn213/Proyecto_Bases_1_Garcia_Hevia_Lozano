/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Adaptadores.ConectorDB;
import Dominio.Usuario;
import java.awt.*;
import javax.swing.ImageIcon;


/**
 *
 * @author alexd
 */
public class PanelLogin extends javax.swing.JPanel {
    ConectorDB conector;

    /**
     * Creates new form PanelLogin
     */
    public PanelLogin() {
        initComponents();
        conector = new ConectorDB();
        conector.conectar();
        this.setSize(411,259);
    }
    @Override
    public void paintComponent(Graphics g){
        Dimension tam = getSize();
        ImageIcon imagenFondo = new ImageIcon(new ImageIcon(getClass().getResource("/Interfaz/Imagenes/FondoLogin.png")).getImage());
        g.drawImage(imagenFondo.getImage(),0,0,tam.width,tam.height,null);
        setOpaque(false);
        super.paintComponent(g);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfClave = new javax.swing.JTextField();
        tfUsuario = new javax.swing.JTextField();
        bAcceder = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(451, 299));
        setPreferredSize(new java.awt.Dimension(451, 299));

        jLabel1.setForeground(new java.awt.Color(84, 110, 122));
        jLabel1.setText("Usuario:");
        jLabel1.setToolTipText("");

        jLabel2.setForeground(new java.awt.Color(84, 110, 122));
        jLabel2.setText("Clave:");
        jLabel2.setToolTipText("");

        tfUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUsuarioActionPerformed(evt);
            }
        });

        bAcceder.setBackground(new java.awt.Color(255, 255, 255));
        bAcceder.setText("Acceder");
        bAcceder.setMargin(new java.awt.Insets(14, 14, 14, 14));
        bAcceder.setMaximumSize(new java.awt.Dimension(421, 269));
        bAcceder.setMinimumSize(new java.awt.Dimension(421, 269));
        bAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAccederActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfClave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Fondo)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(bAcceder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Fondo)
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfClave, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bAcceder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUsuarioActionPerformed

    private void bAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAccederActionPerformed
        String usuario = tfUsuario.getText();
        String clave = tfClave.getText();
        Usuario usuarioActual = Usuario.loguearUsuario(conector, usuario);
        if(usuarioActual!=null){
            System.out.println("Usuario Encontrado");
            if(usuarioActual.getUsu_clave().equals(clave)){
                System.out.println("Usuario Logueado");
            }
            else{
                System.out.println("Clave Incorrecta");
            }
        }
        else{
            System.out.println("Usuario no encontrado");
        }
    }//GEN-LAST:event_bAccederActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton bAcceder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tfClave;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}
