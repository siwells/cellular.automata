package org.simonwells.CellularAutomata;

import org.simonwells.CellularAutomata.CellularAutomata;

/**
* Driver class & main method for the cellular automata application
*
* @author Simon Wells <siwells@gmail.com>
* @version 1.0
* @since 1.6
*/
public class App
{
	public static void main(String[] args)
	{	
		App app = new App();
		app.runApp();
	}
	
	private void runApp()
	{
		CellularAutomata ca =	new CellularAutomata();	
		ca.loop();
	}
}
