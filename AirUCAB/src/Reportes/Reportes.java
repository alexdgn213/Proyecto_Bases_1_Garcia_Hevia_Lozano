/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author alexd
 */
public class Reportes {
    public void ReporteProveedor() throws SQLException,JRException {
        Connection a;
        a=DriverManager.getConnection("jdbc:postgresql://localhost/AirUcabPrueba");
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("jdbc:postgresql://localhost/AirUcabPrueba");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,a);
        JasperViewer ver= new JasperViewer(print);
        ver.setTitle("Proveedores");
        ver.setVisible(true);
    }
    
    public void ReporteModelosDeAviones() throws SQLException,JRException {
        Connection a;
        a=DriverManager.getConnection("jdbc:postgresql://localhost/AirUcabPrueba");
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("jdbc:postgresql://localhost/AirUcabPrueba");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,a);
        JasperViewer ver= new JasperViewer(print);
        ver.setTitle("ModelosDeAviones");
        ver.setVisible(true);
    }
    
    public void ReporteModeloMasVendido() throws SQLException,JRException {
        Connection a;
        a=DriverManager.getConnection("jdbc:postgresql://localhost/AirUcabPrueba");
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("jdbc:postgresql://localhost/AirUcabPrueba");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,a);
        JasperViewer ver= new JasperViewer(print);
        ver.setTitle("ModeloMasVendido");
        ver.setVisible(true);
    }
    
    public void ReporteMaterialMasVendido() throws SQLException,JRException {
        Connection a;
        a=DriverManager.getConnection("jdbc:postgresql://localhost/AirUcabPrueba");
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("jdbc:postgresql://localhost/AirUcabPrueba");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,a);
        JasperViewer ver= new JasperViewer(print);
        ver.setTitle("MaterialMasVendido");
        ver.setVisible(true);
    }
    
}
