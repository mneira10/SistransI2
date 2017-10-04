package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Producto;
import vos.Usuario;

public class DAOTablaProductos {
	
	private ArrayList<Object> recursos;
    private Connection conn;
    
    public DAOTablaProductos() {
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

    public ArrayList<Producto> darProductos() throws SQLException, Exception {
        ArrayList<Producto> productos = new ArrayList<Producto>();

        String sql = "SELECT * FROM PRODUCTOS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarProducto(rs,productos);
        }
        return productos;
    }

    public ArrayList<Producto> buscarProductosPorId(Long id) throws SQLException, Exception {
        ArrayList<Producto> productos = new ArrayList<Producto>();

        String sql = "SELECT * FROM PRODUCTOS WHERE ID ='" + id + "'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarProducto(rs,productos);
        }

        return productos;
    }

    public void updateProducto(Producto producto) throws SQLException, Exception {

        String sql = "UPDATE PRODUCTOS SET ";
        sql += "NOMBRE="+"'"+producto.getNombre()+"',";
        sql += "DESCRESP="+"'"+producto.getDescrEsp()+"',";
        sql += "DESCRING="+"'"+producto.getDescrIng()+"',";
        sql += "TPREP="+"'"+producto.gettPrep()+"',";
        sql += "COSTO="+"'"+producto.getCosto()+"',";
        sql += "PRECIO="+"'"+producto.getPrecio()+"',";
        sql += "RESTAURANTES_ID="+"'"+producto.getRestauranteId()+"' ";
        sql += "WHERE ID="+"'"+producto.getId()+"';";
        
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addProducto(Producto producto) throws SQLException, Exception {

        String sql = "INSERT INTO PRODUCTOS (ID, NOMBRE, DESCRESP, DESCRING, TPREP, COSTO, PRECIO, RESTAURANTE_ID) VALUES (";
        sql += "'"+producto.getId() + "',";
        sql += "'"+producto.getNombre()+"',";
        sql += "'"+producto.getDescrEsp()+"',";
        sql += "'"+producto.getDescrIng()+"',";
        sql += "'"+producto.gettPrep()+"',";
        sql += "'"+producto.getCosto()+"',";
        sql += "'"+producto.getPrecio()+"',";
        sql += "'"+producto.getRestauranteId()+"');";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteProducto(Producto producto) throws SQLException, Exception {

        String sql = "DELETE FROM PRODUCTOS";
        sql += " WHERE ID = " + producto.getId();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private void insertarProducto(ResultSet rs,ArrayList<Producto> productos) throws SQLException{
    	Long id=rs.getLong("ID");
        String nombre = rs.getString("NOMBRE");
        String descrEsp = rs.getString("DESCRESP");
        String descrIng = rs.getString("DESCRING");
        Double tPrep = rs.getDouble("TPREP");
        Double costo = rs.getDouble("COSTO");
        Double precio = rs.getDouble("PRECIO");
        Long restauranteId = rs.getLong("RESTAURANTE_ID");
        productos.add(new Producto(id,nombre,descrEsp,descrIng,tPrep, costo, precio, restauranteId));
    }
}
