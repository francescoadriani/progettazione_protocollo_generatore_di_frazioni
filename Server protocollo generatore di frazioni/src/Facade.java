import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class Facade {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting server");
        ArrayList<ClientHandler> clientHandlerList = new ArrayList<ClientHandler>();
        ServerSocket serverSocket = new ServerSocket(10108);
        System.out.println("Server started at " + serverSocket.getInetAddress().getHostAddress() + " port " + serverSocket.getLocalPort());
        while (true){
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected " + clientSocket.getInetAddress().getHostName() + " port " + clientSocket.getPort());
                ClientHandler cHandler = new ClientHandler(clientSocket);
                cHandler.start();
                clientHandlerList.add(cHandler);
            }
            catch(Exception e){
            }
        }
        //serverSocket.close();
    }
}
