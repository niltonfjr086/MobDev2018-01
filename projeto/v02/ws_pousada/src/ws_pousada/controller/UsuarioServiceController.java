package ws_pousada.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ws_pousada.model.dao.UsuarioDAO;
import ws_pousada.model.entity.Usuario;

@Path("/usuario")
public class UsuarioServiceController extends GenericServiceController<Usuario, UsuarioDAO>{
	
	public UsuarioServiceController() {
		super(new Usuario(), new UsuarioDAO());
	}

	@GET
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateLogin(@QueryParam("email") String email, @QueryParam("senha") String senha) {

		try {
// 			List<Usuario> usuarios = sessionInstance().createQuery("FROM " + "Usuario" + " WHERE " + "login='" + login + "' AND " + "senha='" + senha+"'").getResultList();
//			List<Usuario> usuarios = dao.executeQuery("FROM Usuario WHERE login=? AND senha=?", login, senha);

			Map<String, String> params = new HashMap<>();
			params.put("email", email.trim());
			params.put("senha", senha.trim());
			List<Usuario> usuarios = dao.executeQuery(params);
			
			if (usuarios.size() > 0) {
				
				return Response.status(200).entity(usuarios.get(0)).build();
				
			} else {
//				return Response.status(200).entity(null).build();
				return Response.status(200).entity("Usuário Inexistente").build();
			}

		} catch (Exception ex) {

			ex.printStackTrace();

			return Response.status(404).build();
		}

	}

}
