package server;

import communication.request.Request;
import communication.response.Response;
import protocol.Protocol;
import server.thread.Threading;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server implements ServerInterface {
	private int port;
	private ServerSocket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Protocol protocol;
	private Vector list;
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
		System.out.println("serveur runnig !");
		boolean status_connection = true;
		while (status_connection) {
			try {
				System.out.println("constructor");
				Threading thread = new Threading(socket.accept(), this);
				System.out.println("finsih constructor");
				thread.start(protocol);
				thread.run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	private void addClient(ObjectInputStream out){
		if (out != null)
			list.add(out);
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
