/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adaptadores;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alexd
 */
public class MensajeUI implements Runnable{
    JPanel mensajeError;

    public MensajeUI(JPanel Panelmensaje, String mensaje, int tipo) {
        this.mensajeError = Panelmensaje;
        if(tipo==1){
            mensajeError.setBackground(AdaptadorSQLUI.fondoExito);
        }
        if(tipo==0){
            mensajeError.setBackground(AdaptadorSQLUI.fondoError);
        }
        JLabel texto = (JLabel)mensajeError.getComponent(1);
        JLabel imagen = (JLabel)mensajeError.getComponent(0);
        imagen.setVisible(false);
        texto.setText(mensaje);
    }
    
    
    @Override
    public void run() {
        mensajeError.setVisible(true);
        try{Thread.sleep( 4000 );
    }   catch (InterruptedException ex) {
            Logger.getLogger(MensajeUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        mensajeError.setVisible(false);
    }
    
}
