package ws_pousada.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.ws.rs.core.Response;

public class HttpConnector {

	public static String postResolve(String urlS, String json) {

		URL url = null;

		try {

			url = new URL(urlS);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-type", "application/json");
			//			connection.addRequestProperty("jsonObject", json);
			connection.setRequestProperty("jsonObject", json);
			connection.setDoOutput(true);


			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes());
			os.flush();

			if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Erro ao acessar o webservice : " + connection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String retorno;
			while ((retorno = br.readLine()) != null) {
				System.out.println(retorno);
			}

			connection.disconnect();

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
