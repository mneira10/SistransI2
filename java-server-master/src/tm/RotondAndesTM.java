package tm;

import dao.DAOTablaUsuarios;
import vos.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class RotondAndesTM {

    /**
     * Atributo estatico que contiene el path relativo del archivo que tiene los datos de la conexion
     */
    private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

    /**
     * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
     */
    private  String connectionDataPath;

    /**
     * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
     */
    private String user;

    /**
     * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
     */
    private String password;

    /**
     * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
     */
    private String url;

    /**
     * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
     */
    private String driver;

    /**
     * conexion a la base de datos
     */
    private Connection conn;


    public RotondAndesTM(String contextPathP) {
        connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
        initConnectionData();
    }

    private void initConnectionData() {
        try {
            File arch = new File(this.connectionDataPath);
            Properties prop = new Properties();
            FileInputStream in = new FileInputStream(arch);
            prop.load(in);
            in.close();
            this.url = prop.getProperty("url");
            this.user = prop.getProperty("usuario");
            this.password = prop.getProperty("clave");
            this.driver = prop.getProperty("driver");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection darConexion() throws SQLException {
        System.out.println("Connecting to: " + url + " With user: " + user);
        return DriverManager.getConnection(url, user, password);
    }


    public List<Usuario> darUsuarios() throws Exception{
        List<Usuario> usuarios;
        DAOTablaUsuarios daoUsuarios = new DAOTablaUsuarios();
        try
        {
            //////transaccion
            this.conn = darConexion();
            daoUsuarios.setConn(conn);
            usuarios = daoUsuarios.darUsuarios();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.err.println("GeneralException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                daoUsuarios.cerrarRecursos();
                if(this.conn!=null)
                    this.conn.close();
            } catch (SQLException exception) {
                System.err.println("SQLException closing resources:" + exception.getMessage());
                exception.printStackTrace();
                throw exception;
            }
        }
        return usuarios;

    }

    public Usuario darUsuariosPorID(Long id) throws Exception{
        Usuario usuario;
        DAOTablaUsuarios daoUsuario= new DAOTablaUsuarios();
        try
        {
            //////transaccion
            this.conn = darConexion();
            daoUsuario.setConn(conn);
            usuario = daoUsuario.buscarUsuariosPorId(id);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.err.println("GeneralException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                daoUsuario.cerrarRecursos();
                if(this.conn!=null)
                    this.conn.close();
            } catch (SQLException exception) {
                System.err.println("SQLException closing resources:" + exception.getMessage());
                exception.printStackTrace();
                throw exception;
            }
        }
        return usuario;

    }


    public void addUsuario(Usuario usuario) throws Exception {
        DAOTablaUsuarios daoUsuarios = new DAOTablaUsuarios();
        try
        {
            //////transaccion
            this.conn = darConexion();
            daoUsuarios.setConn(conn);
            daoUsuarios.addUsuario(usuario);
            conn.commit();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.err.println("GeneralException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                daoUsuarios.cerrarRecursos();
                if(this.conn!=null)
                    this.conn.close();
            } catch (SQLException exception) {
                System.err.println("SQLException closing resources:" + exception.getMessage());
                exception.printStackTrace();
                throw exception;
            }
        }
    }

    public void updateUsuario(Usuario usuario) throws Exception {
        DAOTablaUsuarios daoUsuarios = new DAOTablaUsuarios();
        try
        {
            //////transaccion
            this.conn = darConexion();
            daoUsuarios.setConn(conn);
            daoUsuarios.updateUsuario(usuario);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.err.println("GeneralException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                daoUsuarios.cerrarRecursos();
                if(this.conn!=null)
                    this.conn.close();
            } catch (SQLException exception) {
                System.err.println("SQLException closing resources:" + exception.getMessage());
                exception.printStackTrace();
                throw exception;
            }
        }
    }

    public void deleteUsuario(Usuario usuario) throws Exception {
        DAOTablaUsuarios daoUsuario = new DAOTablaUsuarios();
        try
        {
            //////transaccion
            this.conn = darConexion();
            daoUsuario.setConn(conn);
            daoUsuario.deleteUsuario(usuario);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.err.println("GeneralException:" + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                daoUsuario.cerrarRecursos();
                if(this.conn!=null)
                    this.conn.close();
            } catch (SQLException exception) {
                System.err.println("SQLException closing resources:" + exception.getMessage());
                exception.printStackTrace();
                throw exception;
            }
        }
    }


}
