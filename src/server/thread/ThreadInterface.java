package server.thread;

import communication.request.Request;
import protocol.ProtocolInterface;

public interface ThreadInterface {
	public void start(ProtocolInterface protocol, Request output);
	public void run();
	
	
}
