package dao;

import vos.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaZonas {
    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaZonas() {
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

    public ArrayList<Zona> darZonas() throws SQLException, Exception {
        ArrayList<Zona> zonas = new ArrayList<Zona>();

        String sql = "SELECT * FROM ZONAS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarZona(rs,zonas);
        }
        return zonas;
    }

    public ArrayList<Zona> buscarZonasPorName(String name) throws SQLException, Exception {
        ArrayList<Zona> zonas = new ArrayList<Zona>();

        String sql = "SELECT * FROM ZONAS WHERE NOMBRE LIKE '" + name + "'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarZona(rs,zonas);
        }

        return zonas;
    }

    public void updateZona(Zona zona) throws SQLException, Exception {

        String sql = "UPDATE ZONAS SET ";
        sql += "CERRADO='" + antiParseBoolean(zona.getCerrado())+"',";
        sql += " TIPO = '" + zona.getTipo()+"',";
        sql += "APTODESCAP='" + antiParseBoolean(zona.getAptoDescap())+"',";
        sql += "CAPACIDAD='" + zona.getCapacidad()+"'";
        sql += "WHERE NOMBRE LIKE '" + zona.getNombre() + "'";


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addZona(Zona zona) throws SQLException, Exception {

        String sql = "INSERT INTO ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) VALUES (";
        sql += "'"+zona.getNombre() + "',";
        sql += "'"+antiParseBoolean(zona.getCerrado())+ "',";
        sql += "'"+zona.getTipo()+ "',";
        sql += "'"+antiParseBoolean(zona.getAptoDescap())+ "',";
        sql += "'"+zona.getCapacidad()+ "')";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteZona(Zona zona) throws SQLException, Exception {

        String sql = "DELETE FROM ZONAS";
        sql += " WHERE NOMBRE = '" + zona.getNombre()+"'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private boolean parseBoolean(int num){
        if(num==0) return false;
        else return true;
    }

    private void insertarZona(ResultSet rs,ArrayList<Zona> zonas) throws SQLException{
        String nombre = rs.getString("NOMBRE");
        Boolean cerrado = parseBoolean(rs.getInt("CERRADO"));
        String tipo = rs.getString("TIPO");
        Boolean aptoDescap = parseBoolean(rs.getInt("APTODISCAP"));
        Integer capacidad = rs.getInt("CAPACIDAD");
        zonas.add(new Zona(nombre,cerrado,tipo,aptoDescap,capacidad));
    }

    private int antiParseBoolean(Boolean bol){
        if (bol ) return 1;
        else return 0;
    }
}
