import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class GraphicalClient extends JFrame{
	
	private static final long serialVersionUID = -3721238132023174075L;

	JTextField pseudoField;
	JTextField passwordField;
	JButton submit;
	JButton disconnect;
	JScrollPane scTextArea;
	JTextArea textArea;
	JButton sendMessage;
	public GraphicalClient() throws UnsupportedLookAndFeelException{
		super("Connexion");
		this.setLocationRelativeTo(null);
		this.setSize(720,480);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					disconnection(null);
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Au revoir !");
			}
		});
		
		JPanel contentPane = (JPanel) this.getContentPane();
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(centerPanel, BorderLayout.NORTH);
		JLabel pseudoLabel = new JLabel("Pseudo :");
		centerPanel.add(pseudoLabel);
		pseudoField = new JTextField("Entrez votre pseudo");
		centerPanel.add(pseudoField);
		JLabel passwordLabel = new JLabel("Mot de passe :");
		centerPanel.add(passwordLabel);
		passwordField = new JTextField("Entrez votre mot de passe");
		centerPanel.add(passwordField);
		submit = new JButton("Se connecter");
		submit.setEnabled(true);
		centerPanel.add(submit);
		submit.addActionListener(this::connect);
		disconnect = new JButton("Se déconnecter");
		disconnect.setEnabled(false);
		disconnect.addActionListener(e -> {
			try {
				disconnection(e);
			} catch (UnsupportedLookAndFeelException e2) {
				e2.printStackTrace();
			}
		});
		centerPanel.add(disconnect);
		textArea = new JTextArea("Message");
		textArea.setEditable(false);
		scTextArea = new JScrollPane();
		scTextArea.add(textArea);
		contentPane.add(scTextArea, BorderLayout.CENTER);
		sendMessage = new JButton("Envoyer");
		sendMessage.setEnabled(false);
		sendMessage.addActionListener(this::sendMsgFunction);
		contentPane.add(sendMessage,BorderLayout.SOUTH);
	}
	Socket s;
	public void sendMsgFunction(ActionEvent e) {
		try {
			ObjectOutputStream out1 = new ObjectOutputStream(s.getOutputStream());
			out1.writeObject(textArea.getText());
			textArea.setText(null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public boolean connect(ActionEvent e) {
		try {
			s = new Socket("90.62.158.27",8888);
			ArrayList<String> infos = new ArrayList<>();
			String pseudo = pseudoField.getText().replace(" ", "");
			String password = passwordField.getText().replace(" ", "");
			infos.add(pseudo.toLowerCase());
			infos.add(password);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(infos);
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			boolean response = (boolean) in.readObject();
			if(response) {
				System.out.println("Connexion réussie !");
				submit.setEnabled(false);
				disconnect.setEnabled(true);
				textArea.setEditable(true);
				sendMessage.setEnabled(true);
				System.out.println("Bienvenue sur ce chat !");
//				String msg = pseudo+" s'est connecté !";
//				ObjectOutputStream ois = new ObjectOutputStream(s.getOutputStream());
//				ois.writeObject(msg);
			} else {
				System.out.println("Informations de connexion incorrectes.");
			}
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return rootPaneCheckingEnabled;
	}
	
	public void disconnection(ActionEvent e) throws UnsupportedLookAndFeelException {
		try {
			s.close();
			dispose();
			if(e!=null) {
				main(null);
			}
		} catch (NullPointerException e4) {
			dispose();
			if(e!=null) {
				main(null);
			}
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		GraphicalClient window = new GraphicalClient();
		window.setVisible(true);
	}

}
