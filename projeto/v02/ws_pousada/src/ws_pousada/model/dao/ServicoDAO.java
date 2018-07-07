package ws_pousada.model.dao;

import java.util.List;

import ws_pousada.model.entity.Servico;

public class ServicoDAO extends GenericDAO<Servico, Long>{
	
	
	public void listarServicosFiltrado() {
		Object[] params = {1L, "Café"};
		try {
		List<Servico> lista = (List<Servico>)super.executeQuery("FROM " + this.manipulada + " " + "WHERE " + "id=? AND nome=?", params);
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
