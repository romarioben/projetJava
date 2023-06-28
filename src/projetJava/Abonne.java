package projetJava;

public class Abonne {
	private int id;
	private String nom;
	private String prenom;
	
	public Abonne(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public int getId() {
		return this.id;
	}
}
