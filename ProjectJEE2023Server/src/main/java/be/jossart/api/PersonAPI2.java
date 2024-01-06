package be.jossart.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.jossart.javabeans.Person_Server;

@Path("/person2")
public class PersonAPI2 {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getPerson(@PathParam("id") int id) {
        Person_Server person = Person_Server.find(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(person).build();
    }
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getId/{username}")
	public Response GetPersonId(@PathParam("username") String username) {
		
		Person_Server person = new Person_Server(username,null);
		Person_Server personWithId = Person_Server.findId(person);
		if(personWithId  == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).entity(personWithId).build();
	}
}
