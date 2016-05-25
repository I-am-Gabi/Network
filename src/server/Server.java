package server;

import server.protocol.Protocol;
import server.thread.Threading;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket; 

public class Server implements ServerInterface {
	private int port;
	private ServerSocket socket; 

	/**
	 * Server's constructor
	 * @throws IOException
     */
	public Server() throws IOException {
		this.port = 4000;
		this.socket = new ServerSocket(this.port); 
		shutDownServer();
	}

	@Override
	public void run() {
		System.out.println("server running... LOCAL ADDRESS: " + socket.getLocalSocketAddress());

		while (true) {
			try {
				Socket client = socket.accept();
				System.out.println("connected to : "+ client.getPort());
				new Threading(client, new Protocol()).start(); 
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
	}

	@Override
	public void close() throws IOException {
		this.socket.close(); 
	}

	/**
	 * Shutdown the server.
	 * @throws IOException
     */
	private void shutDownServer() throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println();
            System.out.println("closing connection");
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
	
	
	/**
	 * Main to run the Server.
	 * @param args
	 * @throws IOException
     */
	public static void main(String args[]) throws IOException {
		Server server = new Server();
		server.run();
	}
}
