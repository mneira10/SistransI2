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
import vos.*;

/**
 * Clase que expone servicios REST con ruta base: http://"ip o nombre de host":8080/VideoAndes/rest/videos/...
 * @author Monitores 2017-20
 */
@Path("restaurantes")
public class RestauranteServices {

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
	public Response getRestaurantes() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Restaurante> rest;
		try {
			rest = tm.darRestaurantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rest).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el video con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/<<id>>" para la busqueda"
     * @param name - Nombre del video a buscar que entra en la URL como parametro 
     * @return Json con el/los videos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{id:\\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRestaurante( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Restaurante> v = tm.buscarRestaurantePorId( id );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "zona" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRestaurantesZona( @HeaderParam( "nombreZona" ) String zona )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		List<Restaurante> v;
		try
		{
			v = tm.buscarRestaurantesPorZona( zona );
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
	public Response addRestaurante(Restaurante usuarioRegistrado, @HeaderParam("loginAdmin") String loginAdmin, @HeaderParam("adminPassword") String passAdmin) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			if(tm.verificarCredencialesAdmin(loginAdmin,passAdmin)){
			tm.addRestaurante(usuarioRegistrado);
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
	public Response updateRestaurante(Restaurante rest) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateRestaurante(rest);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rest).build();
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
	public Response deleteRestaurante(Restaurante rest) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteRestaurante(rest);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rest).build();
	}

	
	@PUT
	@Path( "surtir/{idRestaurante: \\d+}" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response surtirRestaurante(@PathParam("idRestaurante") Long idRest,
									  @HeaderParam("login") String login,
									  @HeaderParam("password") String password) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		Restaurante r;
		try {
			r=tm.buscarRestaurantePorId(idRest).get(0);
			if(r==null) {
				return Response.status(404).entity("No existe el restaurante a surtir").build();
			}
			else if(!tm.verificarCredencialesRestaurante(login,password)){
				Exception ef = new Exception("Credenciales invalidas");
				return Response.status(412).entity(doErrorMessage(ef)).build();
			}
			else {
				tm.surtirRestaurante(r);
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(r).build();
	}
	
	//\RFC5
	@GET
	@Path( "rentabilidad" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRentabilidadRestaurante( @HeaderParam( "idUsuarioRegistrado" ) Long idUsuarioRegistrado,
												@HeaderParam( "idRestaurante" ) Integer idRestaurante,
												@HeaderParam("login") String login,
												@HeaderParam("password") String password,
												@HeaderParam("fechaInicio") Date fechaInicio,
												@HeaderParam("fechaFin") Date fechaFin)throws Exception
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );

		try
		{
			UsuarioRegistrado usuarioRegistrado = tm.buscarUsuarioRegistradoporId(idUsuarioRegistrado);
			if(usuarioRegistrado.getTipo().equals("RESTAURANTE")){
				if(tm.verificarCredencialesRestaurante(login,password)){
					return Response.status(200).entity(tm.darProductosFacturadosRestaurante(usuarioRegistrado.getUsuario_id(),fechaInicio,fechaFin)).build();
				}
				else{
					Exception ef = new Exception("Credenciales inv�lidas");
					return Response.status(412).entity(doErrorMessage(ef)).build();
				}
			}
			else{
				Exception ef = new Exception("Tipo de usuario invalido");
				return Response.status(412).entity(doErrorMessage(ef)).build();
			}

		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "rentabilidad" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getTotRentabilidadRestaurantes(UsuarioRegistrado usuarioRegistrado,
												   @HeaderParam("login") String login,
												   @HeaderParam("password") String password,
												   @HeaderParam("fechaInicio") Date fechaInicio,
												   @HeaderParam("fechaFin") Date fechaFin)throws Exception
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );

		try
		{
			if(usuarioRegistrado.getTipo().equals("ADMIN")){
				if(tm.verificarCredencialesAdmin(login,password)){
					return Response.status(200).entity(tm.datTotProductosFacturados(fechaFin,fechaFin)).build();
				}
				else{
					Exception ef = new Exception("Credenciales inv�lidas");
					return Response.status(412).entity(doErrorMessage(ef)).build();
				}
			}

			else{
				Exception ef = new Exception("Tipo de usuario invalido");
				return Response.status(412).entity(doErrorMessage(ef)).build();
			}

		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

}
