package ws_pousada.test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.HttpConnector;
import ws_pousada.model.dao.EnderecoDAO;
import ws_pousada.model.entity.Endereco;

public class EnderecoTest {

	EnderecoDAO enderecoDAO = new EnderecoDAO();
	Endereco endereco;

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
			List<Endereco> enderecos = enderecoDAO.findAll();

			assertNotNull("Tabela endereço não criada ou falta mapeamento no hibernate.cfg", enderecos);
			if (enderecos != null && enderecos.size() <= 0) {
				assertTrue("Tabela não deve possuir registros", enderecos.size() <= 0);

				this.postSaveEndereco();
				this.postUpdateEndereco();
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw (e);
		} finally {

		}

		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		System.out.println(enderecoDAO.findAll());

	}

	private void postSaveEndereco() {

		this.endereco = new Endereco();
		this.endereco.setBairro("Santa Mônica");
		this.endereco.setCep("1221928");
		this.endereco.setCidade("Florianópolis");
		this.endereco.setComplemento("Casa");
		this.endereco.setNumero("9090");
		this.endereco.setPais("Brasil");
		this.endereco.setRua("Testes Street");
		this.endereco.setUf("SC");
		try {
			String jsonInString = mapper.writeValueAsString(this.endereco);
			String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/endereco/save",
					jsonInString);

			Endereco e = mapper.readValue(entityResponse, Endereco.class);
			assertNotNull("ID não pode ser nulo", e.getId());
			assertEquals("Nome bairro retornado incorreto", "Santa Mônica", e.getBairro());
			assertTrue("Salvo incompleto", this.salvoCompleto(e));
			System.out.println(e.toString());

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.endereco = new Endereco();
		this.endereco.setBairro("Prainha");
		this.endereco.setCep("3434");
		this.endereco.setCidade("Florianópolis");
		this.endereco.setComplemento("Apto 304");
		this.endereco.setNumero("2020");
		this.endereco.setPais("Brasil");
		this.endereco.setRua("Laranjeiras");
		this.endereco.setUf("SC");
		try {
			String jsonInString = this.mapper.writeValueAsString(this.endereco);
			String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/endereco/save",
					jsonInString);

			Endereco e = this.mapper.readValue(entityResponse, Endereco.class);
			assertNotNull("ID não pode ser nulo", e.getId());
			assertEquals("Nome bairro retornado incorreto", "Prainha", e.getBairro());
			assertTrue("Salvo incompleto", this.salvoCompleto(e));
			System.out.println(e.toString());

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.endereco = new Endereco();

	}

	private void postUpdateEndereco() {

		try {
			this.endereco = new Endereco();
			this.endereco = this.enderecoDAO.findById(1L);
			this.endereco.setPais("Canadá");

			String jsonInString = this.mapper.writeValueAsString(this.endereco);
			String entityResponse = HttpConnector.savePostConnect("http://localhost:8080/ws_pousada/endereco/update",
					jsonInString);

			Endereco e = this.mapper.readValue(entityResponse, Endereco.class);
			System.out.println("Atualizado completo: " + this.salvoCompleto(e) + "\n" + e.toString());

			this.endereco = new Endereco();

		} catch (Exception e) {
			e.printStackTrace();
			this.endereco = new Endereco();
		}

	}

	private boolean salvoCompleto(Endereco e) {
		Field[] fields = e.getClass().getDeclaredFields();

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
