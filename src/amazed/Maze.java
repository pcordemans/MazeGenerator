package amazed;

import java.util.Scanner;

public class Maze {	
	private char grid [][];
	private int size;
	private static final char wall = 'X';
	private static final char unvisited = 'U';
	private int outer;
	private RandomList<Coordinate> listOfWalls = new RandomList<Coordinate>();
	
	
	public Maze(int size){
		this.size = size;
		outer = size - 1;
		grid = new char[size][size];
		initialise();
	}
	
	private void initialise(){
		for(int row = 0; row < size; row++){
			for(int col = 0; col < size; col++){
				if(row == 0) grid[row][col] = wall;
				else if(col == 0) grid[row][col] = wall;
				else if(row == outer) grid[row][col] = wall;
				else if(col == outer) grid[row][col] = wall;
				else if(col % 2 != 0 && row % 2 != 0) grid[row][col] = unvisited;
				else grid[row][col] = wall;
			}
		}
	}
	
	private void print(){
		for(int i = 0; i < size; i++){
			System.out.println(grid[i]);
		}
	}
	
	private class Coordinate{
		private final int xcor, ycor;
		
		public Coordinate(int x, int y){
			xcor = x;
			ycor = y;
		}
		
		public int X(){
			return xcor;
		}
		
		public int Y(){
			return ycor;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter an integer to determine the size of the maze");
		int size = scanner.nextInt();
		scanner.close();
		System.out.println("Generating maze...");
		Maze maze = new Maze(size * 2 + 1);
		maze.print();
	}
}
