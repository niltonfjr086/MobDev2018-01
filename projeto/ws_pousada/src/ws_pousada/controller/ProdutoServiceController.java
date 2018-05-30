package ws_pousada.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ws_pousada.model.dao.ProdutoDAO;
import ws_pousada.model.entity.Produto;

@Path("/produto")
public class ProdutoServiceController extends GenericServiceController<Produto, ProdutoDAO> {

	// public ProdutoServiceController(ProdutoDAO dao) {
	// super(dao);
	// }

	public ProdutoServiceController() {
		super(new Produto(), new ProdutoDAO());
	}
	
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Produto t) {

		try {

//			new GenericDAO<>() {}.save(t);
			new ProdutoDAO().save(t);

			return Response.status(200).entity(t).build();
		} catch (Exception ex) {

			ex.printStackTrace();

			return Response.status(404).build();
		}

	}

}
