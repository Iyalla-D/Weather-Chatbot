package chatbot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import java.awt.*;
import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MyBot extends JFrame{
	HashMap<String, String> knowledge = new HashMap<String, String>();
	 JTextArea chatArea = new JTextArea();
	
		public static void main(String[] args) {
			new MyBot().setVisible(true);
	    }
    /**
     * This is a default constructor.
     */
    public MyBot() {
       knowledge.put("Hi", "Hello... Please to meet you!");
       knowledge.put("how are you?", "Great! And you?");
       knowledge.put("I'm fine", "Thats good to hear.");
       knowledge.put("good", "Thats good to hear.");
       knowledge.put("Are you a robot?", "I am a friend  to help you plan your outfit for the weather.");
       knowledge.put("What's your name","My Name is BMO the weather bot.");
       knowledge.put("Hello","Hi there, What can i help you with? ");
       knowledge.put("What do i do","Im here to help you pick out an out based on the weather.");
       knowledge.put("Do i just tell you the name of the place","Yes please ");
   	   
       setLayout(new BorderLayout());
       setBounds(100, 100, 325, 300);
       setSize(600, 600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setResizable(false);
	    
	    chatArea.setForeground(Color.white);
	    chatArea.setEnabled(true);
	    chatArea.setFont(new Font("Comic Sans",Font.BOLD,15));
	    chatArea.setEditable(false);
	    chatArea.setLineWrap(true);
	    chatArea.setBorder(BorderFactory.createBevelBorder(1));
	    
	    final JTextField chatbox= new JTextField();
	    chatbox.setSize(540, 30);
	    chatbox.setLocation(2, 500);
	    JScrollPane scroll = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	    chatArea.setWrapStyleWord(true);
	    add(chatbox);
	    add(scroll);
	    chatArea.setBackground( new Color(108, 193, 227));
	    chatArea.append("Bot: Type 'hello' to start."+"\n");
	    chatbox.addActionListener(new ActionListener() {

	    	   //@Override
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
	           chatArea.append("Bot: Any where else i can help you with?"+"\n");
	           } 
	       	catch (Exception e) {
	       		chatArea.append("Not a location, Try again \n");
	           //e.printStackTrace(); 
	           
	       }

    }
    public boolean getdata(String json) throws Exception {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray tempData = (JSONArray) jsonObject.get("weather");
            chatArea.append("\n");
            chatArea.append("Weather: " + ((JSONObject) tempData.get(0)).get("main")+"\n");
           
            chatArea.append("Weather Description: " + ((JSONObject) tempData.get(0)).get("description")+"\n");

            JSONObject tempData1 = (JSONObject) jsonObject.get("main");

            chatArea.append("Temperature: " +  (Double.parseDouble(tempData1.get("temp").toString()) - 273.15)+" Deg C\n"); //in Deg C
            chatArea.append("Temperature Min: "+(Double.parseDouble(tempData1.get("temp_min").toString()) - 273.15)+" Deg C\n");//in Deg C
            chatArea.append("Temperature Max: "+ (Double.parseDouble(tempData1.get("temp_max").toString()) - 273.15)+" Deg C\n");//in Deg C
            chatArea.append("Pressure: " + tempData1.get("pressure")+"\n"); //hpa
            chatArea.append("Humidity: " + tempData1.get("humidity")+"\n"); //%
            if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<-10.00) {
            	chatArea.append("Advice:Very cold still but bearable if in lots of warm clothes"+"\n");
            	chatArea.append("\n");
            }
            else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<0) {
            	chatArea.append("Advice: These are freezing temperatures, so put on a thick winter jacket with some winter gloves. If possible wear multilayered clothes underneath"+"\n");
            	chatArea.append("\n");
            }
            else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<4) {
            	chatArea.append("Advice: Its going to be really cold so wear a winter coat, scarf, pullover and maybe some gloves."+"\n");
            	chatArea.append("\n");
            }
            else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<15) {
            	chatArea.append("Advice: The weather is going to be cool so bring a jacket."+"\n");
            	chatArea.append("\n");
            }
           else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<20) {
        	   chatArea.append("Advice: Its going to be room temperature, so maybe wear a long sleeve shirt today."+"\n");
        	   chatArea.append("\n");
           }
           else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<25) {
        	   chatArea.append("Advice:Typical Summer temperature, wear something light and maybe go to the beach or a pool to cool off. "+"\n");
        	   chatArea.append("\n");
           }
           else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<30) {
        	   chatArea.append("Advice: Its a bit hot today so maybe wear a short sleeve shirt/t shirt or a light dress."+"\n");
        	   chatArea.append("\n");
           }
           else if((Double.parseDouble(tempData1.get("temp").toString()) - 273.15)<37) {
        	   chatArea.append("Advice: This is similar to the average body temperature. Wear a tank top and some shorts if you must go outside." +"\n");
        	   chatArea.append("\n");
           }

        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

}
