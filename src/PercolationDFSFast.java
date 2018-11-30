
public class PercolationDFSFast extends PercolationDFS{

	public PercolationDFSFast(int n) {
		super(n);
	}
	
	@Override
	public void updateOnOpen(int row, int col) {
		if(row == 0 && !isFull(row, col)) {
			dfs(row, col);
		}
		
		if(inBounds(row-1, col) && isFull(row-1, col)) {
			dfs(row, col);
		}
		
		if(inBounds(row, col-1) && isFull(row, col-1)) {
			dfs(row, col);
		}
		
		if(inBounds(row, col+1) && isFull(row, col+1)) {
			dfs(row, col);
		}
		
	}
	
	
}
