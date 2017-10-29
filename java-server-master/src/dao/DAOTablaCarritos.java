package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Carrito;


public class DAOTablaCarritos {
	
	private ArrayList<Object> recursos;
    private Connection conn;
    
    public DAOTablaCarritos() {
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

    public ArrayList<Carrito> darCarritos() throws SQLException, Exception {
        ArrayList<Carrito> carritos = new ArrayList<Carrito>();

        String sql = "SELECT * FROM CARRITOS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarCarrito(rs,carritos);
        }
        return carritos;
    }

    public ArrayList<Carrito> buscarCarritosPorId(Long id) throws SQLException, Exception {
        ArrayList<Carrito> carritos = new ArrayList<Carrito>();

        String sql = "SELECT * FROM CARRITOS WHERE ID ='" + id + "'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarCarrito(rs,carritos);
        }

        return carritos;
    }

    public void updateCarrito(Carrito producto) throws SQLException, Exception {

        String sql = "UPDATE CARRITOS SET ";
        sql += "NOMBRE="+"'"+producto.getNombre()+"',";
        sql += "COSTO="+"'"+producto.getCosto()+"',";
        sql += "RESTAURANTES_ID="+"'"+producto.getUsuarioId()+"' ";
        sql += "WHERE ID="+"'"+producto.getId()+"';";
        
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addCarrito(Carrito carrito) throws SQLException, Exception {

        String sql = "INSERT INTO CARRITOS (ID, NOMBRE, COSTO, USUARIO_ID) VALUES (";
        sql += "'"+carrito.getId() + "',";
        sql += "'"+carrito.getNombre()+"',";
        sql += "'"+carrito.getCosto()+"',";
        sql += "'"+carrito.getUsuarioId()+"');";
        
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteCarrito(Carrito carrito) throws SQLException, Exception {

        String sql = "DELETE FROM CARRITOS";
        sql += " WHERE ID = " + carrito.getId();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private void insertarCarrito(ResultSet rs,ArrayList<Carrito> carritos) throws SQLException{
    	Long id=rs.getLong("ID");
        String nombre = rs.getString("NOMBRE");
        Double costo = rs.getDouble("COSTO");
        Long usuarioId = rs.getLong("USUARIO_ID");
        carritos.add(new Carrito(id,nombre,costo,usuarioId));
    }
}
