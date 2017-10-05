package tm;

import dao.DAOTablaIngredientes;
import dao.DAOTablaMenus;
import dao.DAOTablaProductos;
import dao.DAOTablaProductosPreferidos;
import dao.DAOTablaRestaurante;
import dao.DAOTablaUsuarios;
import dao.DAOTablaUsuariosRegistrados;
import dao.DAOTablaZonaPreferida;
import vos.Ingrediente;
import vos.Menu;
import vos.Producto;
import vos.ProductoPreferido;
import vos.Restaurante;
import vos.Usuario;
import vos.UsuarioRegistrado;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaZonas;
import vos.Zona;
import vos.ZonaPreferida;

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

	public void addMenu(Menu ingrediente) throws Exception {
		DAOTablaMenus daoZonas = new DAOTablaMenus();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.addMenu(ingrediente);
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


}
