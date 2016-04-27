package server;

import protocol.Protocol;

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

				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintStream os = new PrintStream(clientSocket.getOutputStream());

				String input, output;
				while (status_connection) {
					input = in.readLine();
					output = protocol.handleInput(input);
					os.println(output);

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
