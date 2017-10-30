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


import java.util.ArrayList;
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
import vos.Producto;
import vos.Restaurante;
import vos.RestauranteDetail;
import vos.Zona;
import vos.ZonaDetail;

/**
 * Clase que expone servicios REST con ruta base: http://"ip o nombre de host":8080/VideoAndes/rest/videos/...
 * @author Monitores 2017-20
 */
@Path("zonas")
public class ZonasServices {

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
	public Response getZonas() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Zona> zonas;
		List<ZonaDetail> zonasResp;
		try {
			zonas = tm.darZonas();
			zonasResp= new ArrayList<>();
			for(Zona zona:zonas) {
				List<Restaurante> restaurantesZona=tm.buscarRestaurantesPorZona(zona.getNombre());
				List<RestauranteDetail> restaurantesDetallados= new ArrayList<>();
				for(Restaurante restaurante:restaurantesZona) {
					List<Producto> productosRestaurante= tm.buscarProductosRestaurante(restaurante.getId());
					RestauranteDetail restDetail=new RestauranteDetail(restaurante.getId(), restaurante.getNombre(), restaurante.getTipo(),restaurante.getPaginaWeb(), restaurante.getZonaId(), restaurante.getIdAdmin(), productosRestaurante);
					restaurantesDetallados.add(restDetail);
				}
				ZonaDetail zona1= new ZonaDetail(zona.getNombre(), zona.getCerrado(), zona.getTipo(), zona.getAptoDescap(), zona.getCapacidad(), restaurantesDetallados);
				zonasResp.add(zona1);
			}

		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zonasResp).build();
	}

	/**
	 * Metodo que expone servicio REST usando GET que busca el video con el id que entra como parametro
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/<<id>>" para la busqueda"
	 * @param name - Nombre del video a buscar que entra en la URL como parametro 
	 * @return Json con el/los videos encontrados con el nombre que entra como parametro o json con 
	 * el error que se produjo
	 */
	@GET
	@Path( "nombre" )
	@HeaderParam("nombre")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZona(@HeaderParam( "nombre" ) String nombre )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Zona v = tm.buscarZonaPorNombre( nombre );
			System.out.println("BUSCAAAAAAAAANDO");
			List<Restaurante> restaurantes=tm.buscarRestaurantesPorZona(v.getNombre());
			List<RestauranteDetail> restDetail=new ArrayList<>();
			for(Restaurante rest: restaurantes) {
				List<Producto> productos= tm.buscarProductosRestaurante(rest.getId());
				RestauranteDetail rD= new RestauranteDetail(rest.getId(), rest.getNombre(), rest.getTipo(), rest.getPaginaWeb(),rest.getZonaId(),rest.getIdAdmin(),productos);
				restDetail.add(rD);
			}
			ZonaDetail vD=new ZonaDetail(v.getNombre(), v.getCerrado(),v.getTipo(),v.getAptoDescap(), v.getCapacidad(), restDetail);
			return Response.status( 200 ).entity( vD ).build( );			
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
	public Response addZona(Zona zona, 
			@HeaderParam("loginAdmin") String loginAdmin,
			@HeaderParam("adminPassword") String passAdmin) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			if(tm.verificarCredencialesAdmin(loginAdmin,passAdmin)){
				tm.addZona(zona);
			}
			else{
				Exception ef = new Exception("Credenciales inv�lidas");
				return Response.status(412).entity(doErrorMessage(ef)).build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zona).build();
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
	public Response updateZona(Zona zona) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateZona(zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zona).build();
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
	public Response deleteZona(Zona zona) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteZona(zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zona).build();
	}


}
