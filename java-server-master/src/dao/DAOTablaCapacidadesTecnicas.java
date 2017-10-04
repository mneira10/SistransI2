package dao;

import vos.CapacidadTecnica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaCapacidadesTecnicas {
    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaCapacidadesTecnicas() {
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

    public ArrayList<CapacidadTecnica> darCapacidadesTecnicas() throws SQLException, Exception {
        ArrayList<CapacidadTecnica> capacidadesTecnicas = new ArrayList<CapacidadTecnica>();

        String sql = "SELECT * FROM CAPACIDADES_TECNICAS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarCapacidadTecnica(rs,capacidadesTecnicas);
        }
        return capacidadesTecnicas;
    }

    public ArrayList<CapacidadTecnica> buscarCapacidadesTecnicasPorID(Long id) throws SQLException, Exception {
        ArrayList<CapacidadTecnica> capacidadesTecnicas = new ArrayList<CapacidadTecnica>();

        String sql = "SELECT * FROM CAPACIDADES_TECNICAS WHERE ID =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarCapacidadTecnica(rs,capacidadesTecnicas);
        }

        return capacidadesTecnicas;
    }

    public void updateCapacidadTecnica(CapacidadTecnica capacidadTecnica) throws SQLException, Exception {

        String sql = "UPDATE CAPACIDADES_TECNICAS SET ";

        sql += "DESCRIPCION='" + capacidadTecnica.getDescripcion()+"',";
        sql += "ZONA = '" + capacidadTecnica.getZona()+"'";

        sql += "WHERE NOMBRE LIKE "+ capacidadTecnica.getNombre();


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addCapacidadTecnica(CapacidadTecnica capacidadTecnica) throws SQLException, Exception {

        String sql = "INSERT INTO CAPACIDADES_TECNICAS (nombre, descripcion, zona) VALUES (";
        sql += "'"+capacidadTecnica.getNombre() + "',";
        sql += "'"+capacidadTecnica.getDescripcion() + "',";
        sql += "'"+capacidadTecnica.getZona()+"')";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteCapacidadTecnica(CapacidadTecnica capacidadTecnica) throws SQLException, Exception {

        String sql = "DELETE FROM CAPACIDADES_TECNICAS";
        sql += " WHERE NOMBRE LIKE " + capacidadTecnica.getNombre();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private boolean parseBoolean(int num){
        if(num==0) return false;
        else return true;
    }

    private void insertarCapacidadTecnica(ResultSet rs,ArrayList<CapacidadTecnica> capacidadesTecnicas) throws SQLException{
        String nombre = rs.getString("NOMBRE");
        String descripcion= rs.getString("DESCRIPCION");
        String zona= rs.getString("ZONA");

        capacidadesTecnicas.add(new CapacidadTecnica(nombre,descripcion,zona));
    }

    private int antiParseBoolean(Boolean bol){
        if (bol ) return 1;
        else return 0;
    }

}
