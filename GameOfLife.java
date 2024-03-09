/** 
 *  Game of Life.
 *  Usage: "java GameOfLife fileName"
 *  The file represents the initial board.
 *  The file format is described in the homework document.
 */

public class GameOfLife {

	public static void main(String[] args) {
		String fileName = args[0];
		
		//// Uncomment the test that you want to execute, and re-compile.
		//// (Run one test at a time).
		////test1(fileName);
		////test2(fileName, i ,j);
		////test3(fileName ,Ngen);
		 play(fileName);
	}
	
	// Reads the data file and prints the initial board.
	private static void test1(String fileName) {
		int[][] board = read(fileName);
		print(board);
	}
		
	private static void test2(String fileName,int i, int j) {
		int[][] board = read(fileName);
		System.out.println();
		System.out.println("the cell is in [" + i + "][" + j + "] = " + board[i][j]);
		System.out.println("and the living cells in his neighborhood is: " + count( board, i, j));
		System.out.println ("the new value is: " + cellValue(board, i, j));
	}
		
	
	private static void test3(String fileName, int Ngen) {
		int[][] board = read(fileName);
		for (int gen = 0; gen < Ngen; gen++) {
			System.out.println("Generation " + gen + ":");
			print(board);
			board = evolve(board);
		}
	}
		
	
	public static void play(String fileName) {
		int[][] board = read(fileName);
		while (true) {
			show1(board);
			board = evolve(board);
		}
	}
	
	
	public static int[][] read(String fileName) {
		In in = new In(fileName); 
		int rows = Integer.parseInt(in.readLine());
		int cols = Integer.parseInt(in.readLine());
		int[][] board = new int[rows + 2][cols + 2];
		int i = 1;
		while (i < rows) {
			String strLine = in.readLine();
			if (strLine.length() > 0) {
				for (int j = 0; j < strLine.length(); j++) {
					if (strLine.charAt(j) == 'x') {
						board [i][j+1] = 1;
					}
				}
			}
			i++;
		}
		return board;
		}


	
	public static int[][] evolve(int[][] board) {
		int [][] newBoard = new int [board.length][board[0].length];
		for (int i = 1; i < newBoard.length - 1; i ++) {
			for (int j = 1; j < newBoard[i].length-1; j++){
				newBoard[i][j] = cellValue(board, i, j);
			}
		}
		return newBoard;
	}

	
	public static int cellValue(int[][] board, int i, int j) {
		int count = count (board, i, j);
		int cellValue = board[i][j];
		if (cellValue == 1) {
			if (count != 2 && count != 3) {
				cellValue = 0;
				}

		} else {
			if (count == 3) {
				cellValue = 1;
			}
		}
				
		return cellValue;
	}
	
	
	public static int count(int[][] board, int i, int j) {
		int count = 0;
		for (int x = i-1; x < i + 2; x++ ) {
			for (int y = j - 1; y < j + 2; y++) {
				count += board[x][y];
			}	
		}
		count -= board[i][j];
		return count;
	}
	
	
    public static void print(int[][] arr) {
		for (int i = 1; i < arr.length-1; i++) {
			for (int j = 1; j < arr[i].length-1; j++) {
				System.out.printf("%3s", arr[i][j]);
			}
			System.out.println();
		}
	}
	
		
    
	public static void show(int[][] board) {
		StdDraw.setCanvasSize(900, 900);
		int rows = board.length;
		int cols = board[0].length;
		StdDraw.setXscale(0, cols);
		StdDraw.setYscale(0, rows);

		
		StdDraw.enableDoubleBuffering();
		
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int color = 255 * (1 - board[i][j]);
				StdDraw.setPenColor(color, color, color);
				StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
		StdDraw.pause(100); 
	}


	public static void show1 (int[][] board) {
		StdDraw.setCanvasSize(10000, 10000);
		int rows = board.length;
		int cols = board[0].length;
		StdDraw.setXscale(0, cols);
		StdDraw.setYscale(0, rows);
		StdDraw.enableDoubleBuffering();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int color = 255 * (1 - board[i][j]);
				StdDraw.setPenColor(color, color, color);
				StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
		StdDraw.pause(100); 
	}

}


