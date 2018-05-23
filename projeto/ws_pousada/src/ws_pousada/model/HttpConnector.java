package ws_pousada.model;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * 
 * @author Matheus
 *
 */

public class HttpConnector {
	
	public static String postResolve(String urlS, String json) {
		
		URL url = null;
		
		try {
			
			url = new URL(urlS);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-type", "application/json");
			connection.addRequestProperty("jsonObject", json);
			connection.setDoOutput(true);
			
//			PrintStream printStream = new PrintStream(connection.getOutputStream());
			
//			printStream.println(json);
			
			connection.connect(); // ENVIA AO SERVIDOR
			
			System.out.println("AQUI");

//			Scanner scan = new Scanner(connection.getInputStream());
						
			String jsonDeResposta = "ESTE";
			
//			while(scan.hasNext()) {
//				
//				jsonDeResposta += scan.next() + " ";
//			}
			return jsonDeResposta;
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}
		
		return "OUTRO";
	}

}
