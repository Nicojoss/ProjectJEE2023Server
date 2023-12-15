package be.jossart.dao;

import java.sql.Connection;

import be.jossart.javabeans.Ingredient;

public class IngredientDAO extends DAO<Ingredient>{

	public IngredientDAO(Connection conn) {
		super(conn);

	}

	@Override
	public boolean create(Ingredient obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Ingredient obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Ingredient obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ingredient find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
