package dao;

import vos.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaItems {
    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaItems() {
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

    public ArrayList<Item> darItems() throws SQLException, Exception {
        ArrayList<Item> items = new ArrayList<Item>();

        String sql = "SELECT * FROM ITEMS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarItem(rs,items);
        }
        return items;
    }

    public ArrayList<Item> buscarItemsPorIDCarrito(Long id) throws SQLException, Exception {
        ArrayList<Item> items = new ArrayList<Item>();

        String sql = "SELECT * FROM ITEMS WHERE ID_CARRITO =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarItem(rs,items);
        }

        return items;
    }

    public void updateItem(Item item) throws SQLException, Exception {

        String sql = "UPDATE ITEMS SET ";
        sql += "PERSONALIZACION='" + item.getPersonalizacion() + "',";
        sql += "ID_PRODUCTO='" + item.getProductoId()+"',";
        sql += "ID_CARRITO= '" + item.getCarritoId()+"'";
        sql += "WHERE ID = "+ item.getId();


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addItem(Item item) throws SQLException, Exception {

        String sql = "INSERT INTO ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) VALUES (";
        sql += "'"+item.getId() + "',";
        sql += "'"+item.getPersonalizacion() + "',";
        sql += "'"+item.getProductoId()+"',";
        sql += "'"+item.getCarritoId()+"')";



        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteItem(Item item) throws SQLException, Exception {

        String sql = "DELETE FROM ITEMS";
        sql += " WHERE ID = " + item.getId();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }


    private void insertarItem(ResultSet rs,ArrayList<Item> items) throws SQLException{
        Long id = rs.getLong("ID");
        String personalizacion= rs.getString("PERSONALIZACION");
        Long idProd= rs.getLong("ID_PRODUCTO");
        Long idCarrito = rs.getLong("ID_CARRITO");
        items.add(new Item(id,personalizacion,idProd,idCarrito));
    }



}
