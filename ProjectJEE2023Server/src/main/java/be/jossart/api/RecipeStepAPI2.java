package be.jossart.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.jossart.javabeans.RecipeStep_Server;
import be.jossart.javabeans.Recipe_Server;

@Path("/recipeStep2")
public class RecipeStepAPI2 {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response GetRecipeStep(@PathParam("id") int id) {
        RecipeStep_Server recipeStep = RecipeStep_Server.find(id);
        if(recipeStep == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(recipeStep).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getId/{instruction}/{recipeId}")
    public Response GetRecipeStepId(@PathParam("instruction") String instruction,
                                    @PathParam("recipeId") int recipeId) {
        Recipe_Server recipe = new Recipe_Server(recipeId, null, null);
        RecipeStep_Server recipeStep = new RecipeStep_Server(instruction, recipe);
        RecipeStep_Server recipeStepWithId = RecipeStep_Server.findId(recipeStep);
        if(recipeStepWithId  == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(recipeStepWithId).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findIds/{recipeId}")
    public Response getRecipeStepIdsByRecipe(@PathParam("recipeId") int recipeId) {
        List<Integer> stepIds = RecipeStep_Server.findIds(recipeId);

        if (stepIds == null || stepIds.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.OK).entity(stepIds).build();
    }
}
