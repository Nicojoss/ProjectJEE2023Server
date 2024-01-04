package be.jossart.javabeans;

import java.io.Serializable;

import java.util.Objects;

import be.jossart.dao.AbstractDAOFactory_Server;
import be.jossart.dao.DAO_Server;
import be.jossart.dao.IngredientDAO_Server;

public class Ingredient_Server implements Serializable{
	//ATTRIBUTES
	private static final long serialVersionUID = 634419802561898936L;
	private int idIngredient;
	private String name;
	private IngredientType type;
	private RecipeIngredient_Server recipeIngredient;
	private static final AbstractDAOFactory_Server adf = AbstractDAOFactory_Server.getFactory();
	private static final DAO_Server<Ingredient_Server> ingredientDAO = adf.getIngredientDAO();
	//CTOR
	public Ingredient_Server() {
	}
	public Ingredient_Server(int idIngredient, String name, IngredientType type,
			RecipeIngredient_Server recipeIngredient) {
		this.idIngredient = idIngredient;
		this.name = name;
		this.type = type;
		this.recipeIngredient = recipeIngredient;
	}
	public Ingredient_Server(String name, IngredientType type,
			RecipeIngredient_Server recipeIngredient) {
		this.name = name;
		this.type = type;
		this.recipeIngredient = recipeIngredient;
	}
	//METHODS
	public boolean create() {
		return ingredientDAO.create(this);
	}
	public boolean delete() {
		return ingredientDAO.delete(this);
	}
	public boolean update() {
		return ingredientDAO.update(this);
	}
	public static Ingredient_Server find(int id) {
		IngredientDAO_Server dao = (IngredientDAO_Server) adf.getIngredientDAO();
		return dao.find(id);
	}
	public static Ingredient_Server findId(Ingredient_Server ingredient) {
		IngredientDAO_Server dao = (IngredientDAO_Server) adf.getIngredientDAO();
		return dao.findId(ingredient);
	}
	//GETTERS SETTERS
	public int getIdIngredient() {
		return idIngredient;
	}
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IngredientType getType() {
		return type;
	}
	public void setType(IngredientType type) {
		this.type = type;
	}
	public RecipeIngredient_Server getRecipeIngredient() {
		return recipeIngredient;
	}
	public void setRecipeIngredient(RecipeIngredient_Server recipeIngredient) {
		this.recipeIngredient = recipeIngredient;
	}
	@Override
	public String toString() {
		return "Ingredient_Server [idIngredient=" + idIngredient + ", "
				+ "name=" + name + ", type=" + type + ", recipeIngredient="
				+ recipeIngredient + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idIngredient, name, recipeIngredient, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient_Server other = (Ingredient_Server) obj;
		return idIngredient == other.idIngredient && Objects.equals(name, other.name)
				&& Objects.equals(recipeIngredient, other.recipeIngredient) && type == other.type;
	}
}
