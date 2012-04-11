/**
 * 
 */
package edu.upenn.cis.cis555.Indexer;

/**
 * @author VamseeKYarlagadda
 *
 */
/*
 * Class to delete all the data from database transactions and close all it repositories.
 * 
 */
public class DeleteDBData {
	
	public static void main(String[] args)
	{
		
		DB db=DB.getInstance("JEDB");
		System.out.println(db.deleteData());
		
	}
}
