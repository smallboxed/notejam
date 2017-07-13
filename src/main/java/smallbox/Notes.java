package smallbox;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "notes" path)
 */
@Path("notes")
public class Notes {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllNotes(@QueryParam("query") String search) {
    		Dao Data = new Dao();
    		if(search!=null) {
    			return Data.selectAllNotes(search);
    		} else {
    			return Data.selectAllNotes();
    		}
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getNote(@PathParam("id") int id) {
    		Dao Data = new Dao();
        return Data.selectNote(id);
    }
    @POST
    @Consumes("text/plain")
    public void setNote(String note) {
    		Dao Data = new Dao();
        Data.createNote(note);
    }
}
