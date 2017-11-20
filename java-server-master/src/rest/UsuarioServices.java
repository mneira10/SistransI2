package rest;

import tm.RotondAndesTM;
import vos.Usuario;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("usuarios")
public class UsuarioServices {

    @Context
    private ServletContext context;

    private String getPath() {
        return context.getRealPath("WEB-INF/ConnectionData");
    }
    private String doErrorMessage(Exception e){
        return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
    }


    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getVideos() {
        RotondAndesTM tm = new RotondAndesTM(getPath());
        List<Usuario> usuarios;
        try {
            usuarios = tm.darUsuarios();
        } catch (Exception e) {
            return Response.status(500).entity(doErrorMessage(e)).build();
        }
        return Response.status(200).entity(usuarios).build();
    }
    
    @GET
    @Path("req9")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getRequerimiento9(@HeaderParam("idRestaurante") Long idRestaurante, @HeaderParam("fecha1") String fecha1, @HeaderParam("fecha1") String fecha2) {
        RotondAndesTM tm = new RotondAndesTM(getPath());
        List<Usuario> usuarios;
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
        return Response.status(200).entity(usuarios).build();
    }
    
    @GET
    @Path("req10")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getRequerimiento10(@HeaderParam("idRestaurante") Long idRestaurante, @HeaderParam("fecha1") String fecha1, @HeaderParam("fecha1") String fecha2) {
        RotondAndesTM tm = new RotondAndesTM(getPath());
        List<Usuario> usuarios;
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


    @GET
    @Path( "{id: \\d+}" )
    @Produces( { MediaType.APPLICATION_JSON } )
    public Response getUsuarioPorId( @PathParam( "id" ) Long id )
    {
        RotondAndesTM tm = new RotondAndesTM( getPath( ) );
        try
        {
            Usuario v = tm.darUsuariosPorID(id);
            return Response.status( 200 ).entity( v ).build( );
        }
        catch( Exception e )
        {
            return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUsuario(Usuario usuario) {
        RotondAndesTM tm = new RotondAndesTM(getPath());
        try {
            tm.addUsuario(usuario);

        } catch (Exception e) {
            return Response.status(500).entity(doErrorMessage(e)).build();
        }
        return Response.status(200).entity(usuario).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVideo(Usuario usuario) {
        RotondAndesTM tm = new RotondAndesTM(getPath());
        try {
            tm.updateUsuario(usuario);
        } catch (Exception e) {
            return Response.status(500).entity(doErrorMessage(e)).build();
        }
        return Response.status(200).entity(usuario).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVideo(Usuario usuario) {
        RotondAndesTM tm = new RotondAndesTM(getPath());
        try {
            tm.deleteUsuario(usuario);
        } catch (Exception e) {
            return Response.status(500).entity(doErrorMessage(e)).build();
        }
        return Response.status(200).entity(usuario).build();
    }


}
