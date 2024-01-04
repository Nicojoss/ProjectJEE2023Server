package be.jossart.dao;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.jossart.javabeans.IngredientType;
import be.jossart.javabeans.Ingredient_Server;
import oracle.jdbc.OracleTypes;

public class IngredientDAO_Server extends DAO_Server<Ingredient_Server>{

	public IngredientDAO_Server(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Ingredient_Server obj) {
	    String query = "{ call Insert_Ingredient(?, ?) }";
	    try (CallableStatement cs = this.connect.prepareCall(query)) {
	        cs.setString(1, obj.getName());
	        cs.setString(2, obj.getType().name());

	        cs.execute();
            
	        return true;
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }

	    return false;
	}


	@Override
    public boolean delete(Ingredient_Server obj) {
        String query = "{ call Delete_Ingredient(?) }";

        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, obj.getIdIngredient());

            cs.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting ingredient: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Ingredient_Server obj) {
        String query = "{ call Update_Ingredient(?,?,?) }";

        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, obj.getIdIngredient());
            cs.setString(2, obj.getName());
            cs.setString(3, obj.getType().name());

            cs.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating ingredient: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Ingredient_Server find(int id) {
        String query = "{ call findIngredientById(?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();

            ResultSet resultSet = (ResultSet) cs.getObject(2);
            if (resultSet.next()) {
                return new Ingredient_Server(
                        resultSet.getInt("IdIngredient"),
                        resultSet.getString("Name"),
                        IngredientType.valueOf(resultSet.getString("TypeIngredient")),
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding ingredient: " + e.getMessage());
        }
        return null;
    }
    
    public Ingredient_Server findId(Ingredient_Server ingredient) {
        String query = "{ call findIngredientId(?, ?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setString(1, ingredient.getName());
            cs.setString(2, ingredient.getType().name());
            cs.registerOutParameter(3, OracleTypes.CURSOR);

            cs.execute();

            ResultSet resultSet = (ResultSet) cs.getObject(3);
            if (resultSet.next()) {
                return new Ingredient_Server(
                        resultSet.getInt("IdIngredient"),
                        resultSet.getString("Name"),
                        IngredientType.valueOf(resultSet.getString("TypeIngredient")),
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding ingredient: " + e.getMessage());
        }
        return null;
    }
}
