import java.net.InetAddress;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

public class State{
    private InetAddress ip;
    private Date lastMessageOn;
    private String number;
    static Duration expiryTime = Duration.ofMinutes(5);

    public State (InetAddress ip, Date lastMessageOn){
        this.ip=ip;
        this.lastMessageOn=lastMessageOn;
        this.number="0";
    }
    public State (InetAddress ip, Date lastMessageOn, String number){
        this(ip,lastMessageOn);
        this.number = number;
    }
    public String getNumber(){
        Date now = new java.util.Date(System.currentTimeMillis());
        Duration lastMessageSince = Duration.ofMillis(now.getTime()- lastMessageOn.getTime());
        if (lastMessageSince.compareTo(expiryTime)>0)
            number = "0";
        return number;
    }
    public void refreshTime(){
        this.lastMessageOn = new java.util.Date(System.currentTimeMillis());
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