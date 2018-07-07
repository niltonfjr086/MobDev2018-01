package ws_pousada.controller;

import javax.ws.rs.Path;

import ws_pousada.model.dao.CategoriaDAO;
import ws_pousada.model.entity.Categoria;

@Path("/categoria")
public class CategoriaServiceController extends GenericServiceController<Categoria, CategoriaDAO> {

	public CategoriaServiceController() {
		super(new Categoria(), new CategoriaDAO());
	}

}
