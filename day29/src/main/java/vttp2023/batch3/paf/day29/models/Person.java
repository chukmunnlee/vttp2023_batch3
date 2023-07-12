package vttp2023.batch3.paf.day29.models;

public class Person {

	private String name;
	private String email;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }

	@Override
	public String toString() {
		return "Person[ name=%s, email=%s ]".formatted(name, email);
	}
}
