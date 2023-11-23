package br.com.tutorialjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public Contact insert(String name, String email, String phone) {
		String sql = "insert into contacts(name, email, phone) values (?,?,?)";
		Contact contact = null;
		try {
			Connection db = Database.getConnection();
			PreparedStatement preparedStatement = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, phone);
			
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				contact = new Contact(id, name, email, phone);
			}
			resultSet.close();
			preparedStatement.close();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Não foi possível inserir o registro!");
		}
		return contact;
	}
	
	public void update(Contact contact) {
		String sql = "update contacts set name = ?, email = ?, phone = ? where id = ?";
		try {
			Connection db = Database.getConnection();
			PreparedStatement preparedStatement = db.prepareStatement(sql);
			preparedStatement.setString(1, contact.getName());
			preparedStatement.setString(2, contact.getEmail());
			preparedStatement.setString(3, contact.getPhone());
			preparedStatement.setInt(4, contact.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Não foi possível atualizar o registro!");
		}
	}
	
	public Contact findById(Integer id) {
		Contact contact = null;
		String sql = "select * from contacts where id = ?";
		try {
			Connection db = Database.getConnection();
			PreparedStatement preparedStatement = db.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				contact = fromResultSet(resultSet);
			}
			resultSet.close();
			preparedStatement.close();
			db.close();
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível executar a operação no banco de dados!");
		}
		return contact;
	}
	
	public void delete(Integer id) {
		String sql = "delete from contacts where id = ?";
		
		try {
			Connection db = Database.getConnection();
			PreparedStatement preparedStatement = db.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Não foi possível remover o registro!");
		}
	}
	
	public List<Contact> findByName(String name){
		List<Contact> records = new ArrayList<Contact>();
		String sql = "select * from contacts where name like ?";
		try {
			Connection db = Database.getConnection();
			PreparedStatement preparedStatement = db.prepareStatement(sql);
			preparedStatement.setString(1, name+"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				records.add(fromResultSet(resultSet));
			}
			resultSet.close();
			preparedStatement.close();
			db.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível executar a operação no banco de dados");
		}
		return records;
		
	}
}
