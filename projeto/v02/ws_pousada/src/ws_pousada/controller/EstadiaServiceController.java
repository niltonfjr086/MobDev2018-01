/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_pousada.controller;

import java.util.Enumeration;
import java.util.HashMap;
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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ws_pousada.model.dao.EstadiaDAO;
import ws_pousada.model.entity.Estadia;

/**
 *
 * @author Augusto
 */
@Path("/estadia")
public class EstadiaServiceController extends GenericServiceController<Estadia, EstadiaDAO>{
	
	@Context
	private HttpServletRequest request;

    public EstadiaServiceController() {
		super(new Estadia(), new EstadiaDAO());
    }
    
    
	@GET
	@Path("/listByFilter")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listByFilter(@Context HttpServletRequest request) {

		try {
			
			Enumeration<String> keys = request.getParameterNames();
			HashMap<String, String> map = new HashMap<>();

			while( keys.hasMoreElements() ) {
				
				String element = keys.nextElement();
				map.put(element.toString(), request.getParameter(element));
				
			}
			
			String jsonInString = "";
			List<Estadia> list = dao.listarPorFiltro(map, false);
			map.clear();
			try {
				
				mapper.registerModule(new JavaTimeModule());
				mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
				jsonInString = this.mapper.writeValueAsString(list);
				mapper = new ObjectMapper();
				
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
