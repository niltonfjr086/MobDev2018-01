package ws_pousada.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ws_pousada.model.dao.EnderecoDAO;
import ws_pousada.model.entity.Endereco;
import ws_pousada.model.entity.Produto;

@Path("/intro")
public class IntroService {
	
	public static ObjectMapper mapper = new ObjectMapper();

	private EnderecoDAO enderecoDAO = new EnderecoDAO();

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(@Context HttpServletRequest request) {

		String url = request.getRequestURL().toString();
		String query = request.getQueryString();

		return "Hello, welcome to Rest Web Service: \n" + url + "\n" + query;
	}

	@GET
	@Path("/bye")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayGoodBye(@Context HttpServletRequest request) {

		String url = request.getRequestURL().toString();

		return "See you next time, bye: \n" + url;
	}

	@GET
	@Path("/fromJson")
	@Produces(MediaType.APPLICATION_JSON)
	public String giveMeGSON(@Context HttpServletRequest request) {
				
		Produto p = mapper.convertValue("{\"nome\":\"Água Mineral Sem Gás\"}", Produto.class);
		System.out.println(p);

		return "JACKSON";
	}

	@GET
	@Path("/array")
	@Produces(MediaType.TEXT_PLAIN)
	public String giveMeJsonArrayGSON(@Context HttpServletRequest request) {

//		List<String> list = new ArrayList<>();

		// try {
		// list.add(new ObjectMapper().writeValueAsString(new Pessoa("Maria")));
		// list.add(new ObjectMapper().writeValueAsString(new Pessoa("Pedro")));
		// list.add(new ObjectMapper().writeValueAsString(new Pessoa("Marcos")));
		//
		// return list.toString();
		//
		// } catch (JsonProcessingException e) {
		// e.printStackTrace();
		// return new ArrayList<>().toString();
		// }

		return "ARRAY";
	}

	@GET
	@Path("/enderecos")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String listAddresses(@Context HttpServletRequest request
	// , @QueryParam("pais") String pais
	// , @QueryParam("cidade") String cidade
	) {

		Map<String, String[]> params = request.getParameterMap();

		List<Endereco> list = new ArrayList<>();

		if (params != null && params.size() > 0) {

			for (Map.Entry<String, String[]> param : params.entrySet()) {

				if (param != null && param.getKey().equals("pais") && param.getValue() != null) {
					try {
						list = enderecoDAO.findAll();

						List<Endereco> filteredList = new ArrayList<>();

						for (Endereco e : list) {
							// String[] test = param.getValue();

							if (e.getPais().toLowerCase().equals(param.getValue()[0].toLowerCase())) {
								filteredList.add(e);
							}
						}
						return mapper.writeValueAsString(filteredList);

					} catch (Exception e) {
						e.printStackTrace();
						return new ArrayList<>().toString();
					}

				} else {
					try {
						return mapper.writeValueAsString(list);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}

			}
		} else {
			list = enderecoDAO.findAll();

			try {
				return mapper.writeValueAsString(list);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return "ARRAY";
	}

	@POST
	@Path("/saveAddress")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Endereco e) {
		
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.save(e);

			return Response.status(200).entity(e).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(404).build();
		}

	}
	


}