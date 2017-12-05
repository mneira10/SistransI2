/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * Autor: Juan Felipe García - jf.garcia268@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package rest;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.Historial;
import vos.Producto;
import vos.ProductoPreferido;
import vos.Usuario;
import vos.UsuarioRegistrado;
import vos.UsuarioRegistradoDetail;
import vos.Zona;
import vos.ZonaPreferida;

/**
 * Clase que expone servicios REST con ruta base: http://"ip o nombre de host":8080/VideoAndes/rest/videos/...
 * @author Monitores 2017-20
 */
@Path("usuariosRegistrados")
public class UsuariosRegistradosServices {

	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	

	/**
	 * Metodo que expone servicio REST usando GET que da todos los videos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
	 * @return Json con todos los videos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUsuariosRegistrados() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<UsuarioRegistrado> usuariosRegistrados;
		try {
			usuariosRegistrados = tm.darUsuariosRegistrados();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuariosRegistrados).build();
	}
	
	@GET
    @Path("req9")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getRequerimiento9(@HeaderParam("idRestaurante") Long idRestaurante, @HeaderParam("fecha1") String fecha1, @HeaderParam("fecha1") String fecha2) {
        RotondAndesTM tm = new RotondAndesTM(getPath());
        List<UsuarioRegistrado> usuarios;
        SimpleDateFormat formato = new SimpleDateFormat("YYYY/MM/DD HH24:MI:SS");
        Date fecha11=null;
        Date fecha12=null;
        try {
        	fecha11=formato.parse(fecha1);
        	fecha12=formato.parse(fecha2);
            usuarios = tm.darUsuariosReq9(idRestaurante, fecha11, fecha12);
        } catch (Exception e) {
            return Response.status(500).entity(doErrorMessage(e)).build();
        }
        Response.ResponseBuilder rb =Response.fromResponse(Response.status(200).entity(usuarios).build());
        return rb.header("nombre", "valor").build();
    }
    
    @GET
    @Path("req10")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getRequerimiento10(@HeaderParam("idRestaurante") Long idRestaurante, @HeaderParam("fecha1") String fecha1, @HeaderParam("fecha1") String fecha2) {
        RotondAndesTM tm = new RotondAndesTM(getPath());
        List<UsuarioRegistrado> usuarios;
        SimpleDateFormat formato = new SimpleDateFormat("YYYY/MM/DD HH24:MI:SS");
        Date fecha11=null;
        Date fecha12=null;
        try {
        	fecha11=formato.parse(fecha1);
        	fecha12=formato.parse(fecha2);
            usuarios = tm.darUsuariosReq10(idRestaurante, fecha11, fecha12);
        } catch (Exception e) {
            return Response.status(500).entity(doErrorMessage(e)).build();
        }
        return Response.status(200).entity(usuarios).build();
    }

	 /**
     * Metodo que expone servicio REST usando GET que busca el video con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/<<id>>" para la busqueda"
     * @param 	name - Nombre del video a buscar que entra en la URL como parametro
     * @return Json con el/los videos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "id/{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getUsuarioRegistradoId( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			UsuarioRegistrado v = tm.buscarUsuarioRegistradoporId( id );
			List<ProductoPreferido> list1=tm.buscarPreferenciaPorUsuario(v.getUsuario_id());
			List<ZonaPreferida> zonas=tm.buscarZonaPreferidaPorId(v.getUsuario_id());
			List<Producto> list2=new ArrayList<>();
			List<Zona> zonas1=new ArrayList<>();
			List<Historial> hist=tm.darHistorialUsuario(v.getUsuario_id());
			List<Producto> historial=new ArrayList<>();
			for(ProductoPreferido prod: list1) {
				Producto prod1=tm.buscarProductoPorId(prod.getProducto());
				list2.add(prod1);
			}
			for(ZonaPreferida zona: zonas) {
				Zona zona2=tm.buscarZonaPorNombre(zona.getZonaNombre());
				zonas1.add(zona2);
			}
			for(Historial hist1:hist) {
				Producto prod=tm.buscarProductoPorId(hist1.getIdProducto());
				historial.add(prod);
			}
			UsuarioRegistradoDetail vD=new UsuarioRegistradoDetail(v.getUsuario_id(), v.getLogin(), v.getPassword(), v.getTipo(),list2, zonas1, historial);
			return Response.status( 200 ).entity( vD ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{login}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getUsuarioRegistradoLogin( @QueryParam( "login" ) String login )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			UsuarioRegistrado v = tm.buscarUsuarioRegistradoporLogin( login );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "buenosClientes" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getBuenosClientes(  )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<UsuarioRegistrado> v = tm.buscarBuenosClientes();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a agregar
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado,
										 @HeaderParam("loginAdmin") String loginAdmin,
										 @HeaderParam("adminPassword") String passAdmin) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			if(tm.verificarCredencialesAdmin(loginAdmin,passAdmin)){
			tm.addUsuarioRegistrado(usuarioRegistrado);
			}
			else{
				Exception ef = new Exception("Credenciales inv�lidas");
				return Response.status(412).entity(doErrorMessage(ef)).build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuarioRegistrado).build();
	}
	
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
     * @param video - video a actualizar. 
     * @return Json con el video que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuarioRegistrado(UsuarioRegistrado user) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateUsuarioRegistrado(user);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(user).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
     * @param video - video a aliminar. 
     * @return Json con el video que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuarioRegistrado(UsuarioRegistrado user) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteUsuarioRegistrado(user);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(user).build();
	}

	
}
