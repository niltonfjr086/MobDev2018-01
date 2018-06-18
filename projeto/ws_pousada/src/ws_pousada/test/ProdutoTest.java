package ws_pousada.test;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import ws_pousada.model.FactoryDAO;
//import ws_pousada.model.HttpConnector;
import ws_pousada.model.dao.ProdutoDAO;
import ws_pousada.model.entity.Produto;

public class ProdutoTest extends GenericTest<Produto, ProdutoDAO> {

	public ProdutoTest() {
		super(new Produto(), new ProdutoDAO());
	}

	@Override
	protected void buildNew() {
		this.t = new Produto();
	}

	@Override
	protected List<Produto> toNewInsert() {

		List<Produto> list = new ArrayList<>();
		Produto p = new Produto();

		p = new Produto();
		p.setCodBarras("8080");
		p.setDescricao("Garrafa de Vinho");
		p.setValorUnitario(60.45);
		p.setTipoProduto("Bebida");
		p.setEstoqueMaximo(50);
		p.setEstoqueMinimo(5);
		list.add(p);

		p = new Produto();
		p.setCodBarras("7878");
		p.setDescricao("Creme Dental");
		p.setTipoProduto("Higiene Pessoal");
		p.setValorUnitario(6.50);
		p.setEstoqueMaximo(250);
		p.setEstoqueMinimo(10);
		list.add(p);

		return list;
	}

	@Override
	protected void toNewUpdate() {

		this.t = new Produto();
		this.t = this.dao.findById(1L);
		this.t.setDescricao("Garrafa Vinho Seco");

	}

	// private ProdutoDAO produtoDAO = new ProdutoDAO();
	// private Produto produto = new Produto();
	//
	// ObjectMapper mapper = new
	// ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	// false);
	//
	// @Before
	// public void before() {
	// FactoryDAO.sessionInstance();
	// }
	//
	// @After
	// public void after() {
	// FactoryDAO.closeInstance();
	// }
	//
	// @Test
	// public void populaAtualizaEndereco() {
	//
	// try {
	// List<Produto> list = produtoDAO.findAll();
	//
	// assertNotNull("Tabela não criada ou falta mapeamento no hibernate.cfg",
	// list);
	// if (list != null && list.size() <= 0) {
	// assertTrue("Tabela não deve possuir registros", list.size() <= 0);
	//
	// this.postSave();
	// this.postUpdate();
	// }
	//
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// throw (e);
	// } finally {
	//
	// }
	//
	// System.out.println("-----------------------------");
	// System.out.println("-----------------------------");
	// System.out.println("-----------------------------");
	// List<Produto> list = produtoDAO.findAll();
	// assertTrue(list.size() > 0);
	// System.out.println(list);
	//
	// }
	//
	// private void postSave() {
	//
	// this.produto = new Produto();
	// this.produto.setCodBarras("8080");
	// this.produto.setDescricao("Garrafa de Vinho");
	// this.produto.setValorUnitario(60.45);
	// this.produto.setTipoProduto("Bebida");
	// this.produto.setEstoqueMaximo(50);
	// this.produto.setEstoqueMinimo(5);
	// try {
	// String jsonInString = mapper.writeValueAsString(this.produto);
	// String entityResponse =
	// HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/produto/save",
	// jsonInString);
	//
	// Produto pp = mapper.readValue(entityResponse, Produto.class);
	// assertNotNull("ID não pode ser nulo", pp.getId());
	// assertEquals("Nome retornado incorreto", "Garrafa de Vinho",
	// pp.getDescricao());
	// assertTrue("Salvo incompleto", this.salvoCompleto(pp));
	// System.out.println(pp.toString());
	//
	// } catch (JsonProcessingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// this.produto = new Produto();
	// }
	//
	// this.produto = new Produto();
	// this.produto.setCodBarras("7878");
	// this.produto.setDescricao("Creme Dental");
	// this.produto.setTipoProduto("Higiene Pessoal");
	// this.produto.setValorUnitario(6.50);
	// this.produto.setEstoqueMaximo(250);
	// this.produto.setEstoqueMinimo(10);
	// try {
	// String jsonInString = mapper.writeValueAsString(this.produto);
	// String entityResponse =
	// HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/produto/save",
	// jsonInString);
	//
	// Produto item = mapper.readValue(entityResponse, Produto.class);
	// assertNotNull("ID não pode ser nulo", item.getId());
	// assertEquals("Nome retornado incorreto", "Creme Dental",
	// item.getDescricao());
	// assertTrue("Salvo incompleto", this.salvoCompleto(item));
	// System.out.println(item.toString());
	//
	// } catch (JsonProcessingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// this.produto = new Produto();
	// }
	//
	// }
	//
	// private void postUpdate() {
	//
	// try {
	// this.produto = new Produto();
	// this.produto = this.produtoDAO.findById(1L);
	// this.produto.setDescricao("Garrafa Vinho Seco");
	//
	// String jsonInString = this.mapper.writeValueAsString(this.produto);
	// String entityResponse =
	// HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/servico/update",
	// jsonInString);
	//
	// Produto item = this.mapper.readValue(entityResponse, Produto.class);
	// System.out.println("Atualizado completo: " + this.salvoCompleto(item) + "\n"
	// + item.toString());
	//
	// this.produto = new Produto();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// this.produto = new Produto();
	// }
	//
	// }
	//
	// private boolean salvoCompleto(Produto s) {
	// Field[] fields = s.getClass().getDeclaredFields();
	//
	// if (fields != null && fields.length > 0) {
	// for (Field field : fields) {
	// if (field == null) {
	// return false;
	// }
	// }
	// return true;
	// }
	// return false;
	// }

}
