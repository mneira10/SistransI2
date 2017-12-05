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
import vos.ProductoIndividual;
import vos.Restaurante;
import vos.Zona;

/**
 * Clase que expone servicios REST con ruta base: http://"ip o nombre de host":8080/VideoAndes/rest/videos/...
 * @author Monitores 2017-20
 */
@Path("productos")
public class ProductosServices {

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
	public Response getProductos() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	
	@GET
	@Path( "masOfrecido" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosMasOfrecidos()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<ProductoIndividual> v = tm.buscarProductoMasOfrecidos();
			List<Producto> vd=new ArrayList<>();
			for(ProductoIndividual prod: v) {
				Producto p= tm.buscarProductoPorId(prod.getProdId());
				vd.add(p);
			}
			return Response.status( 200 ).entity( vd ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "masVendido" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosMasVendidos()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.buscarProductoMasVendidos();
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
	public Response addProducto(Producto producto, @HeaderParam("loginRestaurante") String loginAdmin, @HeaderParam("restaurantePassword") String passAdmin) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			if(tm.verificarCredencialesRestaurante(loginAdmin,passAdmin)){
			tm.addProducto(producto);
			}
			else{
				Exception ef = new Exception("Credenciales inv�lidas");
				return Response.status(412).entity(doErrorMessage(ef)).build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(producto).build();
	}
	
	//RFC1
	@GET
	@Path( "general" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductos1( @HeaderParam( "idRestaurante" ) Long idRestaurante, @HeaderParam( "categoria" ) String categoria, @HeaderParam( "rangoMinimo" ) Double rangoMinimo, @HeaderParam( "rangoMaximo" ) Double rangoMaximo, @HeaderParam("criteriosOrdenamiento") String criterios)
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			String[]criterios2=criterios.split(",");
			List<Producto> v = tm.buscarProductosGenerales(idRestaurante, categoria, rangoMinimo, rangoMaximo, criterios2);
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}
