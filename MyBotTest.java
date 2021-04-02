import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class MyBotTest {

	@Test
	void testMyBot01() {
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
	void testMyBot02() {
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

}
