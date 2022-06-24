package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("Attente de connexion...");
			Socket client = ss.accept();
			System.out.println("Connexion établie !");
			
			DataInputStream ois = new DataInputStream(client.getInputStream());
			System.out.println("Nouvelle connexion de la part de "+ois.readUTF()+" !");
			
			DataOutputStream oos = new DataOutputStream(client.getOutputStream());
			oos.writeUTF("test");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
