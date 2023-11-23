package br.com.tutorialjdbc;

import java.util.List;

public class FindByNameTest {

	public static void main(String[] args) {
		
		ContactRepository repository = new ContactRepository();
		List<Contact> contacts = repository.findByName("M");
		System.out.println("Os contatos que começam com 'M' são: ");
		for (Contact contact : contacts) {
			System.out.println(contact);
		}
	}
}
