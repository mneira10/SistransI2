package dao;

import vos.UsuarioRegistrado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaUsuariosRegistrados {

    private ArrayList<Object> recursos;
    private Connection conn;
    public DAOTablaUsuariosRegistrados() {
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

    public ArrayList<UsuarioRegistrado> darUsuarioRegistrados() throws SQLException, Exception {
        ArrayList<UsuarioRegistrado> usuarioRegistrados = new ArrayList<UsuarioRegistrado>();

        String sql = "SELECT * FROM USUARIOS_REGISTRADOS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuarioRegistrado(rs,usuarioRegistrados);
        }
        return usuarioRegistrados;
    }

    public ArrayList<UsuarioRegistrado> buscarUsuarioRegistradosPorID(Long id) throws SQLException, Exception {
        ArrayList<UsuarioRegistrado> usuarioRegistrados = new ArrayList<UsuarioRegistrado>();

        String sql = "SELECT * FROM USUARIOS_REGISTRADOS WHERE ID =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuarioRegistrado(rs,usuarioRegistrados);
        }

        return usuarioRegistrados;
    }

    public void updateUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) throws SQLException, Exception {

        String sql = "UPDATE USUARIOS_REGISTRADOS SET ";
        sql += "LOGIN='" + usuarioRegistrado.getLogin()+"',";
        sql += "PASSWORD= '" + usuarioRegistrado.getPassword()+"'";

        sql += "WHERE ID = "+ usuarioRegistrado.getUsuario_id();


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) throws SQLException, Exception {

        String sql = "INSERT INTO USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD) VALUES (";
        sql += "'"+usuarioRegistrado.getUsuario_id() + "',";
        sql += "'"+usuarioRegistrado.getLogin() + "',";
        sql += "'"+usuarioRegistrado.getPassword()+"')";



        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) throws SQLException, Exception {

        String sql = "DELETE FROM USUARIOS_REGISTRADOS";
        sql += " WHERE ID = " + usuarioRegistrado.getUsuario_id();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private boolean parseBoolean(int num){
        if(num==0) return false;
        else return true;
    }

    private void insertarUsuarioRegistrado(ResultSet rs,ArrayList<UsuarioRegistrado> usuarioRegistrados) throws SQLException{
        Long id = rs.getLong("ID");
        String login = rs.getString("LOGIN");
        String password = rs.getString("PASSWORD");
        usuarioRegistrados.add(new UsuarioRegistrado(id,login,password));
    }

    private int antiParseBoolean(Boolean bol){
        if (bol ) return 1;
        else return 0;
    }

}
