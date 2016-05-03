package server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import communication.request.Request;
import communication.response.Response;
import protocol.Protocol;
import protocol.ProtocolInterface;
import server.Server;

public class Threading extends Thread implements ThreadInterface {
	private Socket socket;
	private ProtocolInterface protocol;
	private Request input;
	private Response output;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Server server;
	

	public Threading(Socket socket, Server server, ProtocolInterface protocol) {
		super();
		this.protocol = protocol;
		this.server = server;
		this.socket = socket;	
		
	}
	/**
	 * Start the thread !
	 * Initialize the component needed by the protocol
	 * to improve the object analyze.
	 * */
	@Override
	public void start(ProtocolInterface protocol) {
		System.out.println("start thread client on port : " + socket.getLocalPort());
		this.protocol = protocol;
		super.start();
		
	}

	/**
	 *
	 *
	 * */
	@Override
	public void run() {
		System.out.println("run the thread !");
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				input = (Request) in.readObject();
				System.out.print(input.getCommand() + " " + input.getContent() + "\n");
				output = protocol.handleInput(input);
				out.writeObject(output);
				if (output.getContent().equalsIgnoreCase("BYE")){
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	super.run();
	}
}
