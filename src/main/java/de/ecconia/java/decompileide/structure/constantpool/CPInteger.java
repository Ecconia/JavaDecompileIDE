package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPInteger extends ConstantPoolEntry
{
	private final int value;
	
	public CPInteger(DataInput d) throws IOException
	{
		value = d.readInt();
	}
	
	public int getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return "Integer: " + value;
	}
}
