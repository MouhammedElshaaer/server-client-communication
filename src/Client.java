import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    Client(String ip, int clientPort) throws IOException {

        clientSocket = new Socket(ip, clientPort);

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String response = in.readLine();
        return response;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        String server_ip = "127.0.0.1";
        int port = 6666;
        Client client = new Client(server_ip, port);
        client.sendMessage("Hello server");
    }

}
