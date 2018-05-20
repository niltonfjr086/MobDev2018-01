package ws_pousada.test;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.dao.EnderecoDAO;
import ws_pousada.model.entity.Endereco;

public class EnderecoTest {

	EnderecoDAO enderecoDAO = new EnderecoDAO();
	Endereco endereco;

	@Test
	public void test() {

		// Session s = FactoryDAO.sessionInstance();

		try {
			List<Endereco> enderecos = enderecoDAO.findAll();

			if(enderecos != null && enderecos.size() <= 0) {
				
				 this.adicionar();
				 this.update();
				 
				 enderecos = enderecoDAO.findAll();
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

		FactoryDAO.closeInstance();

	}

	private void adicionar() {

		this.endereco = new Endereco();
		this.endereco.setBairro("Prainha");
		this.endereco.setCep("3434");
		this.endereco.setCidade("Florianópolis");
		this.endereco.setComplemento("Apto 304");
		this.endereco.setNumero(2020);
		this.endereco.setPais("Brasil");
		this.endereco.setRua("Laranjeiras");
		this.endereco.setUf("SC");

		Endereco e = this.enderecoDAO.save(this.endereco);
		this.salvoCompleto(e);

		this.endereco = new Endereco();

	}

	private void update() {

		try {
			this.endereco = new Endereco();
			this.endereco = this.enderecoDAO.findById(1L);

			this.endereco.setPais("Canadá");

			this.enderecoDAO.update(this.endereco);

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
