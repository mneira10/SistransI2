package dao;

import vos.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DAOTablaReservas {
    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaReservas() {
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

    public ArrayList<Reserva> darReservas() throws SQLException, Exception {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();

        String sql = "SELECT * FROM RESERVAS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarReserva(rs,reservas);
        }
        return reservas;
    }

    public ArrayList<Reserva> buscarReservasPorID(Long id) throws SQLException, Exception {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();

        String sql = "SELECT * FROM RESERVAS WHERE ID =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarReserva(rs,reservas);
        }

        return reservas;
    }

    public void updateReserva(Reserva reserva) throws SQLException, Exception {

        String sql = "UPDATE RESERVAS SET ";
        sql += "FECHAINICIO='" + reserva.getFechaInicio() + "',";
        sql += "FECHAFIN='" + reserva.getFechaFin()+"',";
        sql += "NUMCOMENSALESFES = '" + reserva.getNumComensales()+"',";
        sql += "USUARIOSREGISTRADOS_ID='" + reserva.getUsrReg()+"',";
        sql += "ZONAS_NOMBRE='" + reserva.getZona()+"',";
        sql += "MENUS_PRODUCTOS_ID='" + reserva.getMenu()+"'";
        sql += "WHERE ID = "+ reserva.getId();


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addReserva(Reserva reserva) throws SQLException, Exception {

        String sql = "INSERT INTO RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID)VALUES (";
        sql += "'"+reserva.getId() + "',";
        sql += "'"+reserva.getFechaInicio() + "',";
        sql += "'"+reserva.getFechaFin()+"',";
        sql += "'"+reserva.getNumComensales()+"',";
        sql += "'"+reserva.getUsrReg()+"',";
        sql += "'"+reserva.getZona()+"',";
        sql += "'"+reserva.getMenu()+"')";



        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteReserva(Reserva reserva) throws SQLException, Exception {

        String sql = "DELETE FROM RESERVAS";
        sql += " WHERE ID = " + reserva.getId();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }


    private void insertarReserva(ResultSet rs,ArrayList<Reserva> reservas) throws SQLException{
        Long id = rs.getLong("ID");
        Date fechaInicio = rs.getDate("FECHAINICIO");
        Date fechaFin = rs.getDate("FECHAFIN");
        Integer numcomensales = rs.getInt("NUMCOMENSALESFES");
        Long usuariosregistrados_id = rs.getLong("USUARIOSREGISTRADOS_ID");
        String zona = rs.getString("ZONAS_NOMBRE");
        Long menu = rs.getLong("MENUS_PRODUCTOS_ID");

        reservas.add(new Reserva(id,fechaInicio,fechaFin,numcomensales,usuariosregistrados_id,zona,menu));
    }


}
