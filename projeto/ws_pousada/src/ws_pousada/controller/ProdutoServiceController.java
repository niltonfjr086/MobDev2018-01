package ws_pousada.controller;

import javax.ws.rs.Path;

import ws_pousada.model.dao.ProdutoDAO;
import ws_pousada.model.entity.OLDProduto;

@Path("/produto")
public class ProdutoServiceController extends GenericServiceController<OLDProduto, ProdutoDAO> {

	public ProdutoServiceController() {
		super(new OLDProduto(), new ProdutoDAO());
	}

}
