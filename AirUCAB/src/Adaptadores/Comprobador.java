/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Adaptadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Veronica Hevia
 */

public class Comprobador{
   
    public static boolean ComprobarInt(JTextField dato,JLabel error){
        Pattern pat = Pattern.compile("[0-9]*");
        String dat = dato.getText();
        Matcher mat = pat.matcher(dat);
        if(!mat.matches()||(dat.length()==0)){
            error.setText("Debe ingresar un número");
            dato.setText("");
            error.setVisible(true);
            return false;
        }
    return true;
    }

    public static boolean ComprobarString(JTextField dato,JLabel error){
        Pattern pat = Pattern.compile("[-a-zA-z0-9 ]*");
        String dat = dato.getText();
        Matcher mat = pat.matcher(dat);
        if(!mat.matches()||(dat.length()==0)){
            error.setText("Texto no válido");
            dato.setText(""); 
            error.setVisible(true);
            return false;
        }
    return true;
    }

    public static boolean ComprobarDate(JTextField dato,JLabel error){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dat = dato.getText();
        if (!(dat.length()==0)){
            try {
               simpleDateFormat.parse(dat);
                return true;
            } catch (ParseException ex) {
                error.setText("Formato no válido");
                dato.setText("");
                error.setVisible(true);
                return false;
            }
        }else{ 
            error.setVisible(true);
            return false;  
        }
    }
}
