package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	// Conexao com o banco de dados
	public static Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/portal_realnews?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=America/Sao_Paulo&user=USUARIO&password=SENHA");
	}
}
