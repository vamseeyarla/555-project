package edu.upenn.cis.cis555.Indexer;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.StoreConfig;

public class DB {

	public String Directory;
	EntityStore storeInvertedIndex;
	EntityStore storeURLDataIndex;
	
	PrimaryIndex <String, InvertedIndex> InvertedIndex;
	PrimaryIndex <String, URLDataIndex> URLDataIndex;
	
	Environment env;
	EnvironmentConfig envConfig;
	StoreConfig storeConfig;
    static DB db=null;
	
    /*
     * Contrcutor to take DB directory as input
     */
	public DB(String Directory)
	{
		this.Directory=Directory;			
	}
	
	 /*
     * Function which takes Directory as input and returns the object of 
     * this class. this ensures only one instance of the 
     * object has been created.
     */
	public static DB getInstance(String Directory)
	{
		if(db==null)
		{
		db=new DB(Directory);
		if(!db.init())
		{
			//System.out.println("FAIL INIT");
			db=null;
		}
		}
		
		return db;	
	}
	
	/*
	 * Initializes the fields required for opening the DB and makes sure
	 * everything is working perfectly or not. If not it returns false
	 */
	public boolean init()
	{
		File dir = new File(Directory);
		
		if(!dir.exists())
		{
		boolean success=dir.mkdirs();
		
		if(success)
		{
			//System.out.println("Created the DB Directory");
				}
		else
		{
			//System.out.println("Cannot Create the DB Directory");
			
		}
		}
		try{
		
		envConfig=new EnvironmentConfig();
		storeConfig=new StoreConfig();
		
		envConfig.setReadOnly(false);
		storeConfig.setReadOnly(false);
		envConfig.setLocking(false);
		//storeConfig.setLocking(false);
		
		envConfig.setAllowCreate(true);
		storeConfig.setAllowCreate(true);
		//storeConfig.setExclusiveCreate(true);
		
		env = new Environment(dir, envConfig);
		
		storeInvertedIndex=new EntityStore(env, "InvertedIndex", storeConfig);
		storeURLDataIndex=new EntityStore(env, "URL Data", storeConfig);
		
		
		InvertedIndex=storeInvertedIndex.getPrimaryIndex(String.class, InvertedIndex.class);
		
		URLDataIndex=storeURLDataIndex.getPrimaryIndex(String.class, URLDataIndex.class);
		
		
		DBClose closingHook=new DBClose(env, storeInvertedIndex,storeURLDataIndex);
		Runtime.getRuntime().addShutdownHook(closingHook);
		env.sync();
		return true;
		}
		catch(Exception e)
		{
			
			//System.out.println(e.toString());
			//System.out.println("Error in Connecting to Berkeley DB");
			return false;
		}
		
		/*
		
		String[] temp={"hi","Kri"};
		UserData x=new UserData("vamsee","krish",temp);
		
		UserData y=new UserData("manoj","krishna yarlagadda",temp);
		UserIndex.put(y);
		ChannelIndex.put(x);
	
		UserIndex.delete("manoj");
		ChannelIndex.delete("vamsee");
		
		UserData result=UserIndex.get("manoj");
		
		System.out.println(result.Password);
		result=ChannelIndex.get("vamsee");
		System.out.println(result.Password);
	*/	
	}
	


	
	
	
	
	/*
	 * Function to close the DB by committing everything.
	 * 
	 */
	public void close()
	{
		env.sync();
		DBClose closingHook=new DBClose(env, storeInvertedIndex,storeURLDataIndex);
		closingHook.start();
	}
	
	/*
	 * Function to delete entire data from the database.
	 * 
	 */
	
	public boolean deleteData()
	{
		try{
			for(InvertedIndex d : InvertedIndex.entities())
			{
				InvertedIndex.delete(d.keyword);
			}
			for(URLDataIndex d : URLDataIndex.entities())
			{
				URLDataIndex.delete(d.urlid);
			}
			
		env.sync();
		System.out.println("DELETED ALL DATA FROM DB");
		return true;
		}
		catch(Exception e)
		{
			return false;	
		}
	}
	
	
}
