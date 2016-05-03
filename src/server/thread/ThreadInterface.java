package server.thread;

import java.net.Socket;

import communication.request.Request;
import protocol.ProtocolInterface;
import server.Server;

public interface ThreadInterface {
	
	public void start(ProtocolInterface protocol);
	public void run();	
	
}
