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

	private String host;
	private int port;
	private Socket socket;
	
	public Client(String adresse, int port) {
		this.host = adresse;
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

	public void testClient() {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 4000);
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			os.writeBytes("HELLO\n");
			os.writeBytes("DATA\n");
			os.writeBytes("BYE\n");
			String responseLine;
			while ((responseLine = in.readLine()) != null) {
				System.out.println("Server: " + responseLine);
				if (responseLine.indexOf("Ok") != -1) {break;}} os.close();
			in.close();
			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: hostname"); } catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: hostname");
		}
	}

	public static void main(String args[]) throws IOException {
		Client test = new Client("127.0.0.1", 4000);
		test.testClient();
	}
}
