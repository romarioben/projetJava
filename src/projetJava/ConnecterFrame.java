package projetJava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnecterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel lbl_connect_error;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnecterFrame frame = new ConnecterFrame();
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
	public ConnecterFrame() {
		setTitle("NewsLetter||Connection");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeConnecter = new JLabel("Se connecter");
		lblSeConnecter.setFont(new Font("L M Mono10", Font.BOLD, 23));
		lblSeConnecter.setBounds(233, 12, 160, 30);
		contentPane.add(lblSeConnecter);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Liberation Sans", Font.BOLD, 20));
		lblUsername.setBounds(143, 92, 118, 20);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(330, 90, 173, 30);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Liberation Sans", Font.BOLD, 20));
		lblPassword.setBounds(143, 145, 118, 20);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(330, 141, 173, 33);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("se connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				//System.out.println(username+" "+password);
				//lbl_connect_error.setText("Aucun des champs ne doit être vide");
				if (username.equals("") | password.equals("")) {
					System.out.println("OK");
					lbl_connect_error.setText("Aucun des champs ne doit être vide");
				}
				else {
					CreateurC createur = Systeme.getCreateurByUsername(username);
					if (createur == null) {
						lbl_connect_error.setText("Username invalide");
					}
					else {
						String cr_password = createur.getPassword();
						if (!cr_password.equals(password)) {
							lbl_connect_error.setText("Mot de passe invalide");
						}
						else {
							lbl_connect_error.setForeground(Color.GREEN);
							lbl_connect_error.setText("Connection réussie");
							MainClass.setMainCreateur(createur);
							MainClass.homeframe = new HomeFrame();
							MainClass.changeFrame(MainClass.connecterframe, MainClass.homeframe);}
					}
				}
			}
		});
		btnNewButton.setBounds(143, 272, 360, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Vous n\"avez pas un compte? cliquez ici");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				MainClass.changeFrame(MainClass.connecterframe, MainClass.inscrireframe);
			}
		});
		lblNewLabel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel.setForeground(Color.BLUE);
			}
		});
		lblNewLabel.setBounds(167, 323, 302, 20);
		contentPane.add(lblNewLabel);
		
		lbl_connect_error = new JLabel("");
		lbl_connect_error.setForeground(Color.RED);
		lbl_connect_error.setFont(new Font("Manjari", Font.ITALIC, 12));
		lbl_connect_error.setBounds(214, 203, 336, 30);
		contentPane.add(lbl_connect_error);
	}
}
