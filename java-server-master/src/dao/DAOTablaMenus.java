package dao;

import vos.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaMenus {
    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaMenus() {
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

    public ArrayList<Menu> darMenus() throws SQLException, Exception {
        ArrayList<Menu> menus = new ArrayList<Menu>();

        String sql = "SELECT * FROM MENUS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarIngrediente(rs,menus);
        }
        return menus;
    }

    public ArrayList<Menu> buscarMenusPorID(Long id) throws SQLException, Exception {
        ArrayList<Menu> menus = new ArrayList<Menu>();

        String sql = "SELECT * FROM MENUS WHERE ID =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarIngrediente(rs,menus);
        }

        return menus;
    }

     public void addMenu(Menu menu) throws SQLException, Exception {

        String sql = "INSERT INTO MENUS (id) VALUES (";
        sql += "'"+menu.getId_producto() + "',)";



        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteMenu(Menu menu) throws SQLException, Exception {

        String sql = "DELETE FROM MENUS";
        sql += " WHERE ID = " + menu.getId_producto();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private boolean parseBoolean(int num){
        if(num==0) return false;
        else return true;
    }

    private void insertarIngrediente(ResultSet rs,ArrayList<Menu> menus) throws SQLException{
        Long id = rs.getLong("ID");

        menus.add(new Menu(id));
    }

    private int antiParseBoolean(Boolean bol){
        if (bol ) return 1;
        else return 0;
    }

}
