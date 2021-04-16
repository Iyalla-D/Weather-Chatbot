import java.net.URL;
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
       knowledge.put("What's your name","My Name is BMO the weather bot.");
       knowledge.put("Hello","Hi there, What can i help you with? ");
       knowledge.put("What do i do","Im here to help you pick out an out based on the weather.");
       knowledge.put("Do i just tell you the name of the place","Yes please ");
    }
    
    public void answer(String question) {
        Set<String> keys = knowledge.keySet();
        for (String key : keys){
            String lowerKey = key.toLowerCase();
            String lowerQuestion = question.toLowerCase();
            if (lowerKey.contains(lowerQuestion)) {
               System.out.println("Bot: " + knowledge.get(key));
               System.out.println("Bot: " + "If you want the weather, enter a location");
               return;//break
            }
              
        }
        weather(question);
        //trainMe(question);
    }
    
    public void trainMe(String question) {
        System.out.println("Bot: Sorry, Im dumb! How should I reply");
        System.out.print("User suggestion: ");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        knowledge.put(question, userInput);
    }
    
    public HashMap<String,String> getKnowledge(){
    	return knowledge;
    }
    
    public void weather(String question) {
	       try {
	           URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="
	           + question+ "&APPID=e3c1d0fed92dfe315302eee99207992a");
	           SimpleHttpClient ht = new SimpleHttpClient(url);
	           //Connect to a HTTP url with "Connect Method", "User" and "Password" if required only.
	           String json = ht.httpConnect("", "GET", "user", "password");
	           json = json.substring(0, json.length()-1);
	           //System.out.println(json);
	           JsonTest parse= new JsonTest();
	           parse.getdata(json);
	           } 
	       	catch (Exception e) {
	       		System.out.println("Not a location, Try again");
	           //e.printStackTrace(); 
	           
	       }

    }
}
