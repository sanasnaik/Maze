//Author: Sana Naik
import java.util.ArrayList;

public class Maze {
    public static boolean isPath(int[][] maze, int r, int c) { //checks if the coordinate in the maze is a 0 (valid spot to traverse)
        if (r >= 1 && r < 9 && c >= 1 && c <= 11) {
            return maze[r][c] == 0;
        }
        return false;
    }

    public static boolean pathFinder(int[][] maze, int r, int c, ArrayList<String> solutions) { //solves the maze - DFS. 
        if  (maze[r][c] == 9) {
            solutions.add(r + ", " + c);
            return true;
        }

        if (isPath(maze, r, c)) {

            maze[r][c] = 2; //mark 2 if visited

            //check up
            boolean found = pathFinder(maze, r - 1, c, solutions);

            //check right
            if (!found) {
                found = pathFinder(maze, r, c + 1, solutions);
            }

            //check down
            if (!found) {
                found = pathFinder(maze, r + 1, c, solutions);
            }

            //check left
            if (!found) {
                found = pathFinder(maze, r, c - 1, solutions);
            }
            
            if(found) {
                solutions.add(r + ", " + c);
            }
            return found;
        }
        return false;
    }

    public static void printMaze(int[][] maze) { 
        for (int[] row : maze) {
            System.out.println();
            for (int box : row) {
                System.out.print(" " + box);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {

        ArrayList<String> solutions = new ArrayList<String>();
        int [][] maze = { //from Stack Overflow
            {1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,1,0,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,1,0,1,1,1,0,1},
            {1,0,0,0,1,1,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,0,0,1},
            {1,0,1,0,1,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,9,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1}
            }; //where (1,1) is the start and 9 is the target at (11,8)

        printMaze(maze);

        if(pathFinder(maze, 1, 1, solutions)) {
            System.out.println("Maze solved.");
            for (int i = solutions.size() - 1; i >= 0; i--) {
                System.out.println(solutions.get(i));
            }
        }

        else {
            System.out.println("No solution.");
        }
    }
}
