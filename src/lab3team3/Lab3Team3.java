/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3team3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import java.lang.Object.*;
import org.json.simple.JSONArray;
import org.json.simple.*;
import com.google.gson.*;
//import org.json.simple.JSONObject;




/**
 *
 * @author Anonymous
 */
public class Lab3Team3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, MalformedURLException  {
        
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in)); 
        String key = "833921b016964f95905442e0fab0c229"; 
//        JSONObject ezm; 
//        System.out.println("test");
//        while (n-- > 0)  
//        { 
//            String image = bfr.readLine(); 
//            ezm = new JSONObject(); 
//            ezm.put("url", image); 
            HttpURLConnection connection;
            URL url =  
                new URL("https://www.ncdc.noaa.gov/cdo-web/api/v2/datacategories"); 
                 connection = 
                        (HttpURLConnection) url.openConnection(); 
            try 
            { 
                
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json") ;
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("token", "ERhdavZTGDwvkVEyiLtadECjGJbRdRdR");
                connection.setInstanceFollowRedirects(false);
                 
                HttpURLConnection.setFollowRedirects(true); 
                connection.setDoOutput(true); 
  
                InputStream ip = connection.getInputStream(); 
                BufferedReader br =  
                        new BufferedReader(new InputStreamReader(ip)); 
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                String xx = sb.toString();
                br.close();
                JsonParser jsonParser = new JsonParser();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(xx);
                String prettyJsonString = gson.toJson(je);
                System.out.println(prettyJsonString);    
            }
                catch (MalformedURLException ex) {
                    System.err.println("ERROR_MALFORMED_URL");
                    ex.printStackTrace();
            } 
                catch (IOException e)  
            { 
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("ERROR_API_CONNECTION_OR_DATA_RETRIEVAL");
            } 
            finally {
            connection.disconnect();
            }           
    }     
}

/*
//Check out the following classes for how to make the Web connection
HttpURLConnection
URL
//JSON Payload using a Banking example
        BankingRecord bankRecord = new BankingRecord();
        Gson gson = new Gson();
        String jsonBank = gson.toJson(bankRecord);
 
//Example of a JSON Payload
{"accountNumber":"1234","accountType":"Savings","transactionType":"Deposit","transactionAmount":"100","balance":"200"}
//Example of response handling
if (response == null) {
System.err.println(BAD_API_RESPONSE);
} else {
System.out.println(String.format(RESPONSE_BODY, JsonUtils.prettyPrintResponse(response)));
}
//Code example of exception handling
} catch (MalformedURLException ex) {
  System.err.println(ERROR_MALFORMED_URL);
  ex.printStackTrace();
} catch (IOException ex) {
  System.err.println(ERROR_API_CONNECTION_OR_DATA_RETRIEVAL);
  ex.printStackTrace();
} catch (IOException ex) {
    ex.printStackTrace();
} finally {
// be sure to clean up; will close both the input stream and the socket
disconnect the http object 
http.disconnect();   
if (apiConnection != null) {
  apiConnection.disconnect();
}

*/