package br.com.tutorialjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static Connection connection;
	private static final String URL = "jdbc:h2:./data/tutorialdb";
	private static final String USER = "sa";
	private static final String PASSWORD = "java";
	
	/*Realiza o carregamento do driver do banco*/
	
	static {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Não foi possível carregar o Driver do banco H2");
		}
	}
	
	/*
	* Cria uma conexão com o banco de dados
	* */
	
	public static Connection getConnection() throws SQLException {
		try {
			if(connection == null || connection.isClosed()) {
				//O método getConnection da classe DriverManager recebe como parâmetros
				//a URL de conexão do banco de dados, o nome do	usuário e a senha do usuário
				//e retorna um objeto do tipo Connection que é a referência da conexão com o banco de dados
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível realizar a conexão	com o banco de dados");
			throw e;
		}
	return connection;
	}
}