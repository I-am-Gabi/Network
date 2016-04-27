package server;

import java.io.IOException;

public interface ServerInterface {
	public void run() throws IOException;
	public void closeConnexion() throws IOException;
}
