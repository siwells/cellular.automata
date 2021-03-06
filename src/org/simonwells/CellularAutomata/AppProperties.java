package org.simonwells.CellularAutomata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.logging.Logger;
import java.util.Properties;

import org.simonwells.utilities.NotBooleanException;
import org.simonwells.utilities.Utils;

/**
* Properties class used to set parameters for the application
*
* @author Simon Wells <siwells@gmail.com>
* @version 1.0
* @since 1.6
*/
public class AppProperties
{
	/**
	* Zero Argument Constructor.
	*/
	public AppProperties()
	{
		this.loadPropsFile();
	}
	
	/** 
  * Load the configuration file from disk and parse the properties into class variables. Sensible default values are set if the requred key is not found/parsed incorrectly.
  */
	public void loadPropsFile()
	{
		try
		{
		  this.is = new FileInputStream(fileName);			
		  this.properties.load(this.is);
			this.appName = this.properties.getProperty("appName", "1D Cellular Automata");
		  this.infiniteLoop = this.properties.getProperty("infiniteLoop", "false");
		  this.maxGenerations = this.properties.getProperty("maxGenerations", "10");		  
		  this.width = this.properties.getProperty("width", "100");
		  this.seed = this.properties.getProperty("seed", "01101010");
		  this.pattern = this.properties.getProperty("pattern", "0000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000");
		  this.wrapAround = this.properties.getProperty("wrapAround", "true");
		  this.symbolicOutput = this.properties.getProperty("symbolicOutput", "true");
		  this.consoleOutput = this.properties.getProperty("consoleOutput", "true");
		  this.dbOutput = this.properties.getProperty("dbOutput", "false");
		  this.fileOutput = this.properties.getProperty("fileOutput", "false");
		  this.clearScreen = this.properties.getProperty("clearScreen", "false");
		}
		catch(FileNotFoundException ex)
		{
			this.propLogger.warning("File not found exception caught: "+ ex.getMessage());
			this.propLogger.warning("This is probably because the configuration file could not be found. It should be located in ./conf/ca.conf relative to the Cellular Automata application folder. If it is not there then please place a valid config file there and restart the program.");
		}
		catch(IOException ex)
		{
			this.propLogger.warning("ioexception: " + ex.getMessage());
			this.propLogger.warning("This exception occured whilst reading property values from the configuration file. Is the file ca.conf corrupt?");
		}
		finally
		{
			if( this.is != null )
			{
				try{ this.is.close(); } 
				catch(IOException ex) 
				{
					this.propLogger.warning("Caught an IOException whilst trying to close the configuration file" + ex.getMessage());
				}
			}
		}
	}
	
	/**
	* Get the application name
	*
	* @return A string containing either the application name as parsed from the config file or a default value
	*/
	public String getAppName()
	{
		return this.appName;
	}
	
	/**
	* Get the breeding pattern
	*
	* @return A string containing either a pattern as parsed from the config file or a default value
	*/
	public String getPattern()
	{
		return this.pattern;
	}

	/**
	* Get the seed generation
	*
	* @return A string containing either the seed generation as parsed from the config file or a default value
	*/
	public String getSeed()
	{
		return this.seed;
	}

	/**
	* Get the maximum number of generations to calculate during a run
	*
	* @return An integer storing either the maximum number of generations for the app to calculate as parsed from the config file or a default value
	*/
	public int getMaxGenerations()
	{
		int value = 0;
		try
		{
			value = Integer.parseInt(this.maxGenerations);
			return value;
		}
		catch(NumberFormatException ex)
		{
			this.propLogger.warning("Caught a Number Format Exception whilst parsing the config file to determine the number of generations: " + ex.getMessage());
		}
		return value;
	}
	
	/**
	* Get the width of a generation
	*
	* @return An integer storing either the maximum width for the cellular automata's generation as parsed from the config file or a default value
	*/
	public int getWidth()
	{
		int value = 0;
		try
		{
			value = Integer.parseInt(this.width);
			return value;
		}
		catch(NumberFormatException ex)
		{
			this.propLogger.warning("Caught a Number Format Exception whilst parsing the config file to determine the number of generations: " + ex.getMessage());
		}
		return value;
	}
	
	/**
	* Find out whether to clear the terminal at the beginning of output
	*
	* @return A boolean indicating whether the terminal should be cleared at the beginning of output
	*/
	public boolean isClearScreen()
	{
			try
			{
				return Utils.boolFromString(this.clearScreen);
			}
			catch(NotBooleanException ex)
			{
				this.propLogger.warning("Caught a not a boolean exception whilst parsing the config file to determine whether to clear the screen. Check that the value for the clearScreen key in ca.conf is a boolean value {true,false}: " + ex.getMessage());
				return false;
			}
	}
	
	/**
	* Find out whether to write output to a database
	*
	* @return A boolean indicating whether output should be written to a database
	*/
	public boolean isDBOutput()
	{
		try
		{
			return Utils.boolFromString(this.dbOutput);
		}
		catch(NotBooleanException ex)
		{
			this.propLogger.warning("Caught a not a boolean exception whilst parsing the config file to determine whether to clear the screen. Check that the value for the dbOutput key in ca.conf is a boolean value {true,false}: " + ex.getMessage());
			return false;
		}
	}
	
	/**
	* Find out whether to write output to a file
	*
	* @return A boolean indicating whether the output should be written to a file
	*/
	public boolean isFileOutput()
	{
		try
		{
			return Utils.boolFromString(this.fileOutput);
		}
		catch(NotBooleanException ex)
		{
			this.propLogger.warning("Caught a not a boolean exception whilst parsing the config file to determine whether to clear the screen. Check that the value for the fileOutput key in ca.conf is a boolean value {true,false}: " + ex.getMessage());
			return false;
		}
	}
	
	/**
	* Find out whether to breed new generations indefinitely
	*
	* @return A boolean indicating whether the application should keep calculating new generations indefinitely
	*/
	public boolean isInfiniteLoop()
	{	
		try
		{
			return Utils.boolFromString(this.infiniteLoop);
		}
		catch(NotBooleanException ex)
		{
			this.propLogger.warning("Caught a not a boolean exception whilst parsing the config file to determine whether to clear the screen. Check that the value for the infiniteLoop key in ca.conf is a boolean value {true,false}: " + ex.getMessage());
			return false;
		}
	}
	
	/**
	* Find out whether to use symbolic output isntead of textual
	*
	* @return A boolean indicating whether the output should be symbolic (using extended ASCII) instead of the default textual output {0,1}
	*/
	public boolean isSymbolicOutput()
	{
		try
		{
			return Utils.boolFromString(this.symbolicOutput);
		}
		catch(NotBooleanException ex)
		{
			this.propLogger.warning("Caught a not a boolean exception whilst parsing the config file to determine whether to clear the screen. Check that the value for the symbolicOutput key in ca.conf is a boolean value {true,false}: " + ex.getMessage());
			return false;
		}
	}

	/**
	* Find out whether each generation is a discreet linear sequence of cells or whether it should wrap around to form a one dimensional loop.
	*
	* @return A boolean indicating whether each generation should "wrap around" so that the next cell after the last cell should be the first once, and vice versa
	*/
	public boolean isWrappedAround()
	{
		try
		{
			return Utils.boolFromString(this.wrapAround);
		}
		catch(NotBooleanException ex)
		{
			this.propLogger.warning("Caught a not a boolean exception whilst parsing the config file to determine whether to clear the screen. Check that the value for the wrapAround key in ca.conf is a boolean value {true,false}: " + ex.getMessage());
			return false;
		}
	}

	
	private static Logger propLogger = Logger.getLogger(Pattern.class.getName());
	private String fileName = "conf/ca.conf";
	private InputStream is = null;
	private Properties properties = new Properties();	
	
	private String appName;
	private String infiniteLoop;
	private String maxGenerations;
	private String width;
	private String seed;
	private String pattern;
	private String wrapAround;
	private String symbolicOutput;
	private String consoleOutput;
	private String dbOutput;
	private String fileOutput;
	private String clearScreen;
}
