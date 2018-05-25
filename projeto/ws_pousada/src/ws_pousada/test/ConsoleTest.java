package ws_pousada.test;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.HttpConnector;
import ws_pousada.model.entity.Endereco;

public class ConsoleTest {

	private Gson gson = new GsonBuilder().create();
	private Endereco endereco = new Endereco();

	@Test
	public void toConsole() {
		System.out.println("TESTE");

		FactoryDAO.sessionInstance();

		this.postTest();

		FactoryDAO.closeInstance();

		assertEquals(0, 0);
	}

	public void postTest() {

		// Client c = null;
		// c.target(getBaseURI());
		// WebTarget target = c.target(getBaseURI());

		this.endereco = new Endereco();
		this.endereco.setBairro("Rio Vermelho");
		this.endereco.setCep("01010101");
		this.endereco.setCidade("Florianópolis");
		this.endereco.setComplemento("Casa");
		this.endereco.setNumero(9090);
		this.endereco.setPais("Brasil");
		this.endereco.setRua("Testes Street");
		this.endereco.setUf("SC");

		// target.queryParam("novo_endereco", this.endereco);

		// Invocation.Builder builder = target.request();
		// Response res = builder.post(Entity.json(gson.toJson(this.endereco)));
		// Client client = Client.create();
		// HttpPost postRequest = new HttpPost(url);

		String es = this.gson.toJson(this.endereco);
		System.out.println(es);
		String entityResponse = HttpConnector.postResolve("http://localhost:8080/ws_pousada/intro/saveAddress", es);

		System.out.println(entityResponse);
		// this.endereco = this.gson.fromJson(entityResponse, Endereco.class);

		// System.out.println(this.endereco);

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/ws_pousada/intro/saveAddress").build();
	}

	// public String post(String url, HashMap<String, String>params, String body)
	// throws Exception {
	// HttpPost postRequest = new HttpPost(url);
	//
	// for(String key : params.keySet()){
	// postRequest.addHeader(key, params.get(key));
	// }
	//
	// StringEntity input = new StringEntity(body);
	// input.setContentType("application/json");
	// postRequest.setEntity(input);
	//
	// HttpResponse response = (new DefaultHttpClient()).execute(postRequest);
	//
	// if (response.getStatusLine().getStatusCode() != 200) {
	// throw new RuntimeException("Failed : HTTP error code : "
	// + response.getStatusLine().getStatusCode());
	// }
	//
	// BufferedReader br = new BufferedReader(
	// new InputStreamReader((response.getEntity().getContent())));
	//
	// String output;
	// StringBuffer totalOutput = new StringBuffer();
	// while ((output = br.readLine()) != null) {
	// totalOutput.append(output);
	// }
	// return totalOutput.toString();
	// }

}
