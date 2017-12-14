/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adaptadores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAB_L11
 */
public class ConectorDB {
    public Connection conexion;
    
    public void conectar(){
            conexion = null;
            String urlDatabase =  "jdbc:postgresql://localhost/AirUcabPrueba"; 
            try {
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(urlDatabase,  "alex", "123456");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            
    }
    
    public void desconectar(){
        if(conexion!=null) try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

}
