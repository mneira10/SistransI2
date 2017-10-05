package dao;

import vos.ProductoPreferido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaProductosPreferidos {

    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaProductosPreferidos() {
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

    public ArrayList<ProductoPreferido> darProductosPreferidos() throws SQLException, Exception {
        ArrayList<ProductoPreferido> productosPreferidos = new ArrayList<ProductoPreferido>();

        String sql = "SELECT * FROM PRODUCTOS_PREFERIDOS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarProductoPreferido(rs,productosPreferidos);
        }
        return productosPreferidos;
    }

    public ArrayList<ProductoPreferido> buscarProductosPreferidosPorIDUsr(Long id) throws SQLException, Exception {
        ArrayList<ProductoPreferido> productosPreferidos = new ArrayList<ProductoPreferido>();

        String sql = "SELECT * FROM PRODUCTOS_PREFERIDOS WHERE ID_USUARIO_REGISTRADO =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarProductoPreferido(rs,productosPreferidos);
        }

        return productosPreferidos;
    }
    
    public ArrayList<ProductoPreferido> buscarProductosPreferidosPorIDUsrIdProd(Long idUser, Long idProd) throws SQLException, Exception {
        ArrayList<ProductoPreferido> productosPreferidos = new ArrayList<ProductoPreferido>();

        String sql = "SELECT * FROM PRODUCTOS_PREFERIDOS WHERE ID_USUARIO_REGISTRADO =" + idUser + " AND ID_PRODUCTO="+ idProd ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarProductoPreferido(rs,productosPreferidos);
        }

        return productosPreferidos;
    }
    
    public ArrayList<ProductoPreferido> buscarProductosPreferidosPorIDProd(Long id) throws SQLException, Exception {
        ArrayList<ProductoPreferido> productosPreferidos = new ArrayList<ProductoPreferido>();

        String sql = "SELECT * FROM PRODUCTOS_PREFERIDOS WHERE ID_PRODUCTO =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarProductoPreferido(rs,productosPreferidos);
        }

        return productosPreferidos;
    }



    public void addProductoPreferido(ProductoPreferido productoPreferido) throws SQLException, Exception {

        String sql = "INSERT INTO PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) VALUES (";
        sql += "'"+productoPreferido.getProducto()+ "',";
        sql += "'"+productoPreferido.getUsuarioRegistrado() + "',)";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteProductoPreferido(ProductoPreferido productoPreferido) throws SQLException, Exception {

        String sql = "DELETE FROM PRODUCTOS_PREFERIDOS";
        sql += " WHERE ID_PRODUCTO = " + productoPreferido.getProducto() +
        " ID_USUARIO_REGISTRADO = "+productoPreferido.getUsuarioRegistrado();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }


    private void insertarProductoPreferido(ResultSet rs,ArrayList<ProductoPreferido> productosPreferidos) throws SQLException{
        Long usr = rs.getLong("ID_PRODUCTO");
        Long prod= rs.getLong("ID_USUARIO_REGISTRADO");

        productosPreferidos.add(new ProductoPreferido(usr,prod));
    }


}
