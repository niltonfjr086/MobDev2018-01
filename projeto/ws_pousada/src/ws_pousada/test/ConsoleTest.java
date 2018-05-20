package ws_pousada.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ws_pousada.model.FactoryDAO;

public class ConsoleTest {

	@Test
	public void toConsole() {
		System.out.println("TESTE");
		
		
		FactoryDAO.sessionInstance();
		
		assertEquals(0, 0);
	}

}
