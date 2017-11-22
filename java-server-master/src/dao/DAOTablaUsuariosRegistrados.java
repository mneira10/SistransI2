package dao;

import vos.Usuario;
import vos.UsuarioRegistrado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
    
    public ArrayList<UsuarioRegistrado> requerimiento9 (Long idRestaurante, Date fecha1, Date fecha2 ) throws SQLException, Exception{
    	
   	 ArrayList<UsuarioRegistrado> usuarios = new ArrayList<UsuarioRegistrado>();

   	String sql = "SELECT *"+
   			" FROM"+
   			  " USUARIOS_REGISTRADOS"+
   			  " JOIN (SELECT ID_USUARIO_REGISTRADO"+
   			          " FROM PRODUCTOS JOIN HISTORIAL ON (HISTORIAL.ID_PRODUCTO = PRODUCTOS.ID)"+
   			          " WHERE PRODUCTOS.RESTAURANTES_ID = "+idRestaurante+" AND (to_date(to_char(HISTORIAL.FECHA, 'YYYY/MM/DD HH24:MI:SS'), 'YYYY/MM/DD HH24:MI:SS') between to_date('"+fecha1+"', 'YYYY/MM/DD HH24:MI:SS') and to_date('"+fecha2+"', 'YYYY/MM/DD HH24:MI:SS')))"+
   			 " ON (USUARIOS_REGISTRADOS.ID = ID_USUARIO_REGISTRADO)";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuarioRegistrado(rs,usuarios);
        }
        return usuarios;
   	
   }
   
   
   public ArrayList<UsuarioRegistrado> requerimiento10 (Long idRestaurante, Date fecha1, Date fecha31 ) throws SQLException, Exception{
   	
   	 ArrayList<UsuarioRegistrado> usuarios = new ArrayList<UsuarioRegistrado>();
        String sql ="SELECT * FROM USUARIOS_REGISTRADOS"+
        " MINUS"+
        " (SELECT USUARIOS_REGISTRADOS.*"+
        " FROM"+
          " USUARIOS_REGISTRADOS"+
          " JOIN (SELECT ID_USUARIO_REGISTRADO"+
                  " FROM PRODUCTOS JOIN HISTORIAL ON (HISTORIAL.ID_PRODUCTO = PRODUCTOS.ID)"+
                  " WHERE PRODUCTOS.RESTAURANTES_ID = "+idRestaurante+" AND (to_date(to_char(HISTORIAL.FECHA, 'YYYY/MM/DD HH24:MI:SS'), 'YYYY/MM/DD HH24:MI:SS') between to_date('"+fecha1+"', 'YYYY/MM/DD HH24:MI:SS') and to_date('"+fecha31+"', 'YYYY/MM/DD HH24:MI:SS')))"+
          " ON (USUARIOS_REGISTRADOS.ID = ID_USUARIO_REGISTRADO))";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarUsuarioRegistrado(rs,usuarios);
        }
        return usuarios;
   	
   }
    
    public ArrayList<UsuarioRegistrado> darBuenosClientes() throws SQLException, Exception {
    	ArrayList<UsuarioRegistrado> usuarioRegistrados = new ArrayList<UsuarioRegistrado>();

        String sql = "(SELECT *"+
" FROM USUARIOS_REGISTRADOS"+
  " NATURAL JOIN"+
  " (SELECT ID_USUARIO_REGISTRADO AS ID"+
      " FROM HISTORIAL"+
      " GROUP BY ID_USUARIO_REGISTRADO"+
      " HAVING (COUNT(DISTINCT TO_NUMBER(TO_CHAR(HISTORIAL.FECHA, 'WW')))-1"+
          " = TO_NUMBER(TO_CHAR(SYSDATE, 'WW')) - MIN(TO_NUMBER(TO_CHAR(HISTORIAL.FECHA, 'WW')) )) ))"+
" UNION"+
" (SELECT *"+
" FROM USUARIOS_REGISTRADOS  WHERE TIPO LIKE 'USUARIO'"+
" MINUS"+
" (SELECT U.*"+
" FROM USUARIOS_REGISTRADOS U JOIN (HISTORIAL H JOIN MENUS M ON (H.ID_PRODUCTO=M.ID)) ON (U.ID=ID_USUARIO_REGISTRADO)))"+
" UNION"+
" (SELECT *"+
" FROM USUARIOS_REGISTRADOS WHERE TIPO LIKE 'USUARIO'"+
"MINUS"+
" (SELECT U.*"+
" FROM USUARIOS_REGISTRADOS U JOIN (HISTORIAL H1 JOIN PRODUCTOS P ON( H1.ID_PRODUCTO  = P.ID AND P.PRECIO <= 36885.84)) ON (U.ID=ID_USUARIO_REGISTRADO)))";

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
