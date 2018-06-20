package ws_pousada.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ws_pousada.model.dao.ProdutoDAO;
import ws_pousada.model.dao.ProdutoEntradaDAO;
import ws_pousada.model.entity.ProdutoEntrada;

public class ProdutoEntradaTest extends GenericTest<ProdutoEntrada, ProdutoEntradaDAO> {

	public ProdutoEntradaTest() {
		super(new ProdutoEntrada(), new ProdutoEntradaDAO());
	}

	@Override
	protected void buildNew() {
		this.t = new ProdutoEntrada();
	}

	@Override
	protected List<ProdutoEntrada> toNewInsert() {
		Calendar c;

		List<ProdutoEntrada> list = new ArrayList<>();
		ProdutoEntrada tt = new ProdutoEntrada();

		tt = new ProdutoEntrada();
		tt.setProduto(new ProdutoDAO().findById(1L));
		tt.setQuantidade(5);
		tt.setValorCustoUnitario(35.25);
		tt.setValorCustoTotal(tt.getValorCustoUnitario() * tt.getQuantidade());

//		tt.setDataEntrada(LocalDateTime.now());
		// c = Calendar.getInstance();
		// c.set(2018, 6, 15);
		// tt.setDataEntrada(c);
		 tt.setDataEntrada(defineData("10/10/2015 10:15:33"));
		list.add(tt);

		tt = new ProdutoEntrada();
		tt.setProduto(new ProdutoDAO().findById(1L));
		tt.setQuantidade(2);
		tt.setValorCustoUnitario(3.50);
		tt.setValorCustoTotal(tt.getValorCustoUnitario() * tt.getQuantidade());

//		tt.setDataEntrada(LocalDateTime.now());
		// c = Calendar.getInstance();
		// c.set(2018, 6, 15);
		// tt.setDataEntrada(c);
		 tt.setDataEntrada(defineData("01/06/2018 20:30:45"));
		list.add(tt);

		return list;
	}

	@Override
	protected void toNewUpdate() {

		this.t = new ProdutoEntrada();
		this.t = this.dao.findById(1L);
		this.t.setValorCustoUnitario(1000.50);
		this.t.setValorCustoTotal(t.getValorCustoUnitario() * t.getQuantidade());
	}

	 static Date defineData(String date) {
	
	 Date retorno = new Date();
	
	 SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	 try {
	 retorno = simpleDate.parse(date);
	 } catch (ParseException e) {
	 e.printStackTrace();
	 }
	
	 return retorno;
	 }

//	@Test
//	public void defineData() {
//
//		Date retorno = new Date();
//
//		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		try {
//			retorno = simpleDate.parse("01/06/2018 20:30:45");
//			System.out.println(retorno.toString());
//		} catch (ParseException e) {
//			e.printStackTrace();
//			System.out.println("Não rolou");
//		}
//
//	}
}
