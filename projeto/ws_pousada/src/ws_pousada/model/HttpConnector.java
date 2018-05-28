package ws_pousada.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpConnector {

	/**
	 * @author Matheus
	 * @param urlS
	 * @param json
	 * @return
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
	
	
	/***
	 * @author https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
	 * @param urlS
	 * @return
	 */
	public static void getConnect(String urlS) {
		
		  try {

				URL url = new URL(urlS);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				conn.disconnect();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }
	}

}
