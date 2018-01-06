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
    public static void ReporteProveedor(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/Proveedores.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print);
        ver.setTitle("Proveedores");
        ver.setVisible(true);
    }
    
    public static void ReporteModelosDeAviones(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/ModelosDeAviones.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print);
        ver.setTitle("Modelos de Aviones");
        ver.setVisible(true);
    }
    
    public static void ReporteModeloMasVendido(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/ModeloMasVendido.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print);
        ver.setTitle("Modelo mas vendido");
        ver.setVisible(true);
    }
    
    public static void ReporteMaterialMasVendido() throws SQLException,JRException {
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
