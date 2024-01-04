package be.jossart.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.jossart.javabeans.Recipe_Server;

@Path("/recipe")
public class RecipeAPI {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{recherche}")
	public Response getRechercheRecipe(@PathParam("recherche") String recherche) {
		List<Recipe_Server> retour = null;
		System.out.println("rechercher : " +recherche);
		if (recherche.length()>=50|| recherche==null) {
			return Response.status(Status.BAD_REQUEST).build(); 
		}else {
			retour  = Recipe_Server.searchRecipe(recherche);
		}

		
		return Response.status(Status.OK).entity(retour).build();
	}
}
