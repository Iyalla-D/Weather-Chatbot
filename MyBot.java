import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class MyBot {
	HashMap<String, String> knowledge = new HashMap<String, String>();
    /**
     * This is a default constructor.
     */
    public MyBot() {
       knowledge.put("Hi", "Hello... Please to meet you!");
       knowledge.put("how are you?", "Great! And you?");
       knowledge.put("Are you a robot?", "I am a friend  to help you plan your outfit for the weather.");
       
    }
    
    public void answer(String question) {
        Set<String> keys = knowledge.keySet();
        for (String key : keys){
            String lowerKey = key.toLowerCase();
            String lowerQuestion = question.toLowerCase();
            if (lowerKey.contains(lowerQuestion)) {
               System.out.println("Bot: " + knowledge.get(key));
               return;//break
            }
              
        }
        trainMe(question);
    }
    public HashMap<String,String> getKnowledge(){
    	return knowledge;
    }
    public void trainMe(String question) {
        System.out.println("Bot: Sorry, Im dumb! How should I reply");
        System.out.print("User suggestion: ");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        knowledge.put(question, userInput);
    }
}
