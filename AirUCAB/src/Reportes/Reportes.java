/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
        ver.setTitle("Lista de Proveedores");
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
    
    public static void ReporteEvolucion(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/EvolucionAeronautica.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Evolución de la Aeronautica");
        ver.setVisible(true);
    }
    
    public static void ReporteAlaMasUtilizada(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/AlaMasUtilizada.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Tipo de ala Más Utilizada");
        ver.setVisible(true);
    }
    
    public static void ReporteDescripcionPiezas(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/DescripcionPiezas.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Descripción de las piezas");
        ver.setVisible(true);
    }
    
    public static void ReporteInventarioMensual(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/InventarioMensual.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Inventario Mensual");
        ver.setVisible(true);
    }
    
    public static void ReporteProduccionAnual(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/ProduccionAnual.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Produccion Anual");
        ver.setVisible(true);
    }
    
    public static void ReporteCantidadMediaAviones(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/CantidadMediaAviones.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Media de Aviones Mensualmente");
        ver.setVisible(true);
    }
    
    public static void ReportePromedioProduccionMensual(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/PromedioProduccionMensual.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Promedio de producción mensual");
        ver.setVisible(true);
    }
    
    public static void ReporteAvionesMasRentables(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/AvionesMasRentables.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Aviones mas Rentables");
        ver.setVisible(true);
    }
    
    public static void ReporteDetalleAvion(Connection c, int mod_codigo,String nombre) throws SQLException,JRException {
        JasperReport reporte = null;
        
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("parVar1", mod_codigo);
        parametros.put("nombre", nombre);
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/EspecificacionesModelo.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,parametros,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Especificaciones del Modelo");
        ver.setVisible(true);
    }
    
    public static void ReportePromedioTraslados(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/PromedioDeTraslados.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Promedios de Traslados");
        ver.setVisible(true);
    }
    
    public static void ReportePlantaMasEficiente(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/PlantaMasEficiente.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Planta más Eficiente");
        ver.setVisible(true);
    }
    public static void ReporteEquipoMasEficiente(Connection c) throws SQLException,JRException {
        JasperReport reporte = null;
        reporte=(JasperReport) JRLoader.loadObjectFromFile("src/Reportes/EquipoMasEficiente.jasper");
        JasperPrint print = JasperFillManager.fillReport(reporte,null,c);
        JasperViewer ver= new JasperViewer(print,false);
        ver.setTitle("Equipo más Eficiente");
        ver.setVisible(true);
    }
}
