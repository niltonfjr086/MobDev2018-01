package ws_pousada.controller;

import javax.ws.rs.Path;

import ws_pousada.model.dao.ProdutoEntradaDAO;
import ws_pousada.model.entity.ProdutoEntrada;

@Path("/produtoentrada")
public class ProdutoEntradaServiceController extends GenericServiceController<ProdutoEntrada, ProdutoEntradaDAO> {

	public ProdutoEntradaServiceController() {
		super(new ProdutoEntrada(), new ProdutoEntradaDAO());
	}

}
