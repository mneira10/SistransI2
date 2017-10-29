package dao;

import vos.ProductoIndividual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaProductosIndividuales {
    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaProductosIndividuales() {
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

    public ArrayList<ProductoIndividual> darProductosIndividuales() throws SQLException, Exception {
        ArrayList<ProductoIndividual> productoIndividuals = new ArrayList<ProductoIndividual>();

        String sql = "SELECT * FROM PRODUCTOS_INDIVIDUALES";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarProductoIndividual(rs,productoIndividuals);
        }
        return productoIndividuals;
    }

    public ArrayList<ProductoIndividual> buscarProductosIndividualesPorID(Long id) throws SQLException, Exception {
        ArrayList<ProductoIndividual> productoIndividuals = new ArrayList<ProductoIndividual>();

        String sql = "SELECT * FROM PRODUCTOS_INDIVIDUALES WHERE ID =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarProductoIndividual(rs,productoIndividuals);
        }

        return productoIndividuals;
    }

    public void updateProductoIndividual(ProductoIndividual productoIndividual) throws SQLException, Exception {

        String sql = "UPDATE PRODUCTOS_INDIVIDUALES SET ";
        sql += "CATEGORIA='" + productoIndividual.getCategoria() + "',";
        sql += "GRUPO='" + productoIndividual.getGrupo() + "',";
        sql += "CANTIDADDISPONIBLE='" + productoIndividual.getCantidadDisponible() + "',";
        sql += "MAXIMO='" + productoIndividual.getMaximo()+"'";

        sql += "WHERE ID = "+ productoIndividual.getProdId();
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }
    
    public void venderProductoIndividual(Long idProdIndividual) throws SQLException, Exception {
    	
    	ArrayList<ProductoIndividual> buscados= buscarProductosIndividualesPorID(idProdIndividual);
    	ProductoIndividual prodInd=buscados.get(0);
    	if(buscados.size()==0){
    		throw new Exception("No existe el producto a vender");
    	}
    	else if(prodInd.getCantidadDisponible()==0) {
    		throw new Exception("No hay inventario");
    	}
    	else{String sql = "UPDATE PRODUCTOS_INDIVIDUALES SET ";
        sql += "CANTIDADDISPONIBLE='" + (prodInd.getCantidadDisponible()-1)+"'";
        sql += "WHERE ID = "+ idProdIndividual;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();}
    }
    
    public void addProductoIndividual(ProductoIndividual productoIndividual) throws SQLException, Exception {

        String sql = "INSERT INTO PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo)  VALUES (";
        sql += "'"+productoIndividual.getProdId() + "',";
        sql += "'"+productoIndividual.getCategoria() + "',";
        sql += "'"+productoIndividual.getGrupo() + "',";
        sql += "'"+productoIndividual.getCantidadDisponible() + "',";
        sql += "'"+productoIndividual.getMaximo()+"')";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteProductoIndividual(ProductoIndividual productoIndividual) throws SQLException, Exception {

        String sql = "DELETE FROM PRODUCTOS_INDIVIDUALES";
        sql += " WHERE ID = " + productoIndividual.getProdId();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private void insertarProductoIndividual(ResultSet rs,ArrayList<ProductoIndividual> productoIndividuals) throws SQLException{
        Long id = rs.getLong("ID");
        String categoria = rs.getString("CATEGORIA");
        Integer grupo = rs.getInt("GRUPO");
        Integer cantDisponible= rs.getInt("CANTIDADDISPONIBLE");
        Integer maximo= rs.getInt("MAXIMO");
        productoIndividuals.add(new ProductoIndividual(id,categoria,grupo, cantDisponible,maximo));
    }

    public void surtirProducto(Long id) throws SQLException{
        String sql = "UPDATE PRODUCTOS_INDIVIDUALES SET CANTIDADDISPONIBLE = MAXIMO WHERE ID = " + id;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }
}
