package server;

import protocol.Protocol;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ServerInterface {
	private int port;
	private ServerSocket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Protocol protocol;
	/**
	 *
	 * @throws IOException
     */
	public Server() throws IOException {
		this.port = 4000;
		this.socket = new ServerSocket(this.port);
		this.socket.setSoTimeout(10000);
		protocol = new Protocol();
		shutDownServer();
	}

	@Override
	public void run() {
		boolean status_connection = true;
		while (status_connection) {
			try {
				System.out.println(">>> waiting client in port " + socket.getLocalPort());
				Socket clientSocket = socket.accept();
				System.out.println(">>> connected to " + clientSocket.getRemoteSocketAddress());

				in = new ObjectInputStream(clientSocket.getInputStream());
				out = new ObjectOutputStream(clientSocket.getOutputStream());

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
		this.in.close();
		this.out.close();
	}

	/**
	 *
	 * @throws IOException
     */
	private void shutDownServer() throws IOException {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println();
            System.out.println("closing connection");
        }));
	}

	/**
	 *
	 * @param args
	 * @throws IOException
     */
	public static void main(String args[]) throws IOException {
		Server server = new Server();
		server.run();
	}
}
