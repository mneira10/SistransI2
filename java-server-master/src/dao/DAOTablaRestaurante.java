package dao;



import vos.Restaurante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaRestaurante {
    private ArrayList<Object> recursos;
    private Connection conn;

    public DAOTablaRestaurante() {
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

    public ArrayList<Restaurante> darRestaurantes() throws SQLException, Exception {
        ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();

        String sql = "SELECT * FROM RESTAURANTES";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarRestaurante(rs,restaurantes);
        }
        return restaurantes;
    }

    public ArrayList<Restaurante> buscarRestaurantesPorID(Long id) throws SQLException, Exception {
        ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();

        String sql = "SELECT * FROM RESTAURANTES WHERE ID =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarRestaurante(rs,restaurantes);
        }

        return restaurantes;
    }

    public void updateRestaurante(Restaurante restaurante) throws SQLException, Exception {

        String sql = "UPDATE RESTAURANTES SET ";
        sql += "NOMBRE='" + restaurante.getNombre() + "',";
        sql += " TIPO = '" + restaurante.getTipo()+"',";
        sql += "PAGINA_WEB='" + restaurante.getPaginaWeb()+"',";
        sql += "ZONA='" + restaurante.getZonaId()+"'";
        sql += "WHERE ID = "+restaurante.getId();


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addRestaurante(Restaurante restaurante) throws SQLException, Exception {

        String sql = "INSERT INTO RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA) VALUES (";
        sql += "'"+restaurante.getId() + "',";
        sql += "'"+restaurante.getNombre()+"',";
        sql += "'"+restaurante.getTipo()+"',";
        sql += "'"+restaurante.getPaginaWeb() + "',";
        sql += "'"+restaurante.getZonaId()+"')";


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteRestaurante(Restaurante restaurante) throws SQLException, Exception {

        String sql = "DELETE FROM RESTAURANTES";
        sql += " WHERE ID = " + restaurante.getId();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private boolean parseBoolean(int num){
        if(num==0) return false;
        else return true;
    }

    private void insertarRestaurante(ResultSet rs,ArrayList<Restaurante> restaurantes) throws SQLException{
        Long id = rs.getLong("ID");
        String nombre = rs.getString("NOMBRE");
        String tipo = rs.getString("TIPO");
        String paginaWeb = rs.getString("PAGINA_WEB");
        String zona = rs.getString("ZONA");
        restaurantes.add(new Restaurante(id,nombre,tipo,paginaWeb,zona));
    }

    private int antiParseBoolean(Boolean bol){
        if (bol ) return 1;
        else return 0;
    }

}
