package be.jossart.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.jossart.javabeans.Person_Server;
import be.jossart.javabeans.RecipeGender;
import be.jossart.javabeans.Recipe_Server;

@Path("/recipe2")
public class RecipeAPI2 {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response GetRecipe(@PathParam("id") int id) {
        Recipe_Server recipe = Recipe_Server.find(id);
        if (recipe == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(recipe).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getId/{name}/{gender}/{idPerson}")
    public Response GetRecipeId(@PathParam("name") String name,
            @PathParam("gender") String gender,
            @PathParam("idPerson") int idPerson) {

        RecipeGender recipeGender = RecipeGender.valueOf(gender);
        Person_Server person = new Person_Server(idPerson, null, null, null, null);
        Recipe_Server recipe = new Recipe_Server(name, person, recipeGender, null, null);
        Recipe_Server recipeWithId = Recipe_Server.findId(recipe);

        if (recipeWithId == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(recipeWithId).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findIds/{idPerson}")
    public Response GetRecipeIdsByPerson(@PathParam("idPerson") int idPerson) {
        List<Integer> recipeIds = Recipe_Server.findIds(idPerson);

        if (recipeIds == null || recipeIds.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.OK).entity(recipeIds).build();
    }
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
