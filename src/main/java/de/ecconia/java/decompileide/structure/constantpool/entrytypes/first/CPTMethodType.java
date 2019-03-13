package de.ecconia.java.decompileide.structure.constantpool.entrytypes.first;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveFirst;
import de.ecconia.java.decompileide.structure.constantpool.special.CPSmallValue;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class CPTMethodType extends CPEntry implements ResolveFirst, CPSmallValue
{
	private final int index;
	
	private Descriptor type;
	
	public CPTMethodType(int index, DataInput d) throws IOException
	{
		super(index);
		
		this.index = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		type = new Descriptor(pool.getUTF(index));
	}
	
	public Descriptor getType()
	{
		return type;
	}
	
	@Override
	public Object getValue()
	{
		return type;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + type + "}";
	}
}
