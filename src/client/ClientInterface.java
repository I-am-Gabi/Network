package client;

import communication.request.Request;

import java.io.IOException;

public interface ClientInterface {
	public void connect() throws IOException, ClassNotFoundException;
	public void sendMessage(Request output) throws IOException, ClassNotFoundException;
	public void writesocket() throws IOException;
	public void readSocket() throws IOException, ClassNotFoundException;
	public void closeConnexion() throws IOException, ClassNotFoundException;
	public void run() throws IOException, ClassNotFoundException;
}
