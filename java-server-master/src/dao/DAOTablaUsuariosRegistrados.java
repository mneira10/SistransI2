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
    
    public ArrayList<UsuarioRegistrado> darBuenosClientes() throws SQLException, Exception {
    	ArrayList<UsuarioRegistrado> usuarioRegistrados = new ArrayList<UsuarioRegistrado>();

        String sql = "(SELECT * FROM USUARIOS_REGISTRADOS NATURAL JOIN (SELECT ID_USUARIO_REGISTRADO AS ID FROM HISTORIAL GROUP BY ID_USUARIO_REGISTRADO HAVING (COUNT(DISTINCT TO_NUMBER(TO_CHAR(HISTORIAL.FECHA, 'WW')))-1 = TO_NUMBER(TO_CHAR(SYSDATE, 'WW')) - MIN(TO_NUMBER(TO_CHAR(HISTORIAL.FECHA, 'WW')) )) )) UNION (SELECT * FROM USUARIOS_REGISTRADOS  WHERE TIPO LIKE NULL MINUS (SELECT U.* FROM USUARIOS_REGISTRADOS U JOIN (HISTORIAL NATURAL JOIN (SELECT ID AS ID_PRODUCTO FROM MENUS )) ON (U.ID=ID_USUARIO_REGISTRADO))) UNION (SELECT * FROM USUARIOS_REGISTRADOS WHERE TIPO LIKE NULL MINUS (SELECT U.* FROM USUARIOS_REGISTRADOS U JOIN (HISTORIAL NATURAL JOIN (SELECT ID AS ID_PRODUCTO FROM PRODUCTOS WHERE PRECIO <= 36885.84)) ON (U.ID=ID_USUARIO_REGISTRADO)))";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuarioRegistrado(rs,usuarioRegistrados);
        }
        return usuarioRegistrados;
    }

    public UsuarioRegistrado buscarUsuarioRegistradosPorID(Long id) throws SQLException, Exception {
        ArrayList<UsuarioRegistrado> usuarioRegistrados = new ArrayList<UsuarioRegistrado>();

        String sql = "SELECT * FROM USUARIOS_REGISTRADOS WHERE ID =" + id ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuarioRegistrado(rs,usuarioRegistrados);
        }

        return usuarioRegistrados.get(0);
    }
    
    public UsuarioRegistrado buscarUsuarioRegistradosPorLogin(String login) throws SQLException, Exception {
        ArrayList<UsuarioRegistrado> usuarioRegistrados = new ArrayList<UsuarioRegistrado>();

        String sql = "SELECT * FROM USUARIOS_REGISTRADOS WHERE LOGIN LIKE" + login ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuarioRegistrado(rs,usuarioRegistrados);
        }

        return usuarioRegistrados.get(0);
    }

    public void updateUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) throws SQLException, Exception {

        String sql = "UPDATE USUARIOS_REGISTRADOS SET ";
        sql += "LOGIN='" + usuarioRegistrado.getLogin()+"',";
        sql += "PASSWORD= '" + usuarioRegistrado.getPassword()+"'";
        sql += "TIPO= '" + usuarioRegistrado.getTipo()+"'";
        sql += "WHERE ID = "+ usuarioRegistrado.getUsuario_id();


        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    public void addUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) throws SQLException, Exception {

        String sql = "INSERT INTO USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) VALUES (";
        sql += "'"+usuarioRegistrado.getUsuario_id() + "',";
        sql += "'"+usuarioRegistrado.getLogin() + "',";
        sql += "'"+usuarioRegistrado.getPassword() + "',";
        sql += "'"+usuarioRegistrado.getTipo()+"')";



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


    private void insertarUsuarioRegistrado(ResultSet rs,ArrayList<UsuarioRegistrado> usuarioRegistrados) throws SQLException{
        Long id = rs.getLong("ID");
        String login = rs.getString("LOGIN");
        String password = rs.getString("PASSWORD");
        String tipo=rs.getString("TIPO");
        usuarioRegistrados.add(new UsuarioRegistrado(id,login,password, tipo));
    }
    
	public boolean confirmarAdmin(String loginAdmin, String passAdmin) throws SQLException, Exception {
		
		boolean resp=false;
		
		ArrayList<UsuarioRegistrado> usuarioRegistrados = new ArrayList<UsuarioRegistrado>();

        String sql = "SELECT * FROM USUARIOS_REGISTRADOS WHERE LOGIN LIKE" + loginAdmin+ " AND PASSWORD LIKE"+passAdmin+ " AND TIPO LIKE ADMIN" ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuarioRegistrado(rs,usuarioRegistrados);
        }
        
        if(usuarioRegistrados.size()==1){
        	resp=true;
        }
        	
       return resp;

	}
	public boolean confirmarRestaurante(String loginAdmin, String passAdmin) throws SQLException, Exception {
		
		boolean resp=false;
		
		ArrayList<UsuarioRegistrado> usuarioRegistrados = new ArrayList<UsuarioRegistrado>();

        String sql = "SELECT * FROM USUARIOS_REGISTRADOS WHERE LOGIN =" + loginAdmin+ " AND PASSWORD="+passAdmin+ " AND TIPO=RESTAURANTE" ;

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuarioRegistrado(rs,usuarioRegistrados);
        }
        
        if(usuarioRegistrados.size()==1){
        	resp=true;
        }
        	
       return resp;
	}


}
