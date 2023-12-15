package be.jossart.dao;

import java.sql.Connection;

import be.jossart.javabeans.RecipeStep;

public class RecipeStepDAO extends DAO<RecipeStep>{

	public RecipeStepDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(RecipeStep obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(RecipeStep obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RecipeStep obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RecipeStep find(int id) {
		// TODO Auto-generated method stub
		return null;
	}



}
