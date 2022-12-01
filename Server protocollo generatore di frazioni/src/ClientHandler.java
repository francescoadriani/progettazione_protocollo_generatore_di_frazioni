import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket client;
    MessageHandler messageHandler;
    public ClientHandler(Socket client){
        this.client=client;
        this.messageHandler = new MessageHandler(this);
    }

    @Override
    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            while (true)
            {
                char[] buf = new char[512];
                int readChars = in.read(buf);
                String message = new String(buf,0,readChars);
                message = message.replace("\n", "");
                message = message.replace("\r", "");
                messageHandler.handle(message);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public InetAddress getMyIp(){
        return client.getInetAddress();
    }

    public void answer(String message){
        if (this.client.isConnected())
        {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println(message);
                //this.client.getOutputStream().write((message + "\r").getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
