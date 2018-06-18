package ws_pousada.controller;

import javax.ws.rs.Path;

import ws_pousada.model.dao.ServicoDAO;
import ws_pousada.model.entity.Servico;

@Path("/servico")
public class ServicoServiceController extends GenericServiceController<Servico, ServicoDAO> {

	public ServicoServiceController() {
		super(new Servico(), new ServicoDAO());
	}

}
