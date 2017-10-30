package tm;

import dao.DAOTablaCarritos;
import dao.DAOTablaHistorial;
import dao.DAOTablaIngredientes;
import dao.DAOTablaItems;
import dao.DAOTablaMenus;
import dao.DAOTablaMenusProductoIndividual;
import dao.DAOTablaProductos;
import dao.DAOTablaProductosIndividuales;
import dao.DAOTablaProductosPreferidos;
import dao.DAOTablaRestaurante;
import dao.DAOTablaUsuarios;
import dao.DAOTablaUsuariosRegistrados;
import dao.DAOTablaZonaPreferida;
import vos.*;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaZonas;

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
	public void addCarrito(Carrito carrito) throws Exception {

		DAOTablaCarritos daoCarritos = new DAOTablaCarritos(); 
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoCarritos.setConn(conn);
			daoCarritos.addCarrito(carrito);
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
				daoCarritos.cerrarRecursos();
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

	public void updateZona(Zona zona) throws Exception {
		DAOTablaZonas daoZona = new DAOTablaZonas();
		try
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			daoZona.updateZona(zona);

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void deleteZona(Zona zona) throws Exception {
		DAOTablaZonas daoZona = new DAOTablaZonas();
		try
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			daoZona.deleteZona(zona);

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
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

	public List<ZonaPreferida> darZonasPreferidas() throws Exception {
		List<ZonaPreferida> zonas;
		DAOTablaZonaPreferida daoZonas = new DAOTablaZonaPreferida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.darZonasPreferidas();

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

	public List<ZonaPreferida> buscarZonaPreferidaPorId(Long id) throws Exception{
		List<ZonaPreferida> zonas;
		DAOTablaZonaPreferida daoZonas = new DAOTablaZonaPreferida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarZonaPreferidaPorIdUsuarioRegistrado(id);

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

	public List<ZonaPreferida> buscarZonaPreferidaPorNombre(String nombre) throws Exception {
		List<ZonaPreferida> zonas;
		DAOTablaZonaPreferida daoZonas = new DAOTablaZonaPreferida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarHistorialPorNombreZona(nombre);

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

	public void addZonaPreferida(ZonaPreferida zona) throws Exception {
		DAOTablaZonaPreferida daoZonas = new DAOTablaZonaPreferida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.addZonaPreferida(zona);
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

	public void deleteZonaPreferida(ZonaPreferida zona) throws Exception {
		DAOTablaZonaPreferida daoZonas = new DAOTablaZonaPreferida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.deleteZonaPreferida(zona);
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

	public List<UsuarioRegistrado> darUsuariosRegistrados() throws Exception {
		List<UsuarioRegistrado> zonas;
		DAOTablaUsuariosRegistrados daoZonas = new DAOTablaUsuariosRegistrados();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.darUsuarioRegistrados();

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

	public UsuarioRegistrado buscarUsuarioRegistradoporId(Long id) throws Exception {
		UsuarioRegistrado zonas;
		DAOTablaUsuariosRegistrados daoZonas = new DAOTablaUsuariosRegistrados();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarUsuarioRegistradosPorID(id);

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

	public UsuarioRegistrado buscarUsuarioRegistradoporLogin(String login) throws Exception {
		UsuarioRegistrado zonas;
		DAOTablaUsuariosRegistrados daoZonas = new DAOTablaUsuariosRegistrados();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarUsuarioRegistradosPorLogin(login);

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

	public void addUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) throws Exception {
		DAOTablaUsuariosRegistrados daoZonas = new DAOTablaUsuariosRegistrados();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.addUsuarioRegistrado(usuarioRegistrado);
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

	public void updateUsuarioRegistrado(UsuarioRegistrado user2) throws Exception {
		DAOTablaUsuariosRegistrados daoZonas = new DAOTablaUsuariosRegistrados();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.updateUsuarioRegistrado(user2);
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

	public void deleteUsuarioRegistrado(UsuarioRegistrado user2) throws Exception {
		DAOTablaUsuariosRegistrados daoZonas = new DAOTablaUsuariosRegistrados();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.deleteUsuarioRegistrado(user2);
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

	public List<Restaurante> darRestaurantes() throws Exception {
		List<Restaurante> zonas;
		DAOTablaRestaurante daoZonas = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.darRestaurantes();

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

	public List<Restaurante> buscarRestaurantePorId(Long id) throws Exception {
		List<Restaurante> zonas;
		DAOTablaRestaurante daoZonas = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarRestaurantesPorID(id);

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

	public List<Restaurante> buscarRestaurantesPorZona(String zona) throws Exception {
		List<Restaurante> zonas;
		DAOTablaRestaurante daoZonas = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarRestaurantesPorZona(zona);

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

	public void addRestaurante(Restaurante rest) throws Exception {
		DAOTablaRestaurante daoZonas = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.addRestaurante(rest);
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

	public void updateRestaurante(Restaurante rest) throws Exception {
		DAOTablaRestaurante daoZonas = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.updateRestaurante(rest);
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

	public void deleteRestaurante(Restaurante rest) throws Exception {
		DAOTablaRestaurante daoZonas = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.deleteRestaurante(rest);
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

	public List<Producto> darProductos() throws Exception {
		List<Producto> zonas;
		DAOTablaProductos daoZonas = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.darProductos();

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

	public Producto buscarProductoPorId(Long id) throws Exception {
		Producto zonas;
		DAOTablaProductos daoZonas = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarProductosPorId(id);

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

	public List<ProductoPreferido> buscarPreferenciaPorProductoUsuario(Long idProd, Long idUsuario) throws Exception {
		List<ProductoPreferido> zonas;
		DAOTablaProductosPreferidos daoZonas = new DAOTablaProductosPreferidos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarProductosPreferidosPorIDUsrIdProd(idUsuario, idProd);

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

	public List<ProductoPreferido> buscarPreferenciaPorProducto(Long idProd) throws Exception {
		List<ProductoPreferido> zonas;
		DAOTablaProductosPreferidos daoZonas = new DAOTablaProductosPreferidos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarProductosPreferidosPorIDProd(idProd);

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

	public void addProdPreferido(ProductoPreferido prod) throws Exception {
		DAOTablaProductosPreferidos daoZonas = new DAOTablaProductosPreferidos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.addProductoPreferido(prod);
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

	public List<ProductoPreferido> buscarPreferenciaPorUsuario(Long idUsuario) throws Exception {
		List<ProductoPreferido> zonas;
		DAOTablaProductosPreferidos daoZonas = new DAOTablaProductosPreferidos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarProductosPreferidosPorIDUsr(idUsuario);

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


	public void deleteProductoPreferido(ProductoPreferido zona) throws Exception {
		DAOTablaProductosPreferidos daoZonas = new DAOTablaProductosPreferidos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.deleteProductoPreferido(zona);
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

	public boolean verificarCredencialesRestaurante(String loginAdmin, String passAdmin) throws Exception {
		DAOTablaUsuariosRegistrados daoUsuarios= new DAOTablaUsuariosRegistrados();
		return daoUsuarios.confirmarAdmin(loginAdmin, passAdmin);
	}

	public boolean verificarCredencialesAdmin(String loginAdmin, String passAdmin) throws Exception {
		DAOTablaUsuariosRegistrados daoUsuarios= new DAOTablaUsuariosRegistrados();
		return daoUsuarios.confirmarRestaurante(loginAdmin, passAdmin);
	}

	public void addIngrediente(Ingrediente ingrediente) throws Exception {
		DAOTablaIngredientes daoZonas = new DAOTablaIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.addIngrediente(ingrediente);
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

	public void addMenu(Menu menu) throws Exception {
		DAOTablaMenus daoMenus = new DAOTablaMenus();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			daoMenus.addMenu(menu);
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
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	public void addProducto(Producto producto) throws Exception {
		DAOTablaProductos daoZonas = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.addProducto(producto);
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
	public void addProductoInd(ProductoIndividual prodInd) throws Exception {
		DAOTablaProductosIndividuales daoProdInd= new DAOTablaProductosIndividuales();
		DAOTablaProductos daoTablaProductos = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProdInd.setConn(conn);
			daoTablaProductos.addProducto(prodInd);
			daoTablaProductos.setConn(conn);
			daoProdInd.addProductoIndividual(prodInd);
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
				daoProdInd.cerrarRecursos();
				daoTablaProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	public List<ZonaPreferida> buscarZonaPreferidaPorIdNombre(Long id, String nombre) throws Exception {
		List<ZonaPreferida> zonas;
		DAOTablaZonaPreferida daoZonas = new DAOTablaZonaPreferida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarZonaPreferidaPorId(nombre, id);

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
	
	public List<MenuProductoIndividual> darMenuProdInd() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Producto> buscarProductosRestaurante(Long idRestaurante) throws SQLException {
		List<Producto> productos;
		DAOTablaProductos daoProductos = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			productos = daoProductos.buscarProductosRestaurante(idRestaurante);

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
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public List<Producto> buscarProductosRangoPrecios(Double rangoMinimo, Double rangoMaximo) throws SQLException {
		List<Producto> productos;
		DAOTablaProductos daoProductos = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			productos = daoProductos.buscarProductosRangoPrecios(rangoMinimo, rangoMaximo);

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
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public List<Producto> buscarProductosCategoria(String categoria) throws SQLException {
		List<Producto> productos;
		DAOTablaProductos daoProductos = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			productos = daoProductos.buscarProductosCategoria(categoria);

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
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public void addMenuProductoIndividual(MenuProductoIndividual menuProductoIndividual) {
		// TODO Auto-generated method stub

	}

	public List<Carrito> darCarritos()  throws Exception{
		List<Carrito> zonas;
		DAOTablaCarritos daoZonas = new DAOTablaCarritos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.darCarritos();

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

	public List<Carrito> buscarCarritoPorId(Long id) throws Exception {
		List<Carrito> carritos;
		DAOTablaCarritos daoZonas = new DAOTablaCarritos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			carritos = daoZonas.buscarCarritosPorId(id);

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
		return carritos;
	}

	public void updateCarrito(Carrito zona) throws Exception {
		DAOTablaCarritos daoZonas = new DAOTablaCarritos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.updateCarrito(zona);
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

	public void deleteCarrito(Carrito zona) throws Exception {
		DAOTablaCarritos daoZonas = new DAOTablaCarritos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.deleteCarrito(zona);
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

	public List<Menu> darMenus() throws Exception {
		List<Menu> zonas;
		DAOTablaMenus daoZonas = new DAOTablaMenus();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.darMenus();

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

	public List<Producto> darProductosMenu(Long id) throws Exception {
		List<MenuProductoIndividual> menusProdInd;
		List<Producto> productos=new ArrayList<>();
		DAOTablaMenusProductoIndividual daoProductos = new DAOTablaMenusProductoIndividual();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			menusProdInd = daoProductos.buscarProductosPreferidosPorIDMenu(id);
			for(MenuProductoIndividual menu: menusProdInd) {
				Producto p=buscarProductoPorId(menu.getId_prod_individual());
				productos.add(p);
			}

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
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public List<Menu> buscarMenuPorId(Long id) throws Exception {
		List<Menu> zonas;
		DAOTablaMenus daoZonas = new DAOTablaMenus();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarMenusPorID(id);

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

	public List<Item> buscarItemsCarrito(Long idCarrito) throws Exception{
		List<Item> zonas;
		DAOTablaItems daoZonas = new DAOTablaItems();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.buscarItemsPorIDCarrito(idCarrito);

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

	public void addItemHistorial(Long idCarrito) throws Exception {
		DAOTablaHistorial daoZonas = new DAOTablaHistorial();
		DAOTablaProductosIndividuales daoItems = new DAOTablaProductosIndividuales();
		try 
		{
			List<Item> itemsCarrito=buscarItemsCarrito(idCarrito);
			Carrito carrito= buscarCarritoPorId(idCarrito).get(0);
			for (Item item: itemsCarrito) {
				List<Menu> esMenu= buscarMenuPorId(item.getProductoId());
				if(esMenu.size()==0) {
					this.conn = darConexion();
					daoZonas.setConn(conn);
					Historial hist= new Historial(item.getProductoId(), carrito.getUsuarioId(),new Date());
					daoZonas.addHistorial(hist);
					daoZonas.cerrarRecursos();
					daoItems.setConn(conn);
					daoItems.venderProductoIndividual(item.getProductoId());
					daoItems.cerrarRecursos();
				}
				else {
					List<Producto> lista=darProductosMenu(esMenu.get(0).getId());
					for(Producto prod: lista) {
						this.conn = darConexion();
						daoZonas.setConn(conn);
						Historial hist= new Historial(prod.getId(), carrito.getUsuarioId(),new Date());
						daoZonas.addHistorial(hist);
						daoZonas.cerrarRecursos();
						daoItems.setConn(conn);
						daoItems.venderProductoIndividual(prod.getId());
						daoItems.cerrarRecursos();
					}
				}
			}
		}
			catch (SQLException e) {
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
					daoItems.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		public void addItem(Item item) throws Exception {
			DAOTablaItems daoMenus = new DAOTablaItems();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoMenus.setConn(conn);
				daoMenus.addItem(item);
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
					daoMenus.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}

		}
		
		public void deleteItem(Item item) {
			//TODO RF17
		}



		public void surtirRestaurante(Restaurante restaurante) throws Exception{
			DAOTablaProductos daoProductos = new DAOTablaProductos();
			DAOTablaProductosIndividuales daoTablaProductosIndividuales = new DAOTablaProductosIndividuales();
			try
			{
				//////transaccion
				this.conn = darConexion();
				List<Producto> productosDeRestaurante = daoProductos.buscarProductosRestaurante(restaurante.getId());
				for(Producto prod : productosDeRestaurante){
					daoTablaProductosIndividuales.surtirProducto(prod.getId());
				}
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
					daoProductos.cerrarRecursos();
					daoTablaProductosIndividuales.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}

		}

		public List<Producto> buscarProductoMasOfrecidos() {
			// TODO RFC4
			return null;
		}

		public List<Producto> buscarProductoMasVendidos() {
			// TODO RFC6
			return null;
		}

	public List<Producto> darPedidosRestaurante(UsuarioRegistrado usuarioRegistrado) throws Exception{
		List<Producto> productos = new ArrayList<>();
		DAOTablaHistorial daoHistorial=  new DAOTablaHistorial();
		try
		{
			//////transaccion
			this.conn = darConexion();
			daoHistorial.setConn(conn);
			List<Historial> historial = daoHistorial.buscarHistorialPorIdUsuarioRegistrado(usuarioRegistrado.getUsuario_id());
			for(Historial historia : historial){
				productos.add(buscarProductoPorId(historia.getIdProducto()));
			}

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
				daoHistorial.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}


	public List<Producto> datTotPedidos() throws Exception {
		List<Producto> productos = new ArrayList<>();
		DAOTablaHistorial daoHistorial=  new DAOTablaHistorial();
		try
		{
			//////transaccion
			this.conn = darConexion();
			daoHistorial.setConn(conn);
			List<Historial> historial = daoHistorial.darHistorial();
			for(Historial historia : historial){
				productos.add(buscarProductoPorId(historia.getIdProducto()));
			}

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
				daoHistorial.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public List<Historial> darHistorialUsuario(Long usuario_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductoFacturado> datTotProductosFacturados(Date fechaInicio,Date fechaFin) throws Exception{
		List<ProductoFacturado> productos = new ArrayList<>();
		DAOTablaHistorial daoHistorial=  new DAOTablaHistorial();
		try
		{
			//////transaccion
			this.conn = darConexion();
			daoHistorial.setConn(conn);
			productos = daoHistorial.darTotProductosFacturados( fechaInicio,fechaFin);

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
				daoHistorial.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public List<ProductoFacturado> darProductosFacturadosRestaurante(Long usuario_id,Date fechaInicio,Date fechaFin) throws Exception {
		List<ProductoFacturado> productos = new ArrayList<>();
		DAOTablaHistorial daoHistorial=  new DAOTablaHistorial();
		try
		{
			//////transaccion
			this.conn = darConexion();
			daoHistorial.setConn(conn);
			productos = daoHistorial.darProductosFactoradosRestaurante(usuario_id,fechaInicio, fechaFin);

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
				daoHistorial.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public List<ProductoMasVendido> darMasVendidos() throws Exception{

		List<ProductoMasVendido> productos = new ArrayList<>();
		DAOTablaHistorial daoHistorial=  new DAOTablaHistorial();
		try
		{
			//////transaccion
			this.conn = darConexion();
			daoHistorial.setConn(conn);
			productos = daoHistorial.darProductosMasVendidos();

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
				daoHistorial.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}
}

