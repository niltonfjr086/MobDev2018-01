package ws_pousada.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import ws_pousada.model.FactoryDAO;
import ws_pousada.model.dao.ProdutoDAO;
import ws_pousada.model.entity.OLDProduto;

public class ProdutoTest {

	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private OLDProduto produto = new OLDProduto();

//	private ItemProdutoDAO itemDAO = new ItemProdutoDAO();
//	private ItemProduto item;

	@Test
	public void test() {
		
//		Session s = FactoryDAO.sessionInstance();
		this.adicionarProdutos();

		this.testFindAll();

		FactoryDAO.closeInstance();
		
	}

	private void testFindAll() {
		System.out.println("TESTE!");
		
		List<OLDProduto> produtos = produtoDAO.findAll();
//		if (produtos != null && produtos.size() <= 0) {
//			adicionarProdutos();
//			System.out.println("Adicionou Produtos");
//			produtos = produtoDAO.findAll();
//		}
//		assertNotNull(produtos);
//		
//		this.testUpdateProduto();
//		this.testUpdateItem();
//
//		System.out.println("Todos os produto cadastrados: \n" + produtos);
//
//		System.out.println("--------------------------------------------");
//		System.out.println("--------------------------------------------");
//
//		List<ItemProduto> itens = itemDAO.findAll();
//		if (itens != null && itens.size() <= 0) {
//			adicionarItens();
//			System.out.println("Adicionou Itens");
//			itens = itemDAO.findAll();
//		}
//		assertNotNull(itens);
//		
//		this.testDeleteItem();
//
//		System.out.println("Todos os itens cadastrados: \n" + itens);
//
//		System.out.println("--------------------------------------------");
//		System.out.println("--------------------------------------------");

	}

	private void adicionarProdutos() {

		produto = new OLDProduto("Milho");
		produtoDAO.save(produto);

		produto = new OLDProduto("Tapete");
		produtoDAO.save(produto);

		produto = new OLDProduto("Televisor");
		produtoDAO.save(produto);

	}

	private void testUpdateProduto() {

		OLDProduto p = produtoDAO.findById(1L);
		p.setNome("Milho Verde");
		produtoDAO.update(p);
		assertEquals("Milho Verde", produtoDAO.findById(1L).getNome());
	}

//	private void adicionarItens() {
//
//		item = new ItemProduto(produtoDAO.findById(1L), 30.00f, 500.00f, 3);
//		itemDAO.save(item);
//
//		item = new ItemProduto(produtoDAO.findById(3L), 20.00f, 350.00f, 5);
//		itemDAO.save(item);
//
//		item = new ItemProduto(produtoDAO.findById(2L), 10.00f, 188.99f, 2);
//		itemDAO.save(item);
//
//		item = new ItemProduto();
//
//	}
//
//	private void testUpdateItem() {
//
//		ItemProduto item = itemDAO.findById(1L);
//		item.setQuantidadeProdutos(8888);
//		itemDAO.update(item);
//
//		assertEquals(String.valueOf(8888), itemDAO.findById(1L).getQuantidadeProdutos().toString());
//	}
//
//	private void testDeleteItem() {
//		
//		itemDAO.delete(2L);
//		System.out.println(itemDAO.findById(2L).toString());;
//
//	}

}
