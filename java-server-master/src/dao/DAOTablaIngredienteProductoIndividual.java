package dao;

import vos.Ingrediente;
import vos.IngredientesProductoIndividual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaIngredienteProductoIndividual {
    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaIngredienteProductoIndividual() {
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

    public ArrayList<IngredientesProductoIndividual> darIngredientesProd() throws SQLException, Exception {
        ArrayList<IngredientesProductoIndividual> ingredientesProductoIndividuals = new ArrayList<IngredientesProductoIndividual>();

        String sql = "SELECT * FROM INGREDIENTES_PRODUCTIND";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarIngrediente(rs,ingredientesProductoIndividuals);
        }
        return ingredientesProductoIndividuals;
    }

    public ArrayList<IngredientesProductoIndividual> buscarIngredientesPorIDIng(Long id) throws SQLException, Exception {
        ArrayList<IngredientesProductoIndividual> ingredientes = new ArrayList<IngredientesProductoIndividual>();

        String sql = "SELECT * FROM INGREDIENTES_PRODUCTIND WHERE ID_INGREDIENTE =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarIngrediente(rs,ingredientes);
        }

        return ingredientes;
    }

    public ArrayList<IngredientesProductoIndividual> buscarIngredientesPorIDProd(Long id) throws SQLException, Exception {
        ArrayList<IngredientesProductoIndividual> ingredientes = new ArrayList<IngredientesProductoIndividual>();

        String sql = "SELECT * FROM INGREDIENTES_PRODUCTIND WHERE ID_PRODINDIVIDUAL=" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarIngrediente(rs,ingredientes);
        }

        return ingredientes;
    }



    public void addIngredienteProd(IngredientesProductoIndividual ingrediente) throws SQLException, Exception {

        String sql = "INSERT INTO INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) VALUES (";
        sql += "'"+ingrediente.getIngrediente()+ "',";
        sql += "'"+ingrediente.getProducto() + "')";
       

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteIngredienteProd(IngredientesProductoIndividual ingredienteProd) throws SQLException, Exception {

        String sql = "DELETE FROM INGREDIENTES_PRODUCTIND";
        sql += " WHERE ID_INGREDIENTE = " + ingredienteProd.getIngrediente() +
                " AND ID_PRODINDIVIDUAL = " + ingredienteProd.getProducto();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }


    private void insertarIngrediente(ResultSet rs,ArrayList<IngredientesProductoIndividual> ingredientesProductoIndividuals) throws SQLException{
        Long ingrediente = rs.getLong("ID_INGREDIENTE");
        Long prod = rs.getLong("ID_PRODINDIVIDUAL");
        ingredientesProductoIndividuals.add(new IngredientesProductoIndividual(ingrediente,prod));
    }



}
