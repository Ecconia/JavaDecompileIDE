package de.ecconia.java.decompileide.structure.constantpool.entrytypes.first;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveFirst;
import de.ecconia.java.decompileide.structure.constantpool.special.CPSmallValue;

public class CPTClass extends CPEntry implements ResolveFirst, CPSmallValue
{
	private final int index;
	
	private String clazz;
	
	public CPTClass(int index, DataInput d) throws IOException
	{
		super(index);
		
		this.index = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		clazz = pool.getUTF(index);
	}
	
	public String getClazz()
	{
		return clazz;
	}
	
	@Override
	public Object getValue()
	{
		return clazz;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + clazz + "}";
	}
}
