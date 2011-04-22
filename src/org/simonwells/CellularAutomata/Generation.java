package org.simonwells.CellularAutomata;

import java.util.Enumeration;
import java.util.Vector;

import org.simonwells.CellularAutomata.Cell;
import org.simonwells.CellularAutomata.Pattern;

/**
* Encapsulates a generation of Cells
*
* @author Simon Wells <siwells@gmail.com>
* @version 1.0
* @since 1.6
*/
public class Generation
{

	/** 
  * Given a parent generation caluclate the child generation.
  *
  * @param pattern describing the state of the parent generation.
  */
	public void breed(Pattern pattern)
	{
		boolean left=false, middle=false, right=false;
		Cell leftCell, middleCell, rightCell;
		Vector<Cell> childGeneration = new Vector<Cell>();
		
		// Iterate through the current generation. Use a for loop to do so as for each cell we are also looking at
		// the cells that are both ahead of and behind the current cell during each iteration. Iterators would make
		// for more complicated code
		for(int i=0; i < this.current.size(); ++i)
		{
			middleCell = (Cell) this.current.get(i);
			middle = middleCell.isAlive();
			
			// Determine state of left neighbour cell. Get the previous cell and check its state
			// Boundary condition: If generation wrapsaround and we are at the start of the generation then
			// get the state of the rightmost cell. If not wrapping around then set cell to dead (e.g. doesn't exist).			
			if(i == 0)
			{
				if(wraparound)
				{
					leftCell = (Cell) this.current.get(current.size()-1);
					left = leftCell.isAlive();	
				}
				else
					left = false;
			}
			else
			{
				leftCell = (Cell) this.current.get(i-1);
				left = leftCell.isAlive();
			}
			
			// Determine state of right neighbour cell. Get the next cell and check its state
			// Boundary condition: If generation wrapsaround and we are at the end of the generation then
			// get the state of the leftmost cell. If not wrapping around then set cell to dead (e.g. doesn't exist).
			if(i < this.current.size()-1)
			{
				rightCell = (Cell) this.current.get(i+1);
				right = rightCell.isAlive();
			}
			else
			{
				if(wraparound)
				{
					rightCell = (Cell) this.current.get(0);
					right = rightCell.isAlive();
				}
				else
					right = false;
			}
			
			// Once we have the state of cell plus left and right neighbours, create a cell cluster object and
			// get the approprate child from that cluster according to the pattern for this CA.
			CellCluster tmpCluster = new CellCluster(left, middle, right);
			Cell childCell = tmpCluster.getChild(pattern);
			
			childGeneration.add(i, childCell);
		}
		this.current = childGeneration;
	}
	
	public Enumeration getGeneration()
	{
		return this.current.elements();
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public boolean isWrappedAround()
	{
		return this.wraparound;
	}
	
	public Generation(String gen, int width, boolean wrapped)
	{
		this.wraparound = wrapped;
		this.width = width;	
		this.current = new Vector<Cell>();
		for (int idx = 0; idx < this.width; ++idx)
		{
			char tmpChar = gen.charAt(idx);
			if(tmpChar == '1')
				this.current.add(new Cell(true));
			else	// default to false if the seed is mangled
				this.current.add(new Cell(false));
		}
	}
	
	public Generation(Generation seed)
	{
		this.wraparound = seed.isWrappedAround();
		this.width = seed.getWidth();
		Enumeration seeds = seed.getGeneration();
		this.current = new Vector<Cell>();
		while(seeds.hasMoreElements())
		{
			Cell tmp = (Cell) seeds.nextElement();

			this.current.add( new Cell( tmp.isAlive() ) );
		}
	}
	
	private boolean wraparound;
	private int width;
	private Vector<Cell> current;
}
