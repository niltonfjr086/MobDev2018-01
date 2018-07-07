package ws_pousada.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.HttpConnector;
import ws_pousada.model.dao.CategoriaDAO;
import ws_pousada.model.dao.EnderecoDAO;
import ws_pousada.model.dao.ProdutoDAO;
import ws_pousada.model.entity.Categoria;
import ws_pousada.model.entity.Endereco;
import ws_pousada.model.entity.Produto;
import ws_pousada.model.entity.Usuario;

public class ConsoleTest {

	private Endereco endereco = new Endereco();

	@Test
	public void toConsole() {
		System.out.println("TESTE");

		FactoryDAO.sessionInstance();

		// this.postSaveEndereco();
		// this.postUpdateEndereco();

		// this.postSaveCategoria();
		// this.postUpdateCategoria();
		// this.listAllCategorias();

		// this.postSaveProduto();
		// this.saveProduto();
		// this.postUpdateProduto();

		// this.getListAllProdutos();
		// this.getProduto();

		// this.postSaveUsuario();
		// this.validateConnection();

		FactoryDAO.closeInstance();

		assertEquals(0, 0);
	}

	public void postSaveEndereco() {

		this.endereco = new Endereco();
		this.endereco.setBairro("Santa Mônica");
		this.endereco.setCep("1221928");
		this.endereco.setCidade("Florianópolis");
		this.endereco.setComplemento("Casa");
		this.endereco.setNumero("9090");
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

		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/endereco/save",
				jsonInString);

		System.out.println(entityResponse);

	}

	public void postUpdateEndereco() {

		EnderecoDAO enderecoDAO = new EnderecoDAO();
		enderecoDAO.findById(1L);

		this.endereco = enderecoDAO.findById(1L);
		this.endereco.setCep("84020200");
		this.endereco.setNumero("7878");
		this.endereco.setRua("Testes Full Street");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(this.endereco);
			System.out.println(jsonInString);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/endereco/update",
				jsonInString);

		System.out.println(entityResponse);

		try {
			Endereco e = mapper.readValue(entityResponse, Endereco.class);
			System.out.println(e.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void postSaveCategoria() {

		Categoria c = new Categoria();
		c.setNome("Sobremesas");

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

//		Categoria c = new Categoria();
//		CategoriaDAO categoriaDAO = new CategoriaDAO();
//		c = categoriaDAO.findById(1L);

		Produto p = new Produto();

//		p.setCategoria(c);
//		p.setNome("Cheese Salada");
//		p.setValor(17.0);

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

//		Categoria c = new Categoria();
//		CategoriaDAO categoriaDAO = new CategoriaDAO();
//		c = categoriaDAO.findById(3L);

		Produto p = new Produto();

//		p.setCategoria(c);
//		p.setNome("Batata Frita");

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.save(p);
	}

	public void postUpdateProduto() {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto p = produtoDAO.findById(1L);

//		p.setNome("Batata Frita Média");

//		Categoria c = new Categoria();
//		CategoriaDAO categoriaDAO = new CategoriaDAO();
//		c = categoriaDAO.findById(2L);
//
//		p.setCategoria(c);

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

		ObjectMapper mapper = new ObjectMapper();

		List<Produto> produtos = new ArrayList<>();

		try {
			List<Object> voidList = mapper.readValue(retorno, List.class);
			for (Object o : voidList) {
				Produto p = mapper.readValue(mapper.writeValueAsString(o), Produto.class);
				produtos.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(produtos);

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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void postSaveUsuario() {

		Usuario u = new Usuario();
		u.setSenha("1234");
		u.setEmail("a@b.com");

		ObjectMapper mapper = new ObjectMapper();

		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(u);
			System.out.println(jsonInString);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/usuario/save",
				jsonInString);
		System.out.println(entityResponse);

	}

	public void validateConnection() {

		String email = "a@b.com";
		String senha = "1234";

		// ObjectMapper mapper = new ObjectMapper();
		// String jsonInString = "";
		// try {
		// jsonInString = mapper.writeValueAsString(u);
		// System.out.println(jsonInString);
		// } catch (JsonProcessingException e) {
		// e.printStackTrace();
		// }

		String entityResponse = HttpConnector.validateConnect("http://localhost:8080/ws_pousada/usuario/login", email,
				senha);

		ObjectMapper mapper = new ObjectMapper();
		// mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		Usuario u = null;
		try {
			u = mapper.readValue(entityResponse, Usuario.class);
			// u = mapper.convertValue(entityResponse, Usuario.class);
			// u =
			// mapper.convertValue("{\"email\":\"a@b.com\",\"id\":1,\"senha\":\"1234\"}",
			// Usuario.class);

			System.out.println(u);

		} catch (Exception e) {
			u = new Usuario();
			e.printStackTrace();
		}

		// System.out.println(entityResponse);

	}

}
