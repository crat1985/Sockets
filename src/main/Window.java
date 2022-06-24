package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Window extends JFrame{
	private static final long serialVersionUID = -3592540922963019935L;

	public Window() {
		super("Programme");
		this.setLocationRelativeTo(null);
		this.setSize(720,480);
		
		JPanel contentPane = (JPanel) this.getContentPane();
		JPanel textLabels = new JPanel(new GridLayout(10,1));
		contentPane.add(textLabels,BorderLayout.CENTER);
		JLabel welcomeButton = new JLabel("Bienvenue dans ce programme !",JLabel.CENTER);
		textLabels.add(welcomeButton);
		JButton playJeuDeRoles = new JButton("Jouer au jeu de rôles");
		playJeuDeRoles.setHorizontalAlignment(JButton.CENTER);
		playJeuDeRoles.setBackground(Color.BLUE);
		playJeuDeRoles.setForeground(Color.WHITE);
		textLabels.add(playJeuDeRoles);
		textLabels.setBackground(Color.BLUE);
	}
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Window window = new Window();
		window.setVisible(true);
	}
}
