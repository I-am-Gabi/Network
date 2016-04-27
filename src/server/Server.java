package server;

import protocol.Protocol;
import server.Request.Request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ServerInterface {
	private int port;
	private ServerSocket socket;

	public Server() throws IOException {
		this.port = 4000;
		this.socket = new ServerSocket(this.port);
		this.socket.setSoTimeout(10000);
		shutDownServer();
	}

	@Override
	public void run() {
		boolean status_connection = true;

		Protocol protocol = new Protocol();

		while (status_connection) {
			try {
				System.out.println(">>> waiting client in port " + socket.getLocalPort());
				Socket clientSocket = socket.accept();
				System.out.println(">>> connected to " + clientSocket.getRemoteSocketAddress());

				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

				String input, output;
				while (status_connection) {
					input = (String) in.readObject();
					output = protocol.handleInput(input);
					out.writeObject(output);

					if (input.equalsIgnoreCase("BYE"))
						status_connection = false;
				}
				closeConnexion();
			} catch (Exception ex) {
				System.err.print(" --- " + ex.getMessage());
			}
		}
	}

	@Override
	public void closeConnexion() throws IOException {
		this.socket.close();
	}

	private void shutDownServer() throws IOException {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println();
            System.out.println("closing connection");
        }));
	}

	public static void main(String args[]) throws IOException {
		Server server = new Server();
		server.run();
	}
}
