package be.jossart.dao;

import java.sql.Connection;

import be.jossart.connection.DbConnection;

public abstract class DAO<T> {
	protected Connection connect = DbConnection.getInstance();
	
	public DAO(){
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);

}
