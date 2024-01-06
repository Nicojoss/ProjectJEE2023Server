package be.jossart.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.jossart.javabeans.RecipeIngredient_Server;

@Path("/RecipeIngredient2")
public class RecipeIngredientAPI2 {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{recipeId}/{ingredientId}")
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getId/{idRecipe}/{quantity}")
    public Response GetRecipeIngredientId(@PathParam("idRecipe") int idRecipe, 
    		@PathParam("quantity") double quantity) {
        RecipeIngredient_Server recipeIngredient = new RecipeIngredient_Server(idRecipe,
        		quantity, null, null);
        RecipeIngredient_Server recipeIngredientWithId = RecipeIngredient_Server
        		.findId(recipeIngredient);

        if (recipeIngredientWithId == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(recipeIngredientWithId).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findIds/{recipeId}")
    public Response getRecipeIngredientIdsByRecipe(@PathParam("recipeId") int recipeId) {
        List<Integer> ingredientIds = RecipeIngredient_Server.findIds(recipeId);

        if (ingredientIds == null || ingredientIds.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.OK).entity(ingredientIds).build();
    }
}
