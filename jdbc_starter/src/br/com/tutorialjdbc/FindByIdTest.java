package br.com.tutorialjdbc;

public class FindByIdTest {
	
	public static void main(String[] args) {
		
		ContactRepository repository = new ContactRepository();
		
		System.out.println(repository.findById(2));
		System.out.println(repository.findById(5));
		System.out.println(repository.findById(10));
		
	}
}
