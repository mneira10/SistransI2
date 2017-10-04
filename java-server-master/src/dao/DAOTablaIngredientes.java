package dao;

import vos.Ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaIngredientes {
    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaIngredientes() {
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

    public ArrayList<Ingrediente> darIngredientes() throws SQLException, Exception {
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

        String sql = "SELECT * FROM INGREDIENTES";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarIngrediente(rs,ingredientes);
        }
        return ingredientes;
    }

    public ArrayList<Ingrediente> buscarIngredientesPorID(Long id) throws SQLException, Exception {
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

        String sql = "SELECT * FROM INGREDIENTES WHERE ID =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarIngrediente(rs,ingredientes);
        }

        return ingredientes;
    }

    public void updateIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

        String sql = "UPDATE INGREDIENTES SET ";
        sql += "NOMBRE='" + ingrediente.getNombre() + "',";
        sql += "DESCRESP='" + ingrediente.getDescrEsp()+"',";
        sql += "DESCRING = '" + ingrediente.getDescrIng()+"',";
        sql += "GRUPO=" + ingrediente.getGrupo()+",";
        sql += "RESTAURANTE=" + ingrediente.getRestauranteId();
        sql += "WHERE ID = "+ ingrediente.getId();


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

        String sql = "INSERT INTO INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) VALUES (";
        sql += "'"+ingrediente.getId() + "',";
        sql += "'"+ingrediente.getNombre() + "',";
        sql += "'"+ingrediente.getDescrEsp()+"',";
        sql += "'"+ingrediente.getDescrIng()+"',";
        sql += "'"+ingrediente.getGrupo()+"',";
        sql += "'"+ingrediente.getRestauranteId()+"')";


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

        String sql = "DELETE FROM INGREDIENTES";
        sql += " WHERE ID = " + ingrediente.getId();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private boolean parseBoolean(int num){
        if(num==0) return false;
        else return true;
    }

    private void insertarIngrediente(ResultSet rs,ArrayList<Ingrediente> ingredientes) throws SQLException{
        Long id = rs.getLong("ID");
        String nombre = rs.getString("NOMBRE");
        String descrEsp = rs.getString("DESCRESP");
        String descrIng = rs.getString("DESCRING");
        Integer grupo = rs.getInt("GRUPO");
        Long restauranteID = rs.getLong("RESTAURANTE");
        ingredientes.add(new Ingrediente(id,nombre,descrEsp,descrIng,grupo,restauranteID));
    }

    private int antiParseBoolean(Boolean bol){
        if (bol ) return 1;
        else return 0;
    }


}
