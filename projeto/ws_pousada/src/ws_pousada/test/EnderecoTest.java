package ws_pousada.test;

import java.util.ArrayList;
import java.util.List;

import ws_pousada.model.dao.EnderecoDAO;
import ws_pousada.model.entity.Endereco;

public class EnderecoTest extends GenericTest<Endereco, EnderecoDAO> {

	public EnderecoTest() {
		super(new Endereco(), new EnderecoDAO());
	}

	@Override
	protected void buildNew() {
		this.t = new Endereco();
	}

	@Override
	protected List<Endereco> toNewInsert() {

		List<Endereco> list = new ArrayList<>();
		Endereco tt = new Endereco();

		tt = new Endereco();
		tt.setBairro("Santa Mônica");
		tt.setCep("1221928");
		tt.setCidade("Florianópolis");
		tt.setComplemento("Casa");
		tt.setNumero("9090");
		tt.setPais("Brasil");
		tt.setRua("Testes Street");
		tt.setUf("SC");
		list.add(tt);

		tt = new Endereco();
		tt.setBairro("Prainha");
		tt.setCep("3434");
		tt.setCidade("Florianópolis");
		tt.setComplemento("Apto 304");
		tt.setNumero("2020");
		tt.setPais("Brasil");
		tt.setRua("Laranjeiras");
		tt.setUf("SC");
		list.add(tt);

		return list;
	}

	@Override
	protected void toNewUpdate() {
		this.t = new Endereco();
		this.t = this.dao.findById(1L);
		this.t.setPais("Canadá");
	}

}
