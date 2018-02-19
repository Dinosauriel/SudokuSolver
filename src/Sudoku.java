import java.util.Arrays;

public class Sudoku {
	int size = 9;
	SudokuField[][] grid;
	
	public void setValue(int v, int row, int column) throws IllegalArgumentException {
		if (column < 0 || column > size - 1 || row < 0 || row > size - 1) {
			throw new IllegalArgumentException();
		}
		
		if (v < 0 || v > 9) {
			throw new IllegalArgumentException();
		}
		
		//update field value
		grid[row][column].value = v;
		
		
		//update row and columns
		for (int i = 0; i < size; ++i) {
			grid[row][i].invalidValues[v] = true;
			grid[i][column].invalidValues[v] = true;
		}
		
		//update block value
		int blockRow = (row / 3) * 3;
		int blockColumn = (column / 3) * 3;
		
		for (int i = blockRow; i < blockRow + 3; ++i) {
			for (int j = blockColumn; j < blockColumn + 3; ++j) {
				grid[i][j].invalidValues[v] = true;
			}
		}
	}
		
	public void print() {
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				System.out.print(grid[i][j].value + " ");
			}
			
			System.out.println();
		}
	}
	
	public Sudoku() {
		grid = new SudokuField[size][size];
		
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				grid[i][j] = new SudokuField();
			}
		}
	}
	
	public Sudoku(Sudoku s) {
		grid = new SudokuField[size][size];

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				grid[i][j] = new SudokuField();
				
				SudokuField sField = s.grid[i][j];
				
				grid[i][j].value = sField.value;
				grid[i][j].invalidValues = Arrays.copyOf(sField.invalidValues, sField.invalidValues.length);
			}
		}
	}
}