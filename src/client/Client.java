package client;

import communication.request.*;
import communication.response.Response;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements ClientInterface {
	private String host;
	private int port;
	private Socket socket;
	private Request request;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Response response;
	public Client(String adresse, int port) {
		this.host = adresse;
		this.port = port;
	}
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		Client client = new Client("127.0.0.1", 4000);
		client.run();
	}

	@Override
	public void connect() throws IOException, ClassNotFoundException {
		socket = new Socket("127.0.0.1", 4000);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());

		Response response;

		request = new InitializeComunication();
		out.writeObject(request);
		response = (Response) in.readObject();
		System.out.println("Server: " + response.getContent());
	}

	@Override
	public void sendMessage(Request request) throws IOException, ClassNotFoundException {
	}

	@Override
	public void writesocket() throws IOException {
		out.writeObject(request);
	}

	@Override
	public void readSocket() throws IOException, ClassNotFoundException {
		response = (Response) in.readObject();
		System.out.println("Server: " + response.getContent());
	}

	@Override
	public void closeConnexion() throws IOException, ClassNotFoundException {
		request = new FinalizeComunication();
		out.writeObject(request);

		out.close();
		in.close();
		socket.close();
	}

	@Override
	public void run() throws IOException, ClassNotFoundException {
		connect();

		ClientState state = ClientState.ENTER_SERVICE;
		ClientParser parser = new ClientParser();
		boolean status_connection = true;

		while (status_connection) {

			System.out.print(">>> ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();

			state = parser.handleState(input, state);
			switch (state) {
				case ENTER_SERVICE:
					request = new SelectService();
					request.setContent(input);
					out.writeObject(request);
					break;
				case ADD_IDEA:
					request = new AddIdea();
					request.setContent(input);
					out.writeObject(request);
					break;
				case QUIT:
					closeConnexion();
					break;
				default:
					System.err.print("ERROR");
			}
			readSocket();
		}

		closeConnexion();
	}
}
