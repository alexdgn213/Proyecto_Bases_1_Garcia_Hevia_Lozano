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
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Proveedores");
        ver.setVisible(true);
    }
    
    public static void ReporteModelosDeAviones(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/ModelosDeAviones.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Modelos de Aviones");
        ver.setVisible(true);
    }
    
    public static void ReporteModeloMasVendido(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/ModeloMasVendido.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Modelo mas vendido");
        ver.setVisible(true);
    }
    
    public static void ReporteMaterialMasVendido(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/MaterialMasVendido.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Material Mas Solicitado");
        ver.setVisible(true);
    }
    
    public static void ReporteProductosNoCalificados(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/ProductosNoCalificados.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Productos No Calificados");
        ver.setVisible(true);
    }
    
    public static void ReporteTop10Clientes(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/Top10Clientes.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Top 10 Clientes");
        ver.setVisible(true);
    }
    
}
