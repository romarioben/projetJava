package projetJava;

import java.sql.*;

public class Systeme {
	
	public static Connection con = null;
	
	
	public static void connect() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:projetJavaDatabase.db");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("Base de données ouvert avec succes");
	}
	
	public static CreateurC getCreateurByUsername(String username) {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT * FROM CreateurC WHERE username LIKE \"" + username + "\";";
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (!rs.next()) {
				System.out.println("Pas de données retrouvées");
				CreateurC createur = null;
				return createur;
			}
			else {
				int id = rs.getInt("idCC");
				String nom = rs.getString("nomCC");
				String prenom = rs.getString("prenomCC");
				String usernameC = rs.getString("username");
				String password = rs.getString("password");
				CreateurC createur = new CreateurC(id, nom, prenom, usernameC, password);
				return createur;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void enregistrerC( String nom, String prenom, String username, String password) {
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "INSERT INTO CreateurC (nomCC, prenomCC, username, password)" + 
		"VALUES (\"" + nom + "\",\""+ prenom + "\", \"" + username + "\", \"" + password + "\")";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Créateur de contenu enregistré avec succès");
		
	}
	
	public static ResultSet list_bulletin(int id_createur) {
		ResultSet rs = null;
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT DISTINCT * FROM Bulletin WHERE Bulletin.idCC = " + id_createur + ";";
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(rs);
		return rs;
		
	}
	
	public static ResultSet list_Abonne(int id_createur) {
		ResultSet rs = null;
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT DISTINCT Abonne.nom, Abonne.prenom FROM  Bulletin, Bull_Abonne, Abonne WHERE Bulletin.id = Bull_Abonne.idBull " + 
		"AND Bull_Abonne.idAbonne = Abonne.id AND Bulletin.idCC =" + id_createur +";";
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(rs);
		return rs;
	}
	
	public static Bulletin getBullbyIDCC_NOM(int idcc, String nom){
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT * FROM Bulletin WHERE idCC= "+ idcc + " AND nomBulletin LIKE \"" + nom + "\"";
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (!rs.next()) {
				System.out.println("Pas de données retrouvées");
				return null;
			}
			else {
				int id = rs.getInt("id");
				String nomb = rs.getString("nomBulletin");
				String description = rs.getString("description");
				Bulletin bull = new Bulletin(id, nomb, description);
				return bull;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void enregistrerBull( String nom, String description, int idcc) {
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "INSERT INTO Bulletin (nomBulletin, description, idCC) VALUES (\"" + nom + "\",\""+ description + "\",\"" + idcc + "\")";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Bulletin enregistré avec succès");
		
	}
	
	public static ResultSet list_Message(int id_bull ) {
		ResultSet rs = null;
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT  * FROM Message WHERE idBull = "+ id_bull ;
		try {
			rs = st.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rs + "resultat message");
		return rs;
	}
	
	public static void enregistrerBullMessage( String objet, String contenu, int idBull) {
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "INSERT INTO Message (objet, contenu, idBull) VALUES (\"" + objet + "\",\""+ contenu + "\"," + idBull +")";
		//System.out.println(sql);
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Message enregistré avec succès");
		
	}
	
	public static ResultSet list_Bull_Abonnes(int id_bull ) {
		ResultSet rs = null;
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT  * FROM Abonne, Bull_Abonne  WHERE Bull_Abonne.idAbonne = Abonne.id AND idBull = "+ id_bull ;
		try {
			rs = st.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(rs + " resultat Bull_Abonnes");
		return rs;
	}
	
	public static void enregistrerBullAbonne( String nom, String prenom, int idBull) {
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "INSERT INTO Abonne (nom, prenom) VALUES (\"" + nom + "\",\""+ prenom + "\")";
		
		
		//System.out.println(sql);
		try {
			st.executeUpdate(sql);
			int idAbonne = getAbonneBynomPrenom(nom, prenom).getId();
			String sql1 = "INSERT INTO Bull_Abonne (idBull, idAbonne) VALUES (" + idBull + ","+ idAbonne + ")";
			st.executeUpdate(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Message enregistré avec succès");
	}
	
	public static void enregistrerBullAbonne( int idAbonne, int idBull) {
		Statement st = null;
		ResultSet rs = null;
		//System.out.println("Bien dans la fonction (int, int)");
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql1 = "INSERT INTO Bull_Abonne (idBull, idAbonne) VALUES (" + idBull + ","+ idAbonne + ")";
		String sql = "SELECT * FROM Bull_Abonne WHERE idBull = " + idBull + " AND idAbonne = " + idAbonne;
		//System.out.println(sql);
		try {
			rs = st.executeQuery(sql);
			//System.out.println("Première requete exécutée avec succès");
			String  idB = rs.getString("idBull");
			String  idA = rs.getString("idAbonne");
			System.out.println(idB + " " + idA);
			if(!(rs.next())) {
				System.out.println("Je suis bien dans if");
				st.executeUpdate(sql1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Abonné enregistré avec succès");
	}
	
	public static Abonne getAbonneBynomPrenom(String nom, String prenom) {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT * FROM Abonne WHERE nom LIKE \"" + nom + "\" AND prenom LIKE \"" + prenom +"\";";
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (!rs.next()) {
				System.out.println("Abonné n\"existe pas");
				System.out.println("Pas de données retrouvées");
				Abonne abonne = null;
				return abonne;
			}
			else {
				System.out.println("Abonné existe bien");
				int id = rs.getInt("id");
				String nomA = rs.getString("nom");
				String prenomA = rs.getString("prenom");
				Abonne abonne = new Abonne(id, nom, prenom);
				return abonne;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void modifierBulletin(int id, String nom, String description) {
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "UPDATE Bulletin SET nomBulletin = \"" + nom + "\", description = \"" + description + "\" WHERE id = " + id +";";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void supprimerBulletin(int id) {
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "DELETE FROM Bulletin WHERE id=" + id;
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
