package ws_pousada.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ws_pousada.model.dao.EnderecoDAO;
import ws_pousada.model.entity.Endereco;
import ws_pousada.model.entity.Produto;

@Path("/intro")
public class IntroService {

	private Gson gson = new GsonBuilder().create();

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
	@Path("/toJson")
	@Produces(MediaType.APPLICATION_JSON)
	public String giveMeJson(@Context HttpServletRequest request) {

		Produto p = new Produto("Água Mineral Com Gás");
		String s = gson.toJson(p);
		System.out.println(s);

		// JSONObject json = new JSONObject();
		// Pessoa p = new Pessoa("João");
		// json.put("nome", p.getNome());
		// return json.toString();

		return s;
	}

	@GET
	@Path("/fromJson")
	@Produces(MediaType.APPLICATION_JSON)
	public String giveMeGSON(@Context HttpServletRequest request) {

		// try {
		// return new ObjectMapper().writeValueAsString(new Pessoa("Maria"));
		// } catch (JsonProcessingException e) {
		// e.printStackTrace();
		// return "";
		// }

		Produto p = gson.fromJson("{\"nome\":\"Água Mineral Sem Gás\"}", Produto.class);
		System.out.println(p);

		return "GSON";
	}

	@GET
	@Path("/array")
	@Produces(MediaType.TEXT_PLAIN)
	public String giveMeJsonArrayGSON(@Context HttpServletRequest request) {

		List<String> list = new ArrayList<>();

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
//							String[] test = param.getValue();
							
							if (e.getPais().equals(param.getValue()[0])) {
								filteredList.add(e);
							}
						}
						return this.gson.toJson(filteredList);

					} catch (Exception e) {
						e.printStackTrace();
						return new ArrayList<>().toString();
					}

				} else {
					return this.gson.toJson(list);
				}

			}
		} else {
			list = enderecoDAO.findAll();
			
			return this.gson.toJson(list);
		}

		// for (Map.Entry<String, String> entry : mappedEntity.entrySet()) {
		//
		// if (!frst) {
		// jpql.append(" AND ");
		// } else {
		// frst = false;
		// }
		//
		// jpql.append("'" + entry.getKey() + "'" + " = " + "'" + entry.getValue() +
		// "'");
		// }

		// }

		// response.setCharacterEncoding("UTF8"); // this line solves the problem
		// response.setContentType("application/json");

		// try {
		//
		// list = enderecoDAO.findAll();
		// return this.gson.toJson(list);
		// if(pais != null && pais.length() > 0) {
		// List<Endereco> filteredList = new ArrayList<>();
		//
		// for(Endereco e : list) {
		// if(e.getPais().equals(pais)) {
		// filteredList.add(e);
		// }
		// }
		// return this.gson.toJson(filteredList);
		//
		// } else {
		// return this.gson.toJson(list);
		// }

		// Endereco e = list.get(1);
		// String s = this.gson.toJson(e);
		// return s;

		// list.add(new ObjectMapper().writeValueAsString(new Pessoa("Maria")));
		// list.add(new ObjectMapper().writeValueAsString(new Pessoa("Pedro")));
		// list.add(new ObjectMapper().writeValueAsString(new Pessoa("Marcos")));
		//
		// return list.toString();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// return new ArrayList<>().toString();
		// }

		 return "ARRAY";
	}

}