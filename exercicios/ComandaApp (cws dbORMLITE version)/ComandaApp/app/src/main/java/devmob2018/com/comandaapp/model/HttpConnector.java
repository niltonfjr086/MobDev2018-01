package devmob2018.com.comandaapp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpConnector {

    /***
     * @author https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
     * @param urlS
     * @return
     */
    public static String getConnect(String urlS) {

        try {

            URL url = new URL(urlS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                return output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * @param urlS
     * @return
     * @author modified by us
     */
    public static String getConnect(String urlS, String id) {

        try {

            urlS += "?id=" + id;
            URL url = new URL(urlS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                return output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * @param urlS
     * @param json
     * @return
     * @author Matheus
     */
    public static String savePostConnect(String urlS, String json) {

        URL url = null;
        HttpURLConnection connection = null;
        try {

            url = new URL(urlS);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");

            connection.setDoOutput(true);

            PrintStream printStream = new PrintStream(connection.getOutputStream());

            printStream.println(json);

            connection.connect(); // ENVIA AO SERVIDOR

            Scanner scan = new Scanner(connection.getInputStream());
            String jsonDeResposta = "";
            while (scan.hasNext()) {
                jsonDeResposta += scan.next() + " ";
            }
            scan.close();

            connection.disconnect();

            return jsonDeResposta;

        } catch (Exception e) {
            if (connection != null)
                connection.disconnect();
        }

        return null;
    }

}
