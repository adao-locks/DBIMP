package br.com.tutorialjdbc;

public class UpdateTest {
	
	public static void main(String[] args) {
		Contact contact = new Contact(12, "Cristian Forbes", "crisfor@test.com", "65471594");
		
		ContactRepository repository = new ContactRepository();
		repository.update(contact);
		
		Contact updated = repository.findAll().stream().filter((co) -> co.getId().equals(10)).findFirst().orElse(null);
		System.out.println(updated);
	}
}
