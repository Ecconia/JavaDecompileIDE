package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPClass extends ConstantPoolEntry
{
	private final int nameIndex;
	
	private String name;
	
	public CPClass(DataInput d) throws IOException
	{
		nameIndex = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		if(name == null)
		{
			name = pool.getUtf8(nameIndex).replace('/', '.');
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return "Class: " + name + "@" + nameIndex;
	}
}
