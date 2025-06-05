/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.seguridad;

import Controlador.seguridad.Bodega; 
import Modelo.Conexion;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class BodegaDAO {
    
    private static final String SQL_SELECT = "SELECT id, tipobodega, direccion, nombre, estado FROM bodega";
    private static final String SQL_INSERT = "INSERT INTO bodega (id,tipobodega, nombre, direccion,  estado) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE bodega SET nombre=?, tipobodega=?, direccion=?, estado=? WHERE id=? ";
    private static final String SQL_DELETE = "DELETE FROM bodega WHERE id=?";
    private static final String SQL_QUERY = "SELECT id, nombre,  tipobodega, direccion, estado FROM bodega WHERE id=?";
    
    public List<Bodega> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bodega> list_bodega = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Bodega bodega = new Bodega();
                bodega.setId(rs.getInt("id"));
                bodega.setNombre(rs.getString("nombre"));
                bodega.settipobodega(rs.getString("tipobodega"));
                bodega.setDireccion(rs.getString("Direccion"));
                bodega.setEstado(rs.getString("estado"));
                
                list_bodega.add(bodega);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return list_bodega;
    }
    public int insert(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setInt(1, bodega.getId());
            stmt.setString(2, bodega.getNombre());
            stmt.setString(3, bodega.gettipobodega());
            stmt.setString(4, bodega.getDireccion());
            stmt.setString(5, bodega.getEstado());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int update(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            
            stmt.setString(1, bodega.getNombre());
            stmt.setString(2, bodega.gettipobodega());
            stmt.setString(5, bodega.getDireccion());
            stmt.setString(6, bodega.getEstado());        
            stmt.setInt(7, bodega.getId());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int delete(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, bodega.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public Bodega query(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, bodega.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                bodega.setNombre(rs.getString("nombre"));
                bodega.settipobodega(rs.getString("tipobodega"));
                bodega.setDireccion(rs.getString("Direccion"));
                bodega.setEstado(rs.getString("estado"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return bodega;
        
    }
     public void imprimirReporte() {
        Connection conn = null;
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            conn = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/ReporteProveedores_cxp/"+ "Reporte.Proveedor.jrxml");
            print = JasperFillManager.fillReport(report, p, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Vendedores");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
