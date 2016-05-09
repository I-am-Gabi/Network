package client;

import java.io.IOException;

public interface ClientInterface {
    /**
     * Initialize the connection.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void connect() throws IOException, ClassNotFoundException; 
    
    /**
     * Read the socket data
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readSocket() throws IOException, ClassNotFoundException;
    
    /**
     * Write the socket data
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void writesocket(Object request) throws IOException, ClassNotFoundException;
    
    /**
     * Close the connection.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void closeConnexion() throws IOException, ClassNotFoundException;
    
    /**
     * Run the client.
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public void run() throws IOException, ClassNotFoundException; 
}
