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
        sql += "GRUPO='" + productoIndividual.getGrupo()+"'";

        sql += "WHERE ID = "+ productoIndividual.getProdId();


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addProductoIndividual(ProductoIndividual productoIndividual) throws SQLException, Exception {

        String sql = "INSERT INTO PRODUCTOS_INDIVIDUALES (id, categoria, grupo)  VALUES (";
        sql += "'"+productoIndividual.getProdId() + "',";
        sql += "'"+productoIndividual.getCategoria() + "',";
        sql += "'"+productoIndividual.getGrupo()+"')";

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
        productoIndividuals.add(new ProductoIndividual(id,categoria,grupo));
    }

}
