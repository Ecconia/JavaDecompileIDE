package de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.special.CPBigValue;
import de.ecconia.java.decompileide.structure.constantpool.special.CPPrimitive;
import de.ecconia.java.decompileide.structure.constantpool.special.CPRawType;

public class CPTDouble extends CPEntry implements CPPrimitive, CPBigValue, CPRawType
{
	private final double value;
	
	public CPTDouble(int index, DataInput d) throws IOException
	{
		super(index);
		
		value = d.readDouble();
	}
	
	@Override
	public Object getValue()
	{
		return value;
	}
	
	public double get()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + value + "}";
	}
}
