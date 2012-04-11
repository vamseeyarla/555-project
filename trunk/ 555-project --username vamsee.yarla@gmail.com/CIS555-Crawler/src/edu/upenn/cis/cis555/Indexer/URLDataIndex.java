package edu.upenn.cis.cis555.Indexer;
import java.util.ArrayList;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
@Entity
public class URLDataIndex {
		@PrimaryKey
		String urlid;
		URLDataObject data = new URLDataObject();
		
		public void setKeyword(String keyword){
			this.urlid=keyword;
		}
		
		public String getKeyword(){
			return urlid;
		}
		
		public void setData(URLDataObject data){
			this.data=data;
		}
		
		public URLDataObject getURLDataObject(){
			return data;
		}
		
		
				
}
