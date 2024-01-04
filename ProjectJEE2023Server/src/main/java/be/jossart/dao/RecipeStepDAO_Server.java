package be.jossart.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.jossart.javabeans.RecipeStep_Server;
import oracle.jdbc.OracleTypes;

public class RecipeStepDAO_Server extends DAO_Server<RecipeStep_Server>{

	public RecipeStepDAO_Server(Connection conn) {
		super(conn);
	}

	@Override
    public boolean create(RecipeStep_Server obj) {
        String query = "{ call Insert_RecipeStep(?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setString(1, obj.getInstruction());
            cs.setInt(2, obj.getRecipe().getIdRecipe());

            cs.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Error creating RecipeStep: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(RecipeStep_Server obj) {
        String query = "{ call Delete_RecipeStep(?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, obj.getIdRecipeStep());

            cs.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting RecipeStep: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(RecipeStep_Server obj) {
        String query = "{ call Update_RecipeStep(?, ?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, obj.getIdRecipeStep());
            cs.setString(2, obj.getInstruction());
            cs.setInt(3, obj.getRecipe().getIdRecipe());

            cs.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Error updating RecipeStep: " + e.getMessage());
        }

        return false;
    }

    @Override
    public RecipeStep_Server find(int id) {
        String query = "{ call findRecipeStepById(?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setInt(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();

            ResultSet resultSet = (ResultSet) cs.getObject(2);
            if (resultSet.next()) {
                return new RecipeStep_Server(
                        resultSet.getInt("IdRecipeStep"),
                        resultSet.getString("Instructions"),
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding RecipeStep: " + e.getMessage());
        }
        return null;
    }
    
    public RecipeStep_Server findId(RecipeStep_Server recipeStep) {
        String query = "{ call findRecipeStepId(?, ?, ?) }";
        try (CallableStatement cs = this.connect.prepareCall(query)) {
            cs.setString(1, recipeStep.getInstruction());
            cs.setInt(2, recipeStep.getRecipe().getIdRecipe());
            cs.registerOutParameter(3, OracleTypes.CURSOR);

            cs.execute();

            ResultSet resultSet = (ResultSet) cs.getObject(3);
            if (resultSet.next()) {
                return new RecipeStep_Server(
                        resultSet.getInt("IdRecipeStep"),
                        resultSet.getString("Instructions"),
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding RecipeStep by Id: " + e.getMessage());
        }
        return null;
    }
}
