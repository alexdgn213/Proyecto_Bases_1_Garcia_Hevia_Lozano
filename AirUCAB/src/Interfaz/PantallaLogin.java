/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Adaptadores.Comprobador;
import Adaptadores.ConectorDB;
import Dominio.Usuario;
import javax.swing.UIManager;  

/**
 *
 * @author alexd
 */
public class PantallaLogin extends javax.swing.JFrame {
    ConectorDB conector;
    

    /**
     * Creates new form PruebaLogin
     */
    public PantallaLogin() {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        } 
        initComponents();
        jlUsuario.setVisible(false);
        jlClave.setVisible(false);
        conector = new ConectorDB();
        conector.conectar();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bAcceder = new javax.swing.JButton();
        tfUsuario = new javax.swing.JTextField();
        tfClave = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jlUsuario = new javax.swing.JLabel();
        jlClave = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);

        jLabel1.setForeground(new java.awt.Color(84, 110, 122));
        jLabel1.setText("Usuario:");
        jLabel1.setToolTipText("");

        bAcceder.setBackground(new java.awt.Color(255, 255, 255));
        bAcceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/ic_arrow_forward_black_24dp_2x.png"))); // NOI18N
        bAcceder.setMargin(new java.awt.Insets(14, 14, 14, 14));
        bAcceder.setMaximumSize(new java.awt.Dimension(421, 269));
        bAcceder.setMinimumSize(new java.awt.Dimension(421, 269));
        bAcceder.setOpaque(false);
        bAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAccederActionPerformed(evt);
            }
        });

        tfUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUsuarioActionPerformed(evt);
            }
        });

        tfClave.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel2.setForeground(new java.awt.Color(84, 110, 122));
        jLabel2.setText("Clave:");
        jLabel2.setToolTipText("");

        jlUsuario.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        jlUsuario.setForeground(new java.awt.Color(244, 67, 54));
        jlUsuario.setText("Usuario no encontrado");

        jlClave.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        jlClave.setForeground(new java.awt.Color(244, 67, 54));
        jlClave.setText("Clave Incorrecta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bAcceder, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfUsuario)
                    .addComponent(tfClave, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlUsuario)
                    .addComponent(jlClave))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlUsuario))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfClave, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlClave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(bAcceder, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 260));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Imagenes/FondoLogin.png"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAccederActionPerformed
        String usuario = tfUsuario.getText();
        String clave = tfClave.getText();
        jlUsuario.setVisible(false);
        jlClave.setVisible(false);
        Usuario usuarioActual = Usuario.loguearUsuario(conector, usuario);
        if(usuarioActual!=null && Comprobador.ComprobarString(tfUsuario, jlUsuario)){
            if(usuarioActual.getUsu_clave().equals(clave)){
                acceder();
            }
            else{
                jlClave.setVisible(true);
            }
        }
        else{
            jlUsuario.setVisible(true); 
        }
        acceder();
    }//GEN-LAST:event_bAccederActionPerformed

    private void tfUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUsuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaLogin().setVisible(true);
            }
        });
    }

    private void acceder(){
        PantallaPrincipal pantallaNueva = new PantallaPrincipal(conector);
        this.dispose();
        pantallaNueva.setVisible(true);
        }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAcceder;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlClave;
    private javax.swing.JLabel jlUsuario;
    private javax.swing.JTextField tfClave;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}
