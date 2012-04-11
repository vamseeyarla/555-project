package edu.upenn.cis.cis555.Indexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class ex {

	public static void main(String [] args)
	{
		ex obj=new ex();
		 try {
			URLConnection conn=new URL("http://wordpress.org/extend/plugins/about/readme.txt").openConnection();
		BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		StringBuffer totalFileBuf=new StringBuffer();
		String temp;
		while((temp=br.readLine())!=null)
		{
			totalFileBuf.append(temp+" ");
		}
		
		HashMap<String,URLDataObject> words=new HashMap<String,URLDataObject>();
		
		String fileContents=totalFileBuf.toString();
		
		String [] allWordsArray=fileContents.split(" ");
		ArrayList<String> allWords=new ArrayList<String>();
		for(int cou=0;cou<allWordsArray.length;cou++)
		{
			allWords.add(allWordsArray[cou].trim());
		}
		
		
		for(int i=0;i<allWords.size();i++)
		{
			
			ArrayList<String> furtherStringSplit=obj.removeBadCharacters(allWords.remove(i));
			
				
			for(int j=0;j<furtherStringSplit.size();j++)
			{
				allWords.add(i,furtherStringSplit.get(j));
			}
			i=i+furtherStringSplit.size()-2;
				
		}	
		
		for(int count=0;count<allWords.size();count++)
		{
				String tempString=allWords.get(count).trim();
			if(!words.containsKey(tempString))
				{
				//Create new
					URLDataObject data=new URLDataObject();
					data.keyword=tempString;
					
				}
			else
			{
				//Update it
			}
			
			}
		
		 
		 
		 
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	
	
	}
	
	public ArrayList<String> removeBadCharacters(String input)
	{
		input=input.toLowerCase();
		ArrayList<String> temp=new ArrayList<String>();
		
		int currentPos=0;
		while(input.length() > 0)
		{
			for(int i=0;i<input.length();i++)
			{
				if((input.charAt(i)>47 && input.charAt(i)<58) || (input.charAt(i)>64 && input.charAt(i)<91) || (input.charAt(i)>96 && input.charAt(i)<123))
				{
					continue;
				}
				else
				{
					if(input.substring(0,i).length()>1)
					{
					temp.add(input.substring(0,i));
					input=input.substring(0,i);
					}
					else
					{
						input=input.substring(1);
					}
				}
			}
			
		}
		return temp;
	}

}
