package Client;

import java.net.InetAddress;
import java.net.Socket;
/**
 * this class represents the clients.
 */
public class Client {
    private InetAddress ip;
    private int port;
    private IClientStrategy strategy;
   /**
     * 
     * @param SIP
     * @param SPort
     * @param CStrategy
     */
    public Client(InetAddress SIP,int SPort,IClientStrategy CStrategy){
        this.ip=SIP;
        this.port=SPort;
        this.strategy=CStrategy;
    }
    /**
     * in charge of connection with the server by the input and output streams.
     */
    public void communicateWithServer(){
        try {
            Socket server = new Socket(ip,port);
            strategy.clientStrategy(server.getInputStream(),server.getOutputStream());
            server.close();
        }
        catch (Exception exp){
            exp.printStackTrace();
        }

    }
}
