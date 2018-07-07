package ws_pousada.controller;

import javax.ws.rs.Path;

import ws_pousada.model.dao.EnderecoDAO;
import ws_pousada.model.entity.Endereco;

@Path("/endereco")
public class EnderecoServiceController extends GenericServiceController<Endereco, EnderecoDAO> {

	public EnderecoServiceController() {
		super(new Endereco(), new EnderecoDAO());
	}

}