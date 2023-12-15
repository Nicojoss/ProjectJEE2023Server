package be.jossart.javabeans;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
	//Attributes
	private static final long serialVersionUID = -3448923763468846826L;
	private int idPerson;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private ArrayList<Recipe> recipeList; // 0..* Donc c'est au moment ou la person va creer une recette qu'il faut l'initialiser
	//CTOR
	public Person(int idPerson, String firstname, String lastname, String username, String password) {
		super();
		this.idPerson = idPerson;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}
	//METHODS
	//GETTERS SETTERS
	public int getIdPerson() {
		return idPerson;
	}
	
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Recipe> getRecipeList() {
		return recipeList;
	}
	public void setRecipeList(ArrayList<Recipe> recipeList) {
		this.recipeList = recipeList;
	}

}
