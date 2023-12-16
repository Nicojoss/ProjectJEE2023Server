package be.jossart.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.jossart.javabeans.Person;

@Path("person")
public class PersonAPI {
	
	@POST
	public Response createPerson(@FormParam("firstname") String firstname, @FormParam("lastname") String lastname,
			@FormParam("username") String username, @FormParam("password") String password ) {
		
		if(firstname == null || lastname == null || username == null || password == null) {
			Response.status(Status.BAD_REQUEST).build();
		}
		
		Person person = new Person(firstname, lastname, username, password);
		if(!person.create()) {
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}else {
			return Response.status(Status.CREATED).entity(person).build();
		}
	}
}
