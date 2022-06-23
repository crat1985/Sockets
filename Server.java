import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			HashMap<String, String> infos = new HashMap<>();
			infos.put("ric21000", "00000000!aA");
			while(true) {
				System.out.println("Attente de connexion...");
				Socket client = ss.accept();
				System.out.println("Connexion établie !");
			
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
//				System.out.println("Nouvelle connexion de la part de "+ois.readObject()+" !");
				ArrayList<String> infosAsked = (ArrayList<String>) ois.readObject();
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				String askedPseudo = infosAsked.get(0).toLowerCase();
				String askedPassword = infosAsked.get(1);
				if((askedPassword==null)||(askedPassword.trim()=="")) {
					oos.writeObject(false);
				}
				if(infos.get(infosAsked.get(0)) != null) {
					if(infos.get(askedPseudo).equals(askedPassword)) {
						oos.writeObject(true);
						while(client.isConnected()) {
							ObjectInputStream out = new ObjectInputStream(client.getInputStream());
						 	System.out.println(askedPseudo+": "+(String) out.readObject());
						}
					} else {
						oos.writeObject(false);
					}
				} else {
					oos.writeObject(false);
				}
			}
		} catch (EOFException | StreamCorruptedException | SocketException | StackOverflowError e) {
			main(null);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
