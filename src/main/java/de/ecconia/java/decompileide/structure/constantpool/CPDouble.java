package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPDouble extends ConstantPoolEntry
{
	private final double value;
	
	public CPDouble(DataInput d) throws IOException
	{
		value = Double.longBitsToDouble(d.readLong());
	}
	
	public double getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return "Double: " + value;
	}
}
