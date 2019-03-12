package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPNameAndType extends ConstantPoolEntry
{
	private final int nameIndex;
	private final int descriptorIndex;
	
	private String name;
	private String descriptor;
	
	public CPNameAndType(DataInput d) throws IOException
	{
		nameIndex = d.readUnsignedShort();
		descriptorIndex = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		if(name == null)
		{
			name = pool.getUtf8(nameIndex);
			descriptor = pool.getUtf8(descriptorIndex);
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescriptor()
	{
		return descriptor;
	}
	
	@Override
	public String toString()
	{
		return "NameAndType: " + name + "@" + nameIndex + " " + descriptor + "@" + descriptorIndex;
	}
}
