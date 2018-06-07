package ws_pousada.model.dao;

import java.util.List;

import ws_pousada.model.entity.Servico;

public class ServicoDAO extends GenericDAO<Servico, Long>{
	
	
	public void listarServicosFiltrado() {
		try {
		List<Servico> lista = (List<Servico>)super.executeQuery("FROM " + this.manipulada + " " + "WHERE " + "id=? AND nome=?", 1L, "Café da manhã");
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
