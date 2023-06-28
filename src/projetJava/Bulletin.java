package projetJava;

public class Bulletin {
	private int id;
	private String nomBulletin;
	private String description;
	
	public Bulletin(int id, String nom, String description) {
		this.id = id;
		this.nomBulletin = nom;
		this.description = description;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nomBulletin;
	}
	
	public String getDescription() {
		return this.description;
	}
}
