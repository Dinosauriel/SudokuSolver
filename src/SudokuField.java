
import java.util.*;

public class SudokuField {
	int value = 0;
	boolean[] invalidValues = new boolean[10];
	
	public Set<Integer> possibleValues() {
		Set<Integer> p = new HashSet<>();
				
		for (int i = 1; i < invalidValues.length; ++i) {
			if (!invalidValues[i]) {
				p.add(i);
			}
		}
		
		return p;
	}
}
