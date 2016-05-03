package client;

import communication.request.*;
import communication.response.Response;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements ClientInterface {

	private String host;
	private int port;
	private Socket socket;
	private Request request;
	
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

	public void testClient() throws ClassNotFoundException {
		Socket socket = null;
		try {
			socket = new Socket("10.212.127.246", 4000);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			Response response;

			request = new InitializeComunication();
			out.writeObject(request);
			response = (Response) in.readObject();
			System.out.println("Server: " + response.getContent());

			request = new SelectService();
			request.setContent("1");
			out.writeObject(request);
			response = (Response) in.readObject();
			System.out.println("Server: " + response.getContent());

			request = new SelectService();
			request.setContent("2");
			out.writeObject(request);
			response = (Response) in.readObject();
			System.out.println("Server: " + response.getContent());

			request = new AddIdea();
			request.setContent("island");
			out.writeObject(request);
			response = (Response) in.readObject();
			System.out.println("Server: " + response.getContent());

			request = new SelectService();
			request.setContent("1");
			out.writeObject(request);
			response = (Response) in.readObject();
			System.out.println("Server: " + response.getContent());

			request = new FinalizeComunication();
			out.writeObject(request);
			response = (Response) in.readObject();
			System.out.println("Server: " + response.getContent());

			out.close();
			in.close();
			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: hostname"); } catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: hostname");
		}
	}

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		Client test = new Client("127.0.0.1", 4000);
		test.testClient();
	}
}
