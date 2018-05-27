package ws_pousada.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.HttpConnector;
import ws_pousada.model.dao.CategoriaDAO;
import ws_pousada.model.entity.Categoria;
import ws_pousada.model.entity.Endereco;
import ws_pousada.model.entity.Produto;

public class ConsoleTest {

	private Gson gson = new GsonBuilder().create();
	private Endereco endereco = new Endereco();

	@Test
	public void toConsole() {
		System.out.println("TESTE");

		FactoryDAO.sessionInstance();

		// this.postEnderecoTest();
		// this.postSaveCategoria();
		 this.postUpdateCategoria();
		// this.listAllCategorias();

//		this.postSaveProduto();

		FactoryDAO.closeInstance();

		assertEquals(0, 0);
	}

	public void postEnderecoTest() {

		this.endereco = new Endereco();
		this.endereco.setBairro("Santa Mônica");
		this.endereco.setCep("1221928");
		this.endereco.setCidade("Florianópolis");
		this.endereco.setComplemento("Casa");
		this.endereco.setNumero(9090);
		this.endereco.setPais("Brasil");
		this.endereco.setRua("Testes Street");
		this.endereco.setUf("SC");

		String es = this.gson.toJson(this.endereco);
		System.out.println(es);
		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/intro/saveAddress", es);

		System.out.println(entityResponse);

	}

	// private static URI getBaseURI() {
	// return
	// UriBuilder.fromUri("http://localhost:8080/ws_pousada/intro/saveAddress").build();
	// }

	public void postSaveCategoria() {

		Categoria c = new Categoria();
		c.setNome("Pratos");

		String es = this.gson.toJson(c);
		System.out.println(es);
		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/categoria/save", es);

		System.out.println(entityResponse);
	}

	public void postUpdateCategoria() {

		Categoria c = new Categoria();
		CategoriaDAO categoriaDAO = new CategoriaDAO();

		// c = categoriaDAO.findById(6L);
		// c.setNome("Pratos Quentes");
		c = categoriaDAO.findById(5L);
		c.setNome("Porções e Lanches");

		String es = this.gson.toJson(c);
		System.out.println(es);
		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/categoria/update", es);

		System.out.println(entityResponse);
	}

	public void listAllCategorias() {

		// Categoria c = new Categoria();
		// CategoriaDAO categoriaDAO = new CategoriaDAO();
		// String es = this.gson.toJson(c);
		// System.out.println(es);

		// String entityResponse =
		// HttpConnector.getConnect("http://localhost:8080/ws_pousada/categoria/listAll");
		// System.out.println(entityResponse);
		HttpConnector.getConnect("http://localhost:8080/ws_pousada/categoria/listAll");
	}

	public void postSaveProduto() {

		Categoria c = new Categoria();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		c = categoriaDAO.findById(5L);

		Produto p = new Produto();

		p.setCategoria(c);
		p.setNome("Cheese Salada");

		String es = this.gson.toJson(p);
		System.out.println(es);
		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/produto/save", es);

		System.out.println(entityResponse);
	}

}
