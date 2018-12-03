
public class PercolationDFSFast extends PercolationDFS{

	public PercolationDFSFast(int n) {
		super(n);
	}
	
	@Override
	public void updateOnOpen(int row, int col) {
		int count = 0;
		
		
		if(row == 0 && !isFull(row, col)) {
			count++;
		}
		
		if(inBounds(row-1, col) && isFull(row-1, col)) {
			count++;
		}
		
		if(inBounds(row+1, col) && isFull(row+1, col)) {
			count++;
		}
		
		if(inBounds(row, col-1) && isFull(row, col-1)) {
			count++;
		}
		
		if(inBounds(row, col+1) && isFull(row, col+1)) {
			count++;
		}
		
		if(count > 0) {
			dfs(row, col);
		}
		
	}
	
	
}
