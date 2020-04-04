import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;

public class Connection extends Thread {
    protected Socket socket;
    protected BufferedReader fromClient;
    protected PrintStream toClient;

    public Connection(Socket socket) {
        this.socket = socket;
        try {
            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toClient = new PrintStream(socket.getOutputStream());

        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Unable to set up streams: " + ex);
                return;
            }
            e.printStackTrace();
        }
        this.start();
    }

    public void run() {
        String clientMessage;
        try {
            while (true) {
                toClient.println("Type your symbols......");
                clientMessage = fromClient.readLine();
                if (clientMessage == null || clientMessage.equals("end")) {
                    return;
                } else {
                    toClient.println(processMessage(clientMessage));
                }
            }
        } catch (IOException e) {
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public String processMessage(String message) {
        char[] arr = message.toCharArray();
        Arrays.sort(arr);
        int count = 1;
        int max = 0;
        char c = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                count++;
                if (count > max) {
                    c = arr[i];
                    max = count;
                }
            } else {
                count = 1;
            }
        }
        return ("Symbol {" + c + "} repeats " + max + " times.");
    }
}
