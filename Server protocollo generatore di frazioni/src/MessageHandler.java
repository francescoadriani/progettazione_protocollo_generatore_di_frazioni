
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
                clientHandler.answer("NUMBER=" + State.getState(clientHandler.getMyIp()));
            }
            else if (command.toUpperCase().equals("GET NUMBER"))
            {
                clientHandler.answer("NUMBER=" + State.getState(clientHandler.getMyIp()));
            }
            else if (command.toUpperCase().equals("GET FRACTION"))
            {
                Solver s=null;
                String number = State.getState(clientHandler.getMyIp());
                if (param.toLowerCase().equals("g"))
                    s = new Solver(number);
                else if (param.equals("c"))
                    s = new CentsSolver(number);
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
        State.saveState(number, this.clientHandler.getMyIp());
    }
}
