package be.jossart.dao;

import java.sql.Connection;

import be.jossart.javabeans.Recipe_Server;

public class RecipeDAO_Server extends DAO_Server<Recipe_Server>{

	public RecipeDAO_Server(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Recipe_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Recipe_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Recipe_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Recipe_Server find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
