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
    
    // request and response object
	private Request request; 
	private Response response;

	/**
     * Client's constructor
     * @param adresse 
     * @param port
     * @throws IOException 
     */
	public Client(String adresse, int port) throws IOException {
		this.host = adresse;
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
        writesocket(new InitializeComunication());
        readSocket();
    } 

	@Override
    public void writesocket(Object request) throws IOException, ClassNotFoundException {
        out.writeObject(request);
    }

	@Override
	public void readSocket() throws IOException, ClassNotFoundException {
		response = (Response) in.readObject();
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
			state = parser.handleState(input, state);
			if (state == null){
				continue;
			}
			switch (state) {
				case ENTER_SERVICE:
					request = new SelectService();
					request.setContent(input);
					out.writeObject(request);
					break;
				case ADD_IDEA:
					request = new AddIdea();
					request.setContent(input);
					out.writeObject(request);
					break;
				case QUIT:
					closeConnexion();
					break;
				default:
					System.err.print("ERROR");
			}
			readSocket();
		}
		closeConnexion();
	}
	
	/**
     * Write messages in the terminal interface and return the input.
     * @return the input.
     */
    private String terminal() {
        System.out.print(">>> ");
        @SuppressWarnings("resource")
        String input = new Scanner(System.in).nextLine();
        return input;
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
               writesocket(new FinalizeComunication());
               closeConnexion();
           } catch (Exception e) { 
               e.printStackTrace();
           }
      }));
   }
	
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Client client = new Client("10.212.127.246", 4000);
        client.run();
    } 
}
