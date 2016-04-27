package server;

import java.io.IOException;

public interface ServerInterface {
	/**
	 *
	 * @throws IOException
     */
	void run() throws IOException;

	/**
	 *
	 * @throws IOException
     */
	void closeConnexion() throws IOException;
}


