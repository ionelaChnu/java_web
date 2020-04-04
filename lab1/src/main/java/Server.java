
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(1500);
        } catch (IOException e) {
            System.out.println("Start server error: " + e);
        }
        System.out.println("Server starts...");
        this.start();
    }

    public void run() {
        try {
            while (true) {
                Socket client = this.serverSocket.accept();
                System.out.println("Connection accepted from " + client.getInetAddress().getHostAddress());
                Connection connection = new Connection(client);
//                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
//                String message = fromClient.readLine();
//                System.out.println(message);
//                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
//                out.writeBytes("Hui " + message);
//                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new Server();
    }
}
