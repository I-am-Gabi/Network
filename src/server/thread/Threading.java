package server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 
import java.net.Socket;

import communication.request.Request;
import communication.response.Response; 
import server.protocol.ProtocolInterface;

public class Threading extends Thread {
	private Socket socket;
	private ProtocolInterface protocol;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	/**
	 * Threading's constructor.
	 * @param socket socket
	 * @param protocol communication server.protocol.
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
				Request input = (Request) in.readObject();
				Response output = protocol.handleInput(input);
				if (output.getContent().equals("BYE")) {
				    running = false;
                    socket.close();
					break;
				}
				out.writeObject(output);
			} catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
		}
		System.exit(0);
	}
}
