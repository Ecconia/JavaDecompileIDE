package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPFloat extends ConstantPoolEntry
{
	private final float value;
	
	public CPFloat(DataInput d) throws IOException
	{
		value = Float.intBitsToFloat(d.readInt());
	}
	
	public float getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return "Float: " + value;
	}
}
