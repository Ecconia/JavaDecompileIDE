package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPMethodType extends ConstantPoolEntry
{
	private final int descriptorIndex;
	
	private String descriptor;
	
	public CPMethodType(DataInput d) throws IOException
	{
		descriptorIndex = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		if(descriptor == null)
		{
			descriptor = pool.getUtf8(descriptorIndex);
		}
	}
	
	public String getDescriptor()
	{
		return descriptor;
	}
	
	@Override
	public String toString()
	{
		return "MethodType: " + descriptor + "@" + descriptorIndex;
	}
}
