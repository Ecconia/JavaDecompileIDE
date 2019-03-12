package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPUtf8 extends ConstantPoolEntry
{
	private final String value;
	
	public CPUtf8(DataInput d) throws IOException
	{
		value = d.readUTF();
	}
	
	public String getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return "UFT-8: " + value;
	}
}
