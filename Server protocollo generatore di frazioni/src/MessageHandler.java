import java.net.InetAddress;
import java.sql.Date;
import java.util.HashMap;

public class MessageHandler {
    private ClientHandler clientHandler;
    public MessageHandler(ClientHandler clientHandler){
        this.clientHandler=clientHandler;
    }

    public void handle(String message){
        try{
            String[] splitted = message.split("=");
            String command = splitted[0];
            String param="";
            if (splitted.length>1)
                param = splitted[1];
            if (command.toUpperCase().equals("SET NUMBER"))
            {
                saveState(param);
                clientHandler.answer("NUMBER=" + MessageHandler.getState(clientHandler.getMyIp()));
            }
            else if (command.toUpperCase().equals("GET NUMBER"))
            {
                clientHandler.answer("NUMBER=" + MessageHandler.getState(clientHandler.getMyIp()));
            }
            else if (command.toUpperCase().equals("GET FRACTION"))
            {
                Solver s=null;
                String number = MessageHandler.getState(clientHandler.getMyIp());
                if (param.toLowerCase().equals("g"))
                    s = new Solver(number);
                else if (param.equals("%"))
                    s = new PercentualSolver(number);
                else if (param.startsWith("/"))
                {
                    String temp = param;
                    String[] splitting = temp.split("/");
                    int denumeratorRequired = Integer.parseInt(splitting[1]);
                    s = new DenumeratorSolver(number, denumeratorRequired);
                }
                if (s!=null)
                    clientHandler.answer(s.solve().toString());
                
            }
        }
        catch(Exception e){

        }
    }

    public void saveState(String number){
        MessageHandler.saveState(number, this.clientHandler.getMyIp());
    }

    static HashMap<InetAddress, State> stateList = new HashMap<InetAddress, State>();
    public static void saveState(String number, InetAddress ip){
        if (stateList.containsKey(ip))
            stateList.remove(ip);
        Date now = new Date(System.currentTimeMillis());
        stateList.put(ip,new State(ip, now, number));
        
    }
    public static String getState(InetAddress ip){
        if (!stateList.containsKey(ip)){
            Date now = new Date(System.currentTimeMillis());
            stateList.put(ip,new State(ip, now));
        }
        return ((State)(stateList.get(ip))).getNumber();
    }
}
