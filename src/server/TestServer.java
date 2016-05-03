package server;

import communication.request.*;
import communication.response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @version 03/05/16.
 */
public class TestServer {
    private String host;
    private int port;
    private Socket socket;
    private Request request;

    public TestServer(String s, int i) {
    }

    public void testClient() throws ClassNotFoundException {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 4000);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Response response;

            request = new InitializeComunication();
            out.writeObject(request);
            response = (Response) in.readObject();
            System.out.println("Server: " + response.getContent());

            request = new SelectService();
            request.setContent("1");
            out.writeObject(request);
            response = (Response) in.readObject();
            System.out.println("Server: " + response.getContent());

            request = new SelectService();
            request.setContent("2");
            out.writeObject(request);
            response = (Response) in.readObject();
            System.out.println("Server: " + response.getContent());

            request = new AddIdea();
            request.setContent("island");
            out.writeObject(request);
            response = (Response) in.readObject();
            System.out.println("Server: " + response.getContent());

            request = new SelectService();
            request.setContent("1");
            out.writeObject(request);
            response = (Response) in.readObject();
            System.out.println("Server: " + response.getContent());

            request = new FinalizeComunication();
            out.writeObject(request);
            response = (Response) in.readObject();
            System.out.println("Server: " + response.getContent());

            out.close();
            in.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname"); } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        TestServer test = new TestServer("127.0.0.1", 4000);
        test.testClient();
    }
}
