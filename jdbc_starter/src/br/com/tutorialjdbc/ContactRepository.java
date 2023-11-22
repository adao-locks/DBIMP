package br.com.tutorialjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
	
	private static final String COL_ID = "id";
	private static final String COL_NAME = "name";
	private static final String COL_EMAIL = "email";
	private static final String COL_PHONE = "phone";
	
	public List<Contact> findAll(){
		List<Contact> records = new ArrayList<>();
		String sql = "select * from contacts";
		
		try {
			Connection db = Database.getConnection();
			PreparedStatement preparedStatement = db.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				records.add(fromResultSet(resultSet));
			}
			resultSet.close();
			preparedStatement.close();
			db.close();
		}catch(SQLException e) {
			throw new RuntimeException("Não foi possível executar a operação no banco de dados");
		}
		return records;
	}

	private Contact fromResultSet(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt(COL_ID);
		String name = resultSet.getString(COL_NAME);
		String email = resultSet.getString(COL_EMAIL);
		String phone = resultSet.getString(COL_PHONE);
		
		return new Contact(id,name,email,phone);
	}
}
