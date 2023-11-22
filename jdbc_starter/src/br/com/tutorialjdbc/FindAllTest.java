package br.com.tutorialjdbc;

import java.util.List;

public class FindAllTest {
	
	public static void main(String[] args) {
		
		ContactRepository repository = new ContactRepository();
		List<Contact> contacts = repository.findAll();
		System.out.println("Os contatos cadastrados são:");
		for(Contact contact: contacts) {
			System.out.println(contact);
		}
	}
}
