package client;

import communication.request.*;
import communication.response.Response;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements ClientInterface {
    
    // host to connection
    private String host;
    // port to connection
    private int port;
    
    // client socket
    private Socket socket; 
    
    // output and input stream
    private ObjectOutputStream out;
    private ObjectInputStream in;

	/**
     * Client's constructor
     * @param address
     * @param port
     * @throws IOException 
     */
	public Client(String address, int port) throws IOException {
		this.host = address;
		this.port = port;
		shutDownServer();
	}

	@Override
	public void connect() throws IOException, ClassNotFoundException {
		socket = new Socket(this.host, this.port);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());

		handShacke();
    }  

    /**
     * Method to make the handshake with the Serve. 
     * It will send a HELLO to confirm the communication.
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private void handShacke() throws ClassNotFoundException, IOException {
        writesocket(new InitializeCommunication());
        readSocket();
    } 

	@Override
    public void writesocket(Object request) throws IOException, ClassNotFoundException {
        out.writeObject(request);
    }

	@Override
	public void readSocket() throws IOException, ClassNotFoundException {
		Response response = (Response) in.readObject();
		System.out.println("Server: " + response.getContent());
	}

	@Override
	public void closeConnexion() throws IOException, ClassNotFoundException {
		out.close();
		in.close();
		socket.close();
	}

	@Override
	public void run() throws IOException, ClassNotFoundException {
		connect();

		ClientState state = ClientState.ENTER_SERVICE;
		ClientParser parser = new ClientParser();
		boolean status_connection = true;

		while (status_connection) {
			String input = terminal();

			if ("BYE".equalsIgnoreCase(input))
				state = ClientState.QUIT;

			assert state != null;
			switch (state) {
				case ENTER_SERVICE:
					Request request = new SelectService();
					request.setContent(input);
					out.writeObject(request);
					break;
				case ADD_IDEA:
					request = new AddIdea();
					request.setContent(input);
					out.writeObject(request);
					break;
				case QUIT:
					request = new FinalizeCommunication();
					out.writeObject(request);
					status_connection = false;
					closeConnexion();
					break;
				default:
					System.err.print("ERROR");
			}

			if (!status_connection)
				break;

			state = parser.handleState(input, state);
			if (state == null)
				continue;

			readSocket();
		}
	}
	
	/**
     * Write messages in the terminal interface and return the input.
     * @return the input.
     */
    private String terminal() {
        System.out.print(">>> ");
		return new Scanner(System.in).nextLine();
    }
    
   /**
    * 
    * @throws IOException
    */
   private void shutDownServer() throws IOException {
       Runtime.getRuntime().addShutdownHook(new Thread(() -> {
           try {
               Thread.sleep(200);
               System.out.println();
               System.out.println("closing connection");
			   if (!socket.isClosed())
				   writesocket(new FinalizeCommunication());
               closeConnexion();
           } catch (Exception e) { 
               e.printStackTrace();
           }
      }));
   }
	
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Client client = new Client("localhost", 4000);
        client.run();
    } 
}
