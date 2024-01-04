package be.jossart.javabeans;

import java.io.Serializable;
import java.util.Objects;

public class RecipeStep_Server implements Serializable{
	//ATTRIBUTES
	private static final long serialVersionUID = -1368662956594765085L;
	private int idRecipeStep;
	private String instruction;
	private Recipe_Server recipe;
	//CTOR
	public RecipeStep_Server() { 
	}
	public RecipeStep_Server(int idRecipeStep, String instruction, Recipe_Server recipe) {
		this.idRecipeStep = idRecipeStep;
		this.instruction = instruction;
		this.recipe = recipe;
	}
	//METHODS
	//GETTERS SETTERS
	public int getIdRecipeStep() {
		return idRecipeStep;
	}
	public void setIdRecipeStep(int idRecipeStep) {
		this.idRecipeStep = idRecipeStep;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public Recipe_Server getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe_Server recipe) {
		this.recipe = recipe;
	}
	@Override
	public String toString() {
		return "RecipeStep_Server [idRecipeStep=" + idRecipeStep + ", instruction=" + instruction + ", recipe=" + recipe
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idRecipeStep, instruction, recipe);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeStep_Server other = (RecipeStep_Server) obj;
		return idRecipeStep == other.idRecipeStep && Objects.equals(instruction, other.instruction)
				&& Objects.equals(recipe, other.recipe);
	}
}
