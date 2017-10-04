package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaVideos;
import dao.DAOTablaZonas;
import vos.Video;
import vos.Zona;

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

	public Zona buscarZonaPorNombre(String name) throws SQLException,Exception {
		List<Zona> zonas;
		DAOTablaZonas daoZonas = new DAOTablaZonas();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarZonasPorName(name);

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
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return zonas.get(0);
	}
	
public void addZona(Zona zona) throws Exception {
		
		DAOTablaZonas daoZonas = new DAOTablaZonas();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.addZona(zona);
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
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

public List<Zona> darZonas() throws Exception {
	List<Zona> zonas;
	DAOTablaZonas daoZonas = new DAOTablaZonas();
	try 
	{
		//////transaccion
		this.conn = darConexion();
		daoZonas.setConn(conn);
		zonas = daoZonas.darZonas();

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
			daoZonas.cerrarRecursos();
			if(this.conn!=null)
				this.conn.close();
		} catch (SQLException exception) {
			System.err.println("SQLException closing resources:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		}
	}
	return zonas;
}




}
