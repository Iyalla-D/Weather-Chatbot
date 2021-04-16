import java.net.URL;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class JsonTest {
	public boolean getdata(String json) throws Exception {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            //System.out.println(jsonObject);
            JSONArray tempData = (JSONArray) jsonObject.get("weather");

            System.out.println("Weather: " + ((JSONObject) tempData.get(0)).get("main"));
            if(((JSONObject) tempData.get(0)).get("main").equals("Clouds")) {
            	 System.out.println("Wear a sweater and maybe bring an umbrella");
            }
           
            System.out.println("Weather Description: " + ((JSONObject) tempData.get(0)).get("description"));

            JSONObject tempData1 = (JSONObject) jsonObject.get("main");

            System.out.printf("Temperature: %.2f",  (Double.parseDouble(tempData1.get("temp").toString()) - 273.15)); //in Deg C
            
            System.out.println(" Deg C");
            System.out.printf("Temperature Min: %.2f",(Double.parseDouble(tempData1.get("temp_min").toString()) - 273.15));//in Deg C
            System.out.println(" Deg C");
            System.out.printf("Temperature Max: %.2f", (Double.parseDouble(tempData1.get("temp_max").toString()) - 273.15));//in Deg C
            System.out.println(" Deg C");
            System.out.println("Pressure: " + tempData1.get("pressure")); //hpa
            System.out.println("Humidity: " + tempData1.get("humidity")); //%
            System.out.println();
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
            	 System.out.println("The weather cold so bring a jacket");
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
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
