package rest;

import tm.RotondAndesTM;
import vos.Producto;
import vos.UsuarioRegistrado;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("pedidos")
public class PedidosServices {
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
    public Response getPedidos(UsuarioRegistrado usuarioRegistrado,
                               @HeaderParam("login") String login,
                               @HeaderParam("password") String password) {
        RotondAndesTM tm = new RotondAndesTM(getPath());

        try {
            if(usuarioRegistrado.getTipo().equals("RESTAURANTE")){
                if(tm.verificarCredencialesRestaurante(login,password)){
                    return Response.status(200).entity(tm.darPedidosRestaurante(usuarioRegistrado)).build();
                }
                else{
                    Exception ef = new Exception("Credenciales inv�lidas");
                    return Response.status(412).entity(doErrorMessage(ef)).build();
                }
            }
            else if(usuarioRegistrado.getTipo().equals("ADMIN")){
                if(tm.verificarCredencialesAdmin(login,password)){
                    return Response.status(200).entity(tm.datTotPedidos()).build();
                }
                else{
                    Exception ef = new Exception("Credenciales inv�lidas");
                    return Response.status(412).entity(doErrorMessage(ef)).build();
                }
            }
            else{
                Exception ef = new Exception("Credenciales inv�lidas");
                return Response.status(412).entity(doErrorMessage(ef)).build();
            }
        } catch (Exception e) {
            return Response.status(500).entity(doErrorMessage(e)).build();
        }

    }



}
