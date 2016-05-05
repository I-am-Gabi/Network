package server;

import java.io.IOException;

public interface ServerInterface {
	
    /**
     * Method to run the Server.
     * @throws IOException
     */
    void run() throws IOException;

    /**
     * Method to close the Server.
     * @throws IOException
     */
    void close() throws IOException;
}


