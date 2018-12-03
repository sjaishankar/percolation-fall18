
public class PercolationUF implements IPercolate {
	
	boolean[][] myGrid;
	int myOpenCount;
	IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	public PercolationUF(int size, IUnionFind finder) {
		VTOP = size*size;
		VBOTTOM = size*size+1;
		
		finder.initialize(size*size + 2);
		myFinder = finder;
		
		myGrid = new boolean[size][size];
		myOpenCount = 0;
	}
	
	/**
	 * Returns true if and only if site (row, col) is OPEN
	 * 
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 */
	@Override
	public boolean isOpen(int row, int col) {
		if(!inBounds(row, col)) {
			throw new IndexOutOfBoundsException(String.format("(%d, %d) not in bounds", row, col));
		}
		
		return myGrid[row][col];
	}
	
	/**
	 * Returns true if and only if site (row, col) is FULL
	 * 
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 */
	public boolean isFull(int row, int col) {
		if(!inBounds(row, col)) {
			throw new IndexOutOfBoundsException(String.format("(%d, %d) not in bounds", row, col));
		}
		
		return myFinder.connected(row*myGrid.length + col, VTOP);
	}
	
	/**
	 * Returns true if the simulated percolation actually percolates. What it
	 * means to percolate could depend on the system being simulated, but
	 * returning true typically means there's a connected path from
	 * top-to-bottom.
	 * 
	 * @return true iff the simulated system percolates
	 */
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	
	/**
	 * Returns the number of distinct sites that have been opened in this
	 * simulation
	 * 
	 * @return number of open sites
	 */
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	/**
	 * Open site (row, col) if it is not already open. By convention, (0, 0)
	 * is the upper-left site
	 * 
	 * The method modifies internal state so that determining if percolation
	 * occurs could change after taking a step in the simulation.
	 * 
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 */
	public void open(int row, int col) {
		if(!inBounds(row, col)) {
			throw new IndexOutOfBoundsException(String.format("(%d, %d) not in bounds", row, col));
		}
		
		if(!myGrid[row][col]) {
			myGrid[row][col] = true;
			myOpenCount++;
			
			if(row == 0) {
				myFinder.union(row*myGrid.length + col, VTOP);
			}
			
			if(row == myGrid.length-1) {
				myFinder.union(row*myGrid.length + col, VBOTTOM);
			}
			
			if(inBounds(row-1, col) && isOpen(row-1, col) ) {
				myFinder.union(row*myGrid.length + col, (row-1)*myGrid.length + col);
			}
			
			if(inBounds(row+1, col) && isOpen(row+1, col)) {
				myFinder.union(row*myGrid.length + col, (row+1)*myGrid.length + col);
			}
			
			if(inBounds(row, col+1) && isOpen(row, col+1)) {
				myFinder.union(row*myGrid.length+col, row*myGrid.length + (col+1));
			}
			
			if(inBounds(row, col-1) && isOpen(row, col-1)) {
				myFinder.union(row*myGrid.length+col, row*myGrid.length + (col-1));
			}
		}
	}
	
	public boolean inBounds(int row, int col) {
		if(row < 0 || row > myGrid.length - 1) {
			return false;
		}
		
		if(col < 0 || col > myGrid.length - 1) {
			return false;
		}
		
		return true;
	}
}
