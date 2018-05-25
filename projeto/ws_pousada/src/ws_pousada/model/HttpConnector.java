package ws_pousada.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public class HttpConnector {
	
	@QueryParam("jsonObject")
	static String js = null;

	public static String postResolve(String urlS, String json) {

		URL url = null;

		try {

			url = new URL(urlS);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-type", "application/json");
			connection.setDoOutput(true);

			connection.connect(); // ENVIA AO SERVIDOR

			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes());
			os.flush();

			if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Erro ao acessar o webservice : " + connection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			connection.disconnect();

			System.out.println("AQUI");

			String jsonDeResposta = "ESTE";
			return jsonDeResposta;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}

		return "OUTRO";
	}

	/**
	 * @author Matheus
	 * @param urlS
	 * @param json
	 * @return
	 */

	public static String connect(String urlS, String json) {

		URL url = null;

		try {
			
			js = json;
			
			url = new URL(urlS);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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

			return jsonDeResposta;

		} catch (Exception e) {

		}

		return null;
	}

}
