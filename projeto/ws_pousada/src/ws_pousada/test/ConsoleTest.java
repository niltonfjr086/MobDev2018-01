package ws_pousada.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.HttpConnector;
import ws_pousada.model.dao.CategoriaDAO;
import ws_pousada.model.dao.ProdutoDAO;
import ws_pousada.model.entity.Categoria;
import ws_pousada.model.entity.Endereco;
import ws_pousada.model.entity.Produto;

public class ConsoleTest {

	private Endereco endereco = new Endereco();

	@Test
	public void toConsole() {
		System.out.println("TESTE");

		FactoryDAO.sessionInstance();

		// this.postEnderecoTest();
		// this.postSaveCategoria();
		// this.postUpdateCategoria();
		// this.listAllCategorias();

		 this.postSaveProduto();
		// this.saveProduto();
//		this.postUpdateProduto();

		// this.getListAllProdutos();
		// this.getProduto();

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

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(this.endereco);
			System.out.println(jsonInString);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/intro/saveAddress",
				jsonInString);

		System.out.println(entityResponse);

	}

	public void postSaveCategoria() {

		Categoria c = new Categoria();
		c.setNome("Porções");

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(c);
			System.out.println(jsonInString);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/categoria/save",
				jsonInString);

		System.out.println(entityResponse);
	}

	public void postUpdateCategoria() {

		Categoria c = new Categoria();
		CategoriaDAO categoriaDAO = new CategoriaDAO();

		// c = categoriaDAO.findById(6L);
		// c.setNome("Pratos Quentes");
		c = categoriaDAO.findById(1L);
		c.setNome("Pizzas Doces ou Salgadas");

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(c);
			System.out.println(jsonInString);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/categoria/update",
				jsonInString);

		System.out.println(entityResponse);
	}

	public void listAllCategorias() {
		HttpConnector.getConnect("http://localhost:8080/ws_pousada/categoria/listAll");
	}

	public void postSaveProduto() {

		Categoria c = new Categoria();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		c = categoriaDAO.findById(1L);

		Produto p = new Produto();

		p.setCategoria(c);
		p.setNome("Cheese Burger");
		p.setValor(13.8);

		ObjectMapper mapper = new ObjectMapper();

		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(p);
			System.out.println(jsonInString);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/produto/save",
				jsonInString);
		System.out.println(entityResponse);

	}

	public void saveProduto() {

		Categoria c = new Categoria();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		c = categoriaDAO.findById(3L);

		Produto p = new Produto();

		p.setCategoria(c);
		p.setNome("Batata Frita");

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.save(p);
	}

	public void postUpdateProduto() {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto p = produtoDAO.findById(1L);

		p.setNome("Batata Frita Média");

		Categoria c = new Categoria();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		c = categoriaDAO.findById(2L);

		p.setCategoria(c);

		ObjectMapper mapper = new ObjectMapper();

		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(p);
			System.out.println(jsonInString);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/produto/update",
				jsonInString);
		System.out.println(entityResponse);

	}

	private void getListAllProdutos() {
		String retorno = HttpConnector.getConnect("http://localhost:8080/ws_pousada/produto/listAll");

		// System.out.println(retorno);

		ObjectMapper mapper = new ObjectMapper();
		// List<Produto> produtos = mapper.convertValue(retorno, new
		// TypeReference<List<Produto>>() {});
		// List<Produto> produtos = mapper.convertValue(retorno, List.class);

		List<Produto> produtos = null;
		try {
			produtos = mapper.readValue(retorno, List.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(produtos.get(0).getNome());
		System.out.println("LALA");
		// System.out.println(produtos.get(0).getNome());
		// try {
		// JsonNode node = mapper.readTree(retorno);
		// List<Produto> produtos = mapper.convertValue(node.findValues("produto"),
		// new TypeReference<List<Produto>>() {});
		//
		// System.out.println(produtos);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// ProdutoDAO produtoDAO = new ProdutoDAO();
		// List<Produto> produtos = produtoDAO.findAll();
		// System.out.println(produtos);

	}

	private void getProduto() {
		String retorno = HttpConnector.getConnect("http://localhost:8080/ws_pousada/produto/getById", "2");

		ObjectMapper mapper = new ObjectMapper();

		Produto produto = null;
		try {
			produto = mapper.readValue(retorno, Produto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(produto);
		System.out.println("LALA");
		
		try {
			
		}catch (Exception e) {
			// TODO: handle exception
		}

	}

}
