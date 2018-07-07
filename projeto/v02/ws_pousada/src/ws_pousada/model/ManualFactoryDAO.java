package ws_pousada.model;

/**
 * Classe responsável por criar e destruir conexÃµes com bancos de dados
 * 
 * O banco escolhido foi o MySQL, assim é necessário utilizar o respectivo
 * driver JDBC
 * 
 * @author Vilmar C. Pereira Júnior
 * 
 *         Disciplina de Desenvolvimento Web Senac
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManualFactoryDAO {

	private static ManualFactoryDAO instance;

	public static ManualFactoryDAO getInstance() {
		// Padrão Singleton
		if (instance == null) {
			instance = new ManualFactoryDAO();
		}

		return instance;
	}

	public Connection obterConexao() {
		// Configurações da conexão
		String nomeEsquema = "Pousada?serverTimezone=GMT-03:00";
		String enderecoBanco = "jdbc:mysql://localhost/" + nomeEsquema;
//		String enderecoBanco = "jdbc:postgresql://localhost/" + nomeEsquema;
		String usuario = "root";
//		String usuario = "postgres";
		String senha = "mysql";
//		String senha = "admin";
		String driverJDBC = "com.mysql.jdbc.Driver";
//		String driverJDBC = "org.postgresql.Driver";

		try {
			Class.forName(driverJDBC);
			Connection conexao = DriverManager.getConnection(enderecoBanco, usuario, senha);
			return conexao;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Erro ao obter conexão com o banco: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static void fecharConexao(Connection con) {
		try {
			con.close();
			System.out.println("Conexão fechada");
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}
}