package org.simonwells.CellularAutomata;

/**
* A basic element of a Cellular Automata
*
* @author Simon Wells <siwells@gmail.com>
* @version 1.0
* @since 1.6
*/
public class Cell
{
	/**
	* Zero Argument Constructor.
	*
	* Creates a living Cell by default.
	*/
	public Cell()
	{
		this.living = true;
	}

	/**
	* Constructor
	*
	* @param living (required) specifies whether the cell is alive or dead. Valid values can be either true (for a living cell) or false (for a dead cell).
	*/	
	public Cell(boolean living)
	{
		this.living = living;
	}
  
  /** 
  * Determine whether this cell object is alive or not.
  *
  *@return Return true if this cell is alive & dead otherwise. 
  */
	public boolean isAlive()
	{
		return this.living;
	}

	/** Value - boolean storing the liveness status of this cell. */
	private boolean living;
}
