package ws_pousada.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.HttpConnector;
import ws_pousada.model.dao.ServicoDAO;
import ws_pousada.model.entity.Servico;

public class ServicoTest {

	Servico s = new Servico();
	ServicoDAO servicoDAO = new ServicoDAO();

	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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
			List<Servico> list = servicoDAO.findAll();

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
		List<Servico> list = servicoDAO.findAll();
		assertTrue(list.size() > 0);
		System.out.println(list);

	}

	private void postSave() {

		this.s = new Servico();
		this.s.setCodBarras("3030");
		this.s.setDescricao("Almoço");
		this.s.setTipoServico("Diário");
		this.s.setValorUnitario(60.45);
		try {
			String jsonInString = mapper.writeValueAsString(this.s);
			String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/servico/save",
					jsonInString);

			Servico ss = mapper.readValue(entityResponse, Servico.class);
			assertNotNull("ID não pode ser nulo", ss.getId());
			assertEquals("Nome retornado incorreto", "Almoço", ss.getDescricao());
			assertTrue("Salvo incompleto", this.salvoCompleto(ss));
			System.out.println(ss.toString());

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.s = new Servico();
		}

		this.s = new Servico();
		this.s.setCodBarras("4040");
		this.s.setDescricao("Jantar");
		this.s.setTipoServico("Demanda");
		this.s.setValorUnitario(120.50);
		try {
			String jsonInString = mapper.writeValueAsString(this.s);
			String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/servico/save",
					jsonInString);

			Servico ss = mapper.readValue(entityResponse, Servico.class);
			assertNotNull("ID não pode ser nulo", ss.getId());
			assertEquals("Nome retornado incorreto", "Jantar", ss.getDescricao());
			assertTrue("Salvo incompleto", this.salvoCompleto(ss));
			System.out.println(ss.toString());

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.s = new Servico();
		}

	}

	private void postUpdate() {

		try {
			this.s = new Servico();
			this.s = this.servicoDAO.findById(1L);
			this.s.setDescricao("Café da manhã");

			String jsonInString = this.mapper.writeValueAsString(this.s);
			String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/servico/update",
					jsonInString);

			Servico ss = this.mapper.readValue(entityResponse, Servico.class);
			System.out.println("Atualizado completo: " + this.salvoCompleto(ss) + "\n" + ss.toString());

			this.s = new Servico();

		} catch (Exception e) {
			e.printStackTrace();
			this.s = new Servico();
		}

	}

	private boolean salvoCompleto(Servico s) {
		Field[] fields = s.getClass().getDeclaredFields();

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
