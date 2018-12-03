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
		
		if(!inBounds(row, col) || isFull(row, col) || !isOpen(row, col)) {
			return;
		}
		
		int size = myGrid.length;
		
		Set<Integer> seen = new TreeSet<Integer>();
		Queue<Integer> q = new LinkedList<>();
		myGrid[row][col] = FULL;
		
		q.add(row*size + col);
		seen.add(row*size+col);
		int number = 0;
		
		while(q.size() != 0) {
			number = q.remove();
			row = number/size;
			col = number % size;
			
			if(!seen.contains((row-1)*size+col) && inBounds(row-1, col) && isOpen(row-1, col) && !isFull(row-1, col)) {
				myGrid[row-1][col] = FULL;
				q.add((row-1)*size+col);
				seen.add((row-1)*size+col);
			}
			
			if(!seen.contains((row+1)*size+col) && inBounds(row+1, col) && isOpen(row+1, col) && !isFull(row+1, col)) {
				myGrid[row+1][col] = FULL;
				q.add((row+1)*size+col);
				seen.add((row+1)*size+col);
			}
			
			if(!seen.contains(row*size+(col-1)) && inBounds(row, col-1) && isOpen(row, col-1) && !isFull(row, col-1)) {
				myGrid[row][col-1] = FULL;
				q.add(row*size+(col-1));
				seen.add(row*size+(col-1));
			}
			
			if(!seen.contains(row*size+(col-1)) && inBounds(row, col+1) && isOpen(row, col+1) && !isFull(row, col+1)) {
				myGrid[row][col+1] = FULL;
				q.add(row*size+(col+1));
				seen.add(row*size+(col-1));
			}
		}		
	}
}
