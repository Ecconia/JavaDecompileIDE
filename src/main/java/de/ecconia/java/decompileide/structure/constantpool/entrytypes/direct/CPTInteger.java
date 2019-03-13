package de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.special.CPPrimitive;
import de.ecconia.java.decompileide.structure.constantpool.special.CPSmallValue;

public class CPTInteger extends CPEntry implements CPPrimitive, CPSmallValue
{
	private int value;
	
	public CPTInteger(int index, DataInput d) throws IOException
	{
		super(index);
		
		value = d.readInt();
	}
	
	@Override
	public Object getValue()
	{
		return value;
	}
	
	public int get()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + value + "}";
	}
}
