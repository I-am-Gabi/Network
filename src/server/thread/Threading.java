package server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import communication.request.Request;
import communication.response.Response;
import protocol.Protocol;
import protocol.ProtocolInterface;
import server.Server;

public class Threading extends Thread implements ThreadInterface {
	Socket socket;
	ProtocolInterface protocol;
	Request input;
	Response output;
	ObjectInputStream in;
	ObjectOutputStream out;
	Server server;
	

	public Threading(Socket socket, Server server) {
		super();
		this.server = server;
		this.socket = socket;
		
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
/**
 * Start the thread !
 * Initialize the component needed by the protocol 
 * to improve the object analyze.
 * 
 * */
		
	@Override
	public void start(ProtocolInterface protocol) {
		System.out.println("start thread client on port : "+ socket.getPort());
		this.socket = socket;
		this.protocol = protocol;
		
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("je suis dans le serveur! ");
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.start();
		
	}
/**
 * 
 * 
 * */
@Override
public void run() {
	
	while (true){
		try {
			input = (Request) in.readObject();
			output = protocol.handleInput(input);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		break;
	}
	
	
	super.run();
}



}
