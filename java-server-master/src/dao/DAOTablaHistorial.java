package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Carrito;
import vos.Historial;
import vos.Producto;
import vos.Usuario;

public class DAOTablaHistorial {
	
	private ArrayList<Object> recursos;
    private Connection conn;
    
    public DAOTablaHistorial() {
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

    public ArrayList<Historial> darHistorial() throws SQLException, Exception {
        ArrayList<Historial> historial = new ArrayList<Historial>();

        String sql = "SELECT * FROM HISTORIAL";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarHistorial(rs,historial);
        }
        return historial;
    }

    public ArrayList<Historial> buscarHistorialPorId(Long idProducto, Long idUsuarioRegistrado) throws SQLException, Exception {
        ArrayList<Historial> historial = new ArrayList<Historial>();

        String sql = "SELECT * FROM HISTORIAL WHERE ID_PRODUCTO ='" + idProducto + "' AND ID_USUARIO_REGISTRADO ='"+idUsuarioRegistrado+"'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarHistorial(rs, historial);
        }

        return historial;
    }
    
    public ArrayList<Historial> buscarHistorialPorIdProducto(Long idProducto) throws SQLException, Exception {
        ArrayList<Historial> historial = new ArrayList<Historial>();

        String sql = "SELECT * FROM HISTORIAL WHERE ID_PRODUCTO ='" + idProducto +"'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarHistorial(rs, historial);
        }

        return historial;
    }
    
    public ArrayList<Historial> buscarHistorialPorIdUsuarioRegistrado(Long idUsuarioRegistrado) throws SQLException, Exception {
        ArrayList<Historial> historial = new ArrayList<Historial>();

        String sql = "SELECT * FROM HISTORIAL WHERE ID_USUARIO_REGISTRADO ='" + idUsuarioRegistrado +"'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarHistorial(rs, historial);
        }

        return historial;
    }

    public void addHistorial(Historial historial) throws SQLException, Exception {

        String sql = "INSERT INTO HISTORIAL (ID_PRODUCTO, ID_USUARIO_REGISTRADO) VALUES (";
        sql += "'"+historial.getIdProducto() + "',";
        sql += "'"+historial.getIdUsuarioRegistrado()+"');";
        
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteHistorial(Historial historial) throws SQLException, Exception {

        String sql = "DELETE FROM HISTORIAL";
        sql += " WHERE ID_PRODUCTO = " + historial.getIdProducto()+" AND ID_USUARIO_REGISTRADO= "+ historial.getIdUsuarioRegistrado();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private void insertarHistorial(ResultSet rs,ArrayList<Historial> historial) throws SQLException{
    	Long idProducto=rs.getLong("ID_PRODUCTO");
        Long idUsuarioRegistrado = rs.getLong("ID_USUARIO_REGISTRADO");
        historial.add(new Historial(idProducto, idUsuarioRegistrado));
    }
}
