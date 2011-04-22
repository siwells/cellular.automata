package org.simonwells.CellularAutomata;

import java.util.Enumeration;
import junit.framework.*;

import org.simonwells.CellularAutomata.Pattern;

public class PatternTest extends TestCase
{
	private Pattern pattern;

	public void testSize()
	{
		this.pattern = new Pattern("00000000");
		Enumeration e = this.pattern.getPattern();
		int expectedSize = 8;
		int actualSize = 0;
		
		while (e.hasMoreElements())
		{
			Character tmp = (Character) e.nextElement();
			++actualSize;
		}
		assertEquals(expectedSize, actualSize);
	}

	public void testContents()
	{
		this.pattern = new Pattern("00000000");
		Enumeration e = this.pattern.getPattern();
		
		while (e.hasMoreElements())
		{
			Character expected = '0';
			Character actual = (Character) e.nextElement();
			assertEquals(expected, actual);			
		}
	}	
}
