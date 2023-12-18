package be.jossart.dao;

import java.sql.Connection;

import be.jossart.javabeans.RecipeStep_Server;

public class RecipeStepDAO_Server extends DAO_Server<RecipeStep_Server>{

	public RecipeStepDAO_Server(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(RecipeStep_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(RecipeStep_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RecipeStep_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RecipeStep_Server find(int id) {
		// TODO Auto-generated method stub
		return null;
	}



}
