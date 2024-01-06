package be.jossart.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.jossart.javabeans.IngredientType;
import be.jossart.javabeans.Ingredient_Server;

@Path("/ingredient2")
public class IngredientAPI2 {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{id}")
	public Response GetIngredient(@PathParam("id") int id) {
		Ingredient_Server ingredient = Ingredient_Server.find(id);
		if(ingredient == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).entity(ingredient).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getId/{name}/{type}")
	public Response GetIngredientId(@PathParam("name") String name, 
			@PathParam("type") String type) {
		
		IngredientType ingredientType = IngredientType.valueOf(type);
		Ingredient_Server ingredient = new Ingredient_Server(name,ingredientType,null);
		Ingredient_Server ingredientWithId = Ingredient_Server.findId(ingredient);
		if(ingredientWithId == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).entity(ingredientWithId).build();
	}
}
