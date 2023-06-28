package projetJava;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeFrame extends JFrame {

	private JPanel contentPane;
	private JTable tableBull;
	private JTable tableAbonne;
	private JTextField BullnomtextField;
	private JTextField BulldescriptiontextField;
	private JLabel lblAjouterror;
	private JTextField bulletintextField;
	private JTable messagetable;
	private JLabel lblBullerror;
	private JTextField objettextField;
	private JTextField contenutextField;
	private JLabel lblBulletinN;
	private JLabel lblBullerror_1;
	private JTextField bulletinAtextField;
	private JTextField nomAtextField;
	private JTextField prenomAtextField;
	private JTable tableBullAbonnes;
	private JLabel lblBulletinNA;
	private JTextField nomBtextField;
	private JTextField nomBtextField1;
	private JTextField descriptionBtextField1;
	private JLabel lblBullerror_1_1;
	private JLabel lblBullerror_1_1_1;

	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		
		Systeme.connect();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame frame = new HomeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public HomeFrame() {
		setTitle("Newsletter||Accueil");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 0, 676, 426);
		contentPane.add(tabbedPane);
		
		JPanel accueil = new JPanel();
		tabbedPane.addTab("Accueil", null, accueil, null);
		accueil.setLayout(null);
		
		if (MainClass.createur == null) {
			MainClass.createur = Systeme.getCreateurByUsername("romario");
		}
		JLabel lblNewLabel = new JLabel("Bienvenu " + MainClass.createur.getNom());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Padauk Book", Font.PLAIN, 28));
		lblNewLabel.setBounds(23, 0, 636, 42);
		accueil.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Voici la liste de vos abonnés");
		lblNewLabel_1.setBounds(12, 40, 258, 21);
		accueil.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 72, 298, 295);
		accueil.add(scrollPane_1);
		
		tableAbonne = new JTable();
		scrollPane_1.setViewportView(tableAbonne);
		setAbonnetable(MainClass.createur.abonnes(), tableAbonne);
		
		JLabel lblNewLabel_6 = new JLabel("Developpée par Romario IFRI SIRI");
		lblNewLabel_6.setBounds(372, 372, 263, 15);
		accueil.add(lblNewLabel_6);
		
		JPanel list_bull = new JPanel();
		tabbedPane.addTab("Bulletins", null, list_bull, null);
		list_bull.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("La liste des bulletins");
		lblNewLabel_2.setBounds(12, 12, 281, 15);
		list_bull.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 39, 301, 318);
		list_bull.add(scrollPane);
		
		String colonnes[] = {"numéro", "nom", "description"}; 
		String data[][] = {};
		tableBull = new JTable(data, colonnes);
		scrollPane.setViewportView(tableBull);
		setBulltable(MainClass.createur.bulletins(), tableBull);
		
		JLabel lblAjouterUnBulletin = new JLabel("Ajouter un bulletin");
		lblAjouterUnBulletin.setBounds(421, 12, 171, 15);
		list_bull.add(lblAjouterUnBulletin);
		
		JLabel lblNom = new JLabel("nom");
		lblNom.setBounds(376, 50, 70, 29);
		list_bull.add(lblNom);
		
		JLabel lblNewLabel_3 = new JLabel("Description");
		lblNewLabel_3.setBounds(376, 105, 82, 36);
		list_bull.add(lblNewLabel_3);
		
		BullnomtextField = new JTextField();
		BullnomtextField.setBounds(478, 38, 156, 41);
		list_bull.add(BullnomtextField);
		BullnomtextField.setColumns(10);
		
		BulldescriptiontextField = new JTextField();
		BulldescriptiontextField.setBounds(478, 114, 162, 36);
		list_bull.add(BulldescriptiontextField);
		BulldescriptiontextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = MainClass.createur.getId();
				String nom = BullnomtextField.getText();
				String description = BulldescriptiontextField.getText();
				if (nom.equals("") || description.equals("")) {
					lblAjouterror.setText("Aucun des champs ne doit rester vide");
				}
				else {
					Bulletin bull = Systeme.getBullbyIDCC_NOM(id, nom);
					if (!(bull == null)) {
						lblAjouterror.setForeground(Color.RED);
						lblAjouterror.setText("Le bulletin existe déjà");
					}
					else {
						//System.out.println("Nous sommes dans else");
						MainClass.createur.ajourAbonne(nom, description);
						lblAjouterror.setForeground(Color.GREEN);
						lblAjouterror.setText("Bulletin ajouter avec succès");
						setBulltable(MainClass.createur.bulletins(), tableBull);
					}
				}
			}
		});
		btnNewButton.setBounds(393, 239, 239, 25);
		list_bull.add(btnNewButton);
		
		lblAjouterror = new JLabel("");
		lblAjouterror.setForeground(Color.RED);
		lblAjouterror.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouterror.setBounds(393, 178, 241, 15);
		list_bull.add(lblAjouterror);
		
		JLabel lblNewLabel_6_1 = new JLabel("Developpée par Romario IFRI SIRI");
		lblNewLabel_6_1.setBounds(369, 372, 263, 15);
		list_bull.add(lblNewLabel_6_1);
		
		JPanel message = new JPanel();
		tabbedPane.addTab("Message", null, message, null);
		message.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Les messages d\"un bulletin");
		lblNewLabel_4.setBounds(12, 12, 218, 33);
		message.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nom bulletin");
		lblNewLabel_5.setBounds(12, 39, 115, 33);
		message.add(lblNewLabel_5);
		
		bulletintextField = new JTextField();
		bulletintextField.setBounds(145, 39, 147, 33);
		message.add(bulletintextField);
		bulletintextField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Afficher");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = bulletintextField.getText();
				int idCC = MainClass.createur.getId();
				Bulletin bull = Systeme.getBullbyIDCC_NOM(idCC, nom);
				if (nom.equals("")) {
					lblBullerror.setText("Le champ ne doit être vide");
				}
				else if (bull == null) {
					lblBullerror.setText("Ce bulletin n\"existe pas");
				}
				else {
					int id = bull.getId();
					setMessagetable(MainClass.createur.bullMessages(id), messagetable);
					lblBullerror.setText("");
				}
				
			}
		});
		btnNewButton_1.setBounds(12, 122, 240, 25);
		message.add(btnNewButton_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(343, 12, 278, 306);
		message.add(scrollPane_2);
		
		messagetable = new JTable();
		scrollPane_2.setViewportView(messagetable);
		
		lblBullerror = new JLabel("");
		lblBullerror.setForeground(Color.RED);
		lblBullerror.setBounds(39, 84, 229, 15);
		message.add(lblBullerror);
		
		JLabel label = new JLabel("Ajouter un message relatif au bulletin");
		label.setBounds(12, 159, 313, 15);
		message.add(label);
		
		JLabel lblObjet = new JLabel("Objet");
		lblObjet.setBounds(12, 198, 70, 15);
		message.add(lblObjet);
		
		JLabel lblContenu = new JLabel("Contenu");
		lblContenu.setBounds(12, 237, 70, 15);
		message.add(lblContenu);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = bulletintextField.getText();
				String objet = objettextField.getText().trim();
				String contenu = contenutextField.getText().trim();
				int idCC = MainClass.createur.getId();
				Bulletin bull = Systeme.getBullbyIDCC_NOM(idCC, nom);
				if (nom.equals("") || objet.equals("") || contenu.equals("")) {
					lblBulletinN.setForeground(Color.RED);
					lblBulletinN.setText("Aucun champ ne doit être vide");
					lblBullerror.setText("Aucun champ ne doit être vide");
				}
				else if (bull == null) {
					lblBullerror.setText("Ce bulletin n\"existe pas");
					lblBulletinN.setText("Ce bulletin n\"existe pas");
				}
				else {
					int id = bull.getId();
					lblBullerror.setText("");
					MainClass.createur.AjouterMessage(objet, contenu, id);
					lblBulletinN.setForeground(Color.GREEN);
					lblBulletinN.setText("Message enregistré avec succès");
					objettextField.setText("");
					contenutextField.setText("");
					setMessagetable(MainClass.createur.bullMessages(id), messagetable);
				}
			}
		});
		btnAjouter.setBounds(76, 326, 117, 25);
		message.add(btnAjouter);
		
		objettextField = new JTextField();
		objettextField.setBounds(138, 186, 154, 31);
		message.add(objettextField);
		objettextField.setColumns(10);
		
		contenutextField = new JTextField();
		contenutextField.setBounds(138, 221, 154, 33);
		message.add(contenutextField);
		contenutextField.setColumns(10);
		
		lblBulletinN = new JLabel("");
		lblBulletinN.setForeground(Color.RED);
		lblBulletinN.setHorizontalAlignment(SwingConstants.CENTER);
		lblBulletinN.setBounds(0, 289, 292, 15);
		message.add(lblBulletinN);
		
		JLabel lblNewLabel_6_2 = new JLabel("Developpée par Romario IFRI SIRI");
		lblNewLabel_6_2.setBounds(358, 372, 263, 15);
		message.add(lblNewLabel_6_2);
		
		JPanel abonnes = new JPanel();
		tabbedPane.addTab("Abonnés", null, abonnes, null);
		abonnes.setLayout(null);
		
		JLabel lblNewLabel_4_1 = new JLabel("Les Abonnés d\"un bulletin");
		lblNewLabel_4_1.setBounds(24, 0, 218, 33);
		abonnes.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("Nom bulletin");
		lblNewLabel_5_1.setBounds(24, 27, 115, 33);
		abonnes.add(lblNewLabel_5_1);
		
		bulletinAtextField = new JTextField();
		bulletinAtextField.setColumns(10);
		bulletinAtextField.setBounds(157, 27, 147, 33);
		abonnes.add(bulletinAtextField);
		
		JButton btnNewButton_1_1 = new JButton("Afficher");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = bulletinAtextField.getText();
				int idCC = MainClass.createur.getId();
				Bulletin bull = Systeme.getBullbyIDCC_NOM(idCC, nom);
				if (nom.equals("")) {
					lblBullerror_1.setText("Le champ ne doit être vide");
				}
				else if (bull == null) {
					lblBullerror_1.setText("Ce bulletin n\"existe pas");
				}
				else {
					int id = bull.getId();
					setAbonnetable(MainClass.createur.bullAbonnes(id), tableBullAbonnes);
					lblBullerror_1.setText("");
				}
				
			}
		});
		btnNewButton_1_1.setBounds(24, 110, 240, 25);
		abonnes.add(btnNewButton_1_1);
		
		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1.setBounds(355, 0, 278, 306);
		abonnes.add(scrollPane_2_1);
		
		tableBullAbonnes = new JTable();
		scrollPane_2_1.setViewportView(tableBullAbonnes);
		
		lblBullerror_1 = new JLabel("");
		lblBullerror_1.setForeground(Color.RED);
		lblBullerror_1.setBounds(51, 72, 229, 15);
		abonnes.add(lblBullerror_1);
		
		JLabel lblAjouterUnAbonn = new JLabel("Ajouter un Abonné  au bulletin");
		lblAjouterUnAbonn.setBounds(24, 147, 313, 15);
		abonnes.add(lblAjouterUnAbonn);
		
		JLabel lblNom_1 = new JLabel("Nom");
		lblNom_1.setBounds(24, 186, 70, 15);
		abonnes.add(lblNom_1);
		
		JLabel lblPrnom = new JLabel("Prénom");
		lblPrnom.setBounds(24, 225, 70, 15);
		abonnes.add(lblPrnom);
		
		JButton btnAjouter_1 = new JButton("Ajouter");
		btnAjouter_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomBull = bulletinAtextField.getText();
				String nom = nomAtextField.getText().trim();
				String prenom = prenomAtextField.getText().trim();
				int idCC = MainClass.createur.getId();
				Bulletin bull = Systeme.getBullbyIDCC_NOM(idCC, nomBull);
				if (nomBull.equals("") || nom.equals("") || prenom.equals("")) {
					lblBulletinNA.setForeground(Color.RED);
					lblBulletinNA.setText("Aucun champ ne doit être vide");
					lblBullerror_1.setText("Aucun champ ne doit être vide");
				}
				else if (bull == null) {
					lblBullerror_1.setText("Ce bulletin n\"existe pas");
					lblBulletinNA.setText("Ce bulletin n\"existe pas");
				}
				else {
					int id = bull.getId();
					lblBullerror.setText("");
					Abonne abonne = Systeme.getAbonneBynomPrenom(nom, prenom);
					if (abonne == null) {
						MainClass.createur.ajouBullAbonne(nom, prenom, id);
					}
					else {
						int idAbonne = abonne.getId();
						MainClass.createur.ajouBullAbonne(idAbonne, id);
					}
					lblBulletinNA.setForeground(Color.GREEN);
					lblBulletinNA.setText("Message enregistré avec succès");
					nomAtextField.setText("");
					prenomAtextField.setText("");
					setAbonnetable(MainClass.createur.bullAbonnes(id), tableBullAbonnes);
					setAbonnetable(MainClass.createur.abonnes(), tableAbonne);
					
				}
			}
		});
		btnAjouter_1.setBounds(88, 314, 117, 25);
		abonnes.add(btnAjouter_1);
		
		nomAtextField = new JTextField();
		nomAtextField.setColumns(10);
		nomAtextField.setBounds(150, 174, 154, 31);
		abonnes.add(nomAtextField);
		
		prenomAtextField = new JTextField();
		prenomAtextField.setColumns(10);
		prenomAtextField.setBounds(150, 209, 154, 33);
		abonnes.add(prenomAtextField);
		
		lblBulletinNA = new JLabel("");
		lblBulletinNA.setHorizontalAlignment(SwingConstants.CENTER);
		lblBulletinNA.setForeground(Color.RED);
		lblBulletinNA.setBounds(12, 277, 292, 15);
		abonnes.add(lblBulletinNA);
		
		JLabel lblNewLabel_6_2_1 = new JLabel("Developpée par Romario IFRI SIRI");
		lblNewLabel_6_2_1.setBounds(370, 360, 263, 15);
		abonnes.add(lblNewLabel_6_2_1);
		
		JPanel modifierBulletin = new JPanel();
		tabbedPane.addTab("Modifier Bulletin", null, modifierBulletin, null);
		modifierBulletin.setLayout(null);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Un bulletin");
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setBounds(24, 12, 218, 33);
		modifierBulletin.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Nom bulletin");
		lblNewLabel_5_1_1.setBounds(24, 39, 115, 33);
		modifierBulletin.add(lblNewLabel_5_1_1);
		
		nomBtextField = new JTextField();
		nomBtextField.setColumns(10);
		nomBtextField.setBounds(157, 39, 147, 33);
		modifierBulletin.add(nomBtextField);
		
		JButton btnNewButton_1_1_1 = new JButton("Vérifier");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = nomBtextField.getText();
				int idCC = MainClass.createur.getId();
				Bulletin bull = Systeme.getBullbyIDCC_NOM(idCC, nom);
				if (nom.equals("")) {
					lblBullerror_1_1.setText("Le champ ne doit être vide");
				}
				else if (bull == null) {
					lblBullerror_1_1.setForeground(Color.RED);
					lblBullerror_1_1.setText("Ce bulletin n\"existe pas");
				}
				else {
					int id = bull.getId();
					lblBullerror_1_1.setForeground(Color.GREEN);
					lblBullerror_1_1.setText("Bulletin existe");
					nomBtextField1.setText(bull.getNom());
					descriptionBtextField1.setText(bull.getDescription());
				}
				
			}
		});
		btnNewButton_1_1_1.setBounds(24, 122, 240, 25);
		modifierBulletin.add(btnNewButton_1_1_1);
		
		lblBullerror_1_1 = new JLabel("");
		lblBullerror_1_1.setForeground(Color.RED);
		lblBullerror_1_1.setBounds(51, 84, 229, 15);
		modifierBulletin.add(lblBullerror_1_1);
		
		JLabel lblModifierOuSupprimer = new JLabel("Modifier ou supprimer un bulletin");
		lblModifierOuSupprimer.setBounds(24, 159, 313, 15);
		modifierBulletin.add(lblModifierOuSupprimer);
		
		JLabel lblNom_1_1 = new JLabel("Nom");
		lblNom_1_1.setBounds(24, 198, 70, 15);
		modifierBulletin.add(lblNom_1_1);
		
		JLabel lblPrnom_1 = new JLabel("description");
		lblPrnom_1.setBounds(24, 237, 93, 15);
		modifierBulletin.add(lblPrnom_1);
		
		JButton btnAjouter_1_1 = new JButton("Modifier");
		btnAjouter_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = nomBtextField1.getText();
				String description =  descriptionBtextField1.getText();
				String  nomB = nomBtextField.getText();
				int idCC = MainClass.createur.getId();
				Bulletin bull = Systeme.getBullbyIDCC_NOM(idCC, nomB);
				if (nomB.equals("") || nom.equals("") || description.equals("")) {
					lblBullerror_1_1_1.setText("Aucun champs ne doit être vide");
					lblBullerror_1_1.setText("Aucun champs ne doit être vide");
				}
				else if (bull == null) {
					lblBullerror_1_1.setForeground(Color.RED);
					lblBullerror_1_1.setText("Ce bulletin n\"existe pas");
				}
				else {
					int id = bull.getId();
					lblBullerror_1_1_1.setForeground(Color.GREEN);
					lblBullerror_1_1_1.setText("Modifier avec succes");
					MainClass.createur.modifierBulletion(id, nom, description);
					setBulltable(MainClass.createur.bulletins(), tableBull);
				}
			}
		});
		btnAjouter_1_1.setBounds(22, 316, 117, 25);
		modifierBulletin.add(btnAjouter_1_1);
		
		nomBtextField1 = new JTextField();
		nomBtextField1.setColumns(10);
		nomBtextField1.setBounds(150, 186, 154, 31);
		modifierBulletin.add(nomBtextField1);
		
		descriptionBtextField1 = new JTextField();
		descriptionBtextField1.setColumns(10);
		descriptionBtextField1.setBounds(150, 221, 154, 33);
		modifierBulletin.add(descriptionBtextField1);
		
		JLabel lblBulletinNA_1 = new JLabel("");
		lblBulletinNA_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBulletinNA_1.setForeground(Color.RED);
		lblBulletinNA_1.setBounds(12, 289, 292, 15);
		modifierBulletin.add(lblBulletinNA_1);
		
		JButton btnAjouter_1_1_1 = new JButton("Supprimer");
		btnAjouter_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = nomBtextField1.getText();
				String description =  descriptionBtextField1.getText();
				String  nomB = nomBtextField.getText();
				int idCC = MainClass.createur.getId();
				Bulletin bull = Systeme.getBullbyIDCC_NOM(idCC, nomB);
				if (nomB.equals("") || nom.equals("") || description.equals("")) {
					lblBullerror_1_1_1.setText("Aucun champs ne doit être vide");
					lblBullerror_1_1.setText("Aucun champs ne doit être vide");
				}
				else if (bull == null) {
					lblBullerror_1_1.setForeground(Color.RED);
					lblBullerror_1_1.setText("Ce bulletin n\"existe pas");
				}
				else {
					int id = bull.getId();
					MainClass.createur.supprimerBulletion(id);
					lblBullerror_1_1_1.setForeground(Color.GREEN);
					lblBullerror_1_1_1.setText("Supprimer avec succes");
					setBulltable(MainClass.createur.bulletins(), tableBull);
					nomBtextField.setText("");
					nomBtextField1.setText("");
					descriptionBtextField1.setText("");
				}
			}
		});
		btnAjouter_1_1_1.setBounds(186, 316, 117, 25);
		modifierBulletin.add(btnAjouter_1_1_1);
		
		JLabel lblNewLabel_6_2_1_1 = new JLabel("Developpée par Romario IFRI SIRI");
		lblNewLabel_6_2_1_1.setBounds(382, 372, 263, 15);
		modifierBulletin.add(lblNewLabel_6_2_1_1);
		
		lblBullerror_1_1_1 = new JLabel("");
		lblBullerror_1_1_1.setForeground(Color.RED);
		lblBullerror_1_1_1.setBounds(51, 272, 229, 15);
		modifierBulletin.add(lblBullerror_1_1_1);
		
	}
	
	public void setBulltable(ResultSet rs, JTable jt) {
		String colonnes[] = {"numéro", "nom", "description"}; 
		DefaultTableModel model = new DefaultTableModel(null, colonnes);
		int i = 1;
		try {
			while(rs.next()) {
				Object o[] = {
					i,
					rs.getString("nomBulletin"),
					rs.getString("description"),
				};
				i++;
				model.addRow(o);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jt.setModel(model);
	}
	
	public void setAbonnetable(ResultSet rs, JTable jt) {
		String colonnes[] = {"numéro", "nom", "prenom"}; 
		DefaultTableModel model = new DefaultTableModel(null, colonnes);
		int i = 1;
		try {
			while(rs.next()) {
				Object o[] = {
					i,
					rs.getString("nom"),
					rs.getString("prenom"),
				};
				i++;
				model.addRow(o);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jt.setModel(model);
	}
	
	public void setMessagetable(ResultSet rs, JTable jt) {
		String colonnes[] = {"numéro", "objet", "contenu"}; 
		DefaultTableModel model = new DefaultTableModel(null, colonnes);
		int i = 1;
		try { 
			while(rs.next()) {
				System.out.println("OK");
				Object o[] = {
					i,
					rs.getString("objet"),
					rs.getString("contenu"),
				};
				i++;
				model.addRow(o);
			}
				rs.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jt.setModel(model);
	}
}
