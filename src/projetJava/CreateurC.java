package projetJava;

import java.sql.ResultSet;
import java.sql.Statement;

public class CreateurC {
	private int idCC;
	private String nomCC;
	private String prenomCC;
	private String username;
	private String password;
	
	public CreateurC(int id, String nom,String prenom, String username,String password) {
		this.idCC = id;
		this.nomCC = nom;
		this.prenomCC = prenom;
		this.username = username;
		this.password = password;
	}
	
	public CreateurC() {
		this.idCC = 0;
		this.nomCC = "";
		this.prenomCC = "";
		this.username = "";
		this.password = "";
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getNom() {
		return this.nomCC + " " + this.prenomCC;
	}
	
	public ResultSet bulletins() {
		return Systeme.list_bulletin(this.idCC);
	}
	
	public ResultSet abonnes() {
		return Systeme.list_Abonne(idCC);
	}
	
	public int getId() {
		return this.idCC;
	}
	
	public void ajourAbonne(String nom, String description) {
		Systeme.enregistrerBull(nom, description, idCC);
	}
	
	public ResultSet bullMessages(int id) {
		return Systeme.list_Message(id);
	}
	
	public void AjouterMessage(String objet, String contenu, int idBull) {
		Systeme.enregistrerBullMessage(objet, contenu, idBull);
	}
	
	public ResultSet bullAbonnes(int id) {
		return Systeme.list_Bull_Abonnes(id);
	}
	
	public void ajouBullAbonne(String nom, String prenom, int idBull) {
		Systeme.enregistrerBullAbonne(nom, prenom, idBull);
	}
	
	public void ajouBullAbonne(int idAbonne, int idBull) {
		Systeme.enregistrerBullAbonne(idAbonne, idBull);
	}
	
	public void modifierBulletion(int id, String nom, String description) {
		Systeme.modifierBulletin(id, nom, description);
	}
	
	public void supprimerBulletion(int id) {
		Systeme.supprimerBulletin(id);
	}
}
