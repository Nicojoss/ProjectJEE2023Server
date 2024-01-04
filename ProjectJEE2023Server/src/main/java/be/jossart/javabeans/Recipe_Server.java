package be.jossart.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.jossart.connection.DbConnection;
import be.jossart.dao.RecipeDAO_Server;

public class Recipe_Server implements Serializable{

	//ATTRIBUTES
	private static final long serialVersionUID = -7287441285222249732L;
	private int idRecipe;
	private String name;
	private Person_Server person;
	private RecipeGender recipeGender;
	private ArrayList<Ingredient_Server> ingredientList;
	private ArrayList<RecipeStep_Server> recipeStepList;
	
	//CTOR
	public Recipe_Server() { // Je les aies mis si on en a pas besoin on supprime à la fin
		ingredientList = new ArrayList<>();
		recipeStepList = new ArrayList<>();
	}
	public Recipe_Server(int idRecipe, String name, Person_Server person, RecipeGender recipeGender,
			ArrayList<Ingredient_Server> ingredientList, ArrayList<RecipeStep_Server> recipeStepList) {
		super();
		this.idRecipe = idRecipe;
		this.name = name;
		this.person = person;
		this.recipeGender = recipeGender;
		this.ingredientList = ingredientList;
		this.recipeStepList = recipeStepList;
	}
	public Recipe_Server(int idRecipe,ArrayList<Ingredient_Server> ingredientList,
			ArrayList<RecipeStep_Server> recipeStepList) {
		super();
		this.idRecipe = idRecipe;
		this.ingredientList = ingredientList;
		this.recipeStepList = recipeStepList;
	}
	//METHODS

	//GETTERS SETTERS
	public int getIdRecipe() {
		return idRecipe;
	}
	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person_Server getPerson() {
		return person;
	}
	public void setPerson(Person_Server person) {
		this.person = person;
	}
	public RecipeGender getRecipeGender() {
		return recipeGender;
	}
	public void setRecipeGender(RecipeGender recipeGender) {
		this.recipeGender = recipeGender;
	}
	public ArrayList<Ingredient_Server> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(ArrayList<Ingredient_Server> ingredientList) {
		this.ingredientList = ingredientList;
	}
	public ArrayList<RecipeStep_Server> getRecipeStepList() {
		return recipeStepList;
	}
	public void setRecipeStepList(ArrayList<RecipeStep_Server> recipeStepList) {
		this.recipeStepList = recipeStepList;
	}
	public static List<Recipe_Server> searchRecipe(String recherche){
		RecipeDAO_Server daoRecipe = new RecipeDAO_Server(DbConnection.getInstance());
		
		return daoRecipe.findRecipeByName(recherche);
	}
	@Override
	public String toString() {
		return "Recipe_Server [idRecipe=" + idRecipe + ", name=" + name + ", person=" + person + ", recipeGender="
				+ recipeGender + ", ingredientList=" + ingredientList + ", recipeStepList=" + recipeStepList + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idRecipe, ingredientList, name, person, recipeGender, recipeStepList);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe_Server other = (Recipe_Server) obj;
		return idRecipe == other.idRecipe && Objects.equals(ingredientList, other.ingredientList)
				&& Objects.equals(name, other.name) && Objects.equals(person, other.person)
				&& recipeGender == other.recipeGender && Objects.equals(recipeStepList, other.recipeStepList);
	}
}
