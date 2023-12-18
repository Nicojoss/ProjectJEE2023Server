package be.jossart.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.jossart.javabeans.Person_Server;
import oracle.jdbc.OracleTypes;

public class PersonDAO_Server extends DAO_Server<Person_Server>{

	public PersonDAO_Server(Connection conn) {
		super(conn);
	}

	@Override
	public  boolean create(Person_Server obj) {
		boolean success = false;

		String query = "{call Insert_Person(?,?,?,?)}";
		try (CallableStatement cs = this.connect.prepareCall(query)) {

			cs.setString(1, obj.getFirstname());
			cs.setString(2, obj.getLastname());
			cs.setString(3, obj.getUsername());
			cs.setString(4, obj.getPassword());
			
			cs.executeUpdate(); // return int type that's why i use 
								//success = true; and not success = cs.executeUpdate();
			success = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return success;
	}

	@Override
	public boolean delete(Person_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Person_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person_Server find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Person_Server login(String username, String password) {
	    Person_Server person = null;

	    String query = "{call Login(?,?,?)}";
	    try (CallableStatement cs = this.connect.prepareCall(query)) {
	        cs.setString(1, username);
	        cs.setString(2, password);
	        cs.registerOutParameter(3, OracleTypes.CURSOR);

	        cs.execute();

	        try (ResultSet resultSet = (ResultSet) cs.getObject(3)) {
	            if (resultSet.next()) {
	                person = new Person_Server();
	                person.setFirstname(resultSet.getString("Firstname"));
	                person.setLastname(resultSet.getString("Lastname"));
	                person.setUsername(resultSet.getString("Username"));
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }

	    return person;
	}
}
