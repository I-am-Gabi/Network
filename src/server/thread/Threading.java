package server.thread;

import communication.request.Request;
import protocol.Protocol;
import protocol.ProtocolInterface;

public class Threading extends Thread implements ThreadInterface {

	Protocol protocol; 
	
	
	@Override
	public void run() {
		super.run();
	}


	@Override
	public void start(ProtocolInterface protocol, Request input) {
		//protocol.handleInput(output);
		protocol.handleInput(input);
		start();
	}

}
