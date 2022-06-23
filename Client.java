import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
			boolean response = (boolean) in.readObject();
			if(response) {
				System.out.println("Connexion réussie !");
				System.out.println("Bienvenue sur ce chat !");
				while(s.isConnected()) {
					System.out.print("Entrez votre message : ");
					String msg = "";
					Scanner sc2 = new Scanner(System.in);
					msg = sc2.nextLine();
					ObjectOutputStream ois = new ObjectOutputStream(s.getOutputStream());
					ois.writeObject(msg);
				}
			} else {
				System.out.println("Informations de connexion incorrectes.");
				return;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
