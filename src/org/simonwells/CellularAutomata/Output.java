package org.simonwells.CellularAutomata;

import java.util.Enumeration;

import org.simonwells.CellularAutomata.Cell;
import org.simonwells.CellularAutomata.Generation;

/**
* Controls the display of the CA
*
* @author Simon Wells <siwells@gmail.com>
* @version 1.0
* @since 1.6
*/
public class Output
{

	/** Display the current generation on the console. Console output is only representative and behaviour could be platform specific. If you want to work with the generated CA then you should output to a file or database (but it does produce pretty patterns ).
  *
  * @param gen An enumeration encapsulating the CA generation to display.
  */
	public void displayOnConsole(Generation gen)
	{
		Enumeration e = gen.getGeneration();
		while(e.hasMoreElements())
		{
			Cell tmp = (Cell) e.nextElement();
			if (this.symbolicOutput)
				System.out.print( tmp.isAlive() ? '\u25A0' : '\u00A0' );
			else
				System.out.print( tmp.isAlive() ? "1" : "0" );
		}	
		System.out.print("\n");	
	}
	
	/** Print the current generation.  
	*
	*Various output methods are available such as (1) console output, (2) file output, (3) database output. 
	*
	* @param gen (Required) An enumeration of Cells
	*/
	public void printGeneration(Generation gen)
	{
		if(this.consoleOutput)
			displayOnConsole(gen);
	}
	
	/** 
  * Display a header describing the CA {@link Pattern} and initial seed Generation.
  */
	public void printHeader()
	{
		//System.out.println("\033c");
		//System.out.println("Pattern: \n"+this.pattern.toString());
		// Display the seed generation
		//System.out.println("Seed: ");
		//this.displayOnConsole(this.generation.elements());
		//System.out.println(" ");
	}

	public Output()
	{ 
		this.consoleOutput = true;
		this.symbolicOutput = true;
	}
	
	private final boolean symbolicOutput;
	private final boolean consoleOutput;
}
