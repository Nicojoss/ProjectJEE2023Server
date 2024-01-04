package be.jossart.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.jossart.javabeans.RecipeIngredient_Server;

@Path("/RecipeIngredient")
public class RecipeIngredientAPI {

	@GET
	@Path("{recipeId}/{ingredientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetRecipeIngredient(@PathParam("idIngredient") int idIngredient,
    		@PathParam("idRecipe") int idRecipe) {
        RecipeIngredient_Server recipeIngredient = RecipeIngredient_Server
        		.find(idRecipe,idIngredient);
        if (recipeIngredient == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(recipeIngredient).build();
    }

    @GET
    @Path("{idRecipe}/{quantity}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetRecipeIngredientId(@PathParam("idRecipe") int idRecipe, 
    		@PathParam("quantity") float quantity) {
        RecipeIngredient_Server recipeIngredient = new RecipeIngredient_Server(idRecipe,
        		quantity, null, null);
        RecipeIngredient_Server recipeIngredientWithId = RecipeIngredient_Server
        		.findId(recipeIngredient);

        if (recipeIngredientWithId == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(recipeIngredientWithId).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response CreateRecipeIngredientAndGetId(@FormParam("recipeId") int recipeId,
            @FormParam("ingredientId") int ingredientId,
            @FormParam("quantity") float quantity) {
    	 RecipeIngredient_Server recipeIngredient = new RecipeIngredient_Server(
         		recipeId, ingredientId, quantity, null, null);

         if (!recipeIngredient.create()) {
             return Response.status(Status.SERVICE_UNAVAILABLE).build();
         } else {
             return Response.status(Status.CREATED).entity(recipeIngredient).build();
         }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{recipeId}/{ingredientId}")
    public Response UpdateRecipeIngredient(@PathParam("recipeId") int recipeId,
            @PathParam("ingredientId") int ingredientId,
            @FormParam("quantity") float quantity) {
    	RecipeIngredient_Server recipeIngredient = new RecipeIngredient_Server(
        		recipeId, ingredientId, quantity, null, null);

        if (!recipeIngredient.update()) {
            return Response.status(Status.NO_CONTENT).build();
        } else {
            return Response.status(Status.SERVICE_UNAVAILABLE).build();
        }
    }

    @DELETE
    @Path("{recipeId}/{ingredientId}")
    public Response DeleteRecipeIngredient(@PathParam("recipeId") int recipeId,
            @PathParam("ingredientId") int ingredientId) {
    	RecipeIngredient_Server recipeIngredient = new RecipeIngredient_Server(
        		recipeId, ingredientId, 0, null, null);
        if (!recipeIngredient.delete()) {
            return Response.status(Status.NO_CONTENT).build();
        } else {
            return Response.status(Status.SERVICE_UNAVAILABLE).build();
        }
    }
}

