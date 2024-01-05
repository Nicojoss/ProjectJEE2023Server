package be.jossart.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.jossart.javabeans.RecipeIngredient_Server;
import oracle.jdbc.OracleTypes;

public class RecipeIngredientDAO_Server extends DAO_Server<RecipeIngredient_Server>{

	public RecipeIngredientDAO_Server(Connection conn) {
		super(conn);
	}

	@Override
    public boolean create(RecipeIngredient_Server obj) {
        String query = "{ call Insert_RecipeIngredient(?, ?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, obj.getIngredient().getIdIngredient());
            cs.setInt(2, obj.getRecipe().getIdRecipe());
            cs.setDouble(3, obj.getQuantity());

            cs.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Error creating RecipeIngredient: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(RecipeIngredient_Server obj) {
        String query = "{ call Delete_RecipeIngredient(?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, obj.getRecipe().getIdRecipe());
            cs.setInt(2, obj.getIngredient().getIdIngredient());

            cs.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting RecipeIngredient: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(RecipeIngredient_Server obj) {
        String query = "{ call Update_RecipeIngredient(?, ?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, obj.getRecipe().getIdRecipe());
            cs.setInt(2, obj.getIngredient().getIdIngredient());
            cs.setDouble(3, obj.getQuantity());

            cs.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Error updating RecipeIngredient: " + e.getMessage());
        }

        return false;
    }
    
    @Override
	public RecipeIngredient_Server find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
    
    public RecipeIngredient_Server find(int idRecipe, int idIngredient) {
        String query = "{ call findRecipeIngredientById(?, ?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, idIngredient);
            cs.setInt(2, idRecipe); 
            cs.registerOutParameter(3, OracleTypes.CURSOR);

            cs.execute();

            ResultSet resultSet = cs.getObject(3, ResultSet.class);
            if (resultSet.next()) {
                return new RecipeIngredient_Server(
                        resultSet.getInt("IdIngredient"),
                        resultSet.getInt("IdRecipe"),
                        resultSet.getDouble("Quantity"),
                        null, // Replace with the appropriate method to fetch Ingredient_Server
                        null  // Replace with the appropriate method to fetch Recipe_Server
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding RecipeIngredient: " + e.getMessage());
        }
        return null;
    }
   

    public RecipeIngredient_Server findId(RecipeIngredient_Server recipeIngredient) {
        String query = "{ call findRecipeIngredientId(?, ?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setDouble(1, recipeIngredient.getQuantity());
            cs.setInt(2, recipeIngredient.getRecipe().getIdRecipe());
            cs.registerOutParameter(3, OracleTypes.CURSOR);

            cs.execute();

            ResultSet resultSet = cs.getObject(3, ResultSet.class);
            if (resultSet.next()) {
                return new RecipeIngredient_Server(
                        resultSet.getInt("IdIngredient"),
                        resultSet.getInt("IdRecipe"),
                        resultSet.getDouble("Quantity"),
                        null, // Replace with the appropriate method to fetch Ingredient_Server
                        null  // Replace with the appropriate method to fetch Recipe_Server
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding RecipeIngredient by Id: " + e.getMessage());
        }
        return null;
    }
}
