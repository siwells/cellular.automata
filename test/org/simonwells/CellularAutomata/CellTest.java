package org.simonwells.CellularAutomata;

import junit.framework.*;

import org.simonwells.CellularAutomata.Cell;

public class CellTest extends TestCase
{
	private Cell cell;

	public void test1()
	{
		this.cell = new Cell();
		assertTrue(this.cell.isAlive());
	}	
	
	public void test2()
	{
		this.cell = new Cell(true);
		assertTrue(this.cell.isAlive());
	}
	
	public void test3()
	{
		this.cell = new Cell(false);
		assertFalse(this.cell.isAlive());
	}
}
