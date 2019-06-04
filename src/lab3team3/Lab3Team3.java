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
import org.json.simple.JSONObject;

/*Looks good but I would also check the payload returned to make there is no error message and is well-formed. 

Can you try and make a small change to the payload parsing to perform the check. 

Also please include a last general exception check catch for any other exceptions that might fall through. Rework and respond by Wednesday.


} catch (Exception e) { // General Exception for all Excpetions that fall through } finally { // be sure to clean up; close both the input stream and the socket*/
public class Lab3Team3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, MalformedURLException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String key = "833921b016964f95905442e0fab0c229";
//        
        HttpURLConnection connection;
        URL url = new URL("https://www.ncdc.noaa.gov/cdo-web/api/v2/datacategories");
        connection = (HttpURLConnection) url.openConnection();

        try {

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("token", "ERhdavZTGDwvkVEyiLtadECjGJbRdRdR");
            connection.setInstanceFollowRedirects(false);

            HttpURLConnection.setFollowRedirects(true);
            connection.setDoOutput(true);

            InputStream ip = connection.getInputStream();
            BufferedReader br
                    = new BufferedReader(new InputStreamReader(ip));
            StringBuilder sb = new StringBuilder();
            String line;
            //This checks the API connection right away as per professor's tech tips example
            //Not positive this is what he is asking for
            
            if (connection.getResponseMessage() == null) {
                System.out.println("Bad API Connection");
            } else {
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
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
        } catch (MalformedURLException ex) {
            System.err.println("ERROR_MALFORMED_URL");
            ex.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ERROR_API_CONNECTION_OR_DATA_RETRIEVAL");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            
            //tech tips also showed the below as a method of closing both the socket and URL connection
            if (connection != null) {
                connection.disconnect();
                System.out.println("Disconnected");
            }
        }
    }
}
