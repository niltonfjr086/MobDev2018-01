package ws_pousada.test;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import ws_pousada.model.FactoryDAO;
//import ws_pousada.model.HttpConnector;
import ws_pousada.model.dao.ProdutoDAO;
import ws_pousada.model.entity.Produto;

public class ProdutoTest extends GenericTest<Produto, ProdutoDAO> {

	public ProdutoTest() {
		super(new Produto(), new ProdutoDAO());
	}

	@Override
	protected void buildNew() {
		this.t = new Produto();
	}

	@Override
	protected List<Produto> toNewInsert() {

		List<Produto> list = new ArrayList<>();
		Produto tt = new Produto();

		tt = new Produto();
		tt.setCodBarras("8080");
		tt.setDescricao("Garrafa de Vinho");
		tt.setValorUnitario(60.45);
		tt.setTipoProduto("Bebida");
		tt.setEstoqueMaximo(50);
		tt.setEstoqueMinimo(5);
		list.add(tt);

		tt = new Produto();
		tt.setCodBarras("7878");
		tt.setDescricao("Creme Dental");
		tt.setTipoProduto("Higiene Pessoal");
		tt.setValorUnitario(6.50);
		tt.setEstoqueMaximo(250);
		tt.setEstoqueMinimo(10);
		list.add(tt);

		return list;
	}

	@Override
	protected void toNewUpdate() {

		this.t = new Produto();
		this.t = this.dao.findById(1L);
		this.t.setDescricao("Garrafa Vinho Seco");
	}
}
