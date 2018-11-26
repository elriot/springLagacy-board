package com.cos.board;

import org.junit.Test;

public class MakeYoutube {
	final String URL1 = "https://youtu.be/WZwr2a_lFWY";
	final String URL2 = "https://www.youtube.com/watch?v=WZwr2a_lFWY";
	String myParse = "https://www.youtube.com/embed/";
	
	@Test
	public void makeParsing(){
		youtubeTest(URL1);
	}
	
	public void youtubeTest(String url){
		String parsing = "";
		if(url.contains("=")){
			System.out.println("=있음");			
			int index = url.indexOf("=");
			parsing = url.substring(index+1);
		} else {
			System.out.println("=없음");
			int index = url.lastIndexOf("/");
			parsing = url.substring(index+1);
		}
				
		myParse = myParse + parsing;		
		System.out.println(myParse);
	}
}
