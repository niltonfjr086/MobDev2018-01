package ws_pousada.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ws_pousada.model.dao.CategoriaDAO;
import ws_pousada.model.entity.Categoria;

@Path("/categoria")
public class CategoriaServiceController extends GenericServiceController<Categoria> {

	public CategoriaServiceController() {

	}

	@GET
	@Path("/listAll")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {

		try {
			// new GenericDAO<>() {}.findAll();

//			List<T> list = new GenericDAO<T,Long>() {}.findAll();
			List<Categoria> list = new CategoriaDAO().findAll();
			
			String ls = IntroService.gson.toJson(list);
			System.out.println(ls);

			return Response.status(200).entity(ls).build();
		} catch (Exception ex) {

			ex.printStackTrace();

			return Response.status(404).build();
		}

	}
}
