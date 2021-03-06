package org.simonwells.CellularAutomata;

import java.lang.String;

import org.simonwells.CellularAutomata.Cell;
import org.simonwells.CellularAutomata.Pattern;


/**
* Groups {@link Cell} objects together so that the state of their offspring can be calculated.
*
* @author Simon Wells <siwells@gmail.com>
* @version 1.0
* @since 1.6
*/
public class CellCluster
{

	/**
	* Three Argument Constructor.
	*
	* Given three boolean values, this constructor will instantiate three Cell objects with liveness 
	* states corresponding to the supplied booleans.
	*
	* @param left (required) describes the liveness of the left cell in a linear cluster of three Cells
	* @param middle (required) describes the liveness of the middle cell in a linear cluster of three Cells
	* @param right (required) describes the liveness of the right cell in a linear cluster of three Cells
	*/
	public CellCluster(boolean left, boolean middle, boolean right)
	{
		this.left = new Cell(left);
		this.middle = new Cell(middle);
		this.right = new Cell(right);
	}
	
	/** 
  * Breed a new child {@link Cell} from this {@link CellCluster} according to the supplied {@link Pattern}.
  *
  * This method needs to compare the three binary states (from the {@link CellCluster}) against the pattern to determine whether a child bred from these cells will be alive or dead. There are eight possible combinations of the three binary input Cell states and the {@link Pattern} specifies a particular liveness state for each of these combinations. The bulk of the method consists of three levels of nested switch statements. The outer level switch statement corresponds to the left Cell, the middle level switch statement corresponds to the middle Cell and the innermost switch statements corresponds to the right Cell. By switching on the liveness state of each Cell we can determine which value to retrieve from the pattern, for example, if all three cells are dead then we will switch on the value 0 in each statement which will cause value 0 to be retrieved from the pattern and used to instantiate a child. However, if all three cells are live then we will switch on the value 1 in each statement and this will cause the value 7 to be retrieved from the Pattern. All other combinations of liveness will cause a value between 1 and 6 inclusive to be used to retrieve the new child state from the Pattern.
  *
  * @param pattern (required) object specifing how to calculate the offspring.
  *@return Return a Cell object representing the offspring from this CellCluster.
  */
	public Cell getChild(Pattern pattern)
	{
		int state = 0;
	
		switch( this.left.isAlive() ? 1 : 0 )
		{
			case 0:
				switch( this.middle.isAlive() ? 1 : 0 )
				{
					case 0:
						switch( this.right.isAlive() ? 1 : 0 )		
						{
							case 0:
								state = pattern.getValue(0);
								break;
							case 1:
								state = pattern.getValue(1);
								break;
						}
						break;				
					case 1:
						switch( this.right.isAlive() ? 1 : 0 )		
						{
							case 0:
								state = pattern.getValue(2);
								break;
							case 1:
								state = pattern.getValue(3);
								break;
						}
						break;
					}
					break;
			case 1:
				switch( this.middle.isAlive() ? 1 : 0 )
				{
					case 0:
						switch( this.right.isAlive() ? 1 : 0 )		
						{
							case 0:
								state = pattern.getValue(4);
								break;
							case 1:
								state = pattern.getValue(5);
								break;
						}				
					break;
					case 1:
						switch( this.right.isAlive() ? 1 : 0 )		
						{
							case 0:
								state = pattern.getValue(6);
								break;
							case 1:
								state = pattern.getValue(7);
								break;
						}
						break;
					}
					break;
		}
		if(state == 0)
			return new Cell(false);
		else
			return new Cell(true);
	}
	
	private Cell left;
	private Cell middle;
	private Cell right;
}
