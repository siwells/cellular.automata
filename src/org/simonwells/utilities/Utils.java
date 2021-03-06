package org.simonwells.utilities;

import org.simonwells.utilities.NotBooleanException;

public class Utils
{
	public static boolean boolFromString(String arg) throws NotBooleanException
	{
		if( "true".equalsIgnoreCase(arg) || "t".equalsIgnoreCase(arg) || "1".equalsIgnoreCase(arg) )
			return true;
		else if ( "false".equalsIgnoreCase(arg)  || "f".equalsIgnoreCase(arg) || "0".equalsIgnoreCase(arg) )
			return false;
		else 
			throw new NotBooleanException();
	}
}
