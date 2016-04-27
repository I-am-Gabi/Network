package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements ServerInterface {
	private int port;
	private ServerSocket socket;

	public Server() throws IOException {
		this.port = 4000;
		this.socket = new ServerSocket(this.port);
		shutDownServer();
	}

	@Override
	public void run() throws IOException {
		boolean status_connection = true;

		while (status_connection) {
			try {
				System.out.println(">>> waiting client in port " +
						socket.getLocalPort());
				Socket clientSocket = socket.accept();

				System.out.println(">>> connected to " + clientSocket.getRemoteSocketAddress());

				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				PrintStream os = new PrintStream(clientSocket.getOutputStream());

				while (status_connection) {
					String line = in.readLine();
					os.println(line);
					System.out.println(line);
					if (line.contains("BYE"))
						status_connection = false;
				}
			} catch (Exception ex) {
				closeConnexion();
				System.err.print(ex.getMessage());
			}
		}
	}

	@Override
	public void closeConnexion() throws IOException {
		this.socket.close();
	}

	private void shutDownServer() throws IOException {
		closeConnexion();
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
