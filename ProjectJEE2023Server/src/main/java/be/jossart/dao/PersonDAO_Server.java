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
        String query = "{ call findPersonById(?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();

            ResultSet resultSet = (ResultSet) cs.getObject(2);
            if (resultSet.next()) {
                return new Person_Server(
                        resultSet.getInt("IdPerson"),
                        resultSet.getString("Firstname"),
                        resultSet.getString("Lastname"),
                        resultSet.getString("Username"),
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding ingredient: " + e.getMessage());
        }
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
	                person.setIdPerson(resultSet.getInt("IdPerson"));
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

	public boolean updatePassword(int idPerson, String newPassword) {
		
		String query = "{call UpdatePassword(?,?)}";
	    
		try (CallableStatement cs = this.connect.prepareCall(query)) {
	        cs.setInt(1, idPerson);
	        cs.setString(2, newPassword);

	        int updatedRows = cs.executeUpdate(); // executeUpdate() return int value

	        return updatedRows > 0;
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        return false;
	    }
	}
	public Person_Server findId(Person_Server person) {
        String query = "{ call findPersonId(?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setString(1, person.getUsername());
            cs.registerOutParameter(3, OracleTypes.CURSOR);

            cs.execute();

            ResultSet resultSet = (ResultSet) cs.getObject(3);
            if (resultSet.next()) {
                return new Person_Server(
                        resultSet.getInt("IdPerson"),
                        person.getFirstname(),
                        person.getLastname(),
                        person.getUsername(),
                        person.getPassword()
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding ingredient: " + e.getMessage());
        }
        return null;
    }
}
