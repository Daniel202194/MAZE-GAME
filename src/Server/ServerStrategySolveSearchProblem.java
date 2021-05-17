package Server;

        import java.io.InputStream;
        import java.io.OutputStream;
        import java.io.*;
        import algorithms.mazeGenerators.* ;
        import algorithms.search.*;
        import java.io.File;
        import java.util.Arrays;
        import java.util.HashMap;


public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private HashMap<String, String> solvedMazes;
    private static int numberOfMazes = 0;

    @Override

    /**
     * This function get InputStream and OutputStream and with two inputs the client send maze to server and the server give him a solution
     *
     * @param fromClient
     * @param toClient

     */
    public void ServerStrategy(InputStream fromClient, OutputStream toClient) {
        solvedMazes = new HashMap<>();
        ISearchingAlgorithm solver = Configurations.SolvingAlgorithmRead();
        try {
            ObjectInputStream FromClient = new ObjectInputStream(fromClient);
            ObjectOutputStream ToClient = new ObjectOutputStream(toClient);
            toClient.flush();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            Maze maze = (Maze) FromClient.readObject();
            byte[] mazeByteArray = maze.toByteArray();
            String mazeStringKey = Arrays.toString(mazeByteArray);
            Solution solution;
            if (solvedMazes.containsKey(mazeStringKey)) {
                String solutionFile = solvedMazes.get(mazeStringKey);
                solution = readSolution(tempDirectoryPath, solutionFile);
            } else {
                numberOfMazes++;
                String mazeSolutionFileName = "mazeSolution" + numberOfMazes + ".txt";
				solvedMazes.put(mazeStringKey,mazeSolutionFileName);
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                solution = solver.solve(searchableMaze);
                writeSolutionToFile(tempDirectoryPath, mazeSolutionFileName, solution);

            }
            ToClient.writeObject(solution);
            ToClient.flush();
            ToClient.close();
            FromClient.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function get path filename and solution and write the solution to the file.
     *
     * @param tempDir
     * @param solutionFileName
     * @param solution

     */



    private void writeSolutionToFile(String tempDir, String solutionFileName, Solution solution) throws IOException {
        File newFileSol = new File(tempDir, solutionFileName);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(newFileSol));
        out.writeObject(solution);

    }
    /**
     * This function get path and file with and read the content of the file and return it.
     * if row and col not in the maze limit it returns  -1
     * otherwise return 1(wall) or 0(path)
     *
     * @param tempDir
     * @param solutionFile
     * @return Solution
     */
    private Solution readSolution(String tempDir, String solutionFile) throws IOException, ClassNotFoundException {
        Solution sol;
        File solutionFileRead = new File(tempDir, solutionFile);
        FileInputStream inFile = new FileInputStream(solutionFileRead);
        ObjectInputStream inPut = new ObjectInputStream(inFile);
        sol = (Solution) inPut.readObject();
        inPut.close();
        return sol;
    }

}