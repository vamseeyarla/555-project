package edu.upenn.cis.cis555.Indexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;


public class TokenizeContent {

	public static void main(String [] args)
	{
		
		
		
		
		TokenizeContent obj=new TokenizeContent();
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
		
		TokenizerModel model = new TokenizerModel(new FileInputStream("en-token.bin"));
		Tokenizer tokenizer = new TokenizerME(model);
		
		Span tags[] = tokenizer.tokenizePos(fileContents);
		String tokens[] = tokenizer.tokenize(fileContents);
		
		for(int i=0;i<tokens.length;i++)
		{
			//System.out.println(tokens[i]);
		}
		 
		ArrayList<String> allWords=new ArrayList<String>();
		ArrayList<String> finalAllWords=new ArrayList<String>();
		
		for(int cou=0;cou<tokens.length;cou++)
		{
			allWords.add(tokens[cou].trim());
		}
		//System.out.println(allWords);
		
		for(int i=0;i<allWords.size();i++)
		{
			
			ArrayList<String> furtherStringSplit=obj.removeBadCharacters(allWords.get(i));
		//	System.out.println(furtherStringSplit.size());
			
				if(furtherStringSplit.size()!=0)
				{
			for(int j=0;j<furtherStringSplit.size();j++)
			{
				
				if(furtherStringSplit.get(j).trim().length()!=0)
				{
					String tempx=furtherStringSplit.get(j).trim();
			if( ((tempx.charAt(0)>47 && tempx.charAt(0)<58) || (tempx.charAt(0)>64 && tempx.charAt(0)<91) || (tempx.charAt(0)>96 && tempx.charAt(0)<123)))
				{
					
					finalAllWords.add(furtherStringSplit.get(j));
					
				}
				else
				{
				
					if(furtherStringSplit.get(j).trim().length()!=1)
					{
						finalAllWords.add(furtherStringSplit.get(j).substring(1));
					}
					else
					{
						
					}
					
				}
				}
				
				
				
			}
		//	i=i+furtherStringSplit.size()-2;
				}
				else
				{
			
				}
		}	
		
		
		/*
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
			*/
		System.out.println(finalAllWords);
		 
		 
		 
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
		int startPos=0;
		boolean status=false;
		
		//System.out.println(input.length());
		for(int i=0;i<input.length();i++)
		{
			if((input.charAt(i)>47 && input.charAt(i)<58) || (input.charAt(i)>64 && input.charAt(i)<91) || (input.charAt(i)>96 && input.charAt(i)<123))
			{
				status=false;
				//System.out.println("NOT HERE");
				currentPos=i;
			}
			else
			{
				////System.out.println("HERE");
				temp.add(input.substring(startPos,currentPos));
				//System.out.println("TEMP: "+input.substring(startPos,currentPos));
				startPos=i;
				currentPos=i;
				status=true;
			
			}
		}
		if(!status)
		{
		temp.add(input.substring(startPos,currentPos+1));
		}
	
		//System.out.println(temp);
		
		return temp;
	}

}
