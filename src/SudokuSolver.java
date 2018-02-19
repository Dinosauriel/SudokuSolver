import java.util.*;

public class SudokuSolver {

	
	public static void main(String[] args) {

		Sudoku sudoku = new Sudoku();
		
		sudoku.setValue(2, 0, 0);
		sudoku.setValue(9, 0, 6);
		sudoku.setValue(1, 0, 7);
		sudoku.setValue(9, 1, 0);
		sudoku.setValue(6, 1, 1);
		sudoku.setValue(5, 1, 2);
		sudoku.setValue(2, 1, 4);
		sudoku.setValue(3, 1, 5);
		sudoku.setValue(7, 1, 7);
		sudoku.setValue(8, 2, 1);
		sudoku.setValue(3, 2, 6);
		sudoku.setValue(3, 3, 0);
		sudoku.setValue(5, 3, 1);
		sudoku.setValue(6, 3, 3);
		sudoku.setValue(1, 3, 4);
		sudoku.setValue(1, 4, 0);
		sudoku.setValue(3, 4, 3);
		sudoku.setValue(4, 4, 5);
		sudoku.setValue(8, 4, 8);
		sudoku.setValue(5, 5, 4);
		sudoku.setValue(2, 5, 5);
		sudoku.setValue(3, 5, 7);
		sudoku.setValue(1, 5, 8);
		sudoku.setValue(9, 6, 2);
		sudoku.setValue(5, 6, 7);
		sudoku.setValue(7, 7, 1);
		sudoku.setValue(9, 7, 3);
		sudoku.setValue(4, 7, 4);
		sudoku.setValue(6, 7, 6);
		sudoku.setValue(8, 7, 7);
		sudoku.setValue(3, 7, 8);
		sudoku.setValue(1, 8, 1);
		sudoku.setValue(6, 8, 2);
		sudoku.setValue(2, 8, 8);
		
		sudoku.print();
		Sudoku solved = solve(sudoku);
		System.out.println();
		solved.print();
	}
	
	
	public static Sudoku solve(Sudoku sudoku) throws IllegalArgumentException {
		
		Deque<Sudoku> scenarios = new ArrayDeque<>();
		scenarios.push(sudoku);
		
		whileloop:
		while (!scenarios.isEmpty()) {
			
			Sudoku current = scenarios.pop();
			
			current.print();
			System.out.println();
			
			int bestFieldRow = 0;
			int bestFieldColumn = 0;
			int bestFieldValidOptionsCount = 9;
			
			int filledOutFields = 0;
			
			for (int i = 0; i < current.size; ++i) {
				for (int j = 0; j < current.size; ++j) {
					SudokuField field = current.grid[i][j];
					
					if (field.value != 0) {
						++filledOutFields;
												
						if (filledOutFields == current.size * current.size) {
							return current;
						}
						
						continue;
					}
					
					Set<Integer> options = field.possibleValues();
					
					if (options.size() == 0) {
						continue whileloop;
					}
					
					
					if (options.size() < bestFieldValidOptionsCount) {
						bestFieldRow = i;
						bestFieldColumn = j;
						bestFieldValidOptionsCount = options.size();
					}
				}
			}
			
			Set<Integer> bestFieldOptions = current.grid[bestFieldRow][bestFieldColumn].possibleValues();
			
			Iterator<Integer> i = bestFieldOptions.iterator();
			while (i.hasNext()) {
				Sudoku newSudoku = new Sudoku(current);
				newSudoku.setValue(i.next(), bestFieldRow, bestFieldColumn);
				scenarios.push(newSudoku);
			}
		}
		
		throw new IllegalArgumentException("unsolvable");
	}

}
