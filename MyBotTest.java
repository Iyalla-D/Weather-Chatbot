package chatbot;

import static org.junit.Assert.*;

import org.junit.Test;

//import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.junit.jupiter.api.Test;

public class MyBotTest {

	@Test
	public void testMyBot01() {
		MyBot Bot = new MyBot();
		
		String input="Hi";
		String actual="";
		Set<String> keys = Bot.getKnowledge().keySet();
		 for (String key : keys){
			 String lowerKey = key.toLowerCase();
	         String lowerQuestion = input.toLowerCase();
	         if (lowerKey.contains(lowerQuestion)) {
	               actual = Bot.getKnowledge().get(key);
	            }
		 }
		String expected = "Hello... Please to meet you!";
		 
		assertEquals(expected,actual);
	}
	
	
	@Test
	public void testMyBot02() {
		MyBot Bot = new MyBot();
		String input="how are you?";
		String actual="";
		Set<String> keys = Bot.getKnowledge().keySet();
		 for (String key : keys){
			 String lowerKey = key.toLowerCase();
	         String lowerQuestion = input.toLowerCase();
	         if (lowerKey.contains(lowerQuestion)) {
	               actual = Bot.getKnowledge().get(key);
	            }
		 }
		String expected = "Great! And you?";
		 
		assertEquals(expected,actual);
	}

	@Test
	public void testMyBot03() {
		MyBot Bot = new MyBot();
		String input="Are you a robot?";
		String actual="";
		Set<String> keys = Bot.getKnowledge().keySet();
		 for (String key : keys){
			 String lowerKey = key.toLowerCase();
	         String lowerQuestion = input.toLowerCase();
	         if (lowerKey.contains(lowerQuestion)) {
	               actual = Bot.getKnowledge().get(key);
	            }
		 }
		String expected = "I am a friend  to help you plan your outfit for the weather.";
		 
		assertEquals(expected,actual);
	}
	
	@Test
	public void testMyBot04() {
		MyBot Bot = new MyBot();
		String input="I'm fine";
		String actual="";
		Set<String> keys = Bot.getKnowledge().keySet();
		 for (String key : keys){
			 String lowerKey = key.toLowerCase();
	         String lowerQuestion = input.toLowerCase();
	         if (lowerKey.contains(lowerQuestion)) {
	               actual = Bot.getKnowledge().get(key);
	            }
		 }
		String expected = "Thats good to hear.";
		 
		assertEquals(expected,actual);
	}
	@Test
	public void testMyBot05() {
		MyBot Bot = new MyBot();
		String input="good";
		String actual="";
		Set<String> keys = Bot.getKnowledge().keySet();
		 for (String key : keys){
			 String lowerKey = key.toLowerCase();
	         String lowerQuestion = input.toLowerCase();
	         if (lowerKey.contains(lowerQuestion)) {
	               actual = Bot.getKnowledge().get(key);
	            }
		 }
		String expected = "Thats good to hear.";
		 
		assertEquals(expected,actual);
	}
	@Test
	public void testMyBot06() {
		MyBot Bot = new MyBot();
		String input="What's your name";
		String actual="";
		Set<String> keys = Bot.getKnowledge().keySet();
		 for (String key : keys){
			 String lowerKey = key.toLowerCase();
	         String lowerQuestion = input.toLowerCase();
	         if (lowerKey.contains(lowerQuestion)) {
	               actual = Bot.getKnowledge().get(key);
	            }
		 }
		String expected = "My Name is BMO the weather bot.";
		 
		assertEquals(expected,actual);
	}
	@Test
	public void testMyBot07() {
		MyBot Bot = new MyBot();
		String input="Hello";
		String actual="";
		Set<String> keys = Bot.getKnowledge().keySet();
		 for (String key : keys){
			 String lowerKey = key.toLowerCase();
	         String lowerQuestion = input.toLowerCase();
	         if (lowerKey.contains(lowerQuestion)) {
	               actual = Bot.getKnowledge().get(key);
	            }
		 }
		String expected = "Hi there, What can i help you with? ";
		 
		assertEquals(expected,actual);
	}
	@Test
	public void testMyBot08() {
		MyBot Bot = new MyBot();
		String input="What do i do";
		String actual="";
		Set<String> keys = Bot.getKnowledge().keySet();
		 for (String key : keys){
			 String lowerKey = key.toLowerCase();
	         String lowerQuestion = input.toLowerCase();
	         if (lowerKey.contains(lowerQuestion)) {
	               actual = Bot.getKnowledge().get(key);
	            }
		 }
		String expected = "Im here to help you pick out an out based on the weather.";
		 
		assertEquals(expected,actual);
	}
	@Test
	public void testMyBot09() {
		MyBot Bot = new MyBot();
		String input="Do i just tell you the name of the place";
		String actual="";
		Set<String> keys = Bot.getKnowledge().keySet();
		 for (String key : keys){
			 String lowerKey = key.toLowerCase();
	         String lowerQuestion = input.toLowerCase();
	         if (lowerKey.contains(lowerQuestion)) {
	               actual = Bot.getKnowledge().get(key);
	            }
		 }
		String expected = "Yes please ";
		 
		assertEquals(expected,actual);
	}
	
	@Test
	public void weathertest01() {
		MyBot Bot = new MyBot();
		
		String input="london";
		String actual="";
		URL url;
		
		try {
			url = new URL("http://api.openweathermap.org/data/2.5/weather?q="
			           + input+ "&APPID=e3c1d0fed92dfe315302eee99207992a");
			SimpleHttpClient ht = new SimpleHttpClient(url);
			String json = ht.httpConnect("", "GET", "user", "password");
	        json = json.substring(0, json.length()-1);
	        
	        JSONParser parser = new JSONParser();
	        
	        Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray tempData = (JSONArray) jsonObject.get("weather");
            
            actual=(String) ((JSONObject) tempData.get(0)).get("main");
            
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        String expected="Clouds";
        
        assertEquals(expected,actual);
        }
	
	@Test
	public void weathertest02() {
		MyBot Bot = new MyBot();
		
		String input="dublin";
		String actual="";
		URL url;
		
		try {
			url = new URL("http://api.openweathermap.org/data/2.5/weather?q="
			           + input+ "&APPID=e3c1d0fed92dfe315302eee99207992a");
			SimpleHttpClient ht = new SimpleHttpClient(url);
			String json = ht.httpConnect("", "GET", "user", "password");
	        json = json.substring(0, json.length()-1);
	        
	        JSONParser parser = new JSONParser();
	        
	        Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray tempData = (JSONArray) jsonObject.get("weather");
            
            actual=(String) ((JSONObject) tempData.get(0)).get("main");
            
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        String expected="Clear";
        
        assertEquals(expected,actual);
        }
	
	@Test
	public void weathertest03() {
		MyBot Bot = new MyBot();
		
		String input="ukraine";
		String actual="";
		URL url;
		
		try {
			url = new URL("http://api.openweathermap.org/data/2.5/weather?q="
			           + input+ "&APPID=e3c1d0fed92dfe315302eee99207992a");
			SimpleHttpClient ht = new SimpleHttpClient(url);
			String json = ht.httpConnect("", "GET", "user", "password");
	        json = json.substring(0, json.length()-1);
	        
	        JSONParser parser = new JSONParser();
	        
	        Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray tempData = (JSONArray) jsonObject.get("weather");
            
            actual=(String) ((JSONObject) tempData.get(0)).get("main");
            
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        String expected="Clouds";
        
        assertEquals(expected,actual);
        }
}
