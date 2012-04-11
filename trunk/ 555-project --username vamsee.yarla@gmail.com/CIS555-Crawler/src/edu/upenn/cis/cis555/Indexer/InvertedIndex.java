package edu.upenn.cis.cis555.Indexer;
import java.util.ArrayList;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
@Entity
public class InvertedIndex {
		@PrimaryKey
		String keyword;
		
		ArrayList<String> urlid = new ArrayList<String>();
		
		public void setKeyword(String keyword){
			this.keyword=keyword;
		}
		
		public void addurl(String url){
			urlid.add(url);
		}
		
		public void seturls(ArrayList<String> urls){
			this.urlid = urls;
		}
		public String getKeyword(){
			return keyword;
		}
		
		public ArrayList<String> getUrls(){
			return urlid;
		}
}
