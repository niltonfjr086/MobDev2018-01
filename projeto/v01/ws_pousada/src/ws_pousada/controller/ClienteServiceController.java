package ws_pousada.controller;

import javax.ws.rs.Path;

import ws_pousada.model.dao.ClienteDAO;
import ws_pousada.model.entity.Cliente;

@Path("/cliente")
public class ClienteServiceController extends GenericServiceController<Cliente, ClienteDAO> {

	public ClienteServiceController() {
		super(new Cliente(), new ClienteDAO());

	}

}