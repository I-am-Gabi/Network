package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements ClientPattern {

	String adresse;
	int port;
	Socket socket;
	
	public Client(String adresse, int port) {
		this.adresse = adresse;
		this.port = port;
	}
	
	public void sayHello(){
		BufferedReader os = null;
		
		
		try {
			os = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void writesocket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readSocket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeConnexion() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendMessage() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void connect() {
		// TODO Auto-generated method stub
		
	}
	
	
}
