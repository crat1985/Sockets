

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("90.62.158.27",8888);
			
//			InputStream is = s.getInputStream();
//			ObjectInputStream ois = new ObjectInputStream(is);
//			String test = new String((String) ois.readObject());
//			System.out.println(test);
			ArrayList<String> infos = new ArrayList<>();
			System.out.print("Pseudo : ");
			Scanner sc = new Scanner(System.in);
			String pseudo = sc.next();
			String password = "";
			System.out.print("Mot de passe : ");
			sc = new Scanner(System.in);
			password = sc.next();
			sc.close();
			infos.add(pseudo.toLowerCase());
			infos.add(password);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(infos);
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			System.out.println((boolean) in.readObject() ? "Connexion réussie !" : "Informations incorrectes");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
