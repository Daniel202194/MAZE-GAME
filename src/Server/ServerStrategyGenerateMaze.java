package Server;

import java.io.*;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;


public class ServerStrategyGenerateMaze implements IServerStrategy {
    /**
     * This function get InputStream and OutputStream and with two inputs the client send sizes of maze   to the server and the server give build the maze and send it to tHe client.
     *
     * @param fromClient
     * @param ToClient

     */
    public void ServerStrategy(InputStream fromClient, OutputStream ToClient){
    try{
     ObjectInputStream FromClient=new ObjectInputStream(fromClient );
     ObjectOutputStream toClient=new ObjectOutputStream (ToClient);
     toClient.flush();
     int[] mazeSize=(int[])FromClient.readObject();

        AMazeGenerator my_maze=Configurations.MazeGenerateRead();
        Maze theMaze=my_maze.generate(mazeSize[0],mazeSize[1]);
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        MyCompressorOutputStream output=new MyCompressorOutputStream(out);
        output.write(theMaze.toByteArray());
        byte[] maze=out.toByteArray();
        toClient.writeObject(maze);

    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}







}
