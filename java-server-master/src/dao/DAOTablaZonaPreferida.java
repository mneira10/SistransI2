package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vos.ZonaPreferida;

public class DAOTablaZonaPreferida {
	
	private ArrayList<Object> recursos;
    private Connection conn;
    
    public DAOTablaZonaPreferida() {
        recursos = new ArrayList<Object>();
    }
    
    public void cerrarRecursos() {
        for(Object ob : recursos){
            if(ob instanceof PreparedStatement)
                try {
                    ((PreparedStatement) ob).close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
    }

    public void setConn(Connection con){
        this.conn = con;
    }

    public ArrayList<ZonaPreferida> darZonasPreferidas() throws SQLException, Exception {
        ArrayList<ZonaPreferida> zonasPreferidas = new ArrayList<ZonaPreferida>();

        String sql = "SELECT * FROM ZONAS_PREFERIDAS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarZonasPreferidas(rs,zonasPreferidas);
        }
        return zonasPreferidas;
    }

    public ArrayList<ZonaPreferida> buscarZonaPreferidaPorId(String zonasNombre, Long idUsuarioRegistrado) throws SQLException, Exception {
        ArrayList<ZonaPreferida> historial = new ArrayList<>();

        String sql = "SELECT * FROM ZONAS_PREFERIDAS WHERE ZONAS_NOMBRE ='" + zonasNombre + "' AND USUARIOSREGISTRADOS_ID ='"+idUsuarioRegistrado+"'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarZonasPreferidas(rs, historial);
        }

        return historial;
    }
    
    public ArrayList<ZonaPreferida> buscarHistorialPorNombreZona(String zonasNombre) throws SQLException, Exception {
        ArrayList<ZonaPreferida> zonasPreferidas = new ArrayList<>();

        String sql = "SELECT * FROM ZONAS_PREFERIDAS WHERE ZONAS_NOMBRE ='" + zonasNombre +"'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarZonasPreferidas(rs, zonasPreferidas);
        }

        return zonasPreferidas;
    }
    
    public ArrayList<ZonaPreferida> buscarZonaPreferidaPorIdUsuarioRegistrado(Long idUsuarioRegistrado) throws SQLException, Exception {
        ArrayList<ZonaPreferida> zonasPreferidas = new ArrayList<ZonaPreferida>();

        String sql = "SELECT * FROM ZONAS_PREFERIDAS WHERE USUARIOSREGISTRADOS_ID = " + idUsuarioRegistrado ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarZonasPreferidas(rs, zonasPreferidas);
        }

        return zonasPreferidas;
    }

    public void addZonaPreferida(ZonaPreferida zonaPreferida) throws SQLException, Exception {

        String sql = "INSERT INTO ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) VALUES (";
        sql += "'"+zonaPreferida.getIdUsuarioRegistrado() + "',";
        sql += "'"+zonaPreferida.getZonaNombre()+"');";
        
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteZonaPreferida(ZonaPreferida zonaPreferida) throws SQLException, Exception {

        String sql = "DELETE FROM ZONAS_PREFERIDAS";
        sql += " WHERE ZONAS_NOMBRE = " + zonaPreferida.getZonaNombre()+" AND USUARIOSREGISTRADOS_ID= "+ zonaPreferida.getIdUsuarioRegistrado();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private void insertarZonasPreferidas(ResultSet rs,ArrayList<ZonaPreferida> zonasPreferidas) throws SQLException{
    	String zonasNombre=rs.getString("ZONAS_NOMBRE");
        Long idUsuarioRegistrado = rs.getLong("USUARIOSREGISTRADOS_ID");
        zonasPreferidas.add(new ZonaPreferida(idUsuarioRegistrado, zonasNombre));
    }
}
