package de.ecconia.java.decompileide;

public class CustomStringReader
{
	private final char[] string;
	private final int length;
	
	private int pointer = 0;
	
	public CustomStringReader(String in)
	{
		this.string = in.toCharArray();
		this.length = string.length;
	}
	
	public boolean hasMore()
	{
		return pointer < length;
	}
	
	public char read()
	{
		if(!hasMore())
		{
			throw new EndOfStringException();
		}
		
		return string[pointer++];
	}
	
	public char peek()
	{
		if(!hasMore())
		{
			throw new EndOfStringException();
		}
		
		return string[pointer];
	}
	
	@SuppressWarnings("serial")
	public static class EndOfStringException extends RuntimeException
	{
	}
}
