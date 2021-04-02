import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Test;

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
	}

