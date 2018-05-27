package ws_pousada.controller;

import javax.ws.rs.Path;

import com.google.gson.Gson;

import ws_pousada.model.entity.Cliente;

@Path("/cliente")
public class ClienteServiceController extends GenericServiceController<Cliente> {
	
	private Gson gson = IntroService.gson;
	
	

}
