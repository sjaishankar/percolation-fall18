import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class PercolationBFS extends PercolationDFSFast {

	public PercolationBFS(int n) {
		super(n);
	}
	
	@Override
	public void dfs(int row, int col) {
		
		if(!inBounds(row, col)) {
			return;
		}
		
		int size = myGrid.length;
		
		Queue<Integer> q = new LinkedList<>();
		myGrid[row][col] = FULL;
		
		q.add(row*size + col);
		
		while(q.size() != 0) {
			int number = q.remove();
			row = number/size;
			col = number % size;
			
			if(inBounds(row-1, col) && isOpen(row-1, col) && !isFull(row-1, col)) {
				myGrid[row-1][col] = FULL;
				q.add((row-1)*size+col);
			}
			
			if(inBounds(row+1, col) && isOpen(row+1, col) && !isFull(row+1, col)) {
				myGrid[row+1][col] = FULL;
				q.add((row+1)*size+col);
			}
			
			if(inBounds(row, col-1) && isOpen(row, col-1) && !isFull(row, col-1)) {
				myGrid[row][col-1] = FULL;
				q.add(row*size+(col-1));
			}
			
			if(inBounds(row, col+1) && isOpen(row, col+1) && !isFull(row, col+1)) {
				myGrid[row][col+1] = FULL;
				q.add(row*size+(col+1));
			}
		}		
	}
}
