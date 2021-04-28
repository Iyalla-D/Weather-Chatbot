
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class MyBot extends JFrame{
	HashMap<String, String> knowledge = new HashMap<String, String>();
	JTextArea chatArea = new JTextArea();
	JTextField chatbox= new JTextField();
		public static void main(String[] args) {
			new MyBot();
	    }
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
       JFrame frame = new JFrame("Chatbot");
       frame.setSize(600, 600);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setLayout(null);
       frame.add(chatbox);
       chatArea.setSize(500, 400);
       chatArea.setLocation(2, 2);
       chatArea.setLineWrap(true);
       chatArea.setWrapStyleWord(true);
       chatArea.setBounds(50, 50, 500, 300);
       chatArea.setEditable(false);
       chatbox.setSize(540, 30);
       chatbox.setLocation(2, 500);
       frame.add(chatArea);
       frame.setVisible(true);
       chatbox.addActionListener(new ActionListener() {

    	   @Override
    	   public void actionPerformed(ActionEvent arg0) {
    		   String gtext = chatbox.getText();
    		   chatArea.append("User: " + gtext+"\n");
    		   chatbox.setText("");
    		   answer(gtext);
			}
			
		});
    }
    
    public void answer(String question) {
        Set<String> keys = knowledge.keySet();
        for (String key : keys){
            String lowerKey = key.toLowerCase();
            String lowerQuestion = question.toLowerCase();
            if (lowerKey.contains(lowerQuestion)) {
            	
               chatArea.append("Bot: " + knowledge.get(key)+"\n");
               chatArea.append("Bot: " + "If you want the weather, enter a location \n");
               return;//break
            }
              
        }
        weather(question);
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
	           getdata(json);
	           } 
	       	catch (Exception e) {
	       		chatArea.append("Not a location, Try again \n");
	           //e.printStackTrace(); 
	           
	       }

    }
    private boolean getdata(String json) throws Exception {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray tempData = (JSONArray) jsonObject.get("weather");

            chatArea.append("Weather: " + ((JSONObject) tempData.get(0)).get("main")+"\n");
            if(((JSONObject) tempData.get(0)).get("main").equals("Clouds")) {
            	 chatArea.append("Wear a sweater and maybe bring an umbrella"+"\n");
            }
           
            chatArea.append("Weather Description: " + ((JSONObject) tempData.get(0)).get("description")+"\n");

            JSONObject tempData1 = (JSONObject) jsonObject.get("main");

            chatArea.append("Temperature: " +  (Double.parseDouble(tempData1.get("temp").toString()) - 273.15)+" Deg C\n"); //in Deg C
            chatArea.append("Temperature Min: "+(Double.parseDouble(tempData1.get("temp_min").toString()) - 273.15)+" Deg C\n");//in Deg C
            chatArea.append("Temperature Max: "+ (Double.parseDouble(tempData1.get("temp_max").toString()) - 273.15)+" Deg C\n");//in Deg C
            chatArea.append("Pressure: " + tempData1.get("pressure")+"\n"); //hpa
            chatArea.append("Humidity: " + tempData1.get("humidity")+"\n"); //%
            if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<-10.00) {
         	   System.out.println();
            }
            else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<0.00) {
         	   System.out.println();
            }
            else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<4.00) {
         	   System.out.println();
            }
            else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<15.00) {
            	chatArea.append("The weather cold so bring a jacket");
            }
           else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<20.00) {
        	   System.out.println();
           }
           else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<25.00) {
        	   System.out.println();
           }
           else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<30.00) {
        	   System.out.println();
           }
           else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<37.00) {
        	   System.out.println();
           }

        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

}
