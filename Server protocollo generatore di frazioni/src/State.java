import java.net.InetAddress;
import java.time.Duration;
import java.util.Date;

public class State{
    private InetAddress ip;
    private Date lastMessageOn;
    private String number;
    static Duration expiryTime = Duration.ofMinutes(5);

    public State (InetAddress ip, Date lastMessageOn){
        this.ip=ip;
        this.lastMessageOn=lastMessageOn;
        this.number="1";
    }
    public State (InetAddress ip, Date lastMessageOn, String number){
        this(ip,lastMessageOn);
        this.number = number;
    }
    public String getNumber(){
        Date now = new java.util.Date(System.currentTimeMillis());
        Duration lastMessageSince = Duration.ofMillis(now.getTime()- lastMessageOn.getTime());
        if (lastMessageSince.compareTo(expiryTime)>0)
            number = "1";
        return number;
    }
    public void refreshTime(){
        this.lastMessageOn = new java.util.Date(System.currentTimeMillis());
    }
}