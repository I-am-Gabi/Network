package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import communication.request.Request;
import communication.response.Response;

import org.json.*;
public class ClientChaine{

	private BufferedReader is;
	private PrintWriter os;
    private String host;
    // port to connection
    private int port;
    private JSONObject output;
    // client socket
    private Socket socket; 
	
    public void requestLIST(){

    }
    
    public void requestListMore(){
    	System.out.println("rentré : titre/mail/technologie/nom +:+ valeur");
    	String input = terminal();
    	String[] listId = input.split(":");
    	try {
			output.put(listId[0], listId[1]);
			os.println(output.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    public void add(){
		JSONObject id = new JSONObject();
		String input;
		try{
			output.put("request", "ADD");
			System.out.println("entre titre :");
			input = terminal();
			id.put("titre",input);
			System.out.println("entre nom :");
			input = terminal();
			id.put("nom",input);
			System.out.println("entre mail :");
			input = terminal();
			id.put("mail",input);
			System.out.println("entre description :");
			input = terminal();
			id.put("description",input);
			System.out.println("entre technologies :");
			input = terminal();
			id.put("technologies",input);
			output.put("idee", id);
			os.println(output.toString());
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void affic(){
    	try {
			System.out.println(is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public ClientChaine(String add, int port){
    	host = add;
    	this.port = port;
    	output = new JSONObject();
    	try {
			socket = new Socket(add,  port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private String terminal() {
        System.out.print(">>> ");
        @SuppressWarnings("resource")
        String input = new Scanner(System.in).nextLine();
        return input;
    }
    
    @SuppressWarnings("deprecation")
	public void run(){
    	try {
			os = new PrintWriter(socket.getOutputStream(), true);
        	is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	boolean connection = true;
    	while (connection){
    		System.out.println("entré : ");
    		output = new JSONObject();
    		String input = terminal();
    		//if (input.equalsIgnoreCase(anotherString))
    		
    		if (input.equalsIgnoreCase("LIST")){
    			try {
					output.put("request", "LIST");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		}
    		if (input.equalsIgnoreCase("ListMore")){
    			System.out.println("listMore");
    			requestListMore();
    		}
    		
    		if (input.equalsIgnoreCase("add")){
    			add();
    		}
    		
    		if (input.equalsIgnoreCase("bye")){
    			connection = false;
    		}
    		affic();
    	}
    }
    //10.212.119.224
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ClientChaine cli = new ClientChaine("10.212.119.224",2009 );
        cli.run();
        
    } 
	
}
