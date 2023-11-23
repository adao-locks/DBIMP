package br.com.tutorialjdbc;

public class insertTest {
	
	public static void main(String[] args) {
		
		ContactRepository repository = new ContactRepository();
		
		Contact contact = repository.insert("Ana", "ana@test.com.br", "999990909");
		System.out.println("O contato inserido foi: " + contact);
	}
}
