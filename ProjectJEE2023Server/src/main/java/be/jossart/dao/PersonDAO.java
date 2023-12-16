package be.jossart.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;

import be.jossart.javabeans.Person;

public class PersonDAO extends DAO<Person>{

	public PersonDAO() {
	}

	@Override
	public boolean create(Person obj) {
		try (CallableStatement callableStatement = connect.prepareCall("{call Insert_Person(?,?,?,?)}")) {
	        callableStatement.setString(1, obj.getFirstname());
	        callableStatement.setString(2, obj.getLastname());
	        callableStatement.setString(3, obj.getUsername());
	        callableStatement.setString(4, obj.getPassword());

	        callableStatement.execute();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean delete(Person obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Person obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
