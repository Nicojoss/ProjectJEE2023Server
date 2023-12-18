package be.jossart.dao;

import java.sql.Connection;

import be.jossart.javabeans.Ingredient_Server;

public class IngredientDAO_Server extends DAO_Server<Ingredient_Server>{

	public IngredientDAO_Server(Connection conn) {
		super(conn);

	}

	@Override
	public boolean create(Ingredient_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Ingredient_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Ingredient_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ingredient_Server find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
