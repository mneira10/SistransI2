package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Usuario;

public class DAOTablaUsuarios {
	
	private ArrayList<Object> recursos;
    private Connection conn;
    
    public DAOTablaUsuarios() {
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

    public ArrayList<Usuario> darUsuarios() throws SQLException, Exception {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        String sql = "SELECT * FROM USUARIOS";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuario(rs,usuarios);
        }
        return usuarios;
    }

    public ArrayList<Usuario> buscarUsuariosPorId(Long id) throws SQLException, Exception {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        String sql = "SELECT * FROM USUARIOS WHERE ID ='" + id + "'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuario(rs,usuarios);
        }

        return usuarios;
    }

    public void updateUsuario(Usuario usuario) throws SQLException, Exception {

        String sql = "UPDATE USUARIOS SET ";
        sql += "NOMBRES="+"'"+usuario.getNombres()+"',";
        sql += "APELLIDOS="+"'"+usuario.getApellidos()+"',";
        sql += "TIPOID="+"'"+usuario.getTipoId()+"',";
        sql += "NUMID="+"'"+usuario.getNumId()+"' ";
        sql += "WHERE ID="+"'"+usuario.getId()+"';";
        
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addUsuario(Usuario usuario) throws SQLException, Exception {

        String sql = "INSERT INTO USUARIOS (ID, NOMBRES, APELLIDOS, TIPOID, NUMID) VALUES (";
        sql += "'"+usuario.getId() + "',";
        sql += "'"+usuario.getNombres()+"',";
        sql += "'"+usuario.getApellidos()+"',";
        sql += "'"+usuario.getTipoId()+"',";
        sql += "'"+usuario.getNumId()+"');";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteUsuario(Usuario usuario) throws SQLException, Exception {

        String sql = "DELETE FROM USUARIOS";
        sql += " WHERE ID = " + usuario.getId();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private void insertarUsuario(ResultSet rs,ArrayList<Usuario> usuarios) throws SQLException{
    	Long id=rs.getLong("ID");
        String nombres = rs.getString("NOMBRES");
        String apellidos = rs.getString("APELLIDOS");
        String tipo = rs.getString("TIPOID");
        Long numId = rs.getLong("NUMID");
        usuarios.add(new Usuario(id,nombres,apellidos,tipo,numId));
    }
}
