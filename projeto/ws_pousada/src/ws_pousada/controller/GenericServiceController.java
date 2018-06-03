package ws_pousada.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ws_pousada.model.dao.GenericDAO;

public abstract class GenericServiceController<T, D extends GenericDAO<T, Long>> {

	protected ObjectMapper mapper = new ObjectMapper();
	protected T manipulated;
	protected D dao;

	public GenericServiceController(T manipulated, D dao) {
		super();
		this.manipulated = manipulated;
		this.dao = dao;
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(T t) {

		try {

			// new GenericDAO<>() {}.save(t);
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

			// new GenericDAO<>() {}.update(t);
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

			String jsonInString = "";
			try {
				jsonInString = this.mapper.writeValueAsString(list);
				// System.out.println(jsonInString);

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			return Response.status(200).entity(jsonInString).build();
		} catch (Exception ex) {

			ex.printStackTrace();

			return Response.status(404).build();
		}

	}

	@GET
	@Path("/getById")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@QueryParam("id") String id) {

		if (id != null && id.length() > 0) {

			boolean onlyDigits = true;
			char[] chars = id.toCharArray();
			for (char c : chars) {
				if (!Character.isDigit(c)) {
					onlyDigits = false;
					break;
				}
			}

			if (onlyDigits) {
				Long i = Long.valueOf(id);
				try {

					T t = dao.findById(i);

					String jsonInString = "";
					try {
						jsonInString = this.mapper.writeValueAsString(t);
						// System.out.println(jsonInString);

					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}

					return Response.status(200).entity(jsonInString).build();
				} catch (Exception ex) {

					ex.printStackTrace();

					return Response.status(404).build();
				}
			}

		}
		return Response.status(404).build();
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
