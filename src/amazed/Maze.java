package amazed;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {	
	private char grid [][];
	private int size;
	private int gridSize;
	private static final char wall = 'X';
	private static final char unvisited = 'U';
	private static final char corridor = ' ';
	private int outerWallPosition;
	private RandomList<Coordinate> listOfWalls = new RandomList<Coordinate>();
	
	
	public Maze(int size){
		this.size = size;
		gridSize = size * 2 + 1;
		outerWallPosition = gridSize - 1;
		grid = new char[gridSize][gridSize];
		initialise();
		pickStartCell();
		constructMaze();
		makeEntryAndExit();
	}
	
	private void makeEntryAndExit(){
		int randomRow = randomPosition();
	}
	
	private void constructMaze(){
		while(!listOfWalls.isEmpty()){
			Coordinate position = listOfWalls.removeRandom();
			boolean newCellFound = visitCell(position.row(), position.col());
			if(newCellFound)grid[position.row()][position.col()] = corridor;			
		}
	}
	
	private boolean visitCell(int row, int col){
		if(grid[row + 1][col] == unvisited){
			grid[row + 1][col] = corridor;
			addSurroundingWalls(row + 1, col);
			return true;
		}
		if(grid[row - 1][col] == unvisited){
			grid[row - 1][col] = corridor;
			addSurroundingWalls(row - 1, col);
			return true;
		}
		if(grid[row][col + 1] == unvisited){
			grid[row][col + 1] = corridor;
			addSurroundingWalls(row, col + 1);
			return true;
		}
		if(grid[row][col - 1] == unvisited){
			grid[row][col - 1] = corridor;
			addSurroundingWalls(row, col - 1);
			return true;
		}
		return false;
	}
	
	private int randomPosition(){
		return ThreadLocalRandom.current().nextInt(0, size) * 2 + 1;
	}
	
	private void pickStartCell(){
		int row = randomPosition();
		int col = randomPosition();
		grid[row][col] = corridor;
		addSurroundingWalls(row, col);		
	}
	
	private void addSurroundingWalls(int row, int col){
		if(row > 1 && grid[row - 2][col] == unvisited && grid[row-1][col] == wall) listOfWalls.add(new Coordinate(row-1, col));
		if(row < gridSize - 2 && grid[row + 2][col] == unvisited && grid[row+1][col] == wall) listOfWalls.add(new Coordinate(row+1, col));
		if(col > 1 && grid[row][col-2] == unvisited && grid[row][col-1] == wall) listOfWalls.add(new Coordinate(row, col-1));
		if(col < gridSize - 2 && grid[row][col+2] == unvisited && grid[row][col+1] == wall) listOfWalls.add(new Coordinate(row, col+1));
	}
	
	private void initialise(){
		for(int row = 0; row < gridSize; row++){
			for(int col = 0; col < gridSize; col++){
				if(row == 0) grid[row][col] = wall;
				else if(col == 0) grid[row][col] = wall;
				else if(row == outerWallPosition) grid[row][col] = wall;
				else if(col == outerWallPosition) grid[row][col] = wall;
				else if(col % 2 != 0 && row % 2 != 0) grid[row][col] = unvisited;
				else grid[row][col] = wall;
			}
		}
	}
	
	public void print(){
		for(int i = 0; i < gridSize; i++){
			System.out.println(grid[i]);
		}
		System.out.println("");
	}
	
	private class Coordinate{
		private final int xcor, ycor;
		
		public Coordinate(int x, int y){
			xcor = x;
			ycor = y;
		}
		
		public int row(){
			return xcor;
		}
		
		public int col(){
			return ycor;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter an integer to determine the size of the maze");
		int size = scanner.nextInt();
		scanner.close();
		System.out.println("Generating maze...");
		Maze maze = new Maze(size);
		maze.print();
	}
}
