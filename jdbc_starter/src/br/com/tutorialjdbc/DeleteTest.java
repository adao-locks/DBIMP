package br.com.tutorialjdbc;

import java.util.List;

public class DeleteTest {

	public static void main(String[] args) {
		ContactRepository repository = new ContactRepository();
		
		List<Contact> contacts = repository.findAll();
		System.out.println("Os contatos cadastrados são:");
		for (Contact contact : contacts) {
			System.out.println(contact);
		}
		System.out.println("Removendo o contato ");
		repository.delete(8);
		contacts = repository.findAll();
		System.out.println("Agora os contatos cadastrados são:");
		for (Contact contact : contacts) {
			System.out.println(contact);
		}

	}
	
}