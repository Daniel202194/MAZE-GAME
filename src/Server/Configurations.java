package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.*;
import java.util.Properties;

/**
 * This class get is properties class when the client can to choose the properties that connected to the maze.
 *
 *

 */


public class Configurations {
    public static void main(String[] args) {
        try (OutputStream output = new FileOutputStream("resources/config.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("threads", "5");
            prop.setProperty("searchAlgorithm", "BestFirstSearch");
            prop.setProperty("generator","MyMazeGenerator");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    /**
     * This function read the property the client chose and return this choose.
     *
     @return AMazeGenerator

     */

    public static AMazeGenerator MazeGenerateRead(){
        AMazeGenerator returnGenerator=new MyMazeGenerator();
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            String generatorType=prop.getProperty("generator");
            if(generatorType.equals("EmptyMazeGenerator"))
                returnGenerator=new EmptyMazeGenerator();
            else if(generatorType.equals("SimpleMazeGenerator"))
                returnGenerator=new SimpleMazeGenerator();
            else
                returnGenerator=new MyMazeGenerator();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return returnGenerator;
    }

    /**
     * This function read the property the client chose and return this choose.
     *
     @return int

     */

    public static int readNumOfThreads(){
            int returnProp=5;
            try (InputStream input = new FileInputStream("resources/config.properties")) {

                Properties prop = new Properties();

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                returnProp=Integer.valueOf(prop.getProperty("threads"));



            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return returnProp;
        }

    /**
     * This function read the property the client chose and return this choose.
     *
     @return ASearchingAlgorithm

     */

    public static ASearchingAlgorithm SolvingAlgorithmRead(){
        ASearchingAlgorithm returnAlgorithm = new DepthFirstSearch();
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            String algorithmType=prop.getProperty("searchAlgorithm");
            if(algorithmType.equals("BestFirstSearch"))
                returnAlgorithm=new BestFirstSearch();
            else if(algorithmType.equals("BreadthFirstSearch"))
                returnAlgorithm=new BreadthFirstSearch();
            else
                returnAlgorithm=new BestFirstSearch();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return returnAlgorithm;
    }





    }







