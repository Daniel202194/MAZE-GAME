



package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * This class is the server class that get the requests from the client and activate them

 */


public class Server {
    private int port;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private int interval;

    public Server(int port,int interval, IServerStrategy serverStrategy) {
        this.port = port;
        this.serverStrategy = serverStrategy;
        this.stop =false;
        this.interval = interval;
    }
    public void start(){
        new Thread(this::runServer).start();
    }
    public void runServer(){
        try{
            ServerSocket serverSocket=new ServerSocket(port);
            serverSocket.setSoTimeout(this.interval);

            ExecutorService executorService= Executors.newFixedThreadPool(Configurations.readNumOfThreads());
         while(!stop) {
             try {
                 Socket clientSocket = serverSocket.accept();
                 InputStream inFromClient=clientSocket.getInputStream();
                 OutputStream outToClient=clientSocket.getOutputStream();
                 executorService.execute(new Runnable() {@Override public void run() { setServerStrategy(inFromClient,outToClient,clientSocket); }});

             }catch (SocketTimeoutException e) {

                 System.out.println("Socket Timeout - No clients pending!");
             }
         }
         executorService.shutdown();
         executorService.awaitTermination(5, TimeUnit.MINUTES);
         serverSocket.close();
        } catch (IOException | InterruptedException e) {

            e.printStackTrace();
        }
    }


    private void setServerStrategy(InputStream in,OutputStream out,Socket client){
        try {
            this.serverStrategy.ServerStrategy(in,out);
            client.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        stop=true;
    }

}
