package de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.special.CPBigValue;
import de.ecconia.java.decompileide.structure.constantpool.special.CPPrimitive;

public class CPTLong extends CPEntry implements CPPrimitive, CPBigValue
{
	private final long value;
	
	public CPTLong(int index, DataInput d) throws IOException
	{
		super(index);
		
		value = d.readLong();
	}
	
	@Override
	public Object getValue()
	{
		return value;
	}
	
	public long get()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + value + "}";
	}
}
