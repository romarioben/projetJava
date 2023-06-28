package projetJava;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InscrireFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nomtextField;
	private JTextField prenomtextField;
	private JTextField usernametextField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscrireFrame frame = new InscrireFrame();
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
	public InscrireFrame() {
		setResizable(false);
		setTitle("Newsletter||Inscrire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inscription");
		lblNewLabel.setFont(new Font("L M Mono10", Font.BOLD, 23));
		lblNewLabel.setBounds(211, 12, 155, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("nom:");
		lblNewLabel_1.setFont(new Font("Nimbus Sans Narrow", Font.BOLD, 18));
		lblNewLabel_1.setBounds(140, 62, 70, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom:");
		lblNewLabel_1_1.setFont(new Font("Nimbus Sans Narrow", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(119, 111, 70, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("username:");
		lblNewLabel_1_2.setFont(new Font("Nimbus Sans Narrow", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(95, 168, 94, 24);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("password1:");
		lblNewLabel_1_3.setFont(new Font("Nimbus Sans Narrow", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(95, 223, 94, 24);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("password2:");
		lblNewLabel_1_3_1.setFont(new Font("Nimbus Sans Narrow", Font.BOLD, 18));
		lblNewLabel_1_3_1.setBounds(95, 279, 94, 24);
		contentPane.add(lblNewLabel_1_3_1);
		
		nomtextField = new JTextField();
		nomtextField.setBounds(228, 48, 182, 33);
		contentPane.add(nomtextField);
		nomtextField.setColumns(10);
		
		prenomtextField = new JTextField();
		prenomtextField.setBounds(228, 98, 182, 33);
		contentPane.add(prenomtextField);
		prenomtextField.setColumns(10);
		
		usernametextField = new JTextField();
		usernametextField.setBounds(228, 157, 182, 35);
		contentPane.add(usernametextField);
		usernametextField.setColumns(10);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(228, 214, 182, 33);
		contentPane.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(228, 272, 182, 33);
		contentPane.add(passwordField2);
		
		JLabel lblInscriptionValide = new JLabel("");
		lblInscriptionValide.setForeground(Color.RED);
		lblInscriptionValide.setFont(new Font("Roboto Condensed Light", Font.BOLD, 15));
		lblInscriptionValide.setBounds(200, 323, 368, 15);
		contentPane.add(lblInscriptionValide);
		
		JLabel lblRetouraLa = new JLabel("se connecter");
		lblRetouraLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRetouraLa.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRetouraLa.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				MainClass.changeFrame(MainClass.inscrireframe, MainClass.connecterframe);

			}
		});
		lblRetouraLa.setBounds(200, 384, 129, 15);
		contentPane.add(lblRetouraLa);
		
		JButton btnSinscrire = new JButton("S\"inscrire");
		btnSinscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = nomtextField.getText();
				String prenom = prenomtextField.getText();
				String username = usernametextField.getText();
				String password1 = passwordField1.getText();
				String password2 = passwordField2.getText();
				CreateurC createur = Systeme.getCreateurByUsername(username);
				if (nom.equals("") || prenom.equals("") || username.equals("") 
						|| password1.equals("") || password2.equals("")) {
					lblInscriptionValide.setText("Aucun des champs ne doit rester vide");
				}
				else {
					if (!password1.equals(password2)) {
						lblInscriptionValide.setText("Les mots de passe ne sont pas conformes");
					}
					else if(!(createur == null)) {
						lblInscriptionValide.setText("Cet username existe déjà");
					}
					else{
						lblInscriptionValide.setForeground(Color.GREEN);
						lblInscriptionValide.setText("Inscription réussie");
						Systeme.enregistrerC(nom, prenom, username, password2);
						CreateurC creat = Systeme.getCreateurByUsername(username);
						MainClass.createur = creat;
						MainClass.homeframe = new HomeFrame();
						MainClass.changeFrame(MainClass.inscrireframe, MainClass.homeframe);
						
					}
				}
			}
		});
		btnSinscrire.setBounds(95, 356, 315, 25);
		contentPane.add(btnSinscrire);
	}

}
