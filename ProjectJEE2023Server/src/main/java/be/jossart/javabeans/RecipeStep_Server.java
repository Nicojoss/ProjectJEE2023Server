package be.jossart.javabeans;

import java.io.Serializable;

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

}
