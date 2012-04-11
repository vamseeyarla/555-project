package edu.upenn.cis.cis555.Indexer;
import java.util.ArrayList;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
@Entity
public class URLDataIndex {
		@PrimaryKey
		String url;
		URLDataObject data = new URLDataObject();
		
		public void setKeyword(String keyword){
			this.url=keyword;
		}
		
	//	public String getKeyword()
		
		
				
}
