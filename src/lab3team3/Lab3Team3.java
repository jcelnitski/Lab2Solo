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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.*;

/* 
Project: L03: Group Work - Connections
Purpose Details: Fetch data from the NOAA
Course: IST 411
Author: Team 3
Date Developed: 2 June 2019
Last Date Changed: 5 June 2019
Revision: Added Oakes' recommendations
*/

public class Lab3Team3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, MalformedURLException {
        String key = "ERhdavZTGDwvkVEyiLtadECjGJbRdRdR";
//        
        HttpURLConnection connection;
        URL url = new URL("https://www.ncdc.noaa.gov/cdo-web/api/v2/datacategories");
        connection = (HttpURLConnection) url.openConnection();

        try {

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("token", key);
            connection.setInstanceFollowRedirects(false);

            HttpURLConnection.setFollowRedirects(true);
            connection.setDoOutput(true);

            InputStream ip = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(ip));
            StringBuilder sb = new StringBuilder();
            String line;
            
            while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
            }
            String xx = sb.toString();
            br.close();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(xx);
            JsonObject obj = je.getAsJsonObject();
            // Check Json object per instructor's recommendation
            if (obj.get("metadata") != null && obj.get("status") == null) {
                String prettyJsonString = gson.toJson(je);
                System.out.println(prettyJsonString);
            } else {
                System.out.println("No results found!");
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
            }
        }
    }
}
