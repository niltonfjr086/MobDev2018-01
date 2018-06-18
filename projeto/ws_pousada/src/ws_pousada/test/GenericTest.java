package ws_pousada.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.HttpConnector;
import ws_pousada.model.dao.GenericDAO;

public abstract class GenericTest<T, DAO extends GenericDAO<T, Long>> {

	protected ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);

	protected T t;
	protected DAO dao;
	protected String serverPath;

	public GenericTest(T t, DAO dao) {
		super();
		this.t = t;
		this.dao = dao;

		this.serverPath = "http://localhost:8080/ws_pousada/" + this.t.getClass().getSimpleName().toLowerCase();
	}

	@Before
	public void before() {
		FactoryDAO.sessionInstance();
	}

	@After
	public void after() {
		FactoryDAO.closeInstance();
	}

	@Test
	public void populaAtualizaEndereco() {

		try {
			List<T> list = dao.findAll();

			assertNotNull("Tabela não criada ou falta mapeamento no hibernate.cfg", list);
			if (list != null && list.size() <= 0) {
				assertTrue("Tabela não deve possuir registros", list.size() <= 0);

				this.postSave();
				this.postUpdate();
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw (e);
		} finally {

		}

		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		List<T> list = dao.findAll();
		assertTrue(list.size() > 0);
		System.out.println(list);

	}

	private void postSave() {

		List<T> dataLoad = this.toNewInsert();

		for (T tt : dataLoad) {
			this.buildNew();

			try {
				this.t = tt;

				String jsonInString = mapper.writeValueAsString(t);
				String entityResponse = HttpConnector.savePostConnect(serverPath + "/save", jsonInString);

				T responseItem = (T) mapper.readValue(entityResponse, t.getClass());
				// assertNotNull("ID não pode ser nulo", responseItem.getId());
				// assertEquals("Nome retornado incorreto", "Garrafa de Vinho",
				// responseItem.getDescricao());
				assertTrue("Salvo incompleto", this.isComplete());
				System.out.println(responseItem.toString());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				this.buildNew();

			}

		}

		// this.produto = new Produto();
		// this.produto.setCodBarras("8080");
		// this.produto.setDescricao("Garrafa de Vinho");
		// this.produto.setValorUnitario(60.45);
		// this.produto.setTipoProduto("Bebida");
		// this.produto.setEstoqueMaximo(50);
		// this.produto.setEstoqueMinimo(5);
		// this.buildNew();
		// this.toNewInsert();
		// try {
		// String jsonInString = mapper.writeValueAsString(this.t);
		// String entityResponse = HttpConnector.savePostConnect(serverPath + "/save",
		// jsonInString);
		//
		// Produto item = mapper.readValue(entityResponse, Produto.class);
		// assertNotNull("ID não pode ser nulo", item.getId());
		// assertEquals("Nome retornado incorreto", "Garrafa de Vinho",
		// item.getDescricao());
		// assertTrue("Salvo incompleto", this.isComplete());
		// System.out.println(item.toString());
		//
		// } catch (JsonProcessingException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// // this.produto = new Produto();
		// this.buildNew();
		//
		// }

		// this.produto = new Produto();
		// this.produto.setCodBarras("7878");
		// this.produto.setDescricao("Creme Dental");
		// this.produto.setTipoProduto("Higiene Pessoal");
		// this.produto.setValorUnitario(6.50);
		// this.produto.setEstoqueMaximo(250);
		// this.produto.setEstoqueMinimo(10);
		// this.buildNew();
		// this.toNewInsert();
		//
		// try {
		// String jsonInString = mapper.writeValueAsString(this.t);
		// String entityResponse = HttpConnector.savePostConnect(serverPath + "/save",
		// jsonInString);
		//
		// Produto item = mapper.readValue(entityResponse, Produto.class);
		// assertNotNull("ID não pode ser nulo", item.getId());
		// assertEquals("Nome retornado incorreto", "Creme Dental",
		// item.getDescricao());
		// assertTrue("Salvo incompleto", this.isComplete());
		// System.out.println(item.toString());
		//
		// } catch (JsonProcessingException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// // this.produto = new Produto();
		// this.buildNew();
		//
		// }

	}

	private void postUpdate() {

		try {
			// this.produto = new Produto();
			this.buildNew();

			// this.produto = this.produtoDAO.findById(1L);
			// this.produto.setDescricao("Garrafa Vinho Seco");
			this.toNewUpdate();

			String jsonInString = this.mapper.writeValueAsString(this.t);
			String entityResponse = HttpConnector.savePostConnect(serverPath + "/update", jsonInString);

			T resItem = (T) this.mapper.readValue(entityResponse, t.getClass());
			System.out.println("Atualizado completo: " + this.isComplete() + "\n" + resItem.toString());

			// this.produto = new Produto();
			this.buildNew();

		} catch (Exception e) {
			e.printStackTrace();
			// this.produto = new Produto();
			this.buildNew();
		}

	}

	protected abstract void buildNew();

	protected abstract List<T> toNewInsert();

	protected abstract void toNewUpdate();

	private boolean isComplete() {
		Field[] fields = this.t.getClass().getDeclaredFields();

		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				if (field == null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
