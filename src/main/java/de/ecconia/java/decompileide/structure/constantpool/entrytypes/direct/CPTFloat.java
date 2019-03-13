package de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.special.CPPrimitive;
import de.ecconia.java.decompileide.structure.constantpool.special.CPSmallValue;

public class CPTFloat extends CPEntry implements CPPrimitive, CPSmallValue
{
	private final float value;
	
	public CPTFloat(int index, DataInput d) throws IOException
	{
		super(index);
		
		value = d.readFloat();
	}
	
	@Override
	public Object getValue()
	{
		return value;
	}
	
	public float get()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + value + "}";
	}
}
