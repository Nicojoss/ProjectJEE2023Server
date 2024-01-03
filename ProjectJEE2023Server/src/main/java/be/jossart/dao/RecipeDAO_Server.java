package be.jossart.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import be.jossart.connection.DbConnection;
import be.jossart.javabeans.IngredientType;
import be.jossart.javabeans.Ingredient_Server;
import be.jossart.javabeans.Person_Server;
import be.jossart.javabeans.RecipeGender;
import be.jossart.javabeans.RecipeStep_Server;
import be.jossart.javabeans.Recipe_Server;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;

import java.sql.*;


public class RecipeDAO_Server extends DAO_Server<Recipe_Server>{

	public RecipeDAO_Server(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Recipe_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Recipe_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Recipe_Server obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Recipe_Server find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Recipe_Server> findRecipeByName(String recherche){
		List<Recipe_Server> retour = new ArrayList<Recipe_Server>();
		
		//Connection connection = connect;

        ResultSet resultSet = null;


            String callFunction = "{ ? = call findRecipeBYName(?) }";
            try(CallableStatement callableStatement = this.connect.prepareCall(callFunction)){
	            callableStatement.setString(2, recherche);
	            ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("TABRECETTEOBJ", connect);
	            callableStatement.registerOutParameter(1, OracleTypes.ARRAY, "TABRECETTEOBJ");
	            callableStatement.execute();
	            ARRAY arrayResult = (ARRAY) ((CallableStatement) callableStatement).getArray(1);
	            Object[] data = (Object[]) arrayResult.getArray();

	            // Parcourir le tableau pour obtenir les valeurs
	            for (Object obj : data) {
	                STRUCT struct = (STRUCT) obj;
	                Object[] attributes = struct.getAttributes();
	                
	                int idRecette = ((Number) attributes[0]).intValue();
	                String nom = (String) attributes[1];
	                String SGender = (String) attributes[2];

	                System.out.println("ID Recette: " + idRecette + ", Titre: " + nom + ", Famille: " + SGender);
	                RecipeGender gender = null;
	                
	                switch(SGender) {
	                case "Entree":
	                	gender = RecipeGender.Entree;
	                	break;
	                case "Dish":
	                	gender = RecipeGender.Dish;
	                	break;
	                case "Desserts":
	                	gender = RecipeGender.Desserts;
	                	break;
	                case "Cocktails":
	                	gender = RecipeGender.Cocktails;
	                	break;
	                case "VegetarianDishes":
	                	gender = RecipeGender.VegetarianDishes;
	                	break;
	                default:
	                	gender = RecipeGender.Dish;
	                	break;
	                }
	                retour.add(new Recipe_Server(idRecette,nom,null,gender,null,null));
	            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                //if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		
		return retour;
	}

}
