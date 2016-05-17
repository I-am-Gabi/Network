package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import communication.request.FinalizeComunication;

public class ClientChained implements ClientInterface {
	private BufferedReader is;
	private PrintWriter os;
    private String host;
    // port to connection
    private int port;
    private JSONObject output;
    // client socket
    private Socket socket; 
    
    public ClientChained(String host, int port){
    	this.host = host;
    	this.port = port;
    }
    
    private String terminal() {
        System.out.print(">>> ");
        @SuppressWarnings("resource")
        String input = new Scanner(System.in).nextLine();
        return input;
    }
    
	@Override
	public void connect() throws IOException, ClassNotFoundException {
		output = new JSONObject();
		socket = new Socket(this.host, this.port);
    	try {

			os = new PrintWriter(socket.getOutputStream(), true);
        	is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socket = new Socket(this.host,  this.port);
		} catch (UnknownHostException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void readSocket() throws IOException, ClassNotFoundException {
    	try {
			System.out.println(is.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void writesocket(Object request) throws IOException, ClassNotFoundException {
		os.flush();
		os.println(output.toString());
		
	}

	@Override
	public void closeConnexion() throws IOException, ClassNotFoundException {
		os.close();
		is.close();
		socket.close();
		
	}

    public void add(){
		JSONObject id = new JSONObject();
		String input;
		
			try {
				output.put("request", "ADD");
				System.out.println("entrez titre :");
				input = terminal();
				id.put("titre",input);
				System.out.println("entrez nom :");
				input = terminal();
				id.put("nom",input);
				System.out.println("entrez mail :");
				input = terminal();
				id.put("mail",input);
				System.out.println("entrez description :");
				input = terminal();
				id.put("description",input);
				System.out.println("entrez technologies :");
				input = terminal();
				id.put("technologies",input);
				output.put("idee", id);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


    }
	@Override
	public void run() throws IOException, ClassNotFoundException {
		boolean status = true;
		String input;
		while (status){
			output = new JSONObject();
			readSocket();
			input = terminal();
			if (input.equalsIgnoreCase("ADD")){
				add();
			}
			else if (input.equalsIgnoreCase("LIST")){
				try {
					output.put("request", "LIST");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			else if (input.equalsIgnoreCase("PARTICIPE")){
				
			}
			else if(input.equalsIgnoreCase("bye")){
				status = false;
				closeConnexion();
			}
			System.out.println("jecris : " +output.toString());
			writesocket(output);
		}
		
	}
	   private void shutDownServer() throws IOException {
	       Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	           try {
	               Thread.sleep(200);
	               System.out.println();
	               System.out.println("closing connection");
	               closeConnexion();
	           } catch (Exception e) { 
	               e.printStackTrace();
	           }
	      }));
	   }

	   public static void main(String args[]) throws IOException, ClassNotFoundException {
	       	ClientChained client = new ClientChained("0.0.0.0", 2009);
	       	client.connect();
	       	client.run();
	        
	    } 
}
