package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPLong extends ConstantPoolEntry
{
	private final long value;
	
	public CPLong(DataInput d) throws IOException
	{
		value = d.readLong();
	}
	
	public long getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return "Long: " + value;
	}
}
