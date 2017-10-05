

 import javax.enterprise.context.SessionScoped;
 import javax.inject.Named;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named()
@SessionScoped

public class MySearchBean implements Serializable {
	
	
	public String getWhichContents() {
		return contentArea;
	}

	public List<String> getMyList() {
		return arr;
	}
	
	private List<String> arr = Arrays.asList("aa", "bb", "cc","aa");
	private String contentArea="../sections/mysection/content_A.xhtml";
	public void action(){
		long count = arr.stream().filter(e -> e.equals(searchWord)).count();
		System.out.println("Count = " + count);
		if ( count < 1 ){
			contentArea = "../sections/mysection/content_A.xhtml";
		} else if ( count == 1 ){
			contentArea = "../sections/mysection/content_B.xhtml";			
		} else {
			contentArea = "../sections/mysection/content_C.xhtml";
		}
		System.out.println(contentArea);
	} 
 
	private String searchWord = "";
 
	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String newValue) {
		searchWord = newValue;
	}


}
