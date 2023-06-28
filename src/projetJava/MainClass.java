package projetJava;

import java.sql.*;
import javax.swing.JFrame;

public class MainClass {
	public static CreateurC createur = null;
	public static ConnecterFrame connecterframe = null;
	public static InscrireFrame inscrireframe = null;
	public static HomeFrame homeframe = null;
	
	
	
	public static void main(String[] args) {
		
		Systeme.connect();
		// TODO Auto-generated method stub
		/*Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT * FROM CreateurC;";
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				int id = rs.getInt("idCC+");
				String nom = rs.getString("nomCC");
				String prenom = rs.getString("prenomCC");
				String username = rs.getString("username");
				String password = rs.getString("password");
				System.out.println("id: " + id);
				System.out.println("nom: " + nom);
				System.out.println("prenom: " + prenom);
				System.out.println("username: " + username);
				System.out.println("password: " + password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		connecterframe = new ConnecterFrame();
		connecterframe.setVisible(true);
		inscrireframe = new InscrireFrame();
		
		

	}
	
	public static void changeFrame(JFrame frameDepart, JFrame frameArrive) {
		frameDepart.dispose();
		frameArrive.setVisible(true);
	}
	
	public static void setMainCreateur(CreateurC createur) {
		MainClass.createur = createur;
	}
}
