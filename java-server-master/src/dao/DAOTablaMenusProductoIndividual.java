package dao;

import vos.MenuProductoIndividual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaMenusProductoIndividual {

    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaMenusProductoIndividual() {
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

    public ArrayList<MenuProductoIndividual> darMenusProductoIndividual() throws SQLException, Exception {
        ArrayList<MenuProductoIndividual> productosPreferidos = new ArrayList<MenuProductoIndividual>();

        String sql = "SELECT * FROM MENU_PRODUCTO_INDIVIDUAL";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarMenuProductoIndividual(rs,productosPreferidos);
        }
        return productosPreferidos;
    }

    public ArrayList<MenuProductoIndividual> buscarProductosPreferidosPorIDProd(Long id) throws SQLException, Exception {
        ArrayList<MenuProductoIndividual> productosPreferidos = new ArrayList<>();

        String sql = "SELECT * FROM MENU_PRODUCTO_INDIVIDUAL WHERE ID_PROD_INDIVIDUAL =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarMenuProductoIndividual(rs,productosPreferidos);
        }

        return productosPreferidos;
    }
    public ArrayList<MenuProductoIndividual> buscarProductosPreferidosPorIDMenu(Long id) throws SQLException, Exception {
        ArrayList<MenuProductoIndividual> menusProductos = new ArrayList<>();

        String sql = "SELECT * FROM MENU_PRODUCTO_INDIVIDUAL WHERE ID_MENU =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarMenuProductoIndividual(rs,menusProductos);
        }

        return menusProductos;
    }



    public void addMenuProd(MenuProductoIndividual menuProd) throws SQLException, Exception {

        String sql = "INSERT INTO MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) VALUES (";
        sql += "'"+menuProd.getId_menu()+ "',";
        sql += "'"+menuProd.getId_prod_individual() + "',)";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteMenuProd(MenuProductoIndividual menu) throws SQLException, Exception {

        String sql = "DELETE FROM MENU_PRODUCTO_INDIVIDUAL";
        sql += " WHERE ID_MENU = " + menu.getId_menu() +
        " ID_PROD_INDIVIDUAL = "+menu.getId_prod_individual();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }


    private void insertarMenuProductoIndividual(ResultSet rs,ArrayList<MenuProductoIndividual> menuIndividual) throws SQLException{
        Long usr = rs.getLong("ID_MENU");
        Long prod= rs.getLong("ID_PROD_INDIVIDUAL");

        menuIndividual.add(new MenuProductoIndividual(usr,prod));
    }


}
