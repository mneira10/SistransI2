package rest;

import tm.RotondAndesTM;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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


   

}
