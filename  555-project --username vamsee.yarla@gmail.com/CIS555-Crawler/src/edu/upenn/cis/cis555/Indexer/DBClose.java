/**
 * 
 */
package edu.upenn.cis.cis555.Indexer;

import com.sleepycat.je.Environment;
import com.sleepycat.persist.EntityStore;

/**
 * @author VamseeKYarlagadda
 *
 */
/*
 * Class to commit all the database transactions and close all it repositories.
 * 
 */
public class DBClose extends Thread {

	private Environment env;
	private EntityStore inverted_index_store;
	private EntityStore url_data_index_store;

	
	public DBClose(Environment env, EntityStore inverted_index_store,EntityStore url_data_index_store)
	{
		this.env=env;
		this.inverted_index_store=inverted_index_store;
		this.url_data_index_store=url_data_index_store;
	}
	
	public void run()
	{
		try{
			if(env!=null)
			{
				
				//System.out.println("Stage1");
				inverted_index_store.close();
				//System.out.println("Stage2");
				url_data_index_store.close();
				//System.out.println("Stage3");
				env.cleanLog();
				//System.out.println("Stage5");
				env.close();
				//System.out.println("Database Closed");
			}
			}
		catch(Exception e)
		{
			//System.out.println("Database not closed properly");
		}
	}
}
