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
        System.out.println("test");
//        while (n-- > 0)  
//        { 
//            String image = bfr.readLine(); 
//            ezm = new JSONObject(); 
//            ezm.put("url", image); 
            
            try 
            { 
                
                URL url =  
             new URL("https://www.ncdc.noaa.gov/cdo-web/api/v2/"); 
                HttpURLConnection connection = 
                        (HttpURLConnection) url.openConnection(); 
                
                // set the request method and properties. 
                connection.setRequestMethod("GET");
//                connection.setRequestProperty("token:"+ key+" "+ url) ;
                connection.setRequestProperty("Content-Type", "application/json") ;
//                connection.setRequestProperty("Authorization", "Bearer " + key);
                connection.setRequestProperty("Accept", "application/json");
                connection.setInstanceFollowRedirects(false);
                 
                
                HttpURLConnection.setFollowRedirects(true); 
  
                connection.setDoOutput(true); 
  
//                OutputStream out = connection.getOutputStream(); 
//                out.write(ezm.toString().getBytes()); 
                InputStream ip = connection.getInputStream(); 
                BufferedReader br =  
                        new BufferedReader(new InputStreamReader(ip)); 
                System.out.println("conn");
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                String xx = sb.toString();
                br.close();
                
                
                System.out.println("testt");
                System.out.println(xx);    
                //AuthMsg msg = new Gson().fromJson(xx, AuthMsg.class);
                //System.out.println(msg);
                
            } catch (Exception e)  
              
            { 
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("error");
            } 
            
//            catch (MalformedURLException e) {
//        // TODO Auto-generated catch block
//            e.printStackTrace();
//        } 
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