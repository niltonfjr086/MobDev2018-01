package ws_pousada.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.HttpConnector;
import ws_pousada.model.dao.CategoriaDAO;
import ws_pousada.model.dao.ProdutoDAO;
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
//		 this.postUpdateCategoria();
		// this.listAllCategorias();

		 this.postSaveProduto();
//		 this.saveProduto();

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
		c.setNome("Pizzas");

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
		c = categoriaDAO.findById(2L);
		c.setNome("Sobremesas Frias");

		String es = this.gson.toJson(c);
		System.out.println(es);
		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/categoria/update", es);

		System.out.println(entityResponse);
	}

	public void listAllCategorias() {
		HttpConnector.getConnect("http://localhost:8080/ws_pousada/categoria/listAll");
	}

	public void postSaveProduto() {

		Categoria c = new Categoria();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		c = categoriaDAO.findById(5L);

		Produto p = new Produto();

		 p.setCategoria(c);
//		p.setCategoria_id(5L);
		p.setNome("Cheese Salada");
//		System.out.println(p);

		String es = this.gson.toJson(p);
		System.out.println(es);
		
		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/produto/save", es);
		System.out.println(entityResponse);
	}

	public void saveProduto() {

		 Categoria c = new Categoria();
		 CategoriaDAO categoriaDAO = new CategoriaDAO();
		 c = categoriaDAO.findById(2L);

		Produto p = new Produto();

		 p.setCategoria(c);
//		p.setCategoria_id(5L);
		p.setNome("Sorvete Chocolate");

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.save(p);
	}

}
