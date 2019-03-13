package de.ecconia.java.decompileide.structure.constantpool.entrytypes.first;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveFirst;
import de.ecconia.java.decompileide.structure.constantpool.special.CPPrimitive;
import de.ecconia.java.decompileide.structure.constantpool.special.CPSmallValue;

public class CPTString extends CPEntry implements CPPrimitive, ResolveFirst, CPSmallValue
{
	private final int index;
	
	private String value;
	
	public CPTString(int index, DataInput d) throws IOException
	{
		super(index);
		
		this.index = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		value = pool.getUTF(index);
	}
	
	@Override
	public Object getValue()
	{
		return value;
	}
	
	public String get()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + value + "}";
	}
}
