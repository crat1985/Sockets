package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("90.62.158.27",8888);
			
//			InputStream is = s.getInputStream();
//			ObjectInputStream ois = new ObjectInputStream(is);
//			String test = new String((String) ois.readObject());
//			System.out.println(test);
			System.out.print("Entrez votre nom : ");
			Scanner sc = new Scanner(System.in);
			String name = sc.next();
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF(name);
			DataInputStream in = new DataInputStream(s.getInputStream());
			System.out.println(in.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
