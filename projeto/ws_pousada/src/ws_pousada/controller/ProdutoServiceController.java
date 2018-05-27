package ws_pousada.controller;

import javax.ws.rs.Path;

import ws_pousada.model.dao.ProdutoDAO;
import ws_pousada.model.entity.Produto;

@Path("/produto")
public class ProdutoServiceController extends GenericServiceController<Produto, ProdutoDAO> {

	// public ProdutoServiceController(ProdutoDAO dao) {
	// super(dao);
	// }

	public ProdutoServiceController() {
		super(new ProdutoDAO());
	}

}
