package org.simonwells.CellularAutomata;

import org.simonwells.CellularAutomata.AppProperties;
import org.simonwells.CellularAutomata.Generation;
import org.simonwells.CellularAutomata.Output;
import org.simonwells.CellularAutomata.Pattern;

/**
* Controls the evolution of the Cellular Automata in terms of a sequence of generations
*
* @author Simon Wells <siwells@gmail.com>
* @version 1.0
* @since 1.6
*/
public class CellularAutomata
{
	/**
	* Zero Argument Constructor.
	*
	* Initialises the member variables for the CellularAutomata and the flags that control how the CA should be calculated and displayed
	*/
	public CellularAutomata()
	{
		this.infiniteLoop = this.props.isInfiniteLoop();
		this.maxGenerations = this.props.getMaxGenerations();
		this.numGenerations = 0;
		this.output = new Output();
		this.seedGen = new Generation(this.props.getSeed(), this.props.getWidth(), this.props.isWrappedAround());
		this.currentGen = new Generation(seedGen);		
		this.pattern = new Pattern(this.props.getPattern());

	}
			
	/** 
  * Control the looping behaviour of the program so that we can calculate a fixed number of generation or otherwise loop indefinitely. Controls the flow of execution: (1) breed next generation, then (2) display next generation
  */
	public void loop()
	{
		this.output.printGeneration(this.seedGen);
		for (;;)
		{
			currentGen.breed(this.pattern);
			this.output.printGeneration(this.currentGen);
			
			if(!this.infiniteLoop && ++this.numGenerations == this.maxGenerations)
				break;
		}
	}
	
	private final boolean infiniteLoop;
	private int numGenerations;
	private final int maxGenerations;
	private Output output;
	private Generation currentGen;
	private final Generation seedGen;
	private final Pattern pattern;
	private static final AppProperties props = new AppProperties();
}
