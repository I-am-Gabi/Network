package server;

import java.io.IOException;

public interface ServerInterface {
	void run() throws IOException;
	void closeConnexion() throws IOException;
}


