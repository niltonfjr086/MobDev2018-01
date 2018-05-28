package ws_pousada.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ws_pousada.model.dao.GenericDAO;

public abstract class GenericServiceController<T, D extends GenericDAO<T, Long>> {

	protected D dao;

	public GenericServiceController(D dao) {
		super();
		this.dao = dao;
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(T t) {

		try {

//			new GenericDAO<>() {}.save(t);
			dao.save(t);

			return Response.status(200).entity(t).build();
		} catch (Exception ex) {

			ex.printStackTrace();

			return Response.status(404).build();
		}

	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(T t) {

		try {

//			new GenericDAO<>() {}.update(t);
			dao.update(t);

			return Response.status(200).entity(t).build();
		} catch (Exception ex) {

			ex.printStackTrace();

			return Response.status(404).build();
		}

	}

	@GET
	@Path("/listAll")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {

		try {

			List<T> list = dao.findAll();

			String ls = IntroService.gson.toJson(list);

			return Response.status(200).entity(ls).build();
		} catch (Exception ex) {

			ex.printStackTrace();

			return Response.status(404).build();
		}

	}

	// @GET
	// @Path("/list")
	// @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	// public String listAddresses(@Context HttpServletRequest request
	// ) {
	//
	// Map<String, String[]> params = request.getParameterMap();
	//
	// List<Endereco> list = new ArrayList<>();
	//
	// if (params != null && params.size() > 0) {
	//
	// for (Map.Entry<String, String[]> param : params.entrySet()) {
	//
	// if (param != null && param.getKey().equals("pais") && param.getValue() !=
	// null) {
	// try {
	// list = enderecoDAO.findAll();
	//
	// List<Endereco> filteredList = new ArrayList<>();
	//
	// for (Endereco e : list) {
	// // String[] test = param.getValue();
	//
	// if (e.getPais().toLowerCase().equals(param.getValue()[0].toLowerCase())) {
	// filteredList.add(e);
	// }
	// }
	// return this.gson.toJson(filteredList);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// return new ArrayList<>().toString();
	// }
	//
	// } else {
	// return this.gson.toJson(list);
	// }
	//
	// }
	// } else {
	// list = enderecoDAO.findAll();
	//
	// return this.gson.toJson(list);
	// }
	//
	// return "ARRAY";
	// }

}
