package server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 
import java.net.Socket;

import communication.request.Request;
import communication.response.Response; 
import protocol.ProtocolInterface; 

public class Threading extends Thread {
	private Socket socket;
	private ProtocolInterface protocol;
	private Request input;
	private Response output;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	/**
	 * Threading's constructor.
	 * @param socket
	 * @param protocol communication protocol.
	 */
	public Threading(Socket socket, ProtocolInterface protocol) {
		super();
		this.protocol = protocol; 
		this.socket = socket;	
	}

	@Override
	public void run() {
        System.out.println("start thread client on port : " + socket.getLocalPort());
        boolean running = true;

        try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		while (running) {
			try {
				input = (Request) in.readObject();
				System.out.print(input.getCommand() + " " + input.getContent() + "\n");
				output = protocol.handleInput(input);
				out.writeObject(output);
				if (output.getContent().equals("BYE")) {
				    running = false;
                    socket.close();
					break;
				}
			} catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
		}
	}
}
