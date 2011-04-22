package org.simonwells.CellularAutomata;

import java.lang.Character;
import java.util.Enumeration;
import java.util.logging.Logger;
import java.util.Vector;

/**
* Describes the calculation used to determine the status of the child of a given cluster of Cell objects
*
* @author Simon Wells <siwells@gmail.com>
* @version 1.0
* @since 1.6
*/
public class Pattern implements PatternInterface
{

	/**
	* One Argument Constructor.
	*
	* This method will check that the input String is of the correct length and if it is not then a default pattern of [0,0,0,0,0,0,0,0] will be set instead and a message printed to the log for this class. If the input String is the correct length then each element of the pattern will be updated with the corresponding char from the input string. Creates a new Vector object with default size 8 (eight).
	*
	* @param pattern (required) encoded as a string of 8 characters
	*/
	public Pattern(String pattern)
	{
		this.size = 8;
		this.binaryPattern = new Vector<Character>(size);
		
		if(pattern.length() != this.size)
		{
			this.patternLogger.warning("Supplied pattern must contain 8 (eight) values.");
			this.patternLogger.warning("Supplied pattern is being replaced with the following: [0,0,0,0,0,0,0,0]");
			pattern = "00000000";
		}
		
		this.setPattern(pattern);
	}
	
	private void setPattern(String pattern)
	{
		this.binaryPattern.removeAllElements();
			for(int idx = 0; idx < this.size; ++idx)
				this.binaryPattern.add(pattern.charAt(idx));
	}
	
	/** 
  * Return the elements of the pattern in the form of an Enumeration.
  *
  * A simple getter method to safely provide access to the pattern stored in this class.
  *
  * @return Return an enumeration of the pattern elements.
  */
	public Enumeration getPattern()
	{
		return this.binaryPattern.elements();
	}
	
	/** 
  * Gets the integer value of the element of the pattern stored in the specifed position.
  *
  * @param position specifying which element of the Pattern you want to retrieve.
  * @return Return the value of the elements at the specified position in the pattern.
  */
	public int getValue(int position)
	{
		Character tmp = (Character) this.binaryPattern.elementAt(position);
		int value = Integer.parseInt(tmp.toString());
		return value;		
	}
	
	/** 
  * Retrieve a string containing all of the elements of the pattern as a comma separated list.
  *
  *@return A String containing the elements of the Pattern.
  */
	public String toString()
	{
		return this.binaryPattern.toString();
	}
	
	private final int size;
	private final Vector<Character> binaryPattern;
	private static Logger patternLogger = Logger.getLogger(Pattern.class.getName());
}
